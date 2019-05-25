package modelo;

import java.io.Serializable;

public class Proyectil extends Movible implements Serializable, IMover {
	private int x;
	private int y;
	private boolean mover;

	public Proyectil(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		mover = false;
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

	public boolean isMover() {
		return mover;
	}

	public void setMover(boolean mover) {
		this.mover = mover;
	}

	public void reiniciar() {
		y = 0;
		x = 0;
	}

	@Override
	public void mover(int dis) {
		this.y += dis;
	}

}
