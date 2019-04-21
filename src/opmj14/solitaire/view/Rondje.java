package opmj14.solitaire.view;

import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

public class Rondje extends Pion {

	private static final long serialVersionUID = 9075706016414534376L;
	public static final Color KLEUR;
	public static final Color SELECTIEKLEUR;

	public Rondje() {
	}

	protected void setAfbeelding(URL appletCodeBase) {
		setCodeBase(appletCodeBase);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (geselecteerd) {
			g.setColor(SELECTIEKLEUR);
		} else {
			g.setColor(KLEUR);
		}
		g.fillOval(0, 0, 25, 25);
	}

	static {
		KLEUR = Color.red;
		SELECTIEKLEUR = Color.blue;
	}
}
