package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import modele.Etat;

public class Affichage extends JPanel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//public final static int LARG = screenSize.width;
    //public final static int HAUT = screenSize.height;
    public final static int LARG = 1000;
    public final static int HAUT = 1000;
    
    private Etat etat;
	
    public Affichage(Etat et) {
    	setPreferredSize(new Dimension(LARG, HAUT));
    	etat = et;
    }
    
    public void background(Graphics2D g) {
    	g.clearRect(0, 0, LARG, HAUT/3);
    	g.setColor(new Color(50,200,250,100));
    	g.fillRect(0, 0, LARG, HAUT/3);
    	g.setColor(Color.BLACK);
    }
    
    public void etatTexte(Graphics2D g) {
    	g.setFont(new Font("TimesRoman", Font.PLAIN+1, (LARG+HAUT)/100)); 
    	g.drawString("Score : "+Integer.toString(etat.piste.getPosition()/100), 20, 40);
    	g.drawString("Temps : "+Integer.toString(etat.vaisseau.vie), LARG - (LARG+HAUT)/13, 40);
    	g.drawString(Integer.toString(etat.vaisseau.vitesse)+" km/h", 20, HAUT - (LARG+HAUT)/30);
    }
    
    public void gameOver(Graphics2D g) {
    	g.setFont(new Font("TimesRoman", Font.PLAIN, (LARG+HAUT)/30)); 
		g.drawString("GAME OVER", LARG/2-(LARG+HAUT)/9, HAUT/3);
		g.setFont(new Font("TimesRoman", Font.PLAIN, (LARG+HAUT)/40));
		g.drawString("Score : "+Integer.toString(etat.piste.getPosition()/100), LARG/2-(LARG+HAUT)/15, HAUT/3+(LARG+HAUT)/20);
    }
    
    public void attentionCheckpoint(Graphics2D g) {
    	g.setFont(new Font("TimesRoman", Font.PLAIN + 1, (LARG + HAUT) / 100));
    	g.drawString("CHECKPOINT !", LARG / 2, (LARG + HAUT) / 25);
    }
    
    @Override
	public void paint(Graphics g2) {
    	super.paint(g2);
    	Graphics2D g = (Graphics2D)g2;
    	
    	//Sol
    	g.setColor(new Color(50, 200, 50));
    	g.fillRect(0, HAUT/3, LARG, HAUT);
    	
    	etat.piste.dessinePiste(g);
    	// etat.piste.dessineCheckpoint(g);
    	
    	background(g);
    	etatTexte(g);
    	if (etat.piste.afficheMessage == true) {
    		attentionCheckpoint(g);
    	}
    	
    	//Horizon
    	g.setStroke(new BasicStroke(1));
    	g.drawLine(0, HAUT/3, LARG, HAUT/3);
    	
    	if (etat.piste.distanceCheckpoint >= etat.piste.prochainCheckpoint) {
    		etat.piste.debutCheckpoint(g); // Cree la ligne a l'horizon
    		etat.piste.prochainCheckpoint += etat.piste.distanceCheckpoint + etat.piste.valDistanceCheckpoint * etat.piste.multDistance;
		}
    	
    	etat.vaisseau.dessineVaisseau(g);
    	
    	if (etat.isPerdu()) {
    		gameOver(g);
    	}
    }
}
