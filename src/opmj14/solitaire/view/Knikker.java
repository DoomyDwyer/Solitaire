package opmj14.solitaire.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;


public class Knikker extends Pion {

	private static final long serialVersionUID = -7658428789483800037L;
	private Image pionNeer;
	private Image pionOp;

	public Knikker() {
	}

	protected void setAfbeelding(URL appletCodeBase) {
		setCodeBase(appletCodeBase);
		pionNeer = getAfbeelding(SolitaireClasses.getString("Knikker.Pawn")); //$NON-NLS-1$
		pionOp = getAfbeelding(SolitaireClasses.getString("Knikker.PawnUp")); //$NON-NLS-1$
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (geselecteerd) {
			g.drawImage(pionOp, 0, 0, this);
		} else {
			g.drawImage(pionNeer, 0, 0, this);
		}
	}
}
