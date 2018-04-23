package capacite;

import exception.HearthstoneException;

public class Capacite implements ICapacite {

	private String nom;
	private String description;

	public Capacite(String nom, String description) {
		this.setNom(nom);
		this.setDescription(description);
	}

	/**
	 * Getter et Setter
	 */

	private void setNom(String nom) {
		this.nom = nom;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public String getNom() {
		return this.nom;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub

	}

}
