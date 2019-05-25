package application;

import java.util.ArrayList;

import javax.sound.midi.Synthesizer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Jugador;

public class ScoreController {

	@FXML
	private Label fondo;
	@FXML
	private Pane tableroDeJuego;
	@FXML
	private Button atras;
	@FXML
	private GridPane grid;

	@FXML
	private Label Nombre1;
	@FXML
	private Label Nombre2;
	@FXML
	private Label Nombre3;
	@FXML
	private Label Nombre4;
	@FXML
	private Label Nombre5;
	@FXML
	private Label Nombre6;
	@FXML
	private Label Nombre7;
	@FXML
	private Label Nombre8;
	@FXML
	private Label Nombre9;
	@FXML
	private Label Nombre10;
	@FXML
	private Label Puntaje1;
	@FXML
	private Label Puntaje2;
	@FXML
	private Label Puntaje3;
	@FXML
	private Label Puntaje4;
	@FXML
	private Label Puntaje5;
	@FXML
	private Label Puntaje6;
	@FXML
	private Label Puntaje7;
	@FXML
	private Label Puntaje8;
	@FXML
	private Label Puntaje9;
	@FXML
	private Label Puntaje10;

	Label[] nombres = new Label[10];
	Label[] puntos = new Label[10];

	private PrincipalController principal;

	public ScoreController() {

	}

	public void initialize() {

		this.inicializarEfectosGraficos();
		System.out.println("chao");
		this.acomodarArreglos();
		System.out.println("Chao2");
		ponerJugadores();
		System.out.println("chao3");

	}

	public void inicializarEfectosGraficos() {

		Image icon = new Image("Imagenes/fondo.gif");
		ImageView img = new ImageView(icon);
		fondo.setGraphic(img);

	}

	public void salirPuntajes() {
		Stage stage = (Stage) atras.getScene().getWindow();
		stage.close();
	}

	public void acomodarArreglos() {
		System.out.println("Arreglos entrada");
		nombres[0] = Nombre1;
		puntos[0] = Puntaje1;
		nombres[1] = Nombre2;
		puntos[1] = Puntaje2;
		nombres[2] = Nombre3;
		puntos[2] = Puntaje3;
		nombres[3] = Nombre4;
		puntos[3] = Puntaje4;
		nombres[4] = Nombre5;
		puntos[4] = Puntaje5;
		nombres[5] = Nombre6;
		puntos[5] = Puntaje6;
		nombres[6] = Nombre7;
		puntos[6] = Puntaje7;
		nombres[7] = Nombre8;
		puntos[7] = Puntaje8;
		nombres[8] = Nombre9;
		puntos[8] = Puntaje9;
		nombres[9] = Nombre10;
		puntos[9] = Puntaje10;
		System.out.println("Arreglos salida");

	}

	public void ponerJugadores() {
		System.out.println("Holsad");
		ArrayList<Jugador> lista = principal.darJuego().ordenarPorPuntaje();
		System.out.println("Hola1");

		int num = lista.size();
		for (int i = 0; i < num || i < 10; i++) {
			Jugador jug = lista.get(i);
			System.out.println("HolaX" + i);
			nombres[i].setText(jug.getNickname());
			puntos[i].setText(jug.getPuntaje() + "");

		}

	}

}
