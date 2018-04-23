package carte;

import joueur.IJoueur;

public class Serviteur extends Carte {

	private int attaque;
	private int vie;

	/**
	 * Constructeur de la classe Serviteur
	 * 
	 * @param attaque
	 * @param vie
	 * @param nom
	 * @param cout
	 * @param proprietaire
	 */
	public Serviteur(int attaque, int vie, String nom, int cout, IJoueur proprietaire) {
		super(nom, cout, proprietaire);
		this.setAttaque(attaque);
		this.setVie(vie);
	}

	/**
	 * Test si l'attaque n'est pas égale à 0
	 * 
	 * @param attaque
	 */
	public void setAttaque(int attaque) {
		if (attaque >= 0) {
			this.attaque = attaque;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Test si la vie n'est pas égale à 0
	 * 
	 * @param pv
	 */
	public void setVie(int vie) {
		if (vie > 0) {
			this.vie = vie;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 
	 * @return attaque
	 */
	public int getAttaque() {
		return this.attaque;
	}

	/**
	 * 
	 * @return vie
	 */
	public int getVie() {
		return this.vie;
	}
}
