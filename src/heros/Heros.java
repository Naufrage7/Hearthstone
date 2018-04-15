package heros;

import exception.HearthstoneException;
import exception.MortException;
import jeu.ICible;

public class Heros implements ICible {

	private String nom;
	private int vie;

	/**
	 * Construit un héros à partir de son nom et de sa vie
	 * 
	 * @param nom
	 * @param vie
	 */
	public Heros(String nom, int vie) {
		this.setNom(nom);
		this.setVie(vie);
	}

	/**
	 * Attribue le nom au héros
	 * 
	 * @param nom
	 */
	private void setNom(String nom) {
		if ( nom == null ) throw new IllegalArgumentException("Le nom du héros ne peut pas être nul !");
		this.nom = nom;
	}

	/**
	 * Attribue la vie au héros
	 * 
	 * @param vie
	 */
	private void setVie(int vie) {
		if ( vie < 0 ) throw new IllegalArgumentException("La vie du héros ne peut pas être une valeur négative !");
		this.vie = vie;
	}

	/**
	 * Retourne le nom du héros
	 * 
	 * @return
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retourne la vie du héros
	 * 
	 * @return
	 */
	public int getVie() {
		return this.vie;
	}

	/**
	 * Occasionne des dégats au héros
	 * 
	 * @param degats
	 * @throws MortException 
	 */
	public void recevoirDegats(int degats) throws HearthstoneException {
		if ( this.getVie() - degats <= 0 ) throw new MortException();
		this.setVie(this.getVie() - degats);
	}

}
