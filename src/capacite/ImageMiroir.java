package capacite;
import java.util.ArrayList;

import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import plateau.IPlateau;
import plateau.Plateau;

public class ImageMiroir extends Capacite {
	private ArrayList<Serviteur> serviteurs;
	
	public ImageMiroir(String nom, String description) {
		super(nom, description);
		this.setServiteurs(new ArrayList<Serviteur>());
	}
	
	private void setServiteurs(ArrayList<Serviteur> serviteurs) {
		if ( serviteurs == null )
			throw new IllegalArgumentException("La liste de serviteurs ne peut pas être nulle.");
		
		this.serviteurs = serviteurs;
	}
	
	public void ajouterServiteur(Serviteur serviteur) {
		if ( serviteur == null )
			throw new IllegalArgumentException("Le serviteur ne peut pas être nul !");

		this.serviteurs.add(serviteur);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		IPlateau plateau = Plateau.getInstance();
		IJoueur joueurCourant = plateau.getJoueurCourant();
		
		for ( Serviteur serviteur : serviteurs )
			joueurCourant.getJeu().add(new Serviteur(serviteur));
	}
}
