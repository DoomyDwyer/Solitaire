package opmj14.solitaire.view;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SolitaireClasses {
	private static final String BUNDLE_NAME = "opmj14.solitaire.view.solitaireclasses"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private SolitaireClasses() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
