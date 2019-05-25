package application;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Hilos.HiloEnemigo;
import Hilos.HiloProyectil;
import excepciones.CampoVacioException;
import excepciones.UsuarioNoEncontradoException;
import excepciones.UsuarioRepetidoException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Enemigo;
import modelo.Juego;
import modelo.Jugador;

public class PrincipalController {

	private Jugador relacionJugador;
	private StartController StartC;

	@FXML
	private Button Score;
	@FXML
	private Button Start;
	@FXML
	private Button Cargar;
	@FXML
	private Label fondo;
	@FXML
	private Label Logo;
	@FXML
	private GridLayout grillaBotones;
	@FXML
	private Pane pantallaInicio;

	private Main main;

	private Juego juego;

	public PrincipalController() {

	}

	public void initialize() {
		main = new Main();
		this.inicializarEfectosGraficos();
//		relacionJugador = new Jugador("");

	}

	public void inicializarEfectosGraficos() {
		fondo.setLayoutX(0);
		fondo.setLayoutY(0);

		Image icono = new Image("Imagenes/fondo1.gif");
		ImageView img = new ImageView(icono);
		fondo.setGraphic(img);

		pantallaInicio.setPrefHeight(700);
		pantallaInicio.setPrefWidth(490);

		Image logo = new Image("Imagenes/title.png");
		ImageView img2 = new ImageView(logo);
		Logo.setGraphic(img2);
		Logo.setLayoutX(45);
		Logo.setLayoutY(50);

		Start.setLayoutX(177);
		Cargar.setLayoutX(177);
		Score.setLayoutX(177);

	}

	public Juego darJuego() {
		return juego;
	}

	public Jugador actual() {
		return juego.getActual();
	}

	public void eleminarEnemigo(String palabra) {
		actual().eliminarEnemigo(palabra);
	}

	public void quitarActual() {
		juego.setActual(null);
	}

	public void reiniciarJugador() {
		juego.reiniciarJugador(actual().getNickname());
	}

	public void cambiarNivel() {
		actual().getPantalla().cambiarNivel();
		if (actual().getPantalla().arregloEnemigo().isEmpty()) {
			StartC.activarGuardar();

		}
	}

	public void iniciar() {
		if (actual().getPantalla().arregloEnemigo().isEmpty()) {
			juego.iniciar();
			moverEnemigos();
		}
	}

	public void crearJugador() throws CampoVacioException, UsuarioRepetidoException {
		String nickname = JOptionPane.showInputDialog(null, "Ingrese su Nickname");
		if (nickname == null || nickname.isEmpty()) {
			throw new CampoVacioException();
		} else {
			Jugador esta = juego.buscarJugador(nickname);
			if (esta == null) {
				Jugador jugador = new Jugador(nickname);
				juego.agregarJugador(jugador);
				juego.setActual(jugador);
			} else {
				throw new UsuarioRepetidoException();
			}
		}
	}

	public ArrayList<Enemigo> listaEnemigo() {
		ArrayList<Enemigo> lista = juego.getActual().getPantalla().arregloEnemigo();
		return lista;
	}

	public void gameOver() {
		actual().gameOver();
	}

	public void moverEnemigos() {
		ArrayList<Enemigo> lista = juego.getActual().getPantalla().arregloEnemigo();
		HiloEnemigo hilo = new HiloEnemigo(lista, this);
		hilo.start();
	}

	public void disparar() {
		ArrayList<Enemigo> lista = juego.getActual().getPantalla().arregloEnemigo();
		for (int i = 0; i < lista.size(); i++) {
			int num = lista.get(i).getY();
			for (int j = 0; j < 3; j++) {
				if (num == 100 || num == 300 || num == 500) {
					HiloProyectil hilo = new HiloProyectil(lista.get(i).getProyectil(), this);
					hilo.start();
				}
			}
		}
	}

	public void guardar() {
		try {
			juego.guardar();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void iniciarPartida() {

		try {
			crearJugador();
		} catch (CampoVacioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (UsuarioRepetidoException a) {
			JOptionPane.showMessageDialog(null, a.getMessage());
		}
		if (actual() != null) {
			Stage st = new Stage();
			main.cargarJuego(st);
		}

	}

	public void cargar() throws UsuarioNoEncontradoException, CampoVacioException {
		String nickname = JOptionPane.showInputDialog(null, "Ingrese su Nickname");
		if (nickname == null || nickname.isEmpty()) {
			throw new CampoVacioException();
		}
		if (!nickname.isEmpty()) {

			ArrayList<Jugador> listaJugadores = juego.arregloJugador();
			boolean salir = false;
			if (listaJugadores != null) {
				for (int i = 0; i < listaJugadores.size() && !salir; i++) {
					if (listaJugadores.get(i).getNickname().equalsIgnoreCase(nickname)) {
						int dialogButton = JOptionPane.YES_NO_OPTION;
						int dialogResult = JOptionPane.showConfirmDialog(null, "Desea cargar la partida?", "Cargar",
								dialogButton);
						if (dialogResult == 0) {
							Jugador player = listaJugadores.get(i);
							juego.setActual(player);
						}
						salir = true;
					} else {
						throw new UsuarioNoEncontradoException();
					}
				}
			} else {
				System.out.println("Esto esta null");
			}
		}
	}

	public void cargarPartida() {
		try {
			cargar();
		} catch (UsuarioNoEncontradoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (CampoVacioException a) {
			JOptionPane.showMessageDialog(null, a.getMessage());
		}
		if (actual() != null) {
			iniciarPartida();
		}
	}

	public void cargarScores() {
		Stage st = new Stage();
		main.cargarPuntajes(st);
	}

}
