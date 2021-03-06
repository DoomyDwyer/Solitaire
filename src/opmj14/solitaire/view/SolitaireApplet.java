package opmj14.solitaire.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import opmj14.solitaire.model.ISolitaireSpel;
import opmj14.solitaire.model.Partij;

public class SolitaireApplet extends JApplet implements ISolitaireSpel {
	private static final long serialVersionUID = -1726167981669202745L;
	private Partij partij;
	private Speelbord speelbord;
	JMenuBar menubalk = new JMenuBar();
	JMenu spelMenu = new JMenu();
	JMenu partijMenu = new JMenu();
	JMenu zetMenu = new JMenu();
	JMenuItem rondeItem = new JMenuItem();
	JMenuItem plaatjeItem = new JMenuItem();
	JMenuItem knikkerItem = new JMenuItem();
	JMenuItem ongedaanItem = new JMenuItem();

	public void init() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.rondeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rondeItem_actionPerformed(e);
			}
		});
		this.plaatjeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plaatjeItem_actionPerformed(e);
			}
		});
		this.knikkerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				knikkerItem_actionPerformed(e);
			}
		});
		
		this.speelbord = new Speelbord(this);
		speelbord.setCodeBase(getCodeBase());	
		getContentPane().add(this.speelbord);
		nieuwePartij();

		getContentPane().setLayout(null);
		setSize(new Dimension(275, 325));

		this.spelMenu.setText(Messages.getString("SolitaireSpel.Game")); //$NON-NLS-1$
		this.partijMenu.setText(Messages.getString("SolitaireSpel.New")); //$NON-NLS-1$
		this.rondeItem.setText(Messages.getString("SolitaireSpel.Discs")); //$NON-NLS-1$
		this.plaatjeItem.setText(Messages.getString("SolitaireSpel.Pegs")); //$NON-NLS-1$
		this.knikkerItem.setText(Messages.getString("SolitaireSpel.Marbles")); //$NON-NLS-1$
		this.zetMenu.setText(Messages.getString("SolitaireSpel.Move")); //$NON-NLS-1$
		this.ongedaanItem.setText(Messages.getString("SolitaireSpel.Undo")); //$NON-NLS-1$
		this.ongedaanItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ongedaanItem_actionPerformed(e);
			}
		});
		this.menubalk.add(this.spelMenu);
		this.menubalk.add(this.zetMenu);
		this.spelMenu.add(this.partijMenu);
		this.partijMenu.add(this.rondeItem);
		this.partijMenu.add(this.plaatjeItem);
		this.partijMenu.add(this.knikkerItem);
		this.zetMenu.add(this.ongedaanItem);
		setJMenuBar(this.menubalk);
	}

	void ongedaanItem_actionPerformed(ActionEvent e) {
		if (!this.speelbord.isSpelen()) {
			this.partij.maakZetOngedaan();
		}
	}

	public Partij getPartij() {
		return this.partij;
	}

	private void nieuwePartij() {
		this.speelbord.removeAll();
		this.partij = new Partij();
		this.partij.addObserver(this.speelbord);
		this.partij.toonOpstelling();
	}

	void rondeItem_actionPerformed(ActionEvent e) {
		if (!this.speelbord.isSpelen()) {
			this.speelbord.setPionsoort(SolitaireClasses.getString("SolitaireSpel.DiscClass")); //$NON-NLS-1$
			this.speelbord.setVeldsoort(SolitaireClasses.getString("SolitaireSpel.DiscBoardClass")); //$NON-NLS-1$
			this.speelbord.setAchtergrond(null);
			nieuwePartij();
		}
	}

	void plaatjeItem_actionPerformed(ActionEvent e) {
		if (!this.speelbord.isSpelen()) {
			this.speelbord.setPionsoort(SolitaireClasses.getString("SolitaireSpel.WoodenPegClass")); //$NON-NLS-1$
			this.speelbord.setVeldsoort(SolitaireClasses.getString("SolitaireSpel.WoodenBoardClass")); //$NON-NLS-1$
			this.speelbord.setAchtergrond(SolitaireClasses.getString("SolitaireSpel.WoodenBoard")); //$NON-NLS-1$
			nieuwePartij();
		}
	}

	void knikkerItem_actionPerformed(ActionEvent e) {
		if (!this.speelbord.isSpelen()) {
			this.speelbord.setPionsoort(SolitaireClasses.getString("SolitaireSpel.MarbleClass")); //$NON-NLS-1$
			this.speelbord.setVeldsoort(SolitaireClasses.getString("SolitaireSpel.MarbleBoardClass")); //$NON-NLS-1$
			this.speelbord.setAchtergrond(SolitaireClasses.getString("SolitaireSpel.MarbleBoard")); //$NON-NLS-1$
			nieuwePartij();
		}
	}
}
