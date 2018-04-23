package plateau;

import java.util.ArrayList;

import exception.HearthstoneException;
import joueur.IJoueur;

public class Plateau implements IPlateau {

	private ArrayList<IJoueur> joueur;

	/**
	 * Constructeur de la classe
	 * 
	 * @param joueur
	 */
	public Plateau(ArrayList<IJoueur> joueur) {
		this.setJoueur(new ArrayList<IJoueur>());
	}

	/**
	 * 
	 * @param joueur
	 */
	private void setJoueur(ArrayList<IJoueur> joueur) {
		if (joueur != null) {
			this.joueur = joueur;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public ArrayList<IJoueur> getJoueur() {
		return this.joueur;
	}

	/**
	 * Renvois l'adversaire du joueur courant
	 * 
	 * @param IJoueur
	 */
	public void setJoueurCourant(IJoueur joueur) throws HearthstoneException {
		if (joueur != null) {
			this.joueur = joueur;
		}
		throw new HearthstoneException();
	}

	/**
	 * 
	 */
	public IJoueur getJoueurCourant() {
		return (IJoueur) this.joueur;
	}

	/**
	 * Renvoie le joueur courant, c'est-à-dire celui qui a le tour et qui doit
	 * jouer
	 */
	public void ajouterJoueur(IJoueur joueur) throws HearthstoneException {
	}

	@Override
	public IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException {
		return null;
	}

	@Override
	public void demarrerPartie() throws HearthstoneException {
	}

	@Override
	public boolean estDemarree() {
		return false;
	}

	@Override
	public void finTour(IJoueur joueur) throws HearthstoneException {
	}

	@Override
	public void gagnePartie(IJoueur joueur) throws HearthstoneException {
	}

}
