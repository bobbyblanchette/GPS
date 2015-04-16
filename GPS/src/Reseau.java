import java.util.ArrayList;

public class Reseau {

	// Contiennent le chemin parcouru par l’algorithme de résolution de trajet
	// ainsi que le temps requis. Dès que l’algorithme détecte un chemin plus
	// court, il remplace les valeurs stockées dans ces variables par celles
	// correspondant au nouveau parcours
	private double tempsOptimal = 0;
	private ArrayList<Route> cheminOptimal = new ArrayList<Route>();
	private ArrayList<Route> cheminTemp = new ArrayList<Route>();

	// Contiennent les points et les routes du réseau
	private ArrayList<Point> listeDesPoints = new ArrayList<Point>();
	private ArrayList<Route> listeDesRoutes = new ArrayList<Route>();

	/**
	 * Calcule le temps minimum requis pour aller d'un point à un autre. En
	 * outre, elle stocke le chemin optimal dans l'ArrayList cheminOptimal
	 * 
	 * @param depart
	 *            Point de départ
	 * @param arrivee
	 *            Point d'arrivée
	 * @return retourne le temps prit pour aller de départ à arrivée
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
		// S’il n’y a pas de chemin possible
		if (tempsOptimal == Double.POSITIVE_INFINITY) {
			throw new CheminImpossibleException("Ce chemin est impossible!");
		}

		return tempsOptimal;
	}

	public void calcTempsMinimal(Point depart, Point arrivee,
			double tempsTemporaire) {
		// Si nous sommes arrivés à destination
		if (depart.equals(arrivee)) {
			// Si le temps trouvé est plus petit que le trajet le plus court
			// trouvé
			// jusqu’à date, on remplace la
			// valeur dans tempsOptimal par la nouvelle valeur trouvée
			if (tempsTemporaire < tempsOptimal) {
				tempsOptimal = tempsTemporaire;
				// Crée une copie de cheminTemp, qu'on met dans
				// cheminOptimal
				cheminOptimal = new ArrayList<Route>(cheminTemp);
			}
			return;
		}

		// Trouve tous les chemins possibles dans un réseau
		for (int i = 0; i < depart.routes.size(); i++) {
			// Mémorise la dernière route empruntée
			Point finDeRoute;
			Route route = depart.routes.get(i);
			// Si le point actuel est le point d'arrivée
			if (route.getPointDepart().equals(depart)) {
				// Le dernier point devient le point d’arrivée
				finDeRoute = route.getPointArrivee();
			} else {
				// Le dernier point devient un nouveau point de départ
				finDeRoute = route.getPointDepart();
			}

			// Si la route n'a pas déjà été emprunte, on poursuit nos opérations
			if (!cheminTemp.contains(route)) {
				// On prend cette route
				cheminTemp.add(route);
				// Calcule la vitesse en mètres par secondes
				double vitesse = route.getLimiteVitesse() / 3.6;
				int etat = route.getEtat();
				// Calcule la longueur en mètres
				double longueur = route.getLongueur() * 1000;
				// Algorithme pour calculer le temps de trajet d’une route
				tempsTemporaire += (longueur / vitesse) * (etat + 1);
				// On passe au prochain point du réseau
				calcTempsMinimal(finDeRoute, arrivee, tempsTemporaire);
				// Enlève le dernier point ajouté
				cheminTemp.remove(cheminTemp.size() - 1);
				tempsTemporaire -= (longueur / vitesse) * (etat + 1);
			}
		}
	}

	/**
	 * Ajoute un point au réseau
	 * 
	 * @param point
	 *            Point à ajouter
	 */
	public void addPoint(Point point) {
		listeDesPoints.add(point);
	}

	/**
	 * Ajoute une route au réseau
	 * 
	 * @param route
	 *            Route à ajouter
	 */
	public void addRoute(Route route) {
		listeDesRoutes.add(route);
	}

	/**
	 * Retourne un point contenu dans le réseau
	 * 
	 * @param index
	 *            index du point à retourner
	 * @return retourne le point spécifié par l'index
	 */
	public Point getPoint(int index) {
		return listeDesPoints.get(index);
	}

	/**
	 * Retourne une route contenue dans le réseau
	 * 
	 * @param index
	 *            index de la route à retourner
	 * @return retourne la route spécifiée par l'index
	 */
	public Route getRoute(int index) {
		return listeDesRoutes.get(index);
	}

	/**
	 * @return la quantité de routes dans le réseau
	 */
	public int getPointQuantite() {
		return listeDesPoints.size();
	}

	/**
	 * @return la quantité le routes présentes dans le réseau
	 */
	public int getRouteQuantite() {
		return listeDesRoutes.size();
	}

	/**
	 * Retourne le chemin calculé par calcTempsMinimal() Si on appelle cette
	 * méthode avant calcTempsMinimal, elle va retourner une liste vide
	 * 
	 * @return retourne une liste de routes représentant le chemin le plus court
	 */
	public ArrayList<Route> getCheminOptimal() {
		return cheminOptimal;
	}

	/**
	 * Enlève tous les points et toutes les routes dans le réseau
	 */
	public void effacer() {
		listeDesRoutes.clear();
		listeDesPoints.clear();
	}
}
