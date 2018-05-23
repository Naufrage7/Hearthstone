package capacite;

import carte.ICible;
import exception.CibleInvalideException;
import exception.HearthstoneException;

public class AttaqueCiblee extends Capacite {
	
	private int degats;
	
	public AttaqueCiblee(String nom, String description, int degats) {
		super(nom, description);
		this.setDegats(degats);
	}
	
	private void setDegats(int degats) {
		this.degats = degats;
	}
	
	protected int getDegats() {
		return this.degats;
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle.");
		
		if ( !(cible instanceof ICible) )
			throw new CibleInvalideException("La cible doit être une cible valide.");
		
		ICible cibleVisee = (ICible) cible;
		cibleVisee.recevoirDegats(this.degats);
	}

}
