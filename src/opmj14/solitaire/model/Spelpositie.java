package opmj14.solitaire.model;

public class Spelpositie {

	private int rij;
	private int kolom;
	private boolean bezet;

	public Spelpositie(int x, int y) {
		kolom = x;
		rij = y;
	}

	public int getKolom() {
		return kolom;
	}

	public int getRij() {
		return rij;
	}

	public boolean isBezet() {
		return bezet;
	}

	public void setBezet(boolean b) {
		bezet = b;
	}
}
