package projet_lo02.view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.util.ArrayList;

import projet_lo02.model.Joueur;

public class GUI extends JFrame {
	// les composants de la vue
    private JFrame mainFrame;
	private JPanel contentPane;
    private JPanel panelMaitreGobi;
    private JButton boutonMaitreGobi;
    private JLabel labelMaitreGobi;
    private JPanel panelElite;
    private ArrayList<JButton> boutonsElite;
    private JLabel labelElite;
    private JPanel panelEtudiant;
    private ArrayList<JButton> boutonsEtudiant;
    private JLabel labelEtudiant;
    private JLabel labelJoueur;
	private JTextField pseudoJoueur;
    private JLabel labelProgramme;
	private Choice programme;
	private JTextField configPersonnage;
    private JLabel labelForce;
	private JTextField force;
    private JLabel labelDexterite;
	private JTextField dexterite;
    private JLabel labelResistance;
	private JTextField resistance;
    private JLabel labelConstitution;
	private JTextField constitution;
    private JLabel labelInitiative;
	private JTextField initiative;
    private JLabel labelZone;
	private Choice choixZone;
    private JLabel labelStrategie;
	private Choice choixStrategie;
    private JLabel labelReserviste;
	private Choice choixReserviste;
	private JLabel soldePoints;
    private JButton boutonOk;
    private JButton boutonValidation;
    private JButton boutonRandom;

	// le joueur
	private Joueur joueur;
	
	public GUI(final Joueur joueur) {
		this.joueur = joueur;
		this.initFenetre();
	}

	public void initFenetre() {
		// ++++++++++++++++++++++++++++++++++++++ Panneau principal +++++++++++++++++++++++++++++++++++++++++++
        mainFrame = new JFrame();
		mainFrame.setForeground(Color.BLACK);
		mainFrame.setTitle("Configuration Equipe");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.pack();
		contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder());
		contentPane.setBackground(Color.lightGray);
		mainFrame.setContentPane(contentPane);
		contentPane.setLayout(null); // politique de placement des composants dans la fenêtre
		mainFrame.setBounds(100, 100, 750, 500);

		// +++++++++++++++++++++++++++++++++++++ config personnage  ++++++++++++++++++++++++++++++++++++++++++++
        
        configPersonnage = new JTextField();
		configPersonnage.setBackground(Color.darkGray);
        configPersonnage.setForeground(Color.WHITE);
        configPersonnage.setHorizontalAlignment(JTextField.CENTER);
		configPersonnage.setFont(new Font("Arial", Font.ITALIC, 15));
		configPersonnage.setBounds(250, 140, 180, 20);
		contentPane.add(configPersonnage);

		// +++++++++++++++++++++++++++++++++++++ Maitre Gobi  +++++++++++++++++++++++++++++++++++++++++++++++++
		// Panel du maitre Gobi
		panelMaitreGobi = new JPanel();
		panelMaitreGobi.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelMaitreGobi.setBounds(40, 50, 45, 68);
		panelMaitreGobi.setLayout(new GridLayout(1, 1, 0, 0)); // politique de placement des composants dans ce panel
        
		Image imgMaitreGobi = new ImageIcon(this.getClass().getResource("/images/maitreGobi.png")).getImage();
        imgMaitreGobi = imgMaitreGobi.getScaledInstance(panelMaitreGobi.getWidth(), panelMaitreGobi.getHeight(), Image.SCALE_SMOOTH);

		boutonMaitreGobi = new JButton(); // pour représenter un personnage, utilisation d'un JButton
        boutonMaitreGobi.setIcon(new ImageIcon(imgMaitreGobi));
		panelMaitreGobi.add(boutonMaitreGobi);

		contentPane.add(panelMaitreGobi);

		// Etiquette Capitaine Gobi
		labelMaitreGobi = new JLabel("Capitaine Gobi");
		labelMaitreGobi.setFont(new Font("Arial", Font.PLAIN, 9));
		labelMaitreGobi.setBounds(30, 38, 75, 10);
		contentPane.add(labelMaitreGobi);

		// +++++++++++++++++++++++++++++++++++++ Les élites +++++++++++++++++++++++++++++++++++++++++++++++++++
		// Idem pour les élites
        
		panelElite = new JPanel();
		panelElite.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelElite.setBounds(150, 50, 180, 68);
		panelElite.setLayout(new GridLayout(1, 4, 0, 0));

        Image imgElite = new ImageIcon(this.getClass().getResource("/images/elite.png")).getImage();
        imgElite = imgElite.getScaledInstance(panelMaitreGobi.getWidth(), panelMaitreGobi.getHeight(), Image.SCALE_SMOOTH);

        boutonsElite = new ArrayList<>(4);
        for(int i = 0; i < 4; i++){
            JButton boutonElite = new JButton();
            boutonsElite.add(boutonElite);
            boutonElite.setIcon(new ImageIcon(imgElite));
            panelElite.add(boutonElite);
        }

		contentPane.add(panelElite);

		// Etiquette Les Elites
		labelElite = new JLabel("Les Elites");
		labelElite.setFont(new Font("Arial", Font.PLAIN, 9));
		labelElite.setBounds(220, 38, 75, 10);
		contentPane.add(labelElite);

		// +++++++++++++++++++++++++++++++++++++ Les étudiants +++++++++++++++++++++++++++++++++++++++++++++++++
		// Idem pour les étudiants de base
        
		panelEtudiant = new JPanel();
		panelEtudiant.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelEtudiant.setBounds(450, 50, 180, 270);
		panelEtudiant.setLayout(new GridLayout(4, 4, 0, 0));
		contentPane.add(panelEtudiant);

        Image imgEtudiant = new ImageIcon(this.getClass().getResource("/images/etudiant.png")).getImage();
        imgEtudiant = imgEtudiant.getScaledInstance(panelMaitreGobi.getWidth(), panelMaitreGobi.getHeight(), Image.SCALE_SMOOTH);

        boutonsEtudiant = new ArrayList<>(15);
        for(int i = 0; i < 15; i++){
            JButton boutonEtudiant = new JButton();
            boutonsEtudiant.add(boutonEtudiant);
            boutonEtudiant.setIcon(new ImageIcon(imgEtudiant));
            panelEtudiant.add(boutonEtudiant);
        }

        contentPane.add(panelEtudiant);

		// Etiquette Les étudiants de base
		labelEtudiant = new JLabel("Les Etudiants de base");
		labelEtudiant.setFont(new Font("Arial", Font.PLAIN, 9));
		labelEtudiant.setBounds(493, 38, 150, 10);
		contentPane.add(labelEtudiant);

		// +++++++++++++++++++++++++++++++++++++++++ Joueur +++++++++++++++++++++++++++++++++++++++++++++++++++
		labelJoueur = new JLabel("Joueur : ");
		labelJoueur.setFont(new Font("Arial", Font.PLAIN, 15));
		labelJoueur.setBounds(40, 13, 65, 15);
		contentPane.add(labelJoueur);
		pseudoJoueur = new JTextField();
		pseudoJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(pseudoJoueur.getText());
				joueur.setPseudo(pseudoJoueur.getText());
			}
		});
		pseudoJoueur.setFont(new Font("Arial", Font.PLAIN, 15));
		pseudoJoueur.setBounds(108, 13, 75, 15);
		contentPane.add(pseudoJoueur);

		// +++++++++++++++++++++++++++++++++++++++++ Programme  ++++++++++++++++++++++++++++++++++++++++++++++++
		labelProgramme = new JLabel("Programme : ");
		labelProgramme.setFont(new Font("Arial", Font.PLAIN, 15));
		labelProgramme.setBounds(200, 13, 105, 15);
		contentPane.add(labelProgramme);
		programme = new Choice();
        programme.setBackground(Color.WHITE);
		programme.setFont(new Font("Arial", Font.PLAIN, 12));
		programme.setBounds(308, 13, 40, 15);
		programme.add("ISI");
		programme.add("GM");
		programme.add("A2I");
		programme.add("RT");
		programme.add("MTE");
		programme.add("GI");
		programme.add("MM");
		contentPane.add(programme);

		// ++++++++++++++++++++++++++++++++++++++++++ Configuration des personnages ++++++++++++++++++++++++++++++++
		// Compteur des points à distribuer
		JLabel labelSoldePoints = new JLabel("Points à distribuer : ");
		labelSoldePoints.setFont(new Font("Arial", Font.PLAIN, 12));
		labelSoldePoints.setBounds(40, 135, 140, 15);
		contentPane.add(labelSoldePoints);
		soldePoints = new JLabel();
		soldePoints.setFont(new Font("Arial", Font.BOLD, 12));
		soldePoints.setText(Integer.toString(joueur.getSoldePoints()));
		soldePoints.setBounds(170, 135, 30, 15);
		contentPane.add(soldePoints);

		// Force
		labelForce = new JLabel("Force : ");
		labelForce.setFont(new Font("Arial", Font.PLAIN, 12));
		labelForce.setBounds(53, 200, 100, 15);
		contentPane.add(labelForce);
		force = new JTextField();
        force.setHorizontalAlignment(JTextField.CENTER);
		force.setFont(new Font("Arial", Font.PLAIN, 12));
		force.setBounds(175, 200, 20, 15);
		contentPane.add(force);

		// Dextérité
		labelDexterite = new JLabel("Dextérité : ");
		labelDexterite.setFont(new Font("Arial", Font.PLAIN, 12));
		labelDexterite.setBounds(53, 225, 100, 15);
		contentPane.add(labelDexterite);
		dexterite = new JTextField();
        dexterite.setHorizontalAlignment(JTextField.CENTER);
		dexterite.setFont(new Font("Arial", Font.PLAIN, 12));
		dexterite.setBounds(175, 225, 20, 15);
		contentPane.add(dexterite);

		// Résistance
		labelResistance = new JLabel("Résistance : ");
		labelResistance.setFont(new Font("Arial", Font.PLAIN, 12));
		labelResistance.setBounds(53, 250, 100, 15);
		contentPane.add(labelResistance);
		resistance = new JTextField();
        resistance.setHorizontalAlignment(JTextField.CENTER);
		resistance.setFont(new Font("Arial", Font.PLAIN, 12));
		resistance.setBounds(175, 250, 20, 15);
		contentPane.add(resistance);

		// Constitution
		labelConstitution = new JLabel("Constitution : ");
		labelConstitution.setFont(new Font("Arial", Font.PLAIN, 12));
		labelConstitution.setBounds(53, 275, 100, 15);
		contentPane.add(labelConstitution);
		constitution = new JTextField();
        constitution.setHorizontalAlignment(JTextField.CENTER);
		constitution.setFont(new Font("Arial", Font.PLAIN, 12));
		constitution.setBounds(175, 275, 20, 15);
		contentPane.add(constitution);

		// Initiative
		labelInitiative = new JLabel("Initiative : ");
		labelInitiative.setFont(new Font("Arial", Font.PLAIN, 12));
		labelInitiative.setBounds(53, 300, 100, 15);
		contentPane.add(labelInitiative);
		initiative = new JTextField();
        initiative.setHorizontalAlignment(JTextField.CENTER);
		initiative.setFont(new Font("Arial", Font.PLAIN, 12));
		initiative.setBounds(175, 300, 20, 15);
		contentPane.add(initiative);

		// Affectation Zone
		labelZone = new JLabel("Affectation : ");
		labelZone.setFont(new Font("Arial", Font.PLAIN, 12));
		labelZone.setBounds(225, 200, 80, 15);
		contentPane.add(labelZone);

		choixZone = new Choice();
		choixZone.setFont(new Font("Arial", Font.PLAIN, 12));
		choixZone.setBounds(305, 200, 125, 15);
        choixZone.setBackground(Color.WHITE);
        choixZone.add("");
		choixZone.add("Administration");
		choixZone.add("BDE");
		choixZone.add("Bibliothèque");
		choixZone.add("Halle industrielle");
		choixZone.add("Halle sportive");
		contentPane.add(choixZone);

		// type de stratégie
		labelStrategie = new JLabel("Stratégie : ");
		labelStrategie.setFont(new Font("Arial", Font.PLAIN, 12));
		labelStrategie.setBounds(225, 225, 80, 15);
		contentPane.add(labelStrategie);
		choixStrategie = new Choice();
		choixStrategie.setFont(new Font("Arial", Font.PLAIN, 12));
		choixStrategie.setBounds(305, 225, 125, 15);
        choixStrategie.setBackground(Color.WHITE);
        choixStrategie.add("");
		choixStrategie.add("Offensive");
		choixStrategie.add("Défensive");
		choixStrategie.add("Aléatoire");
		contentPane.add(choixStrategie);

		// Réserviste ?
        labelReserviste = new JLabel("Réserviste : ");
        labelReserviste.setFont(new Font("Arial", Font.PLAIN, 12));
		labelReserviste.setBounds(225, 250, 80, 15);
        contentPane.add(labelReserviste);
        choixReserviste = new Choice();
        choixReserviste.setFont(new Font("Arial", Font.PLAIN, 12));
        choixReserviste.setBounds(305, 250, 125, 15);
        choixReserviste.setBackground(Color.WHITE);
        choixReserviste.add("");
        choixReserviste.add("OUI");
        choixReserviste.add("NON");
        contentPane.add(choixReserviste);

        // Boutons
		boutonOk = new JButton("OK");
		boutonOk.setFont(new Font("Arial", Font.PLAIN, 15));
        boutonOk.setFocusable(false);
		boutonOk.setBounds(225, 275, 205, 40);
		contentPane.add(boutonOk);

		boutonValidation = new JButton("VALIDER");
		boutonValidation.setFont(new Font("Arial", Font.PLAIN, 15));
        boutonValidation.setFocusable(false);
		boutonValidation.setBounds(450, 325, 180, 40);
		contentPane.add(boutonValidation);

        boutonRandom = new JButton("Randomiser la configuration");
        boutonRandom.setFont(new Font("Arial", Font.PLAIN, 10));
        boutonRandom.setFocusable(false);
        boutonRandom.setBounds(360, 13, 270, 15);
        contentPane.add(boutonRandom);


		// ++++++++++++++++++++++++++++++++++++++++++ Habillage ++++++++++++++++++++++++++++++++++++++++++++
		// panneau configuration
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBounds(40, 150, 405, 215);
		contentPane.add(panel);

    }

    public JButton getBoutonRandom() {
		return boutonRandom;
	}

	//getters
	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getConfigPersonnage() {
		return configPersonnage;
	}

	public JLabel getLabelForce() {
		return labelForce;
	}

	public JTextField getForce() {
		return force;
	}

	public JLabel getLabelDexterite() {
		return labelDexterite;
	}

	public JTextField getDexterite() {
		return dexterite;
	}

	public JLabel getLabelResistance() {
		return labelResistance;
	}

	public JTextField getResistance() {
		return resistance;
	}

	public JLabel getLabelConstitution() {
		return labelConstitution;
	}

	public JTextField getConstitution() {
		return constitution;
	}

	public JLabel getLabelInitiative() {
		return labelInitiative;
	}

	public JTextField getInitiative() {
		return initiative;
	}

	public JLabel getLabelZone() {
		return labelZone;
	}

	public Choice getChoixZone() {
		return choixZone;
	}

	public JLabel getLabelStrategie() {
		return labelStrategie;
	}

	public Choice getChoixStrategie() {
		return choixStrategie;
	}

	public JLabel getLabelReserviste() {
		return labelReserviste;
	}

	public Choice getChoixReserviste() {
		return choixReserviste;
	}

	public JLabel getSoldePoints() {
		return soldePoints;
	}

	public JTextField getPseudoJoueur() {
		return pseudoJoueur;
	}

	public Choice getProgramme() {
		return programme;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public JButton getBoutonMaitreGobi() {
		return boutonMaitreGobi;
	}

	public JPanel getPanelMaitreGobi() {
		return panelMaitreGobi;
	}

	public JLabel getLabelMaitreGobi() {
		return labelMaitreGobi;
	}

	public JPanel getPanelElite() {
		return panelElite;
	}

	public ArrayList<JButton> getBoutonsElite() {
		return boutonsElite;
	}

	public JLabel getLabelElite() {
		return labelElite;
	}

	public JPanel getPanelEtudiant() {
		return panelEtudiant;
	}

	public ArrayList<JButton> getBoutonsEtudiant() {
		return boutonsEtudiant;
	}

	public JLabel getLabelEtudiant() {
		return labelEtudiant;
	}

	public JLabel getLabelJoueur() {
		return labelJoueur;
	}

	public JLabel getLabelProgramme() {
		return labelProgramme;
	}

	public JButton getBoutonOk() {
		return boutonOk;
	}

	public JButton getBoutonValidation() {
		return boutonValidation;
	}

    public JFrame getMainFrame() {
        return mainFrame;
    }

}
