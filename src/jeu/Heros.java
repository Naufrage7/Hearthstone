package jeu;

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
		if (nom != null) {
			this.nom = nom;
		} else {
			// Exception
		}
	}

	/**
	 * Attribue la vie au héros
	 * 
	 * @param vie
	 */
	private void setVie(int vie) {
		if (vie < 0) {
			this.vie = 0;
		} else {
			this.vie = vie;
		}
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
	 */
	public void recevoirDegats(int degats) {
		this.setVie(this.getVie() - degats);
	}

}
