package joueur;

import java.util.ArrayList;
import java.util.Collections;

import application.Main;
import capacite.AttaqueCiblee;
import capacite.AttaqueDuHeros;
import capacite.AttaqueTotale;
import capacite.Charge;
import capacite.EffetPermanent;
import capacite.ITemporisable;
import capacite.ImageMiroir;
import capacite.InvocationDeServiteurs;
import capacite.InvocationDesChiens;
import capacite.MarqueDuChasseur;
import capacite.Pioche;
import capacite.Provocation;
import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.CibleInvalideException;
import exception.HearthstoneException;
import heros.Heros;
import plateau.Plateau;

public class Joueur implements IJoueur {
	
	private String pseudo;
	private Heros heros;
	private int mana;
	private int stockMana;
	private ArrayList<ICarte> main = new ArrayList<ICarte>();
	private ArrayList<ICarte> jeu = new ArrayList<ICarte>();
	private ArrayList<ICarte> deck = new ArrayList<ICarte>();

	/**
	 * Instancie un Joueur
	 * 
	 * @param pseudo le pseudo du joueur
	 * @param heros le héros du joueur
	 */
	public Joueur(String pseudo, Heros heros) {
		this.setPseudo(pseudo);
		this.setHeros(heros);
		this.setMana(0);
		this.setStockMana(0);
		this.initialiserDeck();
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
	
	/**
	 * Attribue une quantité de mana au héros
	 * 
	 * @param mana la quantité de mana
	 */
	private void setMana(int mana) {
		if ( mana < 0 )
			throw new IllegalArgumentException("Le mana ne peut pas être une valeur négative !");
		else if ( mana > IJoueur.MAX_MANA )
			throw new IllegalArgumentException("Le mana ne peut pas dépasser " + IJoueur.MAX_MANA + " !");
		
		this.mana = mana;
	}
	
	/**
	 * Attribue une quantité au stock de mana
	 * 
	 * @param stockMana la quantité du stock de mana
	 */
	private void setStockMana(int stockMana) {
		if ( stockMana < 0 )
			throw new IllegalArgumentException("Le stock de mana ne peut pas être une valeur négative !");
		else if ( stockMana > mana )
			throw new IllegalArgumentException("Le stock de mana ne peut pas être supérieur au mana !");
		
		this.stockMana = stockMana;
	}
	
	private void initialiserDeck() {
		this.deck.add(new Serviteur(2, 1, "Chasse-marée murloc", 2, null, this));
		this.deck.add(new Sort("Charge", 1, new Charge("Charge", "Permet à un serviteur d'attaquer tout de suite !"), this));
		this.deck.add(new Sort("Attaque mentale", 2, new AttaqueDuHeros("Attaque mentale", "Inflige 5 points de dégats au héros", 5), this));
		this.deck.add(new Serviteur(6, 6, "Champion de Hurlevent", 7, new EffetPermanent("Bonus de hurlevent", "Confère un bonus +1/+1", 1, 1),this));
		this.deck.add(new Serviteur(2, 2, "Chef de raid", 3, new EffetPermanent("Bonus du chef de raid", "Confère un bonus +1/0", 1, 0), this));
		this.deck.add(new Serviteur(5, 4, "Garde de Baie-du-butin", 5, new Provocation("Provocation", "Force le joueur adverse à viser cette cible"), this));
		this.deck.add(new Serviteur(5, 2, "La missilière téméraire", 6, new Charge("Charge", "Permet à un serviteur d'attaquer tout de suite !"), this));
		this.deck.add(new Serviteur(4, 4, "L'ogre-magi", 4, new Provocation("Provocation", "Force le joueur adverse à viser cette cible"), this));
		this.deck.add(new Serviteur(4, 7, "Archimage", 6, new Provocation("Provocation", "Force le joueur adverse à viser cette cible"), this));
		this.deck.add(new Serviteur(1, 1, "Gnôme lépreux", 1, new AttaqueCiblee("Attaque du lépreux", "Inflige 2 points de dégats", 2), this));
		this.deck.add(new Serviteur(2, 3, "Golem des moissons", 3, new InvocationDeServiteurs("Golémisation", "Invoque un \"Golem endomagé\" +2/+1 qui n'a aucune capacité", new Serviteur(2, 1, "Golem endomagé", 0, null, this)), this));
	
		if ( this.heros.getNom().equals("Jaina") ) {
			this.deck.add(new Sort("Choc de flamme", 7, new AttaqueTotale("Attaque massive", "Inflige 4 points de dégats à toutes les cibles adverses", 4), this));
			this.deck.add(new Sort("Eclair de givre", 2, new AttaqueCiblee("Attaque du givre", "Inflige 3 de dégats", 3), this));
			this.deck.add(new Sort("Intelligence des arcanes", 2, new Pioche("Pioche", "Pioche 2 cartes", 2), this));
			this.deck.add(new Sort("Image miroir", 7, new ImageMiroir("Image miroir", "Invoque 2 serviteurs de Jaina2"), this));
			this.deck.add(new Sort("Explosion pyrotechnique", 10, new AttaqueCiblee("Explosion pyrotechnique", "Inflige 10 points de dégats", 10), this));
		} else if ( this.heros.getNom().equals("Rexxar") ) {
			this.deck.add(new Serviteur(3, 2, "Busard affamé", 5, new Pioche("Pioche", "Pioche 1 carte", 1), this));
			this.deck.add(new Sort("Marque du chasseur", 1, new MarqueDuChasseur("Marque du chasseur", "Réduit à 1 le nombre de points de vie"), this));
			this.deck.add(new Sort("Tir des arcanes", 1, new AttaqueCiblee("Tir des arcanes", "Inflige 2 points de dégats", 2), this));
			this.deck.add(new Sort("Lâchez les chiens", 3,  new InvocationDesChiens("", "", this), this));
			this.deck.add(new Sort("Ordre de tuer", 3, new AttaqueCiblee("Ordre de tuer", "Inflige 2 points de dégats", 3), this));
		}
		
		Collections.shuffle(this.deck);
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
		return this.mana;
	}

	@Override
	public int getStockMana() {
		return this.stockMana;
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
			if ( c.getNom().toLowerCase().contains(nomCarte.toLowerCase()) ) {
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
			if ( c.getNom().toLowerCase().contains(nomCarteMain.toLowerCase()) ) {
				carte = c;
				break;
			}
		}
		
		return carte;
	}

	@Override
	public void prendreTour() throws HearthstoneException {
		if ( this.mana < IJoueur.MAX_MANA )
			this.setMana(this.mana + 1);
		
		this.stockMana = this.mana;
		
		try {
			this.piocher();
		} catch ( HearthstoneException e ) {
			System.out.println(e.getMessage());
		}
		
		for ( ICarte carte : this.jeu ) {			
			if ( carte instanceof ITemporisable ) {
				ITemporisable temporisable = (ITemporisable) carte;
				if ( !temporisable.peutJouer() )
					temporisable.setPeutJouer(true);
			}
		}
		
		this.getHeros().setCapaciteUtilisee(false);
	}

	@Override
	public void finirTour() throws HearthstoneException {
		if ( Plateau.getInstance().getJoueurCourant() != this )
			throw new HearthstoneException("Impossible, tu n'es pas en train de jouer ...");
	}

	@Override
	public void piocher() throws HearthstoneException {
		if ( this.deck.size() == 0 )
			throw new HearthstoneException("Le deck est vide malheureux !");
		
		ICarte carte = this.deck.remove(0);
		this.main.add(carte);
	}

	@Override
	public void jouerCarte(ICarte carte) throws HearthstoneException {
		jouerCarte(carte, null);
	}

	@Override
	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException {
		if ( carte == null )
			throw new HearthstoneException("La carte est nulle !");
		
		if ( carte.getCout() > this.stockMana )
			throw new HearthstoneException("Impossible de jouer une carte coutant plus de mana que le joueur n'en possède.");
	
		try {
			this.stockMana -= carte.getCout();
			this.main.remove(carte);
			this.jeu.add(carte);
			
			carte.executerEffetDebutMiseEnJeu(cible);
		} catch (CibleInvalideException e ) {
			this.stockMana += carte.getCout();
			this.main.add(carte);
			this.jeu.remove(carte);
			
			throw e;
		} catch (HearthstoneException e) {
			this.stockMana += carte.getCout();
			this.main.add(carte);
			this.jeu.remove(carte);
			
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
		((Serviteur)carte).attaquer(cible);
	}

	@Override
	public void utiliserPouvoir(Object cible) throws HearthstoneException {
		this.getHeros().getPouvoir().executerAction(cible);
	}

	@Override
	public void perdreCarte(ICarte carte) throws HearthstoneException {
		jeu.remove(carte);
		carte.executerEffetDisparition(null);
	}

}
