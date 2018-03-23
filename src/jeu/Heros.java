package jeu;

import jeu.capacites.ICapacite;

public class Heros implements IPersonnage {
	
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
			throw new IllegalArgumentException();
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
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Attribue la vie au héros
	 * @param vie
	 */
	private void setVie(int vie) {
		if ( vie >= 0 ) {
			this.vie = vie;
		} else {
			throw new IllegalArgumentException();
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
	 * Attaque et occasionne des dégats au personnage ciblé et à l'attaquant ( si l'adversaire dispose de points d'attaque )
	 * @param degats
	 */
	public void attaquer(IPersonnage personnage) {
		personnage.recevoirDegats(this.getDegats());
		this.recevoirDegats(personnage.getDegats());
	}
	
	/**
	 * Retourne le nombre de dégats du héros
	 * @return
	 */
	public int getDegats() {
		return 0;
	}
	
	/**
	 * Réduit la vie du héros en lui infligeant des dégats
	 */
	public void recevoirDegats(int degats) {
		int vie = this.getVie() - degats;
		if ( vie >= 0 ) {
			this.setVie(vie);
		} else {
			this.setVie(0);
		}
	}
	
	/**
	 * Utilise le pouvoir du héros sur une cible
	 * @param cible
	 * @throws HearthstoneException 
	 */
	public void utiliserPouvoir(Object cible) throws HearthstoneException {
		this.capacite.executerAction(cible);
	}

}
