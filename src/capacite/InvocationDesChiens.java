package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class InvocationDesChiens extends Capacite {
	public InvocationDesChiens() {
		super("Invocation des chiens", "C'est une capacité propre à Rexxar, de la famille des invocations de serviteurs. Lorsqu'une carte possédant cette capacité est mise en jeu, des serviteurs chiens sont créés. Il y a autant de chiens que le joueur adverse a de serviteurs. Un chien est un serviteur +1/+1 avec \"Charge\"");
	}
	
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		IJoueur joueurAdverse = plateau.getAdversaire(joueurCourant);
		
		for ( ICarte c : joueurAdverse.getJeu() ) {
			joueurCourant.getJeu().add(new Serviteur(1, 1, "Chien de Rexxar", 0, new Charge(), joueurCourant));
		}
	}
}
