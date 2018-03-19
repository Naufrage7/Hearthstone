package jeu.capacites;

public class CapaciteInvocationServiteurs implements ICapacite {

	boolean estUtilisee;
	
	public CapaciteInvocationServiteurs() {
		this.estUtilisee = false;
	}
	
	@Override
	public void executerAction(Object cible) {
		if ( !this.estUtilisee ) {
			this.estUtilisee = true;
			// Invoquer les serviteurs ici
		}
	}

	@Override
	public void executerEffetDebutTour() {
		// Rien à faire
	}

	@Override
	public void executerEffetDisparition() {
		// Rien à faire
	}

	@Override
	public void executerEffetFinTour() {
		// Rien à faire
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) {
		this.executerAction(cible);
	}

	@Override
	public String getDescription() {
		return "Invoque un serviteur 2/2";
	}

	@Override
	public String getNom() {
		return "Invocation de serviteurs";
	}
	

}
