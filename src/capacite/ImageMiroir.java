package capacite;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class ImageMiroir extends Capacite {
	public ImageMiroir() {
		super("Image miroir", "C'est aussi une capacité de la famille des invocations de serviteurs. Elle est propre à Jaina. La carte possédant cette capacité invoque automatiquement, au début du tour, deux serviteurs 0/+2 avec la capacité \"Provocation\"");
	}
	
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( int i = 0; i < 2; i++ )
			joueurCourant.getJeu().add(new Serviteur(0, 2, "L'ombre miroir", 0, new Provocation(), joueurCourant));
	}
}
