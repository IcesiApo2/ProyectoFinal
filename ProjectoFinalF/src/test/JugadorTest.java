package test;

import org.junit.Test;

import junit.framework.TestCase;
import modelo.Enemigo;
import modelo.Jugador;

public class JugadorTest extends TestCase {

	private Jugador jugador;

	private void setupEscenario1() {
		jugador = new Jugador("Pedro");
	}

	@Test
	public void testDisminuirVidaEnemigo() {
		setupEscenario1();
		jugador.disminuirVidaEnemigo();
		assertTrue(jugador.getVida() == 85);
	}

	@Test
	public void testDisminuirVida() {
		setupEscenario1();
		jugador.disminuirVida();
		assertTrue(jugador.getVida() == 97);
	}

	@Test
	public void testAumentarPuntos() {
		setupEscenario1();
		jugador.aumentarPuntos(new Enemigo(0, 0, "hola", Enemigo.MALO));
		assertTrue(jugador.getPuntaje() == 500);
	}

	@Test
	public void testDisminuirPuntos() {
		setupEscenario1();
		jugador.disminuirPuntos("hola");
		assertTrue(jugador.getPuntaje() == -250);
	}

	@Test
	public void testBuscarJugador() {
		setupEscenario1();
		Jugador jug = jugador.buscarJugador("pedro");
		assertTrue(jug != null);
	}

	@Test
	public void testEliminar() {
		setupEscenario1();
		Jugador jug = jugador.eliminar("pedro");
		assertTrue(jug == null);
	}

}
