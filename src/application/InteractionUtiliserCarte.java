package application;

public class InteractionUtiliserCarte extends Interaction {

	@Override
	public String getMessage() {
		return getChoix() + ". Utiliser une carte en jeu";
	}

	@Override
	protected boolean traitementSpecialise(int choix) {
		if ( choix != getChoix() )
			return false;
	
		System.out.println("J'utilise une carte en jeu");
		
		return true;
	}

	@Override
	public int getChoix() {
		return 3;
	}

}
