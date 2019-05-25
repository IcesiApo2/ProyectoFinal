package application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Enemigo;
import modelo.Jugador;
import modelo.Pantalla;
import modelo.Proyectil;

public class StartController implements KeyListener {

	private Jugador relacionJugador;
	private Pantalla relacionPantalla;
	private Enemigo relacionEnemigo;

	private PrincipalController principal;

	public final static double SUPERIOR = 0.0;
	public final static double INFERIOR = 600;
	public final static double IZQUIERDA = 0.0;
	public final static double DERECHA = 400;

	Label[] enemigos = new Label[8];
	Label[] palabras = new Label[8];

	@FXML
	private Pane tableroDeJuego;
	@FXML
	private Label fondo;

	@FXML
	private Label nombre;

	@FXML
	private Label Score;

	@FXML
	private ImageView jugador;

	@FXML
	private Label imagen1;
	@FXML
	private Label imagen2;
	@FXML
	private Label vida;
	@FXML
	private Label nivel;
	@FXML
	private Label finDelJuego;

	@FXML
	private TextField campoPalabras;

	@FXML
	private Button Start;
	@FXML
	private Button Guardar;
	@FXML
	private Button Menu;

	@FXML
	private Label enemigo1;
	@FXML
	private Label enemigo2;
	@FXML
	private Label enemigo3;
	@FXML
	private Label enemigo4;
	@FXML
	private Label enemigo5;
	@FXML
	private Label enemigo6;
	@FXML
	private Label enemigo7;
	@FXML
	private Label enemigo8;

	@FXML
	private Label Palabra1;
	@FXML
	private Label Palabra2;
	@FXML
	private Label Palabra3;
	@FXML
	private Label Palabra4;
	@FXML
	private Label Palabra5;
	@FXML
	private Label Palabra6;
	@FXML
	private Label Palabra7;
	@FXML
	private Label Palabra8;

	SecureRandom random = new SecureRandom();
	int i = 0;
	int x;
	int y;
	int k = 1;
	int w = 1;

	public StartController() {
		relacionJugador = new Jugador(principal.actual().getNickname());
		relacionPantalla = new Pantalla();
		relacionEnemigo = new Enemigo(0, 0, "", 0);
	}

	public int incrementarIndex() {
		if (i < 8) {
			System.out.println("ESTE ES EL INDEX " + i);
			i += 1;
			this.incrementarIndex();
		}
		return i;
	}

	public int ramdonX() {
		x = 1 + random.nextInt(50) * k;
		return x;
	}

	public int ramdonY() {
		y = 1 + random.nextInt(1) * w;
		return y;
	}

	public void initialize() {
		this.inicializarEfectosGraficos();
		this.inicializarJugador();

		nombre.setText(relacionJugador.getNickname());

	}

	public void inicializarEfectosGraficos() {

		tableroDeJuego.setPrefSize(498, 560);
		Image icon = new Image("Imagenes/fondo.gif");
		ImageView img = new ImageView(icon);
		fondo.setGraphic(img);
		fondo.setLayoutX(0);
		fondo.setLayoutY(0);

	}

	public void inicializarJugador() {
		Image icon = new Image("Imagenes/jugador.gif");
		jugador.setImage(icon);
		jugador.setScaleX(3);
		jugador.setScaleY(3);
		jugador.setLayoutX(180);
		jugador.setLayoutY(460);

		Image ic = new Image("Imagenes/1.jpg");
		ImageView img1 = new ImageView(ic);
		img1.setScaleY(0.3);
		img1.setScaleX(1);
		imagen1.setGraphic(img1);

		ImageView img2 = new ImageView(ic);
		img2.setScaleY(0.3);
		img2.setScaleX(1);
		imagen2.setGraphic(img2);

		vida.setText(principal.actual().getVida() + "");
		nombre.setText(principal.actual().getNickname());
		Score.setText(principal.actual().getPuntaje() + "");
		nivel.setText(principal.actual().getPantalla() + "");

	}

	public void iniciarEnemigos() {

		enemigo1.setLayoutX(0);
		enemigo1.setLayoutY(0);

		enemigo2.setLayoutX(0);
		enemigo2.setLayoutY(-10);

		enemigo3.setLayoutX(0);
		enemigo3.setLayoutY(-10);

		enemigo4.setLayoutX(0);
		enemigo4.setLayoutY(-10);

		enemigo5.setLayoutX(0);
		enemigo5.setLayoutY(-10);

		enemigo6.setLayoutX(0);
		enemigo6.setLayoutY(-10);

		enemigo7.setLayoutX(0);
		enemigo7.setLayoutY(-10);

		enemigo8.setLayoutX(0);
		enemigo8.setLayoutY(-10);

		enemigos[0] = enemigo1;
		enemigos[1] = enemigo2;
		enemigos[2] = enemigo3;
		enemigos[3] = enemigo4;
		enemigos[4] = enemigo5;
		enemigos[5] = enemigo6;
		enemigos[6] = enemigo7;
		enemigos[7] = enemigo8;

		bordesX();

		for (int i = 0; i < enemigos.length; i++) {

			Image icono = new Image("application/nave.gif");
			ImageView img = new ImageView(icono);
			img.setScaleX(0.2);
			img.setScaleY(0.2);
			enemigos[i].setGraphic(img);
			this.hiloRandoms();

			relacionEnemigo.setX((int) enemigos[i].getLayoutX());
			relacionEnemigo.setY((int) enemigos[i].getLayoutY());

		}

		Timeline tm = new Timeline(new KeyFrame(Duration.seconds(0.2), new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				enemigo1.setLayoutX(enemigo1.getLayoutX() + ramdonX());
				enemigo1.setLayoutY(enemigo1.getLayoutY() + ramdonY());

				enemigo2.setLayoutX(enemigo2.getLayoutX() + ramdonX());
				enemigo2.setLayoutY(enemigo2.getLayoutY() + ramdonY());

				enemigo3.setLayoutX(enemigo3.getLayoutX() + ramdonX());
				enemigo3.setLayoutY(enemigo3.getLayoutY() + ramdonY());

				enemigo4.setLayoutX(enemigo4.getLayoutX() + ramdonX());
				enemigo4.setLayoutY(enemigo4.getLayoutY() + ramdonY());

				enemigo5.setLayoutX(enemigo5.getLayoutX() + ramdonX());
				enemigo5.setLayoutY(enemigo5.getLayoutY() + ramdonY());

				enemigo6.setLayoutX(enemigo6.getLayoutX() + ramdonX());
				enemigo6.setLayoutY(enemigo6.getLayoutY() + ramdonY());

				enemigo7.setLayoutX(enemigo7.getLayoutX() + ramdonX());
				enemigo7.setLayoutY(enemigo7.getLayoutY() + ramdonY());

				enemigo8.setLayoutX(enemigo8.getLayoutX() + ramdonX());
				enemigo8.setLayoutY(enemigo8.getLayoutY() + ramdonY());

				relacionEnemigo.mover((int) enemigo1.getLayoutX());
				relacionEnemigo.mover((int) enemigo2.getLayoutX());
				relacionEnemigo.mover((int) enemigo3.getLayoutX());
				relacionEnemigo.mover((int) enemigo4.getLayoutX());
				relacionEnemigo.mover((int) enemigo5.getLayoutX());
				relacionEnemigo.mover((int) enemigo6.getLayoutX());
				relacionEnemigo.mover((int) enemigo7.getLayoutX());
				relacionEnemigo.mover((int) enemigo8.getLayoutX());

				bordesX();
				bordesY();

			}
		}));
		tm.setCycleCount(Timeline.INDEFINITE);
		tm.play();

	}

	public void bordesX() {
		Bounds bordes = tableroDeJuego.getBoundsInLocal();

		if (enemigos[0].getLayoutX() >= DERECHA + enemigos[0].getLayoutX()
				|| enemigos[0].getLayoutX() <= IZQUIERDA - enemigos[0].getLayoutX()) {
			k *= -1;
		} else if (enemigos[1].getLayoutX() >= DERECHA + enemigos[1].getLayoutX()
				|| enemigos[1].getLayoutX() <= IZQUIERDA - enemigos[1].getLayoutX()) {
			k *= -1;
		} else if (enemigos[2].getLayoutX() >= DERECHA + enemigos[2].getLayoutX()
				|| enemigos[2].getLayoutX() <= IZQUIERDA - enemigos[2].getLayoutX()) {
			k *= -1;
		} else if (enemigos[3].getLayoutX() >= DERECHA + enemigos[3].getLayoutX()
				|| enemigos[3].getLayoutX() <= IZQUIERDA - enemigos[3].getLayoutX()) {
			k *= -1;
		} else if (enemigos[4].getLayoutX() >= DERECHA + enemigos[4].getLayoutX()
				|| enemigos[4].getLayoutX() <= IZQUIERDA - enemigos[4].getLayoutX()) {
			k *= -1;
		} else if (enemigos[5].getLayoutX() >= DERECHA + enemigos[5].getLayoutX()
				|| enemigos[5].getLayoutX() <= IZQUIERDA - enemigos[5].getLayoutX()) {
			k *= -1;
		} else if (enemigos[6].getLayoutX() >= DERECHA + enemigos[6].getLayoutX()
				|| enemigos[6].getLayoutX() <= IZQUIERDA - enemigos[6].getLayoutX()) {
			k *= -1;
		} else if (enemigos[7].getLayoutX() >= DERECHA + enemigos[7].getLayoutX()
				|| enemigos[7].getLayoutX() <= IZQUIERDA - enemigos[7].getLayoutX()) {
			k *= -1;
		}

	}

	public void bordesY() {
		Image ic = new Image("application/gameOver.png");
		ImageView img = new ImageView(ic);
		finDelJuego.setLayoutX(160);
		finDelJuego.setLayoutY(50);

		if (enemigos[0].getLayoutX() >= INFERIOR - enemigos[0].getLayoutX()) {
			finDelJuego.setGraphic(img);
		} else if (enemigos[1].getLayoutY() >= INFERIOR - enemigos[1].getLayoutY()) {
			finDelJuego.setGraphic(img);
		} else if (enemigos[2].getLayoutY() >= INFERIOR - enemigos[2].getLayoutY()) {
			finDelJuego.setGraphic(img);
		} else if (enemigos[3].getLayoutY() >= INFERIOR - enemigos[3].getLayoutY()) {
			finDelJuego.setGraphic(img);
		} else if (enemigos[4].getLayoutY() >= INFERIOR - enemigos[4].getLayoutY()) {
			finDelJuego.setGraphic(img);
		} else if (enemigos[5].getLayoutY() >= INFERIOR - enemigos[5].getLayoutY()) {
			finDelJuego.setGraphic(img);
		} else if (enemigos[6].getLayoutY() >= INFERIOR - enemigos[6].getLayoutY()) {
			finDelJuego.setGraphic(img);
		} else if (enemigos[7].getLayoutY() >= INFERIOR - enemigos[7].getLayoutY()) {
			finDelJuego.setGraphic(img);
		}
	}

	public Label[] getEnemigos() {
		return enemigos;
	}

	public void hiloRandoms() {
		Timeline tm = new Timeline(new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				ramdonX();
				ramdonY();

			}
		}));
		tm.setCycleCount(Timeline.INDEFINITE);
		tm.play();
	}

	public void irMenu() {
		Stage stage = (Stage) Menu.getScene().getWindow();

		stage.close();
	}

	public void agregarPalabras() {
		palabras[0] = Palabra1;
		palabras[1] = Palabra2;
		palabras[2] = Palabra3;
		palabras[3] = Palabra4;
		palabras[4] = Palabra5;
		palabras[5] = Palabra6;
		palabras[6] = Palabra7;
		palabras[7] = Palabra8;
		for (int i = 0; i < principal.listaEnemigo().size(); i++) {
			palabras[i].setText(principal.listaEnemigo().get(i).getPalabra());

		}

	}

	public void guardar() {

	}

	public void activarArma() {
		campoPalabras.setDisable(false);
	}

	public void Iniciar() {
		activarArma();
		iniciarEnemigos();
		agregarPalabras();

		this.hiloRandoms();
	}

	public void borrarText() {
		campoPalabras.setText("");
	}

	public void ocurrenciasEnemigos() {

		for (int i = 0; i < principal.listaEnemigo().size(); i++) {
			Enemigo mib = principal.listaEnemigo().get(i);

			if (mib.getX() > 440) {
				mib.setCambiar(true);
			} else if (mib.getX() < 0) {
				mib.setCambiar(false);
			}

			if (mib.getY() > 570) {
				if (mib.getTipo() == 0) {
					principal.actual().disminuirVidaEnemigo();
				}
				principal.actual().getPantalla().eliminarEnemigo(mib.getPalabra());
				principal.gameOver();
			}

			ArrayList<Proyectil> meb = mib.getProyectil();
			for (int j = 0; j < meb.size(); j++) {
				if (meb.get(j) != null) {
					if (meb.get(j).isMover() == true) {
					}
				}
				if (meb.get(j).getY() > 570) {
					principal.actual().disminuirVida();
					meb.clear();
					principal.gameOver();
				}
			}
		}
	}

	public void quitarPalabra(String pal) {
		for (int i = 0; i < this.palabras.length; i++) {
			if (this.palabras[i].getText().equals(pal)) {
				palabras[i].setText("");

			}

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ENTER) {
			System.out.println("Pun, toma tu enter");
			Jugador jugador = principal.actual();
			String palabra = campoPalabras.getText();
			try {
				principal.actual().destruirNaves(palabra);
				principal.cambiarNivel();
				quitarPalabra(palabra);

			} catch (Exception e2) {
				// TODO: handle exception
			}
			borrarText();
//			principal.fin();

		}

	}

	public void activarGuardar() {
		Guardar.setDisable(false);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
