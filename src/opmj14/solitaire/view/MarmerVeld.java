package opmj14.solitaire.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;


public class MarmerVeld extends Veld {

	private static final long serialVersionUID = 8020237999928717678L;
	private Image gaatje;

	public MarmerVeld() {
	}

	protected void setAfbeelding(URL appletCodeBase) {
		setCodeBase(appletCodeBase);
		gaatje = getAfbeelding(SolitaireClasses.getString("MarmerVeld.Hole")); //$NON-NLS-1$
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(gaatje, 7, 7, 15, 15, this);
	}
}
