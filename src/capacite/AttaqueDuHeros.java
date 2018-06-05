package capacite;

import carte.ICible;
import exception.CibleInvalideException;
import exception.HearthstoneException;
import heros.Heros;

public class AttaqueDuHeros extends AttaqueCiblee {

	public AttaqueDuHeros(String nom, String description, int degats) {
		super(nom, description, degats);
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		executerAction(cible);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle.");
		
		if ( !(cible instanceof Heros) )
			throw new CibleInvalideException("La cible doit être un héros.");
		
		ICible cibleVisee = (ICible) cible;
		cibleVisee.recevoirDegats(getDegats());
	}
	
}
