package test;

import org.junit.Test;

import junit.framework.TestCase;
import modelo.Enemigo;
import modelo.Pantalla;

public class PantallaTest extends TestCase {
	private Pantalla pantalla;

	private void setupEscenario1() {
		pantalla = new Pantalla();
	}

	private void setupEscenario2() {
		pantalla = new Pantalla();
		pantalla.insertarEnemigo(new Enemigo(0, 0, "hola", Enemigo.MALO));
	}

	@Test
	public void testCambiarNivel() {
		setupEscenario1();
		pantalla.cambiarNivel();
		assertTrue(pantalla.getNivel() == 2);
	}

	@Test
	public void testAumentarNivel() {
		setupEscenario1();
		pantalla.aumentarNivel();
		assertTrue(pantalla.getNivel() == 2);
	}

	@Test
	public void testInsertarEnemigo() {
		setupEscenario1();
		pantalla.insertarEnemigo(new Enemigo(0, 0, "hola", Enemigo.MALO));
		assertTrue(pantalla.arregloEnemigo().get(0).getPalabra().equalsIgnoreCase("hola"));
	}

	@Test
	public void testEliminarEnemigo() {
		setupEscenario2();
		pantalla.eliminarEnemigo("hola");
		assertTrue(pantalla.arregloEnemigo().isEmpty());
	}

	@Test
	public void testBuscar() {
		setupEscenario2();
		boolean en = pantalla.buscar("hola");
		assertTrue(en);
	}

	@Test
	public void testEncontrar() {
		setupEscenario2();
		Enemigo en = pantalla.encontrar("hola");
		assertTrue(en.getPalabra().equalsIgnoreCase("hola"));
	}

}
