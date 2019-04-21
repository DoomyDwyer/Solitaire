package opmj14.solitaire.view;

import java.awt.Color;

public abstract class Veld extends SolitairePanel {

	private static final long serialVersionUID = -6285508301980812716L;
	static final int BREEDTE = 25;
	static final int HOOGTE = 25;
	static final int DOORSNEDE = 15;

	public Veld() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		setSize(BREEDTE, HOOGTE);
		setOpaque(false);
		setBackground(Color.white);
	}
}
