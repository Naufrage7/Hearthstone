package plateau;

import java.util.ArrayList;

import exception.HearthstoneException;
import joueur.IJoueur;

public class Plateau implements IPlateau {

	private ArrayList<IJoueur> joueurs;
	private IJoueur joueurCourant;

	/**
	 * Constructeur de la classe
	 * 
	 * @param joueur
	 */
	public Plateau() {
		this.setJoueurs(new ArrayList<IJoueur>());
	}

	/**
	 * 
	 * @param joueur
	 */
	private void setJoueurs(ArrayList<IJoueur> joueurs) {
		if (joueurs != null) {
			this.joueurs = joueurs;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public ArrayList<IJoueur> getJoueurs() {
		return this.joueurs;
	}

	/**
	 * 
	 * @param IJoueur
	 */
	public void setJoueurCourant(IJoueur joueurs) throws HearthstoneException {
		if (joueurs.contains(o) == true) {
			this.joueurs = joueurs;
		}
		throw new HearthstoneException();
	}

	/**
	 * 
	 */
	public IJoueur getJoueurCourant() {
		return (IJoueur) this.joueurs;
	}

	/**
	 * Renvoie le joueur courant, c'est-à-dire celui qui a le tour et qui doit
	 * jouer
	 */
	public void ajouterJoueur(IJoueur joueurs) throws HearthstoneException {
	}

	@Override
	public IJoueur getAdversaire(IJoueur joueurs) throws HearthstoneException {
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
	public void finTour(IJoueur joueurs) throws HearthstoneException {
	}

	@Override
	public void gagnePartie(IJoueur joueurs) throws HearthstoneException {
	}

}
