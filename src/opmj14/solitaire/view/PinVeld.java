package opmj14.solitaire.view;

import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

public class PinVeld extends Veld {

	private static final long serialVersionUID = 4931166663563133927L;
	public static final Color KLEUR;

	public PinVeld() {
	}

	protected void setAfbeelding(URL appletCodeBase) {
		setCodeBase(appletCodeBase);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(KLEUR);
		g.fillOval(7, 7, 15, 15);
	}

	static {
		KLEUR = Color.black;
	}
}
