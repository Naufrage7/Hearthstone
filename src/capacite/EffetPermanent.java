package capacite;

import java.util.ArrayList;

import carte.Carte;
import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class EffetPermanent extends Capacite {

	private int bonusAttaque;
	private int bonusVie;
	private ArrayList<IBonifiable> bonifiablesAffectes = new ArrayList<IBonifiable>();

	public EffetPermanent(String nom, String description, int bonusAttaque, int bonusVie) {
		super(nom, description);
		this.setBonusAttaque(bonusAttaque);
		this.setBonusVie(bonusVie);
	}

	private void setBonusAttaque(int bonusAttaque) {
		this.bonusAttaque = bonusAttaque;
	}

	private void setBonusVie(int bonusVie) {
		this.bonusVie = bonusVie;
	}

	protected int getBonusAttaque() {
		return this.bonusAttaque;
	}

	protected int getBonusVie() {
		return this.bonusVie;
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();

		for (ICarte carte : joueurCourant.getJeu()) {
			if (!(carte instanceof IBonifiable) || carte.getCapacite() == this)
				continue;
			
			IBonifiable bonifiable = (IBonifiable) carte;
			bonifiable.ajouterBonus(bonusVie, bonusAttaque);
		}
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();

		ICarte carte = joueurCourant.getJeu().get(joueurCourant.getJeu().size() - 1);
		if (!(carte instanceof IBonifiable) || carte.getCapacite() == this)
			return;

		IBonifiable bonifiable = (IBonifiable) carte;
		bonifiable.ajouterBonus(bonusVie, bonusAttaque);
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();

		for (ICarte carte : joueurCourant.getJeu()) {
			if (!(carte instanceof IBonifiable))
				continue;

			IBonifiable bonifiable = (IBonifiable) carte;
			if (this.bonifiablesAffectes.contains(bonifiable))
				bonifiable.retirerBonus(bonusVie, bonusAttaque);
		}
	}

	@Override
	public boolean necessiteCible() {
		return false;
	}
}
