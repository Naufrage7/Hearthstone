package interactions;

import java.util.ArrayList;

public abstract class Interaction {
	private Interaction suivant = null;
	private int choix;
	
	public Interaction(int choix) {
		this.choix = choix;
	}
	
	public Interaction setSuivant(Interaction interaction) {
		if ( interaction == null )
			throw new IllegalArgumentException("L'intéraction ne peut pas être nulle !");
		
		suivant = interaction;
		return suivant;
	}
	
	public ArrayList<String> getMessages() {
		ArrayList<String> messages = new ArrayList<String>();
		messages.add(getMessage());
		
		Interaction temporaire = suivant;
		while ( temporaire != null ) {
			messages.add(temporaire.getMessage());
			temporaire = temporaire.suivant;
		}
		
		return messages;
	}
	
	public void traiter(int choix) throws InteractionChoixNonValideException {
		if ( getChoix() == choix )
			traitementSpecialise();
		else if ( suivant != null )
			suivant.traiter(choix);
		else
			throw new InteractionChoixNonValideException("Le choix n'est pas dans le menu !");
	}
	
	public String getMessage() {
		return getChoix() + ". ";
	}
	
	public int getChoix() {
		return choix;
	}
	
	protected abstract void traitementSpecialise();
	
}
