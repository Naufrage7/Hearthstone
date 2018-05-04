package carte;

import capacite.Capacite;
import joueur.IJoueur;

public class Serviteur extends Carte {

	private int attaque;
	private int vie;
	private boolean peutAttaquer;

	/**
	 * Constructeur de la classe Serviteur
	 * 
	 * @param attaque
	 *            l'attaque du serviteur
	 * @param vie
	 *            la vie du serviteur
	 * @param nom
	 *            le nom du serviteur
	 * @param cout
	 *            le cout du serviteur
	 * @param proprietaire
	 *            le propriétaire du serviteur
	 */
	public Serviteur(int attaque, int vie, String nom, int cout, IJoueur proprietaire) {
		super(nom, cout, proprietaire);
		this.setAttaque(attaque);
		this.setVie(vie);
		this.setPeutAttaquer(false);
	}
	
	/**
	 * Constructeur de la classe Serviteur
	 * 
	 * @param attaque
	 *            l'attaque du serviteur
	 * @param vie
	 *            la vie du serviteur
	 * @param nom
	 *            le nom du serviteur
	 * @param cout
	 *            le cout du serviteur
	 * @param capacite
	 * 			  la capacité du serviteur
	 * @param proprietaire
	 *            le propriétaire du serviteur
	 */
	public Serviteur(int attaque, int vie, String nom, int cout, Capacite capacite, IJoueur proprietaire) {
		super(nom, cout, capacite, proprietaire);
		this.setAttaque(attaque);
		this.setVie(vie);
		this.setPeutAttaquer(false);
	}


	public Serviteur(Serviteur serviteur) {
		super(serviteur.getNom(), serviteur.getCout(), new Capacite(serviteur.getCapacite()), serviteur.getProprietaire());
		this.setAttaque(serviteur.getAttaque());
		this.setVie(serviteur.getVie());
		this.setPeutAttaquer(false);
	}

	/**
	 * Attribue l'attaque du serviteur
	 * 
	 * @param attaque
	 *            l'attaque du serviteur
	 */
	public void setAttaque(int attaque) {
		if (attaque >= 0) {
			this.attaque = attaque;
		} else {
			throw new IllegalArgumentException("L'attaque du serviteur ne peut pas être une valeur négative !");
		}
	}

	/**
	 * Attribue la vie du serviteur
	 * 
	 * @param vie
	 *            la vie du serviteur
	 */
	public void setVie(int vie) {
		if (vie >= 0) {
			this.vie = vie;
		} else {
			throw new IllegalArgumentException("La vie du serviteur ne peut pas être une valeur négative !");
		}
	}

	/**
	 * Attribue la possibilite d'attaquer pour un serviteur
	 */
	public void setPeutAttaquer(boolean peutAttaquer) {
		this.peutAttaquer = peutAttaquer;
	}

	/**
	 * Retourne l'attaque du serviteur
	 * 
	 * @return attaque l'attaque du serviteur
	 */
	public int getAttaque() {
		return this.attaque;
	}

	/**
	 * Retourne la vie du serviteur
	 * 
	 * @return vie la vie du serviteur
	 */
	public int getVie() {
		return this.vie;
	}

	/**
	 * Retourne la possibilite au serviteur d'attaquer
	 * 
	 * @return la possibilite au serviteur d'attaquer
	 */
	public boolean peutAttaquer() {
		return this.peutAttaquer;
	}

	/**
	 * Retourne le toString de Serviteur
	 * 
	 * @return le string de l'attaque, la vie , le nom , le cout et le
	 *         propriétaire
	 */
	public String toString() {
		return super.toString() + " -> Serviteur [attaque=" + this.attaque + ", vie=" + this.vie + "]";
	}
}
