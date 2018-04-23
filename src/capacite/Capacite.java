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

	/***
	 * Getter et Setter
	 * 
	 * @param nom
	 * @param description
	 * @throws IllegalArgumentException
	 */
	private void setNom(String nom) {
		if (nom != null) {
			this.nom = nom;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setDescription(String description) {
		if (description != null) {
			this.description = description;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getNom() {
		return this.nom;
	}

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
		return "Capacite [nom="+this.nom+", description="+this.description+"]";
	}
}
