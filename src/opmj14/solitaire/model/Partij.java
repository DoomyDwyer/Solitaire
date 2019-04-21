package opmj14.solitaire.model;

import java.util.Observable;
import java.util.Stack;

public class Partij extends Observable {

	protected static final int DIMENSIE = 7;
	private static final int DODE_RUIMTE = 2;
	private Spelpositie spelposities[][];
	private Stack<Zet> zetten;

	public Partij() {
		zetten = new Stack<Zet>();
		spelposities = new Spelpositie[DIMENSIE][DIMENSIE];
		for (int x = 0; x < DIMENSIE; x++) {
			for (int y = 0; y < DIMENSIE; y++) {
				boolean legalePositie = true;
				if ((x < DODE_RUIMTE || x >= DIMENSIE - DODE_RUIMTE) && (y < DODE_RUIMTE || y >= DIMENSIE - DODE_RUIMTE)) {
					legalePositie = false;
				}
				if (legalePositie) {
					spelposities[x][y] = new Spelpositie(x, y);
					if (x == 3 && y == 3) {
						spelposities[x][y].setBezet(false);
					} else {
						spelposities[x][y].setBezet(true);
					}
				} else {
					spelposities[x][y] = null;
				}
			}
		}
	}

	public void toonOpstelling() {
		for (int x = 0; x < DIMENSIE; x++) {
			for (int y = 0; y < DIMENSIE; y++) {
				boolean legalePositie = spelposities[x][y] != null;
				if (legalePositie) {
					setChanged();
					notifyObservers(spelposities[x][y]);
				}
			}
		}
	}

	public void voerZetUit(int oudeKolom, int oudeRij, int nieuweKolom, int nieuweRij) {
		if (isGeldigeZet(oudeKolom, oudeRij, nieuweKolom, nieuweRij)) {
			maakZet(oudeKolom, oudeRij, nieuweKolom, nieuweRij);
			verwerkZet();
			setChanged();
			notifyObservers(zetten.peek());
		}
	}

	public void maakZetOngedaan() {
		if (!zetten.empty()) {
			Zet zet = (Zet) zetten.pop();
			spelposities[zet.getOudeKolom()][zet.getOudeRij()].setBezet(true);
			spelposities[zet.getTussenKolom()][zet.getTussenRij()].setBezet(true);
			spelposities[zet.getNieuweKolom()][zet.getNieuweRij()].setBezet(false);
			setChanged();
			notifyObservers(new ZetTerug(zet));
		}
	}

	private boolean isGeldigeZet(int oudeKolom, int oudeRij, int nieuweKolom, int nieuweRij) {
		boolean isGeldig = false;
		if (spelposities[oudeKolom][oudeRij].isBezet() && !spelposities[nieuweKolom][nieuweRij].isBezet()) {
			int kolommenTussen = Math.max(oudeKolom, nieuweKolom) - Math.min(oudeKolom, nieuweKolom);
			int rijenTussen = Math.max(oudeRij, nieuweRij) - Math.min(oudeRij, nieuweRij);
			if (kolommenTussen + rijenTussen == 2 && (kolommenTussen == 0 || rijenTussen == 0)) {
				int tussenKolom = nieuweKolom;
				int tussenRij = nieuweRij;
				if (kolommenTussen != 0) {
					tussenKolom = (Math.min(oudeKolom, nieuweKolom) - 1)
							+ (Math.max(oudeKolom, nieuweKolom) - Math.min(oudeKolom, nieuweKolom));
				}
				if (rijenTussen != 0) {
					tussenRij = (Math.min(oudeRij, nieuweRij) - 1)
							+ (Math.max(oudeRij, nieuweRij) - Math.min(oudeRij, nieuweRij));
				}
				if (spelposities[tussenKolom][tussenRij].isBezet()) {
					isGeldig = true;
				}
			}
		}
		return isGeldig;
	}

	private void maakZet(int oudeKolom, int oudeRij, int nieuweKolom, int nieuweRij) {
		int kolommenTussen = Math.max(oudeKolom, nieuweKolom) - Math.min(oudeKolom, nieuweKolom);
		int rijenTussen = Math.max(oudeRij, nieuweRij) - Math.min(oudeRij, nieuweRij);
		int tussenKolom = nieuweKolom;
		int tussenRij = nieuweRij;
		if (kolommenTussen != 0) {
			tussenKolom = (Math.min(oudeKolom, nieuweKolom) - 1)
					+ (Math.max(oudeKolom, nieuweKolom) - Math.min(oudeKolom, nieuweKolom));
		}
		if (rijenTussen != 0) {
			tussenRij = (Math.min(oudeRij, nieuweRij) - 1) + (Math.max(oudeRij, nieuweRij) - Math.min(oudeRij, nieuweRij));
		}
		zetten.push(new Zet(oudeKolom, oudeRij, tussenKolom, tussenRij, nieuweKolom, nieuweRij));
	}

	private void verwerkZet() {
		Zet zet = (Zet) zetten.peek();
		spelposities[zet.getOudeKolom()][zet.getOudeRij()].setBezet(false);
		spelposities[zet.getTussenKolom()][zet.getTussenRij()].setBezet(false);
		spelposities[zet.getNieuweKolom()][zet.getNieuweRij()].setBezet(true);
	}
}
