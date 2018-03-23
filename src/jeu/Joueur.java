package jeu;

import java.util.ArrayList;

public class Joueur implements IJoueur {
	
	private int mana;
	private int stockMana;
	private String pseudo;
	private Heros heros;
	
	public Joueur(String pseudo, Heros heros) {
		this.setPseudo(pseudo);
		this.setMana(0);
		this.setStockMana(this.getMana());
		this.setHeros(heros);
	}

	private void setHeros(Heros heros) {
		if ( heros != null ) {
			this.heros = heros;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setPseudo(String pseudo) {
		if ( pseudo != null ) {
			this.pseudo = pseudo;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setStockMana(int stockMana) {
		if ( stockMana >= 0 ) {
			this.stockMana = stockMana;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void setMana(int mana) {
		if ( mana >= 0 ) {
			this.mana = mana;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public void finirTour() {
		
	}

	@Override
	public void jouerCarte(ICarte carte) {
		if ( carte.getCout() > this.getStockMana() ) {
			// EXCEPTION
		} else {
			this.setStockMana(this.getStockMana() - carte.getCout());
		}
	}

	@Override
	public void jouerCarte(ICarte carte, Object cible) {
		if ( carte.getCout() > this.getStockMana() ) {
			// EXCEPTION
		} else {
			this.setStockMana(this.getStockMana() - carte.getCout());
		}
	}

	@Override
	public void perdreCarte(ICarte carte) {
		
	}

	@Override
	public void piocher() {
		
	}

	@Override
	public void prendreTour() {
		if ( this.getMana() < IJoueur.MAX_MANA ) {
			this.setMana(this.getMana() + 1);
		}
		this.setStockMana(this.getMana());
	}

	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
		carte.executerAction(cible);
	}

	@Override
	public void utiliserPouvoir(Object cible) throws HearthstoneException {
		this.getHeros().utiliserPouvoir(cible);
	}

	@Override
	public int getMana() {
		return this.mana;
	}

	@Override
	public int getStockMana() {
		return this.stockMana;
	}

	@Override
	public ArrayList<ICarte> getJeu() {
		return null;
	}

	@Override
	public ArrayList<ICarte> getMain() {
		return null;
	}

	@Override
	public ICarte getCarteEnJeu(String nomCarte) {
		return null;
	}

	@Override
	public ICarte getCarteEnMain(String nomCarte) {
		return null;
	}

	@Override
	public Heros getHeros() {
		return this.heros;
	}

	@Override
	public String getPseudo() {
		return this.pseudo;
	}

}
