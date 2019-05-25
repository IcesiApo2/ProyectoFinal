package excepciones;

public class UsuarioRepetidoException extends Exception {

	public UsuarioRepetidoException() {
		// TODO Auto-generated constructor stub
		super("Este usuario ya fue registrado");
	}

}
