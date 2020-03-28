package view;

import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * Cr�e une fenetre d�faut
 */
public class Fenetre {

	/**
	 * Cr�e une fenetre par d�faut
	 * @return renvoie la fenetre defaut cr�e {@link JFrame}
	 */
	public static JFrame newJFrame() {
		//Nouvelle fenetre
		JFrame fenetre = new JFrame("Race");
		
		//Vrai plein-�cran
		if (Affichage.fullscreen) {
			fenetre.setUndecorated(true);
		}
		
		//Cache le curseur
		fenetre.setCursor(fenetre.getToolkit().createCustomCursor(
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB),
				new Point(), null));
		
		//D�finir comportement face � fermeture
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Assembler
		fenetre.pack();
		//Rendre visible
		fenetre.setVisible(true);
		return fenetre;
	}
}