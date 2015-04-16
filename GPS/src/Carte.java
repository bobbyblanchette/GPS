import java.awt.*;
import javax.swing.*;

// Cette classe contient les m�thodes qui permettent d�afficher un r�seau dans notre interface
public class Carte extends JPanel {

	private static final long serialVersionUID = 1L;

	// Le r�seau contenu par la carte
	private Reseau reseau;

	// D�termine si la m�thode paintComponent doit dessiner le chemin le plus
	// court (dessineSolution=true) ou le r�seau (dessineSolution=false)
	private boolean dessineSolution = false;

	// �chelle de la carte. D�termine les dimensions, en pixels, d�un carr� sur
	// la grille
	private int carreTaille = 50;

	public Carte(Reseau reseau) {
		this.reseau = reseau;
	}

	/**
	 * Dessine tous les points et toutes les routes du r�seau sur la carte
	 * 
	 * @param g
	 *            contexte graphique dans lequel on dessine
	 */
	public void paintComponent(Graphics g) {

		// Pr�pare Graphics2D, sur lequel nous allons dessiner la carte
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1)); // taille du pinceau

		// Enl�ve tout ce qui a �t� dessin� pr�c�demment
		g2.setBackground(Color.WHITE);
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
		// Dessine la grille
		g2.setColor(Color.LIGHT_GRAY);
		// Dessine les lignes verticales
		for (int i = 0; (i * carreTaille) < this.getWidth(); i++) {
			g2.drawLine(i * carreTaille, 0, i * carreTaille, this.getHeight());

		}
		// Dessine les lignes horizontales
		for (int i = 0; (i * carreTaille) < this.getHeight(); i++) {
			g2.drawLine(0, i * carreTaille, this.getWidth(), i * carreTaille);
		}

		// Dessine la l�gende
		int rayon = 8; // Rayon des cercles d�finissant les intersections
		g2.setFont(new Font("Tahoma", Font.PLAIN, (int) (rayon * 1.8)));
		g2.setColor(Color.DARK_GRAY);
		g2.drawString("1 carr� = 1 km", carreTaille / 3, this.getHeight()
				- carreTaille / 3);

		// Dessine les routes
		dessinerRoutes(g2);

		// Dessine les points
		dessinerPoints(g2, rayon);
	}

	private void dessinerPoints(Graphics2D g, int rayon) {
		g.setColor(Color.BLACK);
		// Dessine chaque point du r�seau
		for (int i = 0; i < reseau.getPointQuantite(); i++) {
			// Trouve les coordonn�es de l�intersection en pixels selon son
			// emplacement sur la grille
			double x = reseau.getPoint(i).getX() * carreTaille - rayon;
			double y = reseau.getPoint(i).getY() * carreTaille - rayon;
			// Dessine le point
			g.setColor(Color.black);
			g.fillOval((int) x, (int) y, (int) rayon * 2, (int) rayon * 2);
			// Dessine le nom du point
			g.setColor(Color.blue);
			// Assigne une lettre � l�intersection correspondant � sa position
			// dans la liste (index de 0 = A, index de 1 = B, etc.)
			char a = (char) (i + 'A');
			g.drawString("" + a, (int) x, (int) (y + rayon * 4));
		}
	}

	private void dessinerRoutes(Graphics2D g) {
		g.setStroke(new BasicStroke(3)); // taille du pinceau
		// Dessine chaque route du r�seau
		for (int i = 0; i < reseau.getRouteQuantite(); i++) {
			Point startPoint = reseau.getRoute(i).getPointDepart();
			Point endPoint = reseau.getRoute(i).getPointArrivee();
			// Trouve les coordonn�es des points de la route en pixels selon
			// leur position sur la grille
			double initX = startPoint.getX() * carreTaille;
			double initY = startPoint.getY() * carreTaille;
			double endX = endPoint.getX() * carreTaille;
			double endY = endPoint.getY() * carreTaille;
			int state = reseau.getRoute(i).getEtat();
			// Dessine la route d�une diff�rente couleur selon son �tat
			switch (state) {
			case 0:
				g.setColor(Color.green);
				break; // La circulation est fluide
			case 1:
				g.setColor(Color.yellow);
				break; // Il y a un trafic mod�r� sur cette route
			case 2:
				g.setColor(Color.red);
				break; // Il y a un accident sur cette route
			}
			// Si le programme doit dessiner le chemin le plus court, toutes les
			// routes sont noires sauf celles qui font parties du chemin la
			// solution
			if (dessineSolution == true) {
				if (!reseau.getCheminOptimal().contains(reseau.getRoute(i))) {
					g.setColor(Color.black);
				}
			}
			g.drawLine((int) initX, (int) initY, (int) endX, (int) endY);
			g.setColor(Color.gray);
			// On dessine la limite de vitesse au milieu de la route
			g.drawString("" + reseau.getRoute(i).getLimiteVitesse() + "Km/h",
					(int) ((initX + endX) / 2), (int) ((initY + endY) / 2));
		}
	}

	/**
	 * D�termine si, au prochain appel de paintComponent(Graphics g), le
	 * programme doit dessiner le trajet le plus court (true) ou la carte
	 * normale (false)
	 * 
	 * @param dessineSolution
	 *            true signifie qu'on dessine le trajet le plus court
	 */
	public void setDessineSolution(boolean dessineSolution) {
		this.dessineSolution = dessineSolution;
	}

	/**
	 * Supprime toutes les routes et tous les points sur la carte Ensuite, elle
	 * redessine la carte
	 */
	public void effacer() {
		reseau.effacer();
		paintComponent(this.getGraphics());
	}

	/**
	 * @return retourne le r�seau de la carte
	 */
	public Reseau getReseau() {
		return this.reseau;
	}
}
