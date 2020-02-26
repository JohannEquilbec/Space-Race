package modele;

import view.ThreadAff;

public class Etat {
	public static enum DIR {HAUT, BAS, GAUCHE, DROITE};
	public Vaisseau vaisseau = new Vaisseau(this);
	public Piste piste = new Piste(this);
	private ThreadAff affi;
	
	public void setThreadAff(ThreadAff Taffi) {
		affi = Taffi;
	}
	
	public void fly(DIR dir) {
		vaisseau.vole(dir);
		affi.redraw();
	}
	
	public boolean isPerdu() {
		return vaisseau.isDead();
	}
}
