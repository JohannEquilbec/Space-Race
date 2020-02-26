package modele;

import java.awt.Graphics2D;

import view.Affichage;

public class Vaisseau {
	public final static int LARG = (Affichage.LARG+Affichage.HAUT)/30;
	public final static int HAUT = (Affichage.LARG+Affichage.HAUT)/30;
	private Etat etat;
	public int x = Affichage.LARG/2 - (Affichage.LARG+Affichage.HAUT)/60;
	public int y = Affichage.HAUT*2/3;
	public int vitesse = 0;
	public int vitesse_atteignable = 0;
	public int vitesse_max = 150;
	public int deplacement = 15;
	public int acceleration = 1;
	public int frame = 40;
	public int vie = 100;
	
	public Vaisseau(Etat et) {
		etat = et;
	}
	
	public void accelere() {
		etat.piste.distancePisteX(x);
		vitesse_atteignable = (y + Affichage.HAUT/10) * vitesse_max / Affichage.HAUT;
		if (vitesse < vitesse_atteignable) {
			vitesse += acceleration;
		} else if (vitesse > vitesse_atteignable && vitesse > 0) {
			vitesse --;
		}
	}
	
	public void crash() {
		vitesse = 1;
	}
	
	public boolean isDead() {
		return vie <= 0;
	}

	public void vole(Etat.DIR dir) {
		if (dir == Etat.DIR.HAUT) {
			if (y >= deplacement) {
				y -= deplacement;
			} else {
				y = 0;
			}
		} else if (dir == Etat.DIR.BAS) {
			if (y < Affichage.HAUT - Affichage.HAUT/10 - deplacement - HAUT) {
				y += deplacement;
			} else {
				y = Affichage.HAUT - Affichage.HAUT/10 - HAUT - 2;
			}
		} else if (dir == Etat.DIR.DROITE) {
			if (x < Affichage.LARG - deplacement - LARG) {
				x += deplacement;
			} else {
				x = Affichage.LARG - LARG - 2;
			}
		} else if (dir == Etat.DIR.GAUCHE) {
			if (x >= deplacement) {
				x -= deplacement;
			} else {
				x = 0;
			}
		}
	}
	
	public void dessineVaisseau(Graphics2D g) {
		g.fillOval(x, y, LARG, HAUT);
	}
	
	public void perdVie() {
		vie--;
	}
}
