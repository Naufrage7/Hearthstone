package capacite;

import carte.ICarte;
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
		// TODO Auto-generated method stub
		executerAction(cible);
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		if ( cible == null )
			throw new CibleInvalideException("La cible ne peut pas être nulle.");
		
		if ( !(cible instanceof ICible) )
			throw new CibleInvalideException("La cible doit être une cible valide.");
		
		ICible cibleVisee = (ICible) cible;
		if ( cibleVisee.peutRecevoirDegats() ) {
			cibleVisee.recevoirDegats(this.degats);	
		} else {
			throw new CibleInvalideException("La cible ne peut pas recevoir de dégats !");
		}
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean seLanceSurServiteurProprietaire() {
		// TODO Auto-generated method stub
		return false;
	}

}
