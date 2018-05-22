package capacite;

import carte.Cible;
import exception.CibleInvalideException;
import exception.HearthstoneException;

public class AttaqueCiblee extends Capacite {
	
	private int degats;
	
	public AttaqueCiblee(String nom, int degats) {
		super(nom, "Inflige " + degats + " de dégats à la cible");
		this.setDegats(degats);
	}
	
	private void setDegats(int degats) {
		this.degats = degats;
	}
	
	public int getDegats() {
		return this.degats;
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		executerAction(cible);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle.");
		
		if ( !(cible instanceof Cible) )
			throw new CibleInvalideException("La cible doit être une cible valide.");
		
		((Cible)cible).recevoirDegats(this.degats);
	}

}
