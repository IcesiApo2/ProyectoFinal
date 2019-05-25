package test;

import org.junit.Test;

import junit.framework.TestCase;
import modelo.Proyectil;

public class ProyectilTest extends TestCase {
	private Proyectil proyectil;

	private void setupEscenario1() {
		proyectil = new Proyectil(0, 0);
	}

	@Test
	public void testMover() {
		setupEscenario1();
		proyectil.mover(1);
		assertTrue(proyectil.getX() == 0 && proyectil.getY() == 1);
	}

}
