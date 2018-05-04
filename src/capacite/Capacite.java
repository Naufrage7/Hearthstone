package capacite;

import exception.HearthstoneException;

public class Capacite implements ICapacite {

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

	public Capacite(Capacite capacite) {
		this.setNom(capacite.getNom());
		this.setDescription(capacite.getDescription());
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
	public void executerEffetDebutTour() throws HearthstoneException {
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
	}

	public String toString() {
		return "Capacite [nom=" + this.nom + ", description=" + this.description + "]";
	}
}
