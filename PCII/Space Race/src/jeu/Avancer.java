package jeu;

import modele.Etat;
import view.ThreadAff;

public class Avancer extends Thread {
	private ThreadAff affi;
	private Etat etat;
	private static final int tempsAvancement = 20;
	
	/**
	 * Associe le thread avec Etat et Affichage
	 * @param tAffi le {@link ThreadAffichage} responsable de l'affichage
	 * @param et l'etat
	 */
	public Avancer(ThreadAff tAffi, Etat et) {
		this.etat = et;
		this.affi = tAffi;
	}
	
	/**
	 * Définit la fonction run :
	 * Une boucle (s'arretant lors du gameover) qui fait "avancer" l'ovale et actualise la fenetre
	 */
	@Override
	public void run() {
		while (!etat.isPerdu()) {
			etat.piste.avance();
			affi.redraw();
			try {
				Thread.sleep(tempsAvancement);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}