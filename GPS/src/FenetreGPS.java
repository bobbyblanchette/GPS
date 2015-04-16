import java.awt.*;
import javax.swing.*;

/**
 * <h1>FenetreGPS</h1> Cette classe regroupe les panels d'éditage, d'itinéraire
 * ainsi que la carte dans une même JFrame
 * 
 */

public class FenetreGPS extends JFrame {

	private static final long serialVersionUID = 1L;
	private Carte carte;

	public static void main(String[] args) {
		// Force le programme a utiliser le style du OS courant (Windows)
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		FenetreGPS fenetre = new FenetreGPS();
		fenetre.setVisible(true);
		fenetre.carte.repaint();
	}

	// Définit une carte par défaut
	public FenetreGPS() {
		super("GPS");
		Reseau test = test1();

		carte = new Carte(test);
		creerGUI();
	}

	// Création d’un réseau test 1
	public Reseau test1() {
		Reseau test1 = new Reseau();

		// On place les intersections
		try {
			test1.addPoint(new Point(1, 4));
			test1.addPoint(new Point(4, 4));
			test1.addPoint(new Point(8, 4));
			test1.addPoint(new Point(6, 2));
			test1.addPoint(new Point(6, 6));
			test1.addPoint(new Point(6, 8));
		} catch (PointInexistantException e) {

		}

		// On crée les routes
		test1.addRoute(new Route(test1.getPoint(0), test1.getPoint(1), 50, 1));
		test1.addRoute(new Route(test1.getPoint(1), test1.getPoint(3), 40, 0));
		test1.addRoute(new Route(test1.getPoint(3), test1.getPoint(2), 80, 0));
		test1.addRoute(new Route(test1.getPoint(0), test1.getPoint(5), 40, 2));
		test1.addRoute(new Route(test1.getPoint(5), test1.getPoint(4), 40, 0));
		test1.addRoute(new Route(test1.getPoint(1), test1.getPoint(4), 50, 1));
		test1.addRoute(new Route(test1.getPoint(4), test1.getPoint(2), 50, 1));

		return test1;
	}

	// Création d’un réseau test 2
	public Reseau test2() {
		Reseau test2 = new Reseau();

		// On place les intersections
		try {
			test2.addPoint(new Point(1, 1));
			test2.addPoint(new Point(1, 5));
			test2.addPoint(new Point(5, 1));
			test2.addPoint(new Point(5, 5));
			test2.addPoint(new Point(3, 3));
		} catch (PointInexistantException e) {

		}

		// On crée les routes
		test2.addRoute(new Route(test2.getPoint(0), test2.getPoint(1), 60, 1));
		test2.addRoute(new Route(test2.getPoint(0), test2.getPoint(2), 40, 0));
		test2.addRoute(new Route(test2.getPoint(1), test2.getPoint(3), 30, 1));
		test2.addRoute(new Route(test2.getPoint(2), test2.getPoint(3), 40, 2));
		test2.addRoute(new Route(test2.getPoint(2), test2.getPoint(4), 70, 0));
		test2.addRoute(new Route(test2.getPoint(3), test2.getPoint(4), 70, 0));
		return test2;
	}

	// Construit l'interface graphique
	public void creerGUI() {
		// Configuration de la fenêtre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Création de la fenêtre à onglets
		JTabbedPane tab = new JTabbedPane();

		// Crée des variables pour représenter les couleurs du texte du
		// programme
		Color texteNoir = new Color(0, 0, 0);
		Color texteSemiGris = new Color(45, 45, 45);
		Color texteGris = new Color(90, 90, 90);

		// Crée et ajoute l’onglet Itinéraire à la fenêtre du programme
		PanelItineraire panelItineraire = new PanelItineraire(texteNoir,
				texteGris, carte);
		tab.addTab("Itinéraire", panelItineraire);

		// Crée et ajoute l’onglet Éditeur
		PanelEditeur panelEditeur = new PanelEditeur(texteNoir, texteSemiGris,
				carte);
		tab.addTab("Éditeur", panelEditeur);
		add(tab, c);

		// Configuration de la carte
		carte.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // bordures
		c.gridx = 1; // position
		c.gridy = 0;
		carte.setPreferredSize(new Dimension(500, 500)); // dimensions
		carte.setBackground(Color.WHITE); // couleur de fond
		add(carte, c); // Ajoute la carte à la fenêtre
		pack(); // Ajuste la taille de la fenêtre automatiquement
		setLocationRelativeTo(null); // La fenêtre apparaît au milieu de l’écran
	}
}
