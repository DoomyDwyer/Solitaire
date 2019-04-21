package opmj14.solitaire.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import opmj14.solitaire.model.ISolitaireSpel;
import opmj14.solitaire.model.Partij;
import opmj14.solitaire.model.Spelpositie;
import opmj14.solitaire.model.Zet;
import opmj14.solitaire.model.ZetTerug;

public class Speelbord extends SolitairePanel implements Observer {

	private static final long serialVersionUID = -7884103592798469946L;
	private String pionsoort = SolitaireClasses.getString("SolitaireSpel.DefaultPegClass"); //$NON-NLS-1$
	private String veldsoort = SolitaireClasses.getString("SolitaireSpel.DefaultBoardClass"); //$NON-NLS-1$
	private String achtergrond = SolitaireClasses.getString("SolitaireSpel.DefaultBoard"); //$NON-NLS-1$
	private boolean spelen;
	private Pion geselecteerdePion = null;
	private ISolitaireSpel spel;
	Timer timer;

	public Speelbord(ISolitaireSpel s) {
		try {
			jbInit();
			this.spel = s;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		setBackground(Color.white);
		setLayout(null);
		int x = 270;
		int y = 270;
		setSize(x, y);
	}

	public boolean isSpelen() {
		return this.spelen;
	}

	private Pion maakPion() {
		Pion pion = null;
		try {
			Class<?> klasse = Class.forName(this.pionsoort);
			pion = (Pion) klasse.newInstance();
			pion.setAfbeelding(this.codeBase);
		} catch (Exception ex) {
			JOptionPane
					.showMessageDialog(
							this,
							String
									.valueOf(
											String
													.valueOf(Messages.getString("Speelbord.Error")).concat(String.valueOf(ex.getClass().getName()))).concat( //$NON-NLS-1$
											String.valueOf(Messages.getString("Speelbord.TryAgain"))), //$NON-NLS-1$
							String
									.valueOf(
											String
													.valueOf(Messages.getString("Speelbord.ErrorOccurred")).concat(String.valueOf(ex.getMessage()))).concat( //$NON-NLS-1$
											String.valueOf("'")), 0); //$NON-NLS-1$
		}

		return pion;
	}

	public void geefSpelpositieWeer(Spelpositie p) {
		try {
			Class<?> klasse = Class.forName(this.veldsoort);
			Veld v = (Veld) klasse.newInstance();
			v.setAfbeelding(this.codeBase);
			int x = 15 + p.getKolom() * 25 + p.getKolom() * 10;
			int y = 15 + p.getRij() * 25 + p.getRij() * 10;
			v.setLocation(x, y);
			add(v);
			v.addMouseListener(new VeldAdapter());
			if (p.isBezet())
				plaatsPion(x, y);
			repaint();
		} catch (Exception ex) {
			JOptionPane
					.showMessageDialog(
							this,
							String
									.valueOf(
											String
													.valueOf(Messages.getString("Speelbord.Error")).concat(String.valueOf(ex.getClass().getName()))).concat( //$NON-NLS-1$
											String.valueOf(Messages.getString("Speelbord.TryAgain"))), //$NON-NLS-1$
							String
									.valueOf(
											String
													.valueOf(Messages.getString("Speelbord.ErrorOccurred")).concat(String.valueOf(ex.getMessage()))).concat( //$NON-NLS-1$
											String.valueOf("'")), 0); //$NON-NLS-1$
		}
	}

	public void update(Observable o, Object arg) {
		if ((arg instanceof Spelpositie))
			geefSpelpositieWeer((Spelpositie) arg);
		else if ((arg instanceof Zet))
			toonZet((Zet) arg);
		else if ((arg instanceof ZetTerug))
			herstelZet((ZetTerug) arg);
	}

	private void verzoekZet(Veld veld) {
		if (this.geselecteerdePion != null) {
			int oudeKolom = (this.geselecteerdePion.getX() - 15) / 35;
			int oudeRij = (this.geselecteerdePion.getY() - 15) / 35;
			int nieuweKolom = (veld.getX() - 15) / 35;
			int nieuweRij = (veld.getY() - 15) / 35;
			Partij partij = this.spel.getPartij();
			partij.voerZetUit(oudeKolom, oudeRij, nieuweKolom, nieuweRij);
		}
	}

	private void toonZet(Zet zet) {
		this.spelen = true;

		int xNieuw = 15 + zet.getNieuweKolom() * 25 + zet.getNieuweKolom() * 10;
		int yNieuw = 15 + zet.getNieuweRij() * 25 + zet.getNieuweRij() * 10;

		this.timer = new Timer(100, new TimerLuisteraar());
		this.timer.start();

		this.geselecteerdePion.gaNaar(xNieuw, yNieuw);

		int xWeg = 15 + zet.getTussenKolom() * 25 + zet.getTussenKolom() * 10;
		int yWeg = 15 + zet.getTussenRij() * 25 + zet.getTussenRij() * 10;

		haalPionweg((Pion) getComponentAt(xWeg, yWeg));
	}

	private void haalPionweg(Pion pion) {
		if (pion.isGeselecteerd())
			selecteer(pion);
		remove(pion);
		repaint();
	}

	private void herstelZet(ZetTerug zetTerug) {
		this.spelen = true;

		int xNieuw = 15 + zetTerug.getNieuweKolom() * 25 + zetTerug.getNieuweKolom() * 10;
		int yNieuw = 15 + zetTerug.getNieuweRij() * 25 + zetTerug.getNieuweRij() * 10;

		int xOud = 15 + zetTerug.getOudeKolom() * 25 + zetTerug.getOudeKolom() * 10;
		int yOud = 15 + zetTerug.getOudeRij() * 25 + zetTerug.getOudeRij() * 10;

		this.timer = new Timer(100, new TimerLuisteraar());
		this.timer.start();

		selecteer((Pion) getComponentAt(xNieuw, yNieuw));
		this.geselecteerdePion.gaNaar(xOud, yOud);

		int xHerstel = 15 + zetTerug.getTussenKolom() * 25 + zetTerug.getTussenKolom() * 10;
		int yHerstel = 15 + zetTerug.getTussenRij() * 25 + zetTerug.getTussenRij() * 10;

		plaatsPion(xHerstel, yHerstel);
	}

	private void plaatsPion(int x, int y) {
		Pion pion = maakPion();
		pion.setLocation(x, y);
		add(pion, 0);
		pion.addMouseListener(new PionAdapter());
	}

	private void selecteer(Pion pion) {
		if (this.geselecteerdePion != null) {
			this.geselecteerdePion.setGeselecteerd(false);
		}
		if (this.geselecteerdePion != pion) {
			pion.setGeselecteerd(true);
			this.geselecteerdePion = pion;
		} else {
			this.geselecteerdePion = null;
		}
		repaint();
	}

	public void setPionsoort(String klassenaam) {
		this.pionsoort = klassenaam;
	}

	public void setVeldsoort(String klassenaam) {
		this.veldsoort = klassenaam;
	}

	public void setAchtergrond(String bestandsnaam) {
		this.achtergrond = bestandsnaam;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.achtergrond != null) {
			Image achtergrondPlaatje = null;
			achtergrondPlaatje = getAfbeelding(this.achtergrond);
			g.drawImage(achtergrondPlaatje, 0, 0, this);
		}
	}

	public void eindeAnimatie(Pion pion) {
		if (pion.isGeselecteerd())
			selecteer(pion);
		repaint();
		this.spelen = false;
	}

	public void stopTimer() {
		this.timer.stop();
		eindeAnimatie(this.geselecteerdePion);
	}

	public class PionAdapter extends MouseAdapter {
		public PionAdapter() {
		}

		public void mousePressed(MouseEvent e) {
			if (!spelen)
				selecteer((Pion) e.getComponent());
		}
	}

	public class TimerLuisteraar implements ActionListener {
		public TimerLuisteraar() {
		}

		public void actionPerformed(ActionEvent e) {
			geselecteerdePion.volgendeFrame();
			if (geselecteerdePion.animatieKlaar())
				stopTimer();
		}
	}

	public class VeldAdapter extends MouseAdapter {
		public VeldAdapter() {
		}

		public void mousePressed(MouseEvent e) {
			if (!spelen)
				verzoekZet((Veld) e.getComponent());
		}
	}
}
