package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import modele.Etat;
import view.Affichage;

/**
 * Crée un Controleur qui gère les clics de souris
 */
public class ControlKey {
	private Etat etat;
	
	/**
	 * Crée le Controleur et le lie avec {@link Affichage} et {@link Etat}
	 * @param aff l'affichage
	 * @param et l'etat
	 */
	public ControlKey(JFrame fenetre, Etat et) {
		this.etat = et;
		fenetre.addKeyListener(this.Listener());
	}
	
	/**
	 * Crée le KeyAdapter pour faire l'action voulue
	 * @return le KeyAdapter qui execute jump
	 */
	private KeyAdapter Listener() {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!etat.isPerdu()) {
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						etat.fly(Etat.DIR.HAUT);
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						etat.fly(Etat.DIR.BAS);
					} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						etat.fly(Etat.DIR.DROITE);
					} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						etat.fly(Etat.DIR.GAUCHE);
					}
				}
			}
		};
	}
}