package joueur;

import java.util.ArrayList;

import carte.ICarte;
import exception.HearthstoneException;
import heros.Heros;

public class Joueur implements IJoueur {
	
	private String pseudo;
	private Heros heros;
	private int mana;
	private int stockMana;
	private ArrayList<ICarte> main;
	private ArrayList<ICarte> jeu;

	/**
	 * Instancie un Joueur
	 * 
	 * @param pseudo le pseudo du joueur
	 * @param heros le héros du joueur
	 */
	public Joueur(String pseudo, Heros heros) {
		this.setPseudo(pseudo);
		this.setHeros(heros);
	}
	
	/**
	 * Attribue le pseudo au joueur
	 * 
	 * @param pseudo le pseudo du joueur
	 */
	private void setPseudo(String pseudo) {
		if ( pseudo == null )
			throw new IllegalArgumentException("Le pseudo du joueur ne peut pas être nul !");
		this.pseudo = pseudo;
	}
	
	/**
	 * Attribue le héros au joueur
	 * 
	 * @param heros le héros du joueur
	 */
	private void setHeros(Heros heros) {
		if ( heros == null )
			throw new IllegalArgumentException("Le héros du joueur ne peut pas être nul !");
		this.heros = heros;
	}
	
	@Override
	public Heros getHeros() {
		return this.heros;
	}

	@Override
	public String getPseudo() {
		return this.pseudo;
	}

	@Override
	public int getMana() {
		return mana;
	}

	@Override
	public int getStockMana() {
		return stockMana;
	}

	@Override
	public ArrayList<ICarte> getMain() {
		return this.main;
	}

	@Override
	public ArrayList<ICarte> getJeu() {
		return this.jeu;
	}

	@Override
	public ICarte getCarteEnJeu(String nomCarte) {
		ICarte carte = null;
		for (ICarte c : jeu) {
			if ( c.getNom().contains(nomCarte) ) {
				carte = c;
				break;
			}
		}
		
		return carte;
	}

	@Override
	public ICarte getCarteEnMain(String nomCarteMain) {
		ICarte carte = null;
		for (ICarte c : main) {
			if ( c.getNom().contains(nomCarteMain) ) {
				carte = c;
				break;
			}
		}
		
		return carte;
	}

	@Override
	public void prendreTour() throws HearthstoneException {
	}

	@Override
	public void finirTour() throws HearthstoneException {
	}

	@Override
	public void piocher() throws HearthstoneException {
	}

	@Override
	public void jouerCarte(ICarte carte) throws HearthstoneException {
	}

	@Override
	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException {
	}

	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
	}

	@Override
	public void utiliserPouvoir(Object cible) throws HearthstoneException {
	}

	@Override
	public void perdreCarte(ICarte carte) throws HearthstoneException {
	}
	
	public String toString() {
		return "Joueur [pseudo="+this.pseudo+", mana="+this.mana+", stockMana="+this.stockMana+", heros="+this.heros+", main="+this.main+", jeu="+this.jeu+"]";
	}

}
