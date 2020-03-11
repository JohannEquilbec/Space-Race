package jeu;

import java.awt.Graphics2D;

import modele.Etat;
import view.ThreadAff;

public class Checkpoint extends Thread {
	private ThreadAff affi;
	private Etat etat;
	private int distanceCheckpoint = 400;
	private int tempsSepare = 0;
	
	/**
	 * Associe le thread avec Etat et Affichage
	 * @param tAffi le {@link ThreadAffichage} responsable de l'affichage
	 * @param et l'etat
	 */
	public Checkpoint(ThreadAff tAffi, Etat et) {
		this.etat = et;
		this.affi = tAffi;
	}
	
	public void incrDistance() {
		distanceCheckpoint += 500; 
	}
	
	/**
	 * Définit la fonction run :
	 * Une boucle (s'arretant lors du gameover) qui définit si un checkpoint est sur la piste
	 * @param g 
	 */
	public void run(Graphics2D g) {
		while (!etat.isPerdu()) {
			if(etat.piste.isCheckpoint == true) {
				// incrDistance();
				if (tempsSepare < 80) {
					etat.piste.debutCheckpoint(g);
				}
				else {
					etat.piste.dessineCheckpoint(g);
				}
				affi.redraw();
			}
			try {
				Thread.sleep(distanceCheckpoint);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}