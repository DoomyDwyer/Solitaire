package opmj14.solitaire.view;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SolitairePanel extends JPanel {
	private static final long serialVersionUID = -8689556626421259327L;
	protected URL codeBase;

	public void setCodeBase(URL appletCodeBase) {
		codeBase = appletCodeBase;
	}

	protected void setAfbeelding(URL appletCodeBase) {
		setCodeBase(appletCodeBase);
	}

	protected Image getAfbeelding(String locatie) {
		ImageIcon afbeelding;
		if (codeBase == null) {
			afbeelding = new ImageIcon(locatie);
		} else {
			URL locatieURL = null;
			try {
				locatieURL = new URL(codeBase, locatie);
			} catch (MalformedURLException e) {
			}
			afbeelding = new ImageIcon(locatieURL);
		}
		return afbeelding.getImage();
	}
}
