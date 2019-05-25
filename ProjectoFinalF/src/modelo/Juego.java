package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Juego implements Serializable {
	private Jugador raiz;
	private Jugador actual;

	public Juego() {
		raiz = null;
		actual = null;
	}

	public Jugador getRaiz() {
		return raiz;
	}

	public void setRaiz(Jugador raiz) {
		this.raiz = raiz;
	}

	public Jugador getActual() {
		return actual;
	}

	public void setActual(Jugador actual) {
		this.actual = actual;
	}

	public Jugador buscarJugador(String unNombre) {
		if (raiz == null) {
			return null;
		} else {
			return raiz.buscarJugador(unNombre);
		}
	}

	public boolean esVacio() {
		return (raiz == null);
	}

	private void agregarJugador(Jugador nodo, Jugador raiz) {
		if (raiz == null) {
			setRaiz(nodo);
		} else {
			if (nodo.compareTo(raiz) == -1 || nodo.compareTo(raiz) == 0) {
				if (raiz.getIzq() == null) {
					raiz.setIzq(nodo);
				} else {
					agregarJugador(nodo, raiz.getIzq());
				}
			} else {
				if (raiz.getDer() == null) {
					raiz.setDer(nodo);
				} else {
					agregarJugador(nodo, raiz.getDer());
				}
			}
		}
	}

	public void agregarJugador(Jugador nodo) {
		agregarJugador(nodo, raiz);
	}

	public void meterLista(ArrayList<Jugador> lista) {
		for (int i = 0; i < lista.size(); i++) {
			agregarJugador(lista.get(i));
		}
	}

	public void eliminarJugador(String unNickName) {
		raiz.eliminar(unNickName);
	}

	public void arregloJugador(Jugador nodo, ArrayList<Jugador> listaJugador) {
		if (nodo != null) {
			listaJugador.add(nodo);
			arregloJugador(nodo.getIzq(), listaJugador);
			arregloJugador(nodo.getDer(), listaJugador);
		}
	}

	public ArrayList<Jugador> arregloJugador() {
		ArrayList<Jugador> listaJugador = new ArrayList<Jugador>();
		arregloJugador(raiz, listaJugador);
		return listaJugador;
	}

	public void aumentarNivel() {
		Jugador jActual = getActual();
		int nivel = jActual.getPantalla().getNivel();
		int pNivel = nivel * 1000;
		if (nivel == 0 && jActual.getPuntaje() >= 1000) {
			jActual.getPantalla().aumentarNivel();
		} else if (nivel != 0 && jActual.getPuntaje() >= pNivel + 1000) {
			jActual.getPantalla().aumentarNivel();
		}
	}

	public void iniciar() {
		actual.getPantalla().elegirNivel();
	}

	public void reiniciarJugador(String nombre) {
		Jugador este = getActual();
		este.setPuntaje(0);
		este.setVida(100);
		este.setPantalla(new Pantalla());
		if (buscarJugador(nombre) != null) {
			eliminarJugador(nombre);
		}
		agregarJugador(este);
		setActual(este);
	}

	public void guardar() throws IOException {
		FileOutputStream fileOutS = null;
		ObjectOutputStream salida = null;
		ArrayList<Jugador> listaJugadores = null;
		fileOutS = new FileOutputStream("Data/Jugadores.dat");
		salida = new ObjectOutputStream(fileOutS);

		listaJugadores = arregloJugador();

		System.out.println(listaJugadores.get(0).getNickname());

		salida.writeObject(listaJugadores);

		if (salida != null) {
			salida.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void cargar() throws IOException, ClassNotFoundException {
		FileInputStream fileInStr = null;
		ObjectInputStream entrada = null;

		ArrayList<Jugador> listaJugadores;

		try {
			System.out.println("Entro al try");
			fileInStr = new FileInputStream("Data/Jugadores.dat");
			entrada = new ObjectInputStream(fileInStr);

			listaJugadores = (ArrayList<Jugador>) entrada.readObject();

			meterLista(listaJugadores);
		} catch (Exception e) {
			System.out.println("Me fui del try");

		}
		if (fileInStr != null)
			fileInStr.close();

		if (entrada != null)
			entrada.close();
	}

	// burbuja
	public ArrayList<Jugador> ordenarPorNombre() {
		ArrayList<Jugador> lista = arregloJugador();
		for (int i = lista.size(); i > 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				int num = new ComparadorPuntos().compare(lista.get(j), lista.get(j + 1));
				int num2 = lista.get(j).compareTo(lista.get(j + 1));
				if (num > 0 || num2 > 0) {
					Jugador temp = lista.get(j);
					lista.set(j, lista.get(j + 1));
					lista.set(j + 1, temp);
				}
			}
		}
		return lista;
	}

	// insercion
	public ArrayList<Jugador> ordenarPorPuntaje() {
		ArrayList<Jugador> lista = arregloJugador();
		for (int i = 1; i < lista.size(); i++) {
			for (int j = i; j > 0 && lista.get(j - 1).getPuntaje() > lista.get(j).getPuntaje(); j--) {
				Jugador temp = lista.get(j);
				lista.set(j, lista.get(j - 1));
				lista.set(j - 1, temp);
			}
		}
		return lista;
	}

}
