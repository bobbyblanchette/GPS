public class Route {
	private Point a, b; // Les deux points formant une ligne
	private int limiteVitesse, etat;// état d’une route (circulation fluide,
									// trafic, accident)

	/**
	 * Construit une route à partir de deux points, une vitesse et un état
	 * 
	 * @param a
	 *            Point de départ de la route
	 * @param b
	 *            Point d'arrivée de la route
	 * @param limiteVitesse
	 *            limite de vitesse de la route
	 * @param etat
	 *            état de la route (0, 1 ou 2)
	 * @throws IllegalArgumentException
	 *             si l'état est invalide ou si la limite de vitesse est
	 *             négative
	 */
	public Route(Point a, Point b, int limiteVitesse, int etat){
		this.a = a;
		this.b = b;
		this.limiteVitesse = limiteVitesse;
		this.etat = etat;
		a.addRoute(this); // Dit au point a qu’il est connecté à b
		b.addRoute(this); // dit au point b qu’il est connecté à a dans la
							// classe Point, il existe une liste de boolean qui
							// dit si le point est lié aux autres ou non
	}

	/**
	 * @return retourne l'état de la route
	 */
	public int getEtat() {
		return this.etat;
	}

	/**
	 * @return retourne la limite de vitesse de la route
	 */
	public int getLimiteVitesse() {
		return this.limiteVitesse;
	}

	/**
	 * @return retourne le point de départ de la route
	 */
	public Point getPointDepart() {
		return a;
	}

	/**
	 * @return retourne le point d'arrivée de la route
	 */
	public Point getPointArrivee() {
		return b;
	}

	/**
	 * @return retourne la longueur de la route (en kilomètres)
	 */
	public double getLongueur() {
		return Math.sqrt((b.getX() - a.getX()) * (b.getX() - a.getX())
				+ (b.getY() - a.getY()) * (b.getY() - a.getY()));
	}
}
