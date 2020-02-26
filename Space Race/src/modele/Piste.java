package modele;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import view.Affichage;

public class Piste {
	private ArrayList<Point> ligne = new ArrayList<Point>();
	private static final Random rand = new Random();
	private int x_current = Affichage.LARG/2;
	private int y_current = Affichage.HAUT;
	private int position = 0;
	private Etat etat;
	private int longueur_segment = 300;
	private int tournant = 200;
	
	/**
	 * Crée la ligne aléatoirement
	 */
	public Piste(Etat et) {
		etat = et;
		//addPointZero();
		for (int i=0; i < 20; i++) {
			addPoint();
		}
	}
	
	/**
	 * Ajoute un point à la ligne
	 */
	private void addPoint() {
		x_current += rand.nextInt(tournant) - tournant/2;
		if (x_current < 50) {
			x_current=50;
		} else if (x_current > Affichage.LARG - 100) {
			x_current= Affichage.LARG - 100;
		}
		ligne.add(new Point(x_current, y_current));
		y_current -= rand.nextInt(longueur_segment/3) + longueur_segment;
	}
	
	/*private void addPointZero() {
		rand.nextInt();
		ligne.add(new Point(Affichage.LARG/2, Affichage.HAUT));
		ligne.add(new Point(Affichage.LARG/2, Affichage.HAUT/2));
		y_current -= Affichage.HAUT/2 + rand.nextInt(25)+75;
	}*/
	
	/**
	 * Revoie les points visibles dans la fenetre et les avancent suivant la position
	 * @return les points visibles dans la fenetre
	 */
	public ArrayList<Point> getParcours() {
		ArrayList<Point> ligne_visible = new ArrayList<Point>();
		for (int i=0; i<ligne.size(); i++) {
			if (ligne.get(i).y + position < Affichage.HAUT*2) {
				ligne_visible.add(new Point(ligne.get(i).x, ligne.get(i).y + position));
				if (ligne.get(i).y + position < 0) {
					return ligne_visible;
				}
				if (i==ligne.size()-1) {
					addPoint();
				}
			} else {
				ligne.remove(i);
			}
		}
		return ligne_visible;
	}
	
	/**
	 * Getter de la position
	 * @return position
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Avance la postion du parcours
	 */
	public void avance() {
		this.position += etat.vaisseau.vitesse/10;
	}
	
	/**
	 * Affiche le parcours
	 * @param g les Graphics
	 */
	public void dessinePiste(Graphics2D g) {
		ArrayList<Point> parcours = etat.piste.getParcours();
		for (int i=0; i < parcours.size()-1; i++) {
			g.setStroke(new BasicStroke(4));
			g.setColor(Color.BLACK);
			int x_decale_haut = parcours.get(i).x + Affichage.LARG/100 + (parcours.get(i).y - Affichage.HAUT/3)/2;
			int x_decale_bas = parcours.get(i+1).x + Affichage.LARG/100 + (parcours.get(i+1).y - Affichage.HAUT/3)/2;
			
			g.drawLine(parcours.get(i).x, parcours.get(i).y , parcours.get(i+1).x, parcours.get(i+1).y);
			g.drawLine(x_decale_haut, parcours.get(i).y, x_decale_bas, parcours.get(i+1).y);
			
			g.setColor(Color.LIGHT_GRAY);
			g.setStroke(new BasicStroke(1));
			int[] x = {parcours.get(i).x, parcours.get(i+1).x, x_decale_bas, x_decale_haut};
			int[] y = {parcours.get(i).y, parcours.get(i+1).y, parcours.get(i+1).y, parcours.get(i).y};
			g.fillPolygon(x, y, 4);
			
			/*g.setStroke(new BasicStroke(6));
			g.setColor(Color.WHITE);
			g.drawLine((parcours.get(i).x + x_decale_haut)/2 + (parcours.get(i+1).y - Affichage.HAUT)/300, parcours.get(i).y - 100, (parcours.get(i+1).x + x_decale_bas)/2 - (parcours.get(i+1).y - Affichage.HAUT)/300, parcours.get(i+1).y + 100);
			*/
		}
	}
	
	public int distancePisteX(int x) {
		ArrayList<Point> par = getParcours();
		for (int i=1; i < par.size(); i++) {
			if (par.get(i).y < Affichage.HAUT + Affichage.HAUT/10) {
				//(xB-xA) / (xPoint-xA) x (yB-yA) + yB
				//System.out.println(Math.abs(par.get(i).x - par.get(i-1).x) / (par.get(i).x/x));
				//return Math.abs(par.get(i).x - par.get(i-1).x) * (par.get(i-1).y - par.get(i).y) / (x / par.get(i).x);
				return 1;
			}
		}
		return -1;
	}
	
	public int distancePiste(int x, int y) {
		
		return (y/Affichage.HAUT);
	}
}
