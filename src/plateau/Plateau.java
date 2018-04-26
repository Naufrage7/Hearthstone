package plateau;

import java.util.ArrayList;

import exception.HearthstoneException;
import joueur.IJoueur;

public class Plateau implements IPlateau {

	private static Plateau INSTANCE = new Plateau();
	private ArrayList<IJoueur> listeJoueurs;
	private IJoueur joueurCourant;
	private boolean partieDemarree;

	/**
	 * Instancie un nouveau Plateau
	 */
	private Plateau() {
		this.setListeJoueurs(new ArrayList<IJoueur>());
		this.setPartieDemarree(false);
	}
	
	public static Plateau getInstance()
    {
		return INSTANCE;
    }

	/**
	 * Attribue l'état de la partie
	 * 
	 * @param partieDemarree
	 *            l'état de la partie
	 */
	private void setPartieDemarree(boolean partieDemarree) {
		this.partieDemarree = partieDemarree;
	}

	/**
	 * Attribue la liste de joueurs
	 * 
	 * @param listeJoueurs
	 *            la liste de joueurs
	 */
	private void setListeJoueurs(ArrayList<IJoueur> listeJoueurs) {
		if (listeJoueurs != null) {
			this.listeJoueurs = listeJoueurs;
		} else {
			throw new IllegalArgumentException("La liste de joueurs ne peut pas être nulle !");
		}
	}

	/**
	 * Retourne la liste des joueurs
	 * 
	 * @return la liste des joueurs
	 */
	public ArrayList<IJoueur> getJoueurs() {
		return this.listeJoueurs;
	}

	@Override
	public void setJoueurCourant(IJoueur joueurCourant) throws HearthstoneException {
		if (!this.listeJoueurs.contains(joueurCourant))
			throw new HearthstoneException("Le joueur courant doit faire partie de la liste des joueurs du plateau !");

		this.joueurCourant = joueurCourant;
	}

	@Override
	public IJoueur getJoueurCourant() {
		return this.joueurCourant;
	}

	@Override
	public void ajouterJoueur(IJoueur joueur) throws HearthstoneException {
		if (this.listeJoueurs.size() >= 2)
			throw new HearthstoneException("Il ne peut y avoir que 2 joueurs sur un plateau !");

		this.listeJoueurs.add(joueur);
	}

	@Override
	public IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException {
		if (this.listeJoueurs.size() != 2)
			throw new HearthstoneException("Il faut deux joueurs pour retourner l'adversaire d'un joueur !");

		IJoueur adversaire = this.listeJoueurs.get(0);
		if (adversaire == this.getJoueurCourant())
			adversaire = this.listeJoueurs.get(1);

		return adversaire;
	}

	@Override
	public void demarrerPartie() throws HearthstoneException {
		if (this.listeJoueurs.size() != 2)
			throw new HearthstoneException("Il faut 2 joueurs pour démarrer une partie !");

		this.setPartieDemarree(true);
		this.setJoueurCourant(this.listeJoueurs.get(0));
		this.joueurCourant.prendreTour();
	}

	@Override
	public boolean estDemarree() {
		return this.partieDemarree;
	}

	@Override
	public void finTour(IJoueur joueur) throws HearthstoneException {
		this.joueurCourant.finirTour();
		this.setJoueurCourant(this.getAdversaire(joueur));
		this.joueurCourant.prendreTour();
	}

	@Override
	public void gagnePartie(IJoueur joueur) throws HearthstoneException {
	}

	/**
	 * Fonction toString de Plateau
	 * 
	 * @return retourne la liste des joueurs , le joueur courant et si la partis
	 *         et demarree
	 */
	public String toString() {
		return "Plateau [listeJoueurs=" + this.listeJoueurs + ", joueurCourant=" + this.joueurCourant
				+ ", partieDemarree=" + this.partieDemarree + "]";
	}

}
