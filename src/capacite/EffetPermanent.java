package capacite;

import java.util.ArrayList;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class EffetPermanent extends Capacite {
	
	private int bonusAttaque;
	private int bonusVie;
	private ArrayList<Serviteur> serviteursAffectes;
	
	
	public EffetPermanent(String nom, int bonusAttaque, int bonusVie) {
		super(nom, "Effet permanent sur les autres serviteurs alliés donnant un bonus " + bonusAttaque + "/" + bonusVie);
		this.setBonusAttaque(bonusAttaque);
		this.setBonusVie(bonusVie);
		this.setServiteursAffectes(new ArrayList<Serviteur>());
	}
	
	private void setServiteursAffectes(ArrayList<Serviteur> serviteursAffectes) {
		this.serviteursAffectes = serviteursAffectes;
	}
	
	private void setBonusAttaque(int bonusAttaque) {
		this.bonusAttaque = bonusAttaque;
	}
	
	private void setBonusVie(int bonusVie) {
		this.bonusVie = bonusVie;
	}
	
	public int getBonusAttaque() {
		return this.bonusAttaque;
	}
	
	public int getBonusVie() {
		return this.bonusVie;
	}
	
	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
		executerAction(null);
	}
	
	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		executerAction(null);
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		executerAction(null);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( ICarte c : joueurCourant.getJeu() ) {
			if ( c instanceof Serviteur ) {
				Serviteur s = (Serviteur) c;
				if ( s.getCapacite() != this && !this.serviteursAffectes.contains(s) ) {
					s.setAttaque(s.getAttaque() + getBonusAttaque());
					s.setVie(s.getVie() + getBonusVie());
					this.serviteursAffectes.add(s);
				}
			}
		}
	}
	
	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( ICarte c : joueurCourant.getJeu() ) {
			if ( c instanceof Serviteur ) {
				Serviteur s = (Serviteur) c;
				if ( this.serviteursAffectes.contains(s) ) {
					s.setAttaque(s.getAttaque() - 1);
					s.setVie(s.getVie() - 1);
				}
			}
		}
	}
}
