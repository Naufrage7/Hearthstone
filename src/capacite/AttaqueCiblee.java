package capacite;

import carte.Serviteur;
import cible.ICible;
import exception.CibleInvalideException;
import exception.HearthstoneException;
import heros.Heros;

public class AttaqueCiblee extends Capacite {
	private int degats;
	
	public AttaqueCiblee(String nom, int degats) {
		super(nom, "Inflige " + degats + " de dégats à la cible");
		this.setDegats(degats);
	}
	
	private void setDegats(int degats) {
		this.degats = degats;
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		executerAction(cible);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle");
		
		if ( !(cible instanceof ICible) )
			throw new CibleInvalideException("La cible doit être une cible valide ( = avoir des points de vie ).");
		
		ICible cibleVisee = (ICible) cible;
		cibleVisee.recevoirDegats(this.degats);
	}
}
