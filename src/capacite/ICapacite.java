package capacite;

import exception.HearthstoneException;

/**
 * Une capacité, c'est l'abstraction générique pour décrire, le pouvoir du
 * héros, le sort d'une carte Sort, la capacité d'un serviteur, etc.
 */
public interface ICapacite {
	/**
	 * Le nom de la capacité ("Boule de feu")
	 * 
	 * @return Une chaine de caractéres...
	 */
	String getNom();

	/**
	 * La description de la capacité ("Inflige +1 points de dégat au héros visé")
	 * 
	 * @return Une chaine de caractères
	 */
	String getDescription();

	/**
	 * Certaines capacités agissent en début de tour (J'ai pas d'exemple mais on ne
	 * sait jamais)
	 * 
	 * @throws HearthstoneException
	 *             En cas de problème...
	 */
	void executerEffetDebutTour() throws HearthstoneException;

	/**
	 * Certaines capacités agissent en fin de tour (J'ai pas d'exemple mais on ne
	 * sait jamais)
	 * 
	 * @throws HearthstoneException
	 *             En cas de problème...
	 */
	void executerEffetFinTour() throws HearthstoneException;

	/**
	 * Certaines capacités agissent quand on le demande, et éventuellement sur une
	 * cible...
	 * 
	 * @throws HearthstoneException
	 *             En cas de probème...
	 */
	void executerAction(Object cible) throws HearthstoneException;

	/**
	 * Certaines capacités agissent en début de mise en jeu. C'est souvent le cas
	 * des sorts.
	 * 
	 * @throws HearthstoneException
	 *             En cas de problème...
	 */
	void executerEffetMiseEnJeu(Object cible) throws HearthstoneException;

	/**
	 * Certaines capacités agissent lorsque la carte disparaÃ®t du jeu (comme le
	 * râle d'agonie...).
	 * 
	 * @throws HearthstoneException
	 *             En cas de problème...
	 */
	void executerEffetDisparition(Object cible) throws HearthstoneException;

	boolean seLanceSurServiteurProprietaire();
}
