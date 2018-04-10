package jeu;

import java.util.ArrayList;

public class Joueur implements IJoueur {
	
	/**
	 * Représente la quantité totale de mana du joueur
	 */
	private int mana;
	
	/**
	 * Représente la quantité de mana encore disponible du joueur
	 */
	private int stockMana;
	
	/**
	 * Représente le pseudo du joueur
	 */
	private String pseudo;
	
	/**
	 * Représente le Heros du joueur
	 */
	private Heros heros;
	
	/**
	 * Représente le jeu du joueur
	 */
	private ArrayList<ICarte> jeu;
	
	/**
	 * Représente la main du joueur
	 */
	private ArrayList<ICarte> main;
	
	/**
	 * Initialise un joueur avec son pseudo et son héros
	 * @param pseudo
	 * @param heros
	 */
	public Joueur(String pseudo, Heros heros) {
		this.setPseudo(pseudo);
		this.setMana(0);
		this.setStockMana(this.getMana());
		this.setHeros(heros);
		
		this.jeu = new ArrayList<ICarte>();
		this.main = new ArrayList<ICarte>();
	}

	/**
	 * Défini le héros du joueur
	 * @param heros
	 */
	private void setHeros(Heros heros) {
		if ( heros != null ) {
			this.heros = heros;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Défini le pseudo du joueur
	 * @param pseudo
	 */
	private void setPseudo(String pseudo) {
		if ( pseudo != null ) {
			this.pseudo = pseudo;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Défini la quantité de mana encore disponible du joueur
	 * @param stockMana
	 */
	private void setStockMana(int stockMana) {
		if ( stockMana >= 0 ) {
			this.stockMana = stockMana;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Défini la quantité totale de mana du joueur
	 * @param mana
	 */
	private void setMana(int mana) {
		if ( mana >= 0 ) {
			this.mana = mana;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Actions exécutées lors de la fin du tour
	 * @throws HearthstoneException 
	 */
	@Override
	public void finirTour() throws HearthstoneException {
		for ( ICarte c : this.jeu ) {
			c.executerEffetFinTour(null); // ?
		}
	}


	/**
	 * Le joueur joue la carte
	 * @param carte
	 */
	@Override
	public void jouerCarte(ICarte carte) throws HearthstoneException {
		if ( carte.getCout() > this.getStockMana() ) {
			throw new HearthstoneException("Le joueur n'a pas assez de mana pour jouer la carte");
		} else {
			this.setStockMana(this.getStockMana() - carte.getCout());
		}
	}

	/**
	 * Le joueur joue la carte sur une cible
	 * @param carte
	 * @param cible
	 */
	@Override
	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException {
		if ( carte.getCout() > this.getStockMana() ) {
			throw new HearthstoneException("Le joueur n'a pas assez de mana pour jouer la carte");
		} else {
			this.setStockMana(this.getStockMana() - carte.getCout());
		}
	}

	/**
	 * Le joueur perd la carte ( serviteur mort par exemple )
	 * @param carte
	 * @throws HearthstoneException 
	 */
	@Override
	public void perdreCarte(ICarte carte) throws HearthstoneException {
		carte.executerEffetDisparition(null); // ??
		this.jeu.remove(carte);
	}

	/**
	 * Le joueur pioche une carte dans son deck
	 */
	@Override
	public void piocher() {
		// ??
		this.main.add(null); // ??
	}

	/**
	 * Actions liées à la prise de tour du joueur
	 * @throws HearthstoneException 
	 */
	@Override
	public void prendreTour() throws HearthstoneException {
		if ( this.getMana() < IJoueur.MAX_MANA ) {
			this.setMana(this.getMana() + 1);
		}
		this.setStockMana(this.getMana());
		
		for ( ICarte c : this.jeu ) {
			c.executerEffetDebutTour(null); // ?
		}
	}

	/**
	 * Le joueur utilise la carte sur la cible
	 * @param carte
	 * @param cible
	 */
	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
		carte.executerAction(cible);
	}

	/**
	 * Le joueur utilise le pouvoir de son héros sur une cible
	 * @param cible
	 */
	@Override
	public void utiliserPouvoir(Object cible) throws HearthstoneException {
		this.getHeros().utiliserPouvoir(cible);
	}

	/**
	 * Retourne la quantité totale de mana du joueur
	 */
	@Override
	public int getMana() {
		return this.mana;
	}

	/**
	 * Retourne la quantité de mana restante du joueur
	 */
	@Override
	public int getStockMana() {
		return this.stockMana;
	}

	/**
	 * Retourne une liste des cartes en jeu du joueur
	 */
	@Override
	public ArrayList<ICarte> getJeu() {
		return this.jeu;
	}

	/**
	 * Retourne une liste des cartes dans la main du joueur
	 */
	@Override
	public ArrayList<ICarte> getMain() {
		return this.main;
	}

	/**
	 * Retourne la carte en jeu du joueur
	 * @param nomCarte
	 */
	@Override
	public ICarte getCarteEnJeu(String nomCarte) {
		for ( ICarte c : this.jeu ) {
			if ( c.getNom().equalsIgnoreCase(nomCarte) )
				return c;
		}
		
		return null;
	}

	/**
	 * Retourne la carte en main du joueur
	 * @param nomCarte
	 */
	@Override
	public ICarte getCarteEnMain(String nomCarte) {
		for ( ICarte c : this.main ) {
			if ( c.getNom().equalsIgnoreCase(nomCarte) )
				return c;
		}
		
		return null;
	}

	/**
	 * Retourne le héros du joueur
	 */
	@Override
	public Heros getHeros() {
		return this.heros;
	}

	/**
	 * Retourne le pseudo du joueur
	 */
	@Override
	public String getPseudo() {
		return this.pseudo;
	}

}
