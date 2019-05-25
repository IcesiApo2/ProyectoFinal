package excepciones;

public class CampoVacioException extends Exception {
	public CampoVacioException() {
		super("Espacio vacio, ingrese su nombre");
	}

}
