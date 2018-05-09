package application;

import java.util.ArrayList;

public abstract class Interaction {
	private Interaction suivant = null;
	
	public Interaction setSuivant(Interaction interaction) {
		if ( interaction == null )
			throw new IllegalArgumentException("L'intéraction ne peut pas être nulle !");
		suivant = interaction;
		return this;
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
	
	public boolean traiter(int choix) {
		if ( traitementSpecialise(choix) ) {
			return true;
		}
		
		if ( suivant != null )
			return suivant.traiter(choix);
		
		return false;
	}
	
	public String getMessage() {
		return getChoix() + ". ";
	}
	protected abstract boolean traitementSpecialise(int choix);
	public abstract int getChoix();
}
