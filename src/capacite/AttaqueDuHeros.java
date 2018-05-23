package capacite;

import carte.ICible;
import exception.CibleInvalideException;
import exception.HearthstoneException;
import heros.Heros;

public class AttaqueDuHeros extends AttaqueCiblee {

	public AttaqueDuHeros(String nom, int degats) {
		super("Inflige " + degats + " de dégats au héros adverse", degats);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle.");
		
		if ( !(cible instanceof Heros) )
			throw new CibleInvalideException("La cible doit être un héros.");
		
		((ICible)cible).recevoirDegats(getDegats());
	}
	
}
