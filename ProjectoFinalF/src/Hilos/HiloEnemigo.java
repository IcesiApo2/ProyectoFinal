package Hilos;

import java.util.ArrayList;

import application.PrincipalController;
import modelo.Enemigo;

public class HiloEnemigo extends Thread {

	private PrincipalController principal;
	private ArrayList<Enemigo> enemigo;

	public HiloEnemigo(ArrayList<Enemigo> enemigo, PrincipalController principal) {
		this.enemigo = enemigo;
		this.principal = principal;
	}

	public void run() {
		while (true) {
			for (int i = 0; i < enemigo.size(); i++) {
				if (enemigo.get(i).getTipo() == Enemigo.MALO) {
					enemigo.get(i).mover(5);
				}
//				else{
//					enemigo.get(i).mover(10);
//				}
			}
			try {
				Thread.sleep(90);
			} catch (Exception e) {
				e.printStackTrace();
			}
			principal.disparar();
//			principal.repaint();
		}
	}

}