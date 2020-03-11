package jeu;

import modele.Etat;
import view.ThreadAff;

public class Accelerer extends Thread {
	private ThreadAff affi;
	private Etat etat;
	private int frame;
	
	/**
	 * Associe le thread avec Etat et Affichage
	 * @param tAffi le {@link ThreadAffichage} responsable de l'affichage
	 * @param et l'etat
	 */
	public Accelerer(ThreadAff tAffi, Etat et) {
		this.etat = et;
		this.affi = tAffi;
		this.frame = etat.vaisseau.frame;
	}
	
	/**
	 * Définit la fonction run :
	 * Une boucle (s'arretant lors du gameover) qui fait "avancer" l'ovale et actualise la fenetre
	 */
	@Override
	public void run() {
		while (!etat.isPerdu()) {
			etat.vaisseau.accelere();
			affi.redraw();
			try {
				Thread.sleep(frame);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}