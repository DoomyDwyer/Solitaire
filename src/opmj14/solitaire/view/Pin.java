package opmj14.solitaire.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;


public class Pin extends Pion {

	private static final long serialVersionUID = 4743071094118641217L;
	private Image pionNeer;
	private Image pionOp;

	public Pin() {
	}

	protected void setAfbeelding(URL appletCodeBase) {
		setCodeBase(appletCodeBase);
		pionNeer = getAfbeelding(SolitaireClasses.getString("Pin.Pawn")); //$NON-NLS-1$
		pionOp = getAfbeelding(SolitaireClasses.getString("Pin.PawnUp")); //$NON-NLS-1$
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (geselecteerd) {
			g.drawImage(pionOp, 0, 0, this);
		} else {
			g.drawImage(pionNeer, 2, 2, this);
		}
	}
}
