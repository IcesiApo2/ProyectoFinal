package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Pantalla implements Serializable {

	public final static String NIVEL_SUPERADO = "Imagenes/nivelCompletado.png";
	public final static String FIN_JUEGO = "Imagenes/end.png";
	public final static String GAME_OVER = "Imagenes/gameOver.png";

	private Enemigo inicio;

	private int nivel;
	private int velocidad;
	private String letrero;

	public Pantalla() {
		inicio = null;
		nivel = 1;
		velocidad = 2;
		letrero = "";
	}

	public Enemigo getInicio() {
		return inicio;
	}

	public void setInicio(Enemigo inicio) {
		this.inicio = inicio;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public String getLetrero() {
		return letrero;
	}

	public void setLetrero(String letrero) {
		this.letrero = letrero;
	}

	public void elegirNivel() {
		if (nivel == 1) {
			nivelUno();
		} else if (nivel == 2) {
			nivelDos();
		} else if (nivel == 3) {
			nivelTres();
		} else if (nivel == 4) {
			nivelCuatro();
		} else if (nivel == 5) {
			nivelCinco();
		} else if (nivel > 5) {
			// letrero = FIN_JUEGO;
		}
	}

	public void nivelUno() {
		if (inicio == null) {
			File file = new File("Archivos/Nivel1.txt");
			try {
				FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader);
				String line = "";
				while ((line = br.readLine()) != null) {
					insertarEnemigo(new Enemigo(ThreadLocalRandom.current().nextInt(0, 490),
							ThreadLocalRandom.current().nextInt(0, 150), line, 0));
				}

				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {

			}
		}
	}

	public void nivelDos() {
		if (inicio == null) {
			File file = new File("Archivos/Nivel2.txt");
			try {
				FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader);
				String line = "";
				while ((line = br.readLine()) != null) {
					insertarEnemigo(new Enemigo(ThreadLocalRandom.current().nextInt(0, 490),
							ThreadLocalRandom.current().nextInt(0, 150), line, 0));
				}

				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {

			}
		}
	}

	public void nivelTres() {
		if (inicio == null) {
			File file = new File("Archivos/Nivel3.txt");
			try {
				FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader);
				String line = "";
				while ((line = br.readLine()) != null) {
					insertarEnemigo(new Enemigo(ThreadLocalRandom.current().nextInt(0, 490),
							ThreadLocalRandom.current().nextInt(0, 150), line, 0));
				}

				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {

			}
		}
	}

	public void nivelCuatro() {
		if (inicio == null) {
			File file = new File("Archivos/Nivel4.txt");
			try {
				FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader);
				String line = "";
				while ((line = br.readLine()) != null) {
					insertarEnemigo(new Enemigo(ThreadLocalRandom.current().nextInt(0, 490),
							ThreadLocalRandom.current().nextInt(0, 150), line, 0));
				}

				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {

			}
		}
	}

	public void nivelCinco() {
		if (inicio == null) {
			File file = new File("Archivos/Nivel5.txt");
			try {
				FileReader reader = new FileReader(file);
				BufferedReader br = new BufferedReader(reader);
				String line = "";
				while ((line = br.readLine()) != null) {
					insertarEnemigo(new Enemigo(ThreadLocalRandom.current().nextInt(0, 490),
							ThreadLocalRandom.current().nextInt(0, 150), line, 0));
				}

				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {

			}
		}
	}

	public void terminar() {
		inicio = null;
	}

	public void cambiarNivel() {
		if (arregloEnemigo().isEmpty()) {
			aumentarNivel();
			if (nivel < 6) {
				letrero = NIVEL_SUPERADO;
			} else {
				letrero = FIN_JUEGO;
			}
		}
	}

	public void aumentarNivel() {
		if (nivel == 1) {
			nivel = 2;
		} else if (nivel == 2) {
			nivel = 3;
		} else if (nivel == 3) {
			nivel = 4;
		} else if (nivel == 4) {
			nivel = 5;
		} else if (nivel == 5) {
			nivel = 6;
		}
	}

	public void insertarEnemigo(Enemigo inicial, Enemigo enemigo) {
		if (inicial == null) {
			enemigo.setAnterior(null);
			setInicio(enemigo);
		} else {
			Enemigo aux = inicial;
			Enemigo euz = aux.getSiguiente();
			if (euz == null) {
				enemigo.setAnterior(aux);
				aux.setSiguiente(enemigo);
			} else {
				insertarEnemigo(euz, enemigo);
			}
		}
	}

	public void insertarEnemigo(Enemigo enemigo) {
		insertarEnemigo(inicio, enemigo);
	}

	public void moverEnemigo() {
		Enemigo aux = inicio;
		while (aux != null) {
			aux.mover(velocidad);
			aux = aux.getSiguiente();
		}
	}

	public String nivelSuperado() {
		String respuesta = "";
		if (arregloEnemigo().isEmpty()) {
			respuesta = "NIVEL SUPERADO";
		}
		return respuesta;
	}

	public void eliminarEnemigo(String palabra) {
		if (!palabra.isEmpty()) {
			if (inicio.getPalabra().equalsIgnoreCase(palabra)) {
				inicio = inicio.getSiguiente();
			} else {
				Enemigo aux = inicio;
				while (!aux.getSiguiente().getPalabra().equalsIgnoreCase(palabra)) {
					aux = aux.getSiguiente();
				}
				Enemigo siguiente = aux.getSiguiente().getSiguiente();
				aux.setSiguiente(siguiente);
			}
		}
	}

	public ArrayList<Enemigo> arregloEnemigo() {
		ArrayList<Enemigo> listaEnemigo = new ArrayList<Enemigo>();
		if (inicio != null) {
			Enemigo aux = inicio;
			listaEnemigo.add(aux);
			while (aux.getSiguiente() != null) {
				aux = aux.getSiguiente();
				listaEnemigo.add(aux);
			}
			if (aux.getSiguiente() == null) {
				listaEnemigo.add(aux);
			}
		}
		return listaEnemigo;
	}

	public boolean buscar(String referencia) {
		Enemigo aux = inicio;
		boolean encontrado = false;
		while (aux != null && encontrado != true) {
			if (referencia.equalsIgnoreCase(aux.getPalabra())) {
				encontrado = true;
			} else {
				aux = aux.getSiguiente();
			}
		}
		return encontrado;
	}

	public Enemigo encontrar(String palabra) {
		Enemigo aux = inicio;
		Enemigo este = null;
		boolean encontrado = false;
		while (aux != null && encontrado != true) {
			if (palabra.equalsIgnoreCase(aux.getPalabra())) {
				encontrado = true;
				este = aux;
			} else {
				aux = aux.getSiguiente();
			}
		}
		return este;
	}

}
