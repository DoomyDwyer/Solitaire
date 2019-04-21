package opmj14.solitaire.model;

public class ZetTerug {

	private Zet zet;

	public ZetTerug(Zet laatsteZet) {
		zet = laatsteZet;
	}

	public int getOudeKolom() {
		return zet.getOudeKolom();
	}

	public int getOudeRij() {
		return zet.getOudeRij();
	}

	public int getTussenKolom() {
		return zet.getTussenKolom();
	}

	public int getTussenRij() {
		return zet.getTussenRij();
	}

	public int getNieuweKolom() {
		return zet.getNieuweKolom();
	}

	public int getNieuweRij() {
		return zet.getNieuweRij();
	}
}
