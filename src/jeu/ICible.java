package jeu;

import exception.HearthstoneException;

public interface ICible {

	/**
	 * Occasionne des dégats à la cible
	 * 
	 * @param degats
	 * @throws HearthstoneException 
	 */
	public void recevoirDegats(int degats) throws HearthstoneException;

}
