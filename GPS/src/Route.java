public class Route {
	private Point a, b; // Les deux points formant une ligne
	private int limiteVitesse, etat;// �tat d�une route (circulation fluide,
									// trafic, accident)

	/**
	 * Construit une route � partir de deux points, une vitesse et un �tat
	 * 
	 * @param a
	 *            Point de d�part de la route
	 * @param b
	 *            Point d'arriv�e de la route
	 * @param limiteVitesse
	 *            limite de vitesse de la route
	 * @param etat
	 *            �tat de la route (0, 1 ou 2)
	 * @throws IllegalArgumentException
	 *             si l'�tat est invalide ou si la limite de vitesse est
	 *             n�gative
	 */
	public Route(Point a, Point b, int limiteVitesse, int etat){
		this.a = a;
		this.b = b;
		this.limiteVitesse = limiteVitesse;
		this.etat = etat;
		a.addRoute(this); // Dit au point a qu�il est connect� � b
		b.addRoute(this); // dit au point b qu�il est connect� � a dans la
							// classe Point, il existe une liste de boolean qui
							// dit si le point est li� aux autres ou non
	}

	/**
	 * @return retourne l'�tat de la route
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
	 * @return retourne le point de d�part de la route
	 */
	public Point getPointDepart() {
		return a;
	}

	/**
	 * @return retourne le point d'arriv�e de la route
	 */
	public Point getPointArrivee() {
		return b;
	}

	/**
	 * @return retourne la longueur de la route (en kilom�tres)
	 */
	public double getLongueur() {
		return Math.sqrt((b.getX() - a.getX()) * (b.getX() - a.getX())
				+ (b.getY() - a.getY()) * (b.getY() - a.getY()));
	}
}
