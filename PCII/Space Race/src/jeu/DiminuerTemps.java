package jeu;

import modele.Etat;

public class DiminuerTemps extends Thread {
	private Etat etat;
	
	/**
	 * Associe le thread avec Etat et Affichage
	 * @param tAffi le {@link ThreadAffichage} responsable de l'affichage
	 * @param et l'etat
	 */
	public DiminuerTemps(Etat et) {
		this.etat = et;
	}
	
	/**
	 * Définit la fonction run :
	 * Une boucle (s'arretant lors du gameover) qui définit le temps restant
	 */
	@Override
	public void run() {
		while (!etat.isPerdu()) {
			etat.vaisseau.perdVie();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}