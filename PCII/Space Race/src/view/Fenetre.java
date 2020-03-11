package view;

import java.awt.Color;

import javax.swing.JFrame;

/**
 * Crée une fenetre défaut
 */
public class Fenetre {
	/**
	 * Crée une fenetre par défaut
	 * @return renvoie la fenetre defaut crée
	 */
	public static JFrame newJFrame() {
		//Nouvelle fenetre
		JFrame fenetre = new JFrame("Race");
		//Rendre visible
		//fenetre.setUndecorated(true);
		//Définir comportement face à fermeture
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setBackground(Color.white);
		//Assembler
		fenetre.pack();
		fenetre.setVisible(true);
		return fenetre;
	}
}