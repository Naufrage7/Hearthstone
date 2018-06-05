package capacite;

import exception.HearthstoneException;

public abstract class Capacite implements ICapacite {

	private String nom;
	private String description;

	/***
	 * Implémentation du constructeur
	 * 
	 * @param nom
	 * @param description
	 * 
	 */
	public Capacite(String nom, String description) {
		this.setNom(nom);
		this.setDescription(description);
	}

	/***
	 * Attribue le nom de la capacité
	 * 
	 * @param nom Le nom de la capacité
	 */
	private void setNom(String nom) {
		if (nom != null) {
			this.nom = nom;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Attribue la description de la capactie
	 * 
	 * @param description
	 */
	private void setDescription(String description) {
		if (description != null) {
			this.description = description;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retourne la description de la capacite
	 * 
	 * @return Retourne la description
	 */
	public String getDescription() {
		return this.description;
	}

	@Override
	public abstract void executerEffetDebutTour() throws HearthstoneException;

	@Override
	public abstract void executerEffetFinTour() throws HearthstoneException;

	@Override
	public abstract void executerAction(Object cible) throws HearthstoneException;

	@Override
	public abstract void executerEffetMiseEnJeu(Object cible) throws HearthstoneException;

	@Override
	public abstract void executerEffetDisparition(Object cible) throws HearthstoneException;
	
	@Override
	public abstract boolean seLanceSurServiteurProprietaire();

	public String toString() {
		return "Capacite [nom=" + this.nom + ", description=" + this.description + "]";
	}

}
