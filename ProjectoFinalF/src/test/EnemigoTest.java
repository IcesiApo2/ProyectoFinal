package test;

import org.junit.Test;

import junit.framework.TestCase;
import modelo.Enemigo;

public class EnemigoTest extends TestCase {
	private Enemigo enemigo;

	private void setupEscenario1() {
		enemigo = new Enemigo(0, 0, "hola", Enemigo.MALO);
	}

	@Test
	public void testAgregarProyectiles() {
		setupEscenario1();
		enemigo.agregarProyectiles();
		assertTrue(!enemigo.getProyectil().isEmpty());
	}

	@Test
	public void testEliminarProyectiles() {
		setupEscenario1();
		enemigo.agregarProyectiles();
		enemigo.eliminarProyectiles();
		assertTrue(enemigo.getProyectil().isEmpty());
	}

	@Test
	public void testMover() {
		setupEscenario1();
		enemigo.mover(1);
		assertTrue(enemigo.getX() == 1 && enemigo.getY() == 1);
	}
}
