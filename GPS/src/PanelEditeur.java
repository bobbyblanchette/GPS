import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * <h1>PanelEditeur</h1> Cette classe contient tous les éléments graphiques du
 * panneau d'édition
 * <p>
 */

public class PanelEditeur extends JPanel {

	private static final long serialVersionUID = 1L;

	// PanelEditeur Components
	private JLabel ajouterPointLabel; // Titre « Ajouter Point »

	private JLabel ajouterPointXLabel; // Coordonnée X
	private JTextField ajouterPointXTextField;

	private JLabel ajouterPointYLabel; // Coordonnée Y
	private JTextField ajouterPointYTextField;

	private JButton ajouterPointBouton; // Bouton d’envoi pour point

	private JLabel ajouterRouteLabel; // Titre « Ajouter Route »

	private JLabel ajouterRoutePoint1Label; // Point 1 de la route
	private JTextField ajouterRoutePoint1TextField;

	private JLabel ajouterRoutePoint2Label; // Point 2 de la route
	private JTextField ajouterRoutePoint2TextField;

	private JLabel ajouterRouteVitesseLabel; // Vitesse de la route
	private JTextField ajouterRouteVitesseTextField;

	private JLabel ajouterRouteEtatLabel; // État de la route
	private JTextField ajouterRouteEtatTextField;

	private JButton ajouterRouteBouton; // Bouton d’envoi pour route

	private JButton effacerBouton; // Bouton de réinitialisation

	public PanelEditeur(Color couleurDuTexte, Color semiGris, final Carte carte) {
		super(new GridBagLayout());
		// Construction du panel d'edition

		GridBagConstraints c = new GridBagConstraints();

		// Crée et position le titre « Ajouter un point »
		ajouterPointLabel = new JLabel("<html><u>Ajouter un Point</html></u>");
		c.insets = new Insets(30, 0, 0, 0);
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.2;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		add(ajouterPointLabel, c);

		// Crée et positionne « X: »
		c.insets = new Insets(10, 0, 0, 0);
		ajouterPointXLabel = new JLabel("X:");
		ajouterPointXLabel.setForeground(semiGris);
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(ajouterPointXLabel, c);

		// Crée et positionne ajouterPointXTextField
		ajouterPointXTextField = new JTextField(2);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 1;
		c.gridy = 1;
		add(ajouterPointXTextField, c);

		// Crée et positionne « Y: »
		ajouterPointYLabel = new JLabel("Y:");
		ajouterPointYLabel.setForeground(semiGris);
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 2;
		c.gridy = 1;
		add(ajouterPointYLabel, c);

		// Crée et positionne ajouterPointYTextField
		ajouterPointYTextField = new JTextField(2);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 3;
		c.gridy = 1;
		add(ajouterPointYTextField, c);

		// Crée et positionne ajouterPointBouton
		ajouterPointBouton = new JButton(">");
		ajouterPointBouton.setMargin(new Insets(2, 2, 2, 2));
		// À l’appui du bouton, récupère les données, les valide et dessine le
		// point sur la carte
		ajouterPointBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Récupère les données
					double x = Double.parseDouble(ajouterPointXTextField
							.getText());
					double y = Double.parseDouble(ajouterPointYTextField
							.getText());
					// Crée le point à l’aide des coordonnées
					carte.getReseau().addPoint(new Point(x, y));
					// Dessine le point sur la carte
					carte.repaint();
				} catch (IllegalArgumentException error) {
					JOptionPane.showMessageDialog(null,
							"Veuillez entrer des coordonnées valides.",
							"Avertissement", JOptionPane.ERROR_MESSAGE);
				} catch (PointInexistantException error) {
					JOptionPane.showMessageDialog(null, error.getMessage(),
							"Avertissement", JOptionPane.ERROR_MESSAGE);
				} finally { // Vide les boîtes de saisie de texte
					ajouterPointXTextField.setText("");
					ajouterPointYTextField.setText("");
				}
			}
		});
		c.gridx = 4;
		c.gridy = 1;
		add(ajouterPointBouton, c);

		// Crée et positionne le titre « Ajouter une route »
		ajouterRouteLabel = new JLabel("<html><u>Ajouter une Route</html></u>");
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5;
		add(ajouterRouteLabel, c);

		// Crée et positionne « Point 1: »
		ajouterRoutePoint1Label = new JLabel("Point 1:");
		ajouterRoutePoint1Label.setForeground(semiGris);
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		add(ajouterRoutePoint1Label, c);

		// Crée et positionne « Point 2: »
		ajouterRoutePoint2Label = new JLabel("Point 2:");
		ajouterRoutePoint2Label.setForeground(semiGris);
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 0;
		c.gridy = 4;
		add(ajouterRoutePoint2Label, c);

		// Crée et positionne « Vitesse »
		ajouterRouteVitesseLabel = new JLabel("Vitesse:");
		ajouterRouteVitesseLabel.setForeground(semiGris);
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 0;
		c.gridy = 5;
		add(ajouterRouteVitesseLabel, c);

		// Crée et positionne « État »
		ajouterRouteEtatLabel = new JLabel("État:");
		ajouterRouteEtatLabel.setForeground(semiGris);
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 0;
		c.gridy = 6;
		add(ajouterRouteEtatLabel, c);

		// Crée et positionne ajouterRoutePoint1TextField
		ajouterRoutePoint1TextField = new JTextField(5);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 3;
		add(ajouterRoutePoint1TextField, c);

		// Crée et positionne ajouterRoutePoint2TextField
		ajouterRoutePoint2TextField = new JTextField(5);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 2;
		c.gridy = 4;
		add(ajouterRoutePoint2TextField, c);

		// Crée et positionne ajouterRouteVitesseTextField
		ajouterRouteVitesseTextField = new JTextField(5);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 2;
		c.gridy = 5;
		add(ajouterRouteVitesseTextField, c);

		// Crée et positionne ajouterRouteEtatTextField
		ajouterRouteEtatTextField = new JTextField(5);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 2;
		c.gridy = 6;
		add(ajouterRouteEtatTextField, c);

		// Crée et positionne ajouterRouteBouton
		ajouterRouteBouton = new JButton(">");
		ajouterRouteBouton.setMargin(new Insets(2, 21, 2, 21));
		// À l’appui du bouton, récupère les données, les valide et dessine la
		// ligne sur la carte
		ajouterRouteBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupère les données des deux points
				String point1 = ajouterRoutePoint1TextField.getText();
				ajouterRoutePoint1TextField.setText("");
				String point2 = ajouterRoutePoint2TextField.getText();
				ajouterRoutePoint2TextField.setText("");
				try {
					// Récupère la vitesse saisie
					int vitesse = Integer.parseInt(ajouterRouteVitesseTextField
							.getText());
					try {
						// Récupère l’état saisi
						int etat = Integer.parseInt(ajouterRouteEtatTextField
								.getText());
						if (etat > 2) {
							throw new IllegalArgumentException();
						}
						// on vérifie que l’entrée a la bonne longueur
						try {
							// Les entrées des points ont la bonne longueur
							if (point1.length() != 1 || point2.length() != 1) {

								throw new IllegalArgumentException();
							}
							point1 = point1.toLowerCase();
							point2 = point2.toLowerCase();
							char pointA = point1.charAt(0);
							char pointB = point2.charAt(0);

							// Les entrées des points se situent entre [a-z]
							if (pointA < 97 || pointA > 122 || pointB < 97
									|| pointB > 122) {
								throw new IllegalArgumentException();
							}
							int a = (int) pointA - (int) 'a';
							int b = (int) pointB - (int) 'a';
							// Les points existent dans la carte
							if (a >= carte.getReseau().getPointQuantite()
									|| b >= carte.getReseau()
											.getPointQuantite()) {
								throw new PointInexistantException(
										"Veuillez entrer des point existant.");
							}
							carte.getReseau().addRoute(
									new Route(carte.getReseau().getPoint(a),
											carte.getReseau().getPoint(b),
											vitesse, etat));
							// Dessine la ligne
							carte.repaint();

						} catch (IllegalArgumentException error) {
							JOptionPane.showMessageDialog(null,
									"Veuillez entrer des point valide.",
									"Avertissement", JOptionPane.ERROR_MESSAGE);
						} catch (PointInexistantException error) {
							JOptionPane.showMessageDialog(null,
									error.getMessage(), "Avertissement",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (IllegalArgumentException error) {
						JOptionPane.showMessageDialog(null,
								"Veuillez entrer un état valide.",
								"Avertissement", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IllegalArgumentException error) {
					JOptionPane.showMessageDialog(null,
							"Veuillez entrer une vitesse valide.",
							"Avertissement", JOptionPane.ERROR_MESSAGE);
				} finally { // Vide la boîte de saisie de la vitesse
					ajouterRouteEtatTextField.setText("");
					ajouterRouteVitesseTextField.setText("");
				}

			}
		});
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 7;
		c.weightx = 1;
		c.weighty = 0.75;
		add(ajouterRouteBouton, c);

		// Crée et positionne effacerBouton
		effacerBouton = new JButton("Effacer");
		// Lorsque ce bouton est appuyé, on efface tous les points et lignes du
		// réseau
		effacerBouton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
				carte.effacer();
				carte.setDessineSolution(false);
			}
		});
		c.insets = new Insets(0, 0, 30, 0);
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridx = 1;
		c.gridy = 8;
		c.weighty = 0.25;
		add(effacerBouton, c);
	}
}
