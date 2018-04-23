package carte;

import exception.HearthstoneException;
import joueur.IJoueur;

public class Carte implements ICarte {
	
	private String nom;
	private int cout;
	
	public Carte(String nom, int cout) {
		this.setNom(nom);
		this.setCout(cout);
	}

	private void setNom(String nom) {
		if ( nom == null )
			throw new IllegalArgumentException("Le nom de la carte ne peut pas être nul !");
		this.nom = nom;
	}
	
	private void setCout(int cout) {
		if ( cout < 0 )
			throw new IllegalArgumentException("Le cout de la carte ne peut pas être une valeur négative !");
		this.cout = cout;
	}
	
	@Override
	public String getNom() {
		return this.nom;
	}
	
	@Override
	public int getCout() {
		return this.cout;
	}

	@Override
	public IJoueur getProprietaire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executerEffetDebutTour(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean disparait() {
		// TODO Auto-generated method stub
		return false;
	}

}
