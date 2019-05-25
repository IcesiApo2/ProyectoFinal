package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Enemigo extends Movible implements Serializable, IMover {

	public final static int MALO = 0;
	public final static int BUENO = 1;

	private ArrayList<Proyectil> proyectil;

	private int x;
	private int y;

	public boolean cambiar;

	private Enemigo siguiente;
	private Enemigo anterior;

	private String palabra;
	private int tipo;

	public Enemigo(int x, int y, String palabra, int tipo) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.palabra = palabra;
		this.tipo = tipo;
		cambiar = false;
		siguiente = null;
		anterior = null;
		proyectil = new ArrayList<Proyectil>();
		agregarProyectiles();
	}

	public ArrayList<Proyectil> getProyectil() {
		return proyectil;
	}

	public void setProyectil(ArrayList<Proyectil> proyectil) {
		this.proyectil = proyectil;
	}

	public Enemigo getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Enemigo siguiente) {
		this.siguiente = siguiente;
	}

	public Enemigo getAnterior() {
		return anterior;
	}

	public void setAnterior(Enemigo anterior) {
		this.anterior = anterior;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getTipo() {
		return tipo;
	}

	public boolean isCambiar() {
		return cambiar;
	}

	public void setCambiar(boolean cambiar) {
		this.cambiar = cambiar;
	}

	public void agregarProyectiles() {
		if (tipo == MALO) {
			proyectil.add(new Proyectil(x, y));
		}
	}

	public void eliminarProyectiles() {
		proyectil.clear();
	}

	@Override
	public void mover(int dis) {
		if (cambiar) {
			this.y += 1;
			this.x -= dis;
			for (int i = 0; i < proyectil.size(); i++) {
				if (proyectil.get(i).isMover() == false) {
					proyectil.get(i).setX(this.x + 15);
					proyectil.get(i).setY(this.y + 20);
				}
			}
		} else {
			this.x += dis;
			this.y += 1;
			for (int i = 0; i < proyectil.size(); i++) {
				if (proyectil.get(i).isMover() == false) {
					proyectil.get(i).setX(this.x + 25);
					proyectil.get(i).setY(this.y + 20);
				}
			}
		}
	}

}