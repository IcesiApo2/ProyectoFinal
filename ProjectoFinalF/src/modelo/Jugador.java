package modelo;

import java.io.Serializable;

public class Jugador implements Comparable<Jugador>, Serializable {

	private Jugador izq;
	private Jugador der;

	private Pantalla pantalla;

	private String nickname;
	private int puntaje;
	private int vida;

	public Jugador(String nickn) {

		nickname = nickn;
		this.izq = null;
		this.der = null;
		puntaje = 0;
		vida = 100;
		pantalla = new Pantalla();

	}

	public Pantalla getPantalla() {
		return pantalla;
	}

	public void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickn) {
		System.out.println("llego" + nickn);
		this.nickname = nickn;
		System.out.println(this.getNickname());
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public Jugador getIzq() {
		return izq;
	}

	public void setIzq(Jugador izq) {
		this.izq = izq;
	}

	public Jugador getDer() {
		return der;
	}

	public void setDer(Jugador der) {
		this.der = der;
	}

	public boolean esHoja() {
		return (izq == null && der == null);
	}

	public Jugador darMenor() {
		return (izq == null) ? this : izq.darMenor();
	}

	public Jugador darMayor() {
		return (der == null) ? this : der.darMenor();
	}

	public void destruirNaves(String palabra) throws NullPointerException {
		Enemigo ene = pantalla.encontrar(palabra);
		if (palabra.isEmpty()) {
			throw new NullPointerException();
		} else {
			if (pantalla.buscar(palabra) == true) {
				pantalla.eliminarEnemigo(palabra);
				aumentarPuntos(ene);
			} else {
				disminuirPuntos(palabra);
			}
		}
	}

	public void gameOver() {
		if (vida <= 0) {
			pantalla.setLetrero(Pantalla.GAME_OVER);
			pantalla.terminar();
		}
	}

	public void disminuirVidaEnemigo() {
		vida = vida - 15;
	}

	public void disminuirVida() {
		vida = vida - 3;
	}

	public void aumentarPuntos(Enemigo ene) {
		if (ene != null && ene.getTipo() != Enemigo.BUENO) {
			puntaje += 500;
		}
	}

	public void disminuirPuntos(String palabra) {
		if (pantalla.buscar(palabra) == false) {
			puntaje = puntaje - 250;
		}
	}

	public void eliminarEnemigo(String palabra) {
		pantalla.eliminarEnemigo(palabra);
	}

	public Jugador buscarJugador(String unNombre) {
		if (nickname.compareToIgnoreCase(unNombre) == 0) {
			return this;
		} else if (nickname.compareToIgnoreCase(unNombre) > 0) {
			return (izq == null) ? null : izq.buscarJugador(unNombre);
		} else {
			return (der == null) ? null : der.buscarJugador(unNombre);
		}
	}

	public Jugador eliminar(String unNickName) {
		if (esHoja()) {
			return null;
		}
		if (nickname.compareToIgnoreCase(unNickName) == 0) {
			if (izq == null) {
				return der;
			}
			if (der == null) {
				return izq;
			}
			Jugador sucesor = der.darMenor();
			der = der.eliminar(sucesor.getNickname());
			sucesor.izq = izq;
			sucesor.der = der;
			return sucesor;
		} else if (nickname.compareToIgnoreCase(unNickName) > 0) {
			izq = izq.eliminar(unNickName);
		} else {
			der = der.eliminar(unNickName);
		}
		return this;
	}

	@Override
	public int compareTo(Jugador obj) {
		int com = this.nickname.compareToIgnoreCase(obj.getNickname());
		return com;
	}

}
