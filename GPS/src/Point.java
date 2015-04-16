import java.util.ArrayList;

public class Point {
	// Coordonnées x et y d’un point
	private double x, y;
	// Liste qui contient toutes les routes auxquelles le point est connecté
	public ArrayList<Route> routes = new ArrayList<Route>();

	/**
	 * Construit un point à partir de ses coordonnées
	 * 
	 * @param x
	 *            position horizontale du point par rapport à l'origine (en
	 *            kilomètres)
	 * @param y
	 *            position verticale du point par rapport à l'origine (en
	 *            kilomètres)
	 * @throws IllegalArgumentException
	 *             si l'une des coordonnées est négative
	 */
	public Point(double x, double y) throws PointInexistantException {
		if (x < 0 || y < 0) {
			throw new PointInexistantException(
					"Veuillez entrer des coordonées positives.");
		}
		if (x > 10 || y > 10) {
			throw new PointInexistantException(
					"Veuillez entrer des coordonées qui se trouvent à l'intérieur de la carte");
		}
		this.x = x;
		this.y = y;
	}

	/**
	 * Relie le point à une route
	 * 
	 * @param route
	 *            Route qui se connecte au point
	 */
	public void addRoute(Route route) {
		routes.add(route);
	}

	/**
	 * @return retourne la position horizontale de la route (en kilomètres)
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * @return retourne la position verticale de la route (en kilomètres)
	 */
	public double getY() {
		return this.y;
	}
}
