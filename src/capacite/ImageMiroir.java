package capacite;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class ImageMiroir extends Capacite {
	private Serviteur serviteur;
	
	public ImageMiroir(String nom, Serviteur serviteur) {
		super(nom, "Invoque 2 fois un serviteur");
		this.setServiteur(serviteur);
	}
	
	private void setServiteur(Serviteur serviteur) {
		this.serviteur = serviteur;
	}
	
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( int i = 0; i < 2; i++ )
			joueurCourant.getJeu().add(new Serviteur(serviteur));
	}
}
