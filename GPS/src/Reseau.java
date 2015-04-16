import java.util.ArrayList;

public class Reseau {

	// Contiennent le chemin parcouru par l�algorithme de r�solution de trajet
	// ainsi que le temps requis. D�s que l�algorithme d�tecte un chemin plus
	// court, il remplace les valeurs stock�es dans ces variables par celles
	// correspondant au nouveau parcours
	private double tempsOptimal = 0;
	private ArrayList<Route> cheminOptimal = new ArrayList<Route>();
	private ArrayList<Route> cheminTemp = new ArrayList<Route>();

	// Contiennent les points et les routes du r�seau
	private ArrayList<Point> listeDesPoints = new ArrayList<Point>();
	private ArrayList<Route> listeDesRoutes = new ArrayList<Route>();

	/**
	 * Calcule le temps minimum requis pour aller d'un point � un autre. En
	 * outre, elle stocke le chemin optimal dans l'ArrayList cheminOptimal
	 * 
	 * @param depart
	 *            Point de d�part
	 * @param arrivee
	 *            Point d'arriv�e
	 * @return retourne le temps prit pour aller de d�part � arriv�e
	 * @throws CheminImpossibleException
	 */
	public double calcTempsMinimal(Point depart, Point arrivee)
			throws CheminImpossibleException {
		// Initialise les variables
		tempsOptimal = Double.POSITIVE_INFINITY;
		cheminOptimal.clear();
		cheminTemp.clear();
		// Calcule le meilleur chemin
		calcTempsMinimal(depart, arrivee, 0);
		// S�il n�y a pas de chemin possible
		if (tempsOptimal == Double.POSITIVE_INFINITY) {
			throw new CheminImpossibleException("Ce chemin est impossible!");
		}

		return tempsOptimal;
	}

	public void calcTempsMinimal(Point depart, Point arrivee,
			double tempsTemporaire) {
		// Si nous sommes arriv�s � destination
		if (depart.equals(arrivee)) {
			// Si le temps trouv� est plus petit que le trajet le plus court
			// trouv�
			// jusqu�� date, on remplace la
			// valeur dans tempsOptimal par la nouvelle valeur trouv�e
			if (tempsTemporaire < tempsOptimal) {
				tempsOptimal = tempsTemporaire;
				// Cr�e une copie de cheminTemp, qu'on met dans
				// cheminOptimal
				cheminOptimal = new ArrayList<Route>(cheminTemp);
			}
			return;
		}

		// Trouve tous les chemins possibles dans un r�seau
		for (int i = 0; i < depart.routes.size(); i++) {
			// M�morise la derni�re route emprunt�e
			Point finDeRoute;
			Route route = depart.routes.get(i);
			// Si le point actuel est le point d'arriv�e
			if (route.getPointDepart().equals(depart)) {
				// Le dernier point devient le point d�arriv�e
				finDeRoute = route.getPointArrivee();
			} else {
				// Le dernier point devient un nouveau point de d�part
				finDeRoute = route.getPointDepart();
			}

			// Si la route n'a pas d�j� �t� emprunte, on poursuit nos op�rations
			if (!cheminTemp.contains(route)) {
				// On prend cette route
				cheminTemp.add(route);
				// Calcule la vitesse en m�tres par secondes
				double vitesse = route.getLimiteVitesse() / 3.6;
				int etat = route.getEtat();
				// Calcule la longueur en m�tres
				double longueur = route.getLongueur() * 1000;
				// Algorithme pour calculer le temps de trajet d�une route
				tempsTemporaire += (longueur / vitesse) * (etat + 1);
				// On passe au prochain point du r�seau
				calcTempsMinimal(finDeRoute, arrivee, tempsTemporaire);
				// Enl�ve le dernier point ajout�
				cheminTemp.remove(cheminTemp.size() - 1);
				tempsTemporaire -= (longueur / vitesse) * (etat + 1);
			}
		}
	}

	/**
	 * Ajoute un point au r�seau
	 * 
	 * @param point
	 *            Point � ajouter
	 */
	public void addPoint(Point point) {
		listeDesPoints.add(point);
	}

	/**
	 * Ajoute une route au r�seau
	 * 
	 * @param route
	 *            Route � ajouter
	 */
	public void addRoute(Route route) {
		listeDesRoutes.add(route);
	}

	/**
	 * Retourne un point contenu dans le r�seau
	 * 
	 * @param index
	 *            index du point � retourner
	 * @return retourne le point sp�cifi� par l'index
	 */
	public Point getPoint(int index) {
		return listeDesPoints.get(index);
	}

	/**
	 * Retourne une route contenue dans le r�seau
	 * 
	 * @param index
	 *            index de la route � retourner
	 * @return retourne la route sp�cifi�e par l'index
	 */
	public Route getRoute(int index) {
		return listeDesRoutes.get(index);
	}

	/**
	 * @return la quantit� de routes dans le r�seau
	 */
	public int getPointQuantite() {
		return listeDesPoints.size();
	}

	/**
	 * @return la quantit� le routes pr�sentes dans le r�seau
	 */
	public int getRouteQuantite() {
		return listeDesRoutes.size();
	}

	/**
	 * Retourne le chemin calcul� par calcTempsMinimal() Si on appelle cette
	 * m�thode avant calcTempsMinimal, elle va retourner une liste vide
	 * 
	 * @return retourne une liste de routes repr�sentant le chemin le plus court
	 */
	public ArrayList<Route> getCheminOptimal() {
		return cheminOptimal;
	}

	/**
	 * Enl�ve tous les points et toutes les routes dans le r�seau
	 */
	public void effacer() {
		listeDesRoutes.clear();
		listeDesPoints.clear();
	}
}
