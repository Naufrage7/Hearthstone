package carte;

import capacite.Capacite;
import capacite.IBonifiable;
import capacite.ITemporisable;
import capacite.Provocation;
import exception.CibleInvalideException;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class Serviteur extends Carte implements ICible, IBonifiable, ITemporisable {

	private int vie;
	private int attaque;
	private boolean peutJouer;

	/**
	 * Constructeur de la classe Serviteur
	 * 
	 * @param attaque
	 *            l'attaque du serviteur
	 * @param vie
	 *            la vie du serviteur
	 * @param nom
	 *            le nom du serviteur
	 * @param cout
	 *            le cout du serviteur
	 * @param capacite
	 *            la capacité du serviteur
	 * @param proprietaire
	 *            le propriétaire du serviteur
	 */
	public Serviteur(int attaque, int vie, String nom, int cout, Capacite capacite, IJoueur proprietaire) {
		super(nom, cout, capacite, proprietaire);
		this.setAttaque(attaque);
		this.setVie(vie);
		this.setPeutJouer(false);
	}

	/**
	 * Attribue l'attaque du serviteur
	 * 
	 * @param attaque
	 *            l'attaque du serviteur
	 */
	public void setAttaque(int attaque) {
		if (attaque < 0)
			throw new IllegalArgumentException("L'attaque du serviteur doit être une valeur positive !");

		this.attaque = attaque;
	}

	/**
	 * Attribue la vie du serviteur
	 * 
	 * @param vie
	 *            la vie du serviteur
	 */
	public void setVie(int vie) {
		if (vie >= 0)
			this.vie = vie;
		else
			this.vie = 0;
	}

	/**
	 * Attribue la possibilite d'attaquer pour un serviteur
	 */
	public void setPeutJouer(boolean peutJouer) {
		this.peutJouer = peutJouer;
	}

	/**
	 * Retourne l'attaque du serviteur
	 * 
	 * @return attaque l'attaque du serviteur
	 */
	public int getAttaque() {
		return attaque;
	}

	/**
	 * Retourne la vie du serviteur
	 * 
	 * @return vie la vie du serviteur
	 */
	public int getVie() {
		return this.vie;
	}

	/**
	 * Retourne la possibilite au serviteur d'attaquer
	 * 
	 * @return la possibilite au serviteur d'attaquer
	 */
	public boolean peutJouer() {
		return this.peutJouer;
	}

	public boolean disparait() {
		return this.vie <= 0;
	}

	public void attaquer(Object cible) throws HearthstoneException {
		if (!(cible instanceof ICible))
			throw new CibleInvalideException("La cible ne peut pas être nulle !");

		ICible cibleVisee = (ICible) cible;
		if (cibleVisee.peutRecevoirDegats()) {
			cibleVisee.recevoirDegats(attaque);
			setVie(getVie() - cibleVisee.getVie());
		} else {
			throw new CibleInvalideException("La cible ne peut pas recevoir de dégats !");
		}
	}

	@Override
	public void recevoirDegats(int degats) {
		this.setVie(this.getVie() - degats);
		if ( disparait() ) {
			Plateau.getInstance().getJoueurCourant().getJeu().remove(this);
			try {
				Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).getJeu().remove(this);
			} catch (HearthstoneException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException {
		super.executerEffetDebutMiseEnJeu(cible);

		if ( super.getCapacite() != null ) {
			if ( super.getCapacite().seLanceSurServiteurProprietaire() )
				super.getCapacite().executerEffetMiseEnJeu(this);
			else
				super.getCapacite().executerEffetMiseEnJeu(cible);
		}
	}

	@Override
	public void ajouterBonus(int bonusVie, int bonusAttaque) {
		setVie(getVie() + bonusVie);
		setAttaque(getAttaque() + bonusAttaque);
	}

	@Override
	public void retirerBonus(int bonusVie, int bonusAttaque) {
		setVie(getVie() - bonusVie);
		setAttaque(getAttaque() - bonusAttaque);
	}

	@Override
	public boolean peutRecevoirDegats() {
		if (super.getCapacite() instanceof Provocation)
			return true;

		IJoueur joueurProprietaire = super.getProprietaire();

		boolean provocationSurLeTerrain = false;
		for (ICarte carte : joueurProprietaire.getJeu()) {
			if (carte.getCapacite() instanceof Provocation) {
				provocationSurLeTerrain = true;
				break;
			}
		}

		return !provocationSurLeTerrain;
	}

	/**
	 * Retourne le toString de Serviteur
	 * 
	 * @return le string de l'attaque, la vie , le nom , le cout et le propriétaire
	 */
	public String toString() {
		String toString = "";
		toString += this.getNom() + " " + this.getAttaque() + "/" + this.getVie();
		if (this.getCapacite() != null)
			toString += " ( " + this.getCapacite().getNom() + " : " + this.getCapacite().getDescription() + " )";
		else
			toString += " ( Sans capacité )";
		return toString;
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		attaquer(cible);		
	}

	@Override
	public void executerEffetDebutTour(Object cible) throws HearthstoneException {
		if ( super.getCapacite() != null )
			super.getCapacite().executerEffetDebutTour();
	}

	@Override
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		if ( super.getCapacite() != null )
			super.getCapacite().executerEffetFinTour();
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		if ( super.getCapacite() != null )
			super.getCapacite().executerEffetDisparition(cible);
	}
	
}
