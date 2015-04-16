import java.awt.Color;
import java.awt.Dimension;
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
 * <h1>PanelItineraire</h1> Cette classe contient tous les éléments graphiques
 * du panneau d'itinéraire
 * <p>
 */

public class PanelItineraire extends JPanel {

	private static final long serialVersionUID = 1L;

	// PanelItineraire Components

	private JLabel departLabel; // Point de départ
	private JTextField departTextField;

	private JLabel destinationLabel; // Point d’arrivée
	private JTextField destinationTextField;

	private JButton itineraireBouton; // Bouton d’envoi

	private JLabel tempsLabel; // Temps de trajet
	private JLabel temps;

	private JButton effacerBouton; // Bouton de réinitialisation

	public PanelItineraire(Color couleurDuTexte, Color gris, final Carte carte) {
		super(new GridBagLayout());
		// Construction du panel d'itinéraire

		GridBagConstraints c = new GridBagConstraints();

		// Padding de 30pixels dans le haut du panel
		c.insets = new Insets(30, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH;

		// Texte « Départ: »
		departLabel = new JLabel("<html><u>Départ</u></html>");
		departLabel.setForeground(couleurDuTexte);
		add(departLabel, c);

		// Crée et positionne departTextField
		departTextField = new JTextField(7);
		c.insets = new Insets(10, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 1;
		add(departTextField, c);

		// Texte « Destination: »
		destinationLabel = new JLabel("<html><u>Destination</html></u>");
		destinationLabel.setForeground(couleurDuTexte);
		c.gridx = 0;
		c.gridy = 2;
		add(destinationLabel, c);

		// Crée et positionne destinationTextField
		destinationTextField = new JTextField(7);
		c.gridx = 0;
		c.gridy = 3;
		add(destinationTextField, c);

		// Crée et positionne itineraireBouton
		itineraireBouton = new JButton(">");
		itineraireBouton.setMargin(new Insets(2, 20, 2, 20));
		// À l’appui du bouton, récupère les données, les valide, calcule le
		// trajet et dessine le trajet sur la carte
		itineraireBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupère les données
				String pointDepart = departTextField.getText();
				departTextField.setText("");
				String pointArrivee = destinationTextField.getText();
				destinationTextField.setText("");
				try {
					// Vérifie si les entrées des points ont la bonne longueur
					if (pointDepart.length() != 1 || pointArrivee.length() != 1) {
						throw new IllegalArgumentException();
					}
					pointDepart = pointDepart.toLowerCase();
					char pointD = pointDepart.charAt(0);
					pointArrivee = pointArrivee.toLowerCase();
					char pointA = pointArrivee.charAt(0);

					// Les points se situent entre [a-z]
					if (pointD < 97 || pointD > 122 || pointA < 97
							|| pointA > 122) {
						throw new IllegalArgumentException();
					}
					int a = (int) pointD - (int) 'a';
					int b = (int) pointA - (int) 'a';
					// Les points existent dans la carte
					if (a >= carte.getReseau().getPointQuantite()
							|| b >= carte.getReseau().getPointQuantite()) {
						throw new PointInexistantException(
								"Veuillez entrer un point existant.");
					}
					int time;

					// Calcule le trajet et le temps
					time = (int) carte.getReseau().calcTempsMinimal(
							carte.getReseau().getPoint(a),
							carte.getReseau().getPoint(b));

					// Dessine la solution
					carte.setDessineSolution(true);
					carte.repaint();

					// Convertit le temps en heures:minutes:secondes
					int heures = time / 3600;
					int secondesRestantes = time % 3600;
					int minutes = secondesRestantes / 60;
					secondesRestantes = secondesRestantes % 60;
					String heure = "" + heures + ":";
					// Format du temps à l’écran
					if (minutes < 10) {
						heure += "0";
					}
					heure += minutes + ":";
					if (secondesRestantes < 10) {
						heure += "0";
					}
					heure += (secondesRestantes);
					temps.setText(heure);

				} catch (IllegalArgumentException error) {
					JOptionPane.showMessageDialog(null,
							"Veuillez entrer un point valide.",
							"Avertissement", JOptionPane.ERROR_MESSAGE);
				} catch (CheminImpossibleException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),
							"Avertissement", JOptionPane.ERROR_MESSAGE);
				} catch (PointInexistantException error) {
					JOptionPane.showMessageDialog(null, error.getMessage(),
							"Avertissement", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		c.gridx = 0;
		c.gridy = 4;
		add(itineraireBouton, c);

		// Crée et positionne le texte « Temps: »
		tempsLabel = new JLabel("<html><u>Temps</html></u>");
		tempsLabel.setForeground(couleurDuTexte);
		c.gridx = 0;
		c.gridy = 5;
		add(tempsLabel, c);

		// Crée et positionne le champ du temps
		temps = new JLabel("0:00:00"); // valeur par défaut
		temps.setForeground(gris);
		c.gridx = 0;
		c.gridy = 6;
		c.weighty = 0.75;
		add(temps, c);

		// Crée et positionne effacerBouton
		effacerBouton = new JButton("Effacer");
		// Lorsque ce bouton est appuyé, on efface l’itinéraire précédemment
		// demandé
		effacerBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carte.setDessineSolution(false);
				carte.repaint();
				temps.setText("0:00:00");
			}
		});
		c.insets = new Insets(0, 0, 30, 0);
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridx = 0;
		c.gridy = 7;
		c.weighty = 0.25;
		add(effacerBouton, c);

		setPreferredSize(new Dimension(100, 457));
	}
}
