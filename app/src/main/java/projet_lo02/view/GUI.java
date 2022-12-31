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

import projet_lo02.model.Joueur;

public class GUI extends JFrame {
	// les composants de la vue
	private JPanel contentPane;
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
    private JLabel labelAffectation;
	private Choice choixAffectation;
    private JLabel labelStrategie;
	private Choice choixStrategie;
    private JLabel labelReserviste;
	private Choice choixReserviste;
	private JLabel soldePoints;
	private JTextField pseudoJoueur;
	private Choice programme;

	// le joueur
	private Joueur joueur;
	
	public GUI(final Joueur joueur) {
		this.joueur = joueur;
		this.initFenetre();
	}

	public void initFenetre() {
		// ++++++++++++++++++++++++++++++++++++++ Panneau principal +++++++++++++++++++++++++++++++++++++++++++
        
		setForeground(Color.BLACK);
		setTitle("Configuration Equipe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new CompoundBorder());
		this.contentPane.setBackground(Color.lightGray);
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null); // politique de placement des composants dans la fenêtre
		this.setBounds(100, 100, 620, 405);

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
		JPanel panelMaitre = new JPanel();
		panelMaitre.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelMaitre.setBounds(40, 50, 45, 68);
		panelMaitre.setLayout(new GridLayout(1, 1, 0, 0)); // politique de placement des composants dans ce panel
        
		Image imgMaitreGobi = new ImageIcon(this.getClass().getResource("/images/maitreGobi.png")).getImage();
		JButton boutonMaitreGobi = new JButton(); // pour représenter un personnage, utilisation d'un JButton
        imgMaitreGobi = imgMaitreGobi.getScaledInstance(panelMaitre.getWidth(), panelMaitre.getHeight(), Image.SCALE_SMOOTH);
        boutonMaitreGobi.setIcon(new ImageIcon(imgMaitreGobi));

		panelMaitre.add(boutonMaitreGobi);
		contentPane.add(panelMaitre);

		// Etiquette Capitaine Gobi
		JLabel lblNewLabel = new JLabel("Capitaine Gobi");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 9));
		lblNewLabel.setBounds(30, 38, 75, 10);
		contentPane.add(lblNewLabel);

		// +++++++++++++++++++++++++++++++++++++ Les élites +++++++++++++++++++++++++++++++++++++++++++++++++++
		// Idem pour les élites
        
		JPanel panelElite = new JPanel();
		panelElite.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelElite.setBounds(150, 50, 180, 68);
		panelElite.setLayout(new GridLayout(1, 4, 0, 0));

        Image imgElite = new ImageIcon(this.getClass().getResource("/images/elite.png")).getImage();
        imgElite = imgElite.getScaledInstance(panelMaitre.getWidth(), panelMaitre.getHeight(), Image.SCALE_SMOOTH);

		JButton[] jb = new JButton[4];
		for (int i = 0; i < 4; i++) {
			jb[i] = new JButton();
			jb[i].setIcon(new ImageIcon(imgElite));
			panelElite.add(jb[i]);
		}
		contentPane.add(panelElite);
		// Etiquette Les Elites
		JLabel lblNewLabel_1 = new JLabel("Les Elites");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(220, 38, 75, 10);
		contentPane.add(lblNewLabel_1);

		// +++++++++++++++++++++++++++++++++++++ Les étudiants +++++++++++++++++++++++++++++++++++++++++++++++++
		// Idem pour les étudiants de base
        
		JPanel panelEtu = new JPanel();
		panelEtu.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelEtu.setBounds(450, 50, 180, 270);
		panelEtu.setLayout(new GridLayout(4, 4, 0, 0));
		contentPane.add(panelEtu);

        Image imgEtudiant = new ImageIcon(this.getClass().getResource("/images/etudiant.png")).getImage();
        imgEtudiant = imgEtudiant.getScaledInstance(panelMaitre.getWidth(), panelMaitre.getHeight(), Image.SCALE_SMOOTH);

		// Etiquette Les étudiants de base
		JLabel lblNewLabel_2 = new JLabel("Les Etudiants de base");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 9));
		lblNewLabel_2.setBounds(493, 38, 150, 10);
		contentPane.add(lblNewLabel_2);
		JButton[] jb2 = new JButton[15];
		for (int i = 0; i < 15; i++) {
			jb2[i] = new JButton();
			jb2[i].setIcon(new ImageIcon(imgEtudiant));
			panelEtu.add(jb2[i]);
		}

		// +++++++++++++++++++++++++++++++++++++++++ Joueur +++++++++++++++++++++++++++++++++++++++++++++++++++
		JLabel lblNewLabel_3 = new JLabel("Joueur : ");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(40, 13, 65, 15);
		contentPane.add(lblNewLabel_3);
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
		JLabel lblNewLabel_13 = new JLabel("Programme : ");
		lblNewLabel_13.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_13.setBounds(200, 13, 105, 15);
		contentPane.add(lblNewLabel_13);
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

		// Affectation
		labelAffectation = new JLabel("Affectation : ");
		labelAffectation.setFont(new Font("Arial", Font.PLAIN, 12));
		labelAffectation.setBounds(225, 200, 80, 15);
		contentPane.add(labelAffectation);

		choixAffectation = new Choice();
		choixAffectation.setFont(new Font("Arial", Font.PLAIN, 12));
		choixAffectation.setBounds(305, 200, 125, 15);
        choixAffectation.setBackground(Color.WHITE);
		choixAffectation.add("BDE");
		choixAffectation.add("Bibliothèque");
		choixAffectation.add("Quartier administratif");
		choixAffectation.add("Halle industrielle");
		choixAffectation.add("Halle sportive");
		contentPane.add(choixAffectation);

		// type de stratégie
		labelStrategie = new JLabel("Stratégie : ");
		labelStrategie.setFont(new Font("Arial", Font.PLAIN, 12));
		labelStrategie.setBounds(225, 225, 80, 15);
		contentPane.add(labelStrategie);
		choixStrategie = new Choice();
		choixStrategie.setFont(new Font("Arial", Font.PLAIN, 12));
		choixStrategie.setBounds(305, 225, 125, 15);
        choixStrategie.setBackground(Color.WHITE);
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
        choixReserviste.add("OUI");
        choixReserviste.add("NON");
        contentPane.add(choixReserviste);

		JButton ok = new JButton("OK");
		ok.setFont(new Font("Arial", Font.PLAIN, 15));
        ok.setFocusable(false);
		ok.setBounds(225, 275, 205, 40);
		contentPane.add(ok);

		JButton validation = new JButton("VALIDER");
		validation.setFont(new Font("Arial", Font.PLAIN, 15));
        validation.setFocusable(false);
		validation.setBounds(450, 325, 180, 40);
		contentPane.add(validation);

		// ++++++++++++++++++++++++++++++++++++++++++ Habillage ++++++++++++++++++++++++++++++++++++++++++++
		// panneau configuration
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBounds(40, 150, 405, 215);
		contentPane.add(panel);

	}
}
