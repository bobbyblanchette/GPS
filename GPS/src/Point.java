import java.util.ArrayList;

public class Point {
	// Coordonn�es x et y d�un point
	private double x, y;
	// Liste qui contient toutes les routes auxquelles le point est connect�
	public ArrayList<Route> routes = new ArrayList<Route>();

	/**
	 * Construit un point � partir de ses coordonn�es
	 * 
	 * @param x
	 *            position horizontale du point par rapport � l'origine (en
	 *            kilom�tres)
	 * @param y
	 *            position verticale du point par rapport � l'origine (en
	 *            kilom�tres)
	 * @throws IllegalArgumentException
	 *             si l'une des coordonn�es est n�gative
	 */
	public Point(double x, double y) throws PointInexistantException {
		if (x < 0 || y < 0) {
			throw new PointInexistantException(
					"Veuillez entrer des coordon�es positives.");
		}
		if (x > 10 || y > 10) {
			throw new PointInexistantException(
					"Veuillez entrer des coordon�es qui se trouvent � l'int�rieur de la carte");
		}
		this.x = x;
		this.y = y;
	}

	/**
	 * Relie le point � une route
	 * 
	 * @param route
	 *            Route qui se connecte au point
	 */
	public void addRoute(Route route) {
		routes.add(route);
	}

	/**
	 * @return retourne la position horizontale de la route (en kilom�tres)
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * @return retourne la position verticale de la route (en kilom�tres)
	 */
	public double getY() {
		return this.y;
	}
}
