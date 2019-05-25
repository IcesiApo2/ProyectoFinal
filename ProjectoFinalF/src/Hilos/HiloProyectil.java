package Hilos;

import java.util.ArrayList;

import application.PrincipalController;
import modelo.Proyectil;

public class HiloProyectil extends Thread {

	private PrincipalController principal;
	private ArrayList<Proyectil> proyectil;

	public HiloProyectil(ArrayList<Proyectil> proyectil, PrincipalController principal) {
		this.principal = principal;
		this.proyectil = proyectil;
	}

	public void run() {
		while (true) {
			for (int i = 0; i < proyectil.size(); i++) {
				proyectil.get(i).setMover(true);
				proyectil.get(i).mover(5);
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			principal.repaint();
		}
	}

}
