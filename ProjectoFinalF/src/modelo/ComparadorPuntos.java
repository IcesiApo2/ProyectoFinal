package modelo;

import java.util.Comparator;

public class ComparadorPuntos implements Comparator<Jugador> {
	@Override
	public int compare(Jugador o1, Jugador o2) {
		return (int) (o1.getPuntaje() - o2.getPuntaje());
	}
}
