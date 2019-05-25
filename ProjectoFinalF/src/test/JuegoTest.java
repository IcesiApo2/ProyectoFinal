package test;

import org.junit.Test;

import junit.framework.TestCase;
import modelo.Juego;
import modelo.Jugador;

public class JuegoTest extends TestCase {
	private Juego juego;

	private void setupEscenario1() {
		juego = new Juego();
	}

	private void setupEscenario2() {
		juego = new Juego();
		juego.agregarJugador(new Jugador("pedro"));
	}

	@Test
	public void testAgregarJugador() {
		setupEscenario1();
		juego.agregarJugador(new Jugador("pedro"));
		assertTrue(juego.arregloJugador().get(0).getNickname().equalsIgnoreCase("pedro"));
	}

	@Test
	public void testEliminarJugador() {
		setupEscenario2();
		juego.eliminarJugador("pedro");
		assertTrue(juego.getRaiz() != null);
	}

}
