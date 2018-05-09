package heros;

import exception.HearthstoneException;
import exception.MortException;
import capacite.Capacite;
import cible.ICible;

public class Heros implements ICible {

	private String nom;
	private int vie;
	private Capacite pouvoir;

	/**
	 * Construit un héros à partir de son nom et de sa vie
	 * 
	 * @param nom le nom du héros
	 * @param vie la vie du héros
	 */
	public Heros(String nom, int vie, Capacite pouvoir) {
		this.setNom(nom);
		this.setVie(vie);
		this.setPouvoir(pouvoir);
	}

	/**
	 * Attribue le nom au héros
	 * 
	 * @param nom le nom du héros
	 */
	private void setNom(String nom) {
		if (nom == null)
			throw new IllegalArgumentException("Le nom du héros ne peut pas être nul !");
		this.nom = nom;
	}

	/**
	 * Attribue la vie au héros
	 * 
	 * @param vie la vie du héros
	 */
	private void setVie(int vie) {
		if (vie < 0)
			this.vie = 0;
		else
			this.vie = vie;
	}
	
	/**
	 * Attribue le pouvoir au héros
	 * 
	 * @param pouvoir le pouvoir du héros
	 */
	private void setPouvoir(Capacite pouvoir) {
		if ( pouvoir == null )
			throw new IllegalArgumentException("Le pouvoir ne peut pas être nul !");
		this.pouvoir = pouvoir;
	}

	/**
	 * Retourne le nom du héros
	 * 
	 * @return le nom du héros
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retourne la vie du héros
	 * 
	 * @return la vie du héros
	 */
	public int getVie() {
		return this.vie;
	}
	
	/**
	 * Retourne le pouvoir du héros
	 * @return le pouvoir du héros
	 */
	public Capacite getPouvoir() {
		return this.pouvoir;
	}

	/**
	 * Occasionne des dégats au héros
	 * 
	 * @param degats le nombre de dégats à occasionner
	 * @throws MortException
	 */
	public void recevoirDegats(int degats) throws HearthstoneException {
		this.setVie(this.getVie() - degats);
	}
	
	public String toString() {
		return "Heros [nom="+this.nom+", vie="+this.vie+", pouvoir="+this.pouvoir+"]";
	}

}
