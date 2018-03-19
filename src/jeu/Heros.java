package jeu;

import jeu.capacites.ICapacite;

public class Heros implements ICible {
	
	private String nom;
	private int vie;
	private ICapacite capacite;
	
	/**
	 * Construit un héros à partir de son nom et de sa vie
	 * @param nom
	 * @param vie
	 */
	public Heros(String nom, int vie, ICapacite capacite) {
		this.setNom(nom);
		this.setVie(vie);
		this.setCapacite(capacite);
	}
	
	/**
	 * Attribue une capacité au héros
	 * @param capacite
	 */
	private void setCapacite(ICapacite capacite) {
		if ( capacite != null ) {
			this.capacite = capacite;
		} else {
			// Exception
		}
	}
	
	/**
	 * Retourne la capacité du héros
	 * @return
	 */
	public ICapacite getCapacite() {
		return this.capacite;
	}

	/**
	 * Attribue le nom au héros
	 * @param nom
	 */
	private void setNom(String nom) {
		if ( nom != null ) {
			this.nom = nom;
		} else {
			// Exception
		}
	}

	/**
	 * Attribue la vie au héros
	 * @param vie
	 */
	private void setVie(int vie) {
		if ( vie < 0 ) {
			this.vie = 0;
		} else {
			this.vie = vie;
		}
	}
	
	/**
	 * Retourne le nom du héros
	 * @return
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Retourne la vie du héros
	 * @return
	 */
	public int getVie() {
		return this.vie;
	}
	
	/**
	 * Occasionne des dégats au héros
	 * @param degats
	 */
	public void recevoirDegats(int degats) {
		this.setVie(this.getVie() - degats);
	}
	
	public void utiliserPouvoir(Object cible) {
		this.capacite.executerAction(cible);
	}

}
