package opmj14.solitaire.model;

public class Zet {

	private int oudeKolom;
	private int oudeRij;
	private int tussenKolom;
	private int tussenRij;
	private int nieuweKolom;
	private int nieuweRij;

	public Zet(int oudeKolom, int oudeRij, int tussenKolom, int tussenRij, int nieuweKolom, int nieuweRij) {
		this.oudeKolom = oudeKolom;
		this.oudeRij = oudeRij;
		this.tussenKolom = tussenKolom;
		this.tussenRij = tussenRij;
		this.nieuweKolom = nieuweKolom;
		this.nieuweRij = nieuweRij;
	}

	public int getOudeKolom() {
		return oudeKolom;
	}

	public int getOudeRij() {
		return oudeRij;
	}

	public int getTussenKolom() {
		return tussenKolom;
	}

	public int getTussenRij() {
		return tussenRij;
	}

	public int getNieuweKolom() {
		return nieuweKolom;
	}

	public int getNieuweRij() {
		return nieuweRij;
	}
}
