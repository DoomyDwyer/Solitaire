package opmj14.solitaire.view;

import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

public class PlainVeld extends Veld {

	private static final long serialVersionUID = 3362020050605159564L;
	public static final Color KLEUR;

	public PlainVeld() {
	}

	protected void setAfbeelding(URL appletCodeBase) {
		setCodeBase(appletCodeBase);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawOval(5, 5, 15, 15);
		g.setColor(KLEUR);
		g.fillOval(6, 6, 13, 13);
	}

	static {
		KLEUR = Color.white;
	}
}
