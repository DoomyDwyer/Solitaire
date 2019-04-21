package opmj14.solitaire.view;

public abstract class Pion extends SolitairePanel {

	private static final long serialVersionUID = 2829338334878456346L;
	static final int BREEDTE = 25;
	static final int HOOGTE = 25;
	private int x;
	private int y;
	private int stapX;
	private int stapY;
	protected boolean geselecteerd;

	public Pion() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		setSize(BREEDTE, HOOGTE);
		setOpaque(false);
	}

	public void setGeselecteerd(boolean sel) {
		geselecteerd = sel;
	}

	public boolean isGeselecteerd() {
		return geselecteerd;
	}

	public void gaNaar(int x, int y) {
		this.x = x;
		this.y = y;
		stapX = (x - getX()) / 10;
		stapY = (y - getY()) / 10;
	}

	public void volgendeFrame() {
		setLocation(getX() + stapX, getY() + stapY);
	}

	public boolean animatieKlaar() {
		return getX() == x && getY() == y;
	}
}
