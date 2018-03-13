package jeu;

import java.util.ArrayList;

public interface IJoueur {

	static int MAX_MANA = 10;
	static int TAILLE_DECK = 15;
	
	void finirTour();
	void jouerCarte(ICarte carte);
	void jouerCarte(ICarte carte, Object cible);
	void perdreCarte(ICarte carte);
	void piocher();
	void prendreTour();
	void utiliserCarte(ICarte carte, Object cible);
	void utiliserPouvoir(Object cible);
	
	int getMana();
	int getStockMana();
	
	ArrayList<ICarte> getJeu();
	ArrayList<ICarte> getMain();
	
	ICarte getCarteEnJeu(String nomCarte);
	ICarte getCarteEnMain(String nomCarte);
	
	Heros getHeros();
	
	String getPseudo();

}
