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
		this.setBounds(100, 100, 1240, 810);

		// +++++++++++++++++++++++++++++++++++++ config personnage  ++++++++++++++++++++++++++++++++++++++++++++
        
        configPersonnage = new JTextField();
		configPersonnage.setBackground(Color.darkGray);
        configPersonnage.setForeground(Color.WHITE);
        configPersonnage.setHorizontalAlignment(JTextField.CENTER);
		configPersonnage.setFont(new Font("Arial", Font.ITALIC, 30));
		configPersonnage.setBounds(500, 280, 360, 40);
		contentPane.add(configPersonnage);

		// +++++++++++++++++++++++++++++++++++++ Maitre Gobi  +++++++++++++++++++++++++++++++++++++++++++++++++
		// Panel du maitre Gobi
		JPanel panelMaitre = new JPanel();
		panelMaitre.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelMaitre.setBounds(80, 100, 90, 135);
		panelMaitre.setLayout(new GridLayout(1, 1, 0, 0)); // politique de placement des composants dans ce panel
        
		Image imgMaitreGobi = new ImageIcon(this.getClass().getResource("/images/maitreGobi.png")).getImage();
		JButton boutonMaitreGobi = new JButton(); // pour représenter un personnage, utilisation d'un JButton
        imgMaitreGobi = imgMaitreGobi.getScaledInstance(panelMaitre.getWidth(), panelMaitre.getHeight(), Image.SCALE_SMOOTH);
        boutonMaitreGobi.setIcon(new ImageIcon(imgMaitreGobi));

		panelMaitre.add(boutonMaitreGobi);
		contentPane.add(panelMaitre);

		// Etiquette Capitaine Gobi
		JLabel lblNewLabel = new JLabel("Capitaine Gobi");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(60, 75, 150, 20);
		contentPane.add(lblNewLabel);

		// +++++++++++++++++++++++++++++++++++++ Les élites +++++++++++++++++++++++++++++++++++++++++++++++++++
		// Idem pour les élites
        
		JPanel panelElite = new JPanel();
		panelElite.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelElite.setBounds(300, 100, 360, 135);
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
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(440, 75, 150, 20);
		contentPane.add(lblNewLabel_1);

		// +++++++++++++++++++++++++++++++++++++ Les étudiants +++++++++++++++++++++++++++++++++++++++++++++++++
		// Idem pour les étudiants de base
        
		JPanel panelEtu = new JPanel();
		panelEtu.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelEtu.setBounds(900, 100, 360, 540);
		panelEtu.setLayout(new GridLayout(4, 4, 0, 0));
		contentPane.add(panelEtu);

        Image imgEtudiant = new ImageIcon(this.getClass().getResource("/images/etudiant.png")).getImage();
        imgEtudiant = imgEtudiant.getScaledInstance(panelMaitre.getWidth(), panelMaitre.getHeight(), Image.SCALE_SMOOTH);

		// Etiquette Les étudiants de base
		JLabel lblNewLabel_2 = new JLabel("Les Etudiants de base");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(985, 75, 200, 20);
		contentPane.add(lblNewLabel_2);
		JButton[] jb2 = new JButton[15];
		for (int i = 0; i < 15; i++) {
			jb2[i] = new JButton();
			jb2[i].setIcon(new ImageIcon(imgEtudiant));
			panelEtu.add(jb2[i]);
		}

		// +++++++++++++++++++++++++++++++++++++++++ Joueur +++++++++++++++++++++++++++++++++++++++++++++++++++
		JLabel lblNewLabel_3 = new JLabel("Joueur : ");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(80, 25, 130, 30);
		contentPane.add(lblNewLabel_3);
		pseudoJoueur = new JTextField();
		pseudoJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(pseudoJoueur.getText());
				joueur.setPseudo(pseudoJoueur.getText());
			}
		});
		pseudoJoueur.setFont(new Font("Arial", Font.PLAIN, 30));
		pseudoJoueur.setBounds(215, 25, 150, 30);
		contentPane.add(pseudoJoueur);

		// +++++++++++++++++++++++++++++++++++++++++ Programme  ++++++++++++++++++++++++++++++++++++++++++++++++
		JLabel lblNewLabel_13 = new JLabel("Programme : ");
		lblNewLabel_13.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel_13.setBounds(400, 25, 210, 30);
		contentPane.add(lblNewLabel_13);
		programme = new Choice();
        programme.setBackground(Color.WHITE);
		programme.setFont(new Font("Arial", Font.PLAIN, 24));
		programme.setBounds(615, 25, 80, 30);
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
		labelSoldePoints.setFont(new Font("Arial", Font.PLAIN, 24));
		labelSoldePoints.setBounds(80, 270, 240, 30);
		contentPane.add(labelSoldePoints);
		soldePoints = new JLabel();
		soldePoints.setFont(new Font("Arial", Font.BOLD, 24));
		soldePoints.setText(Integer.toString(joueur.getSoldePoints()));
		soldePoints.setBounds(320, 270, 60, 30);
		contentPane.add(soldePoints);

		// Force
		labelForce = new JLabel("Force : ");
		labelForce.setFont(new Font("Arial", Font.PLAIN, 24));
		labelForce.setBounds(105, 400, 200, 30);
		contentPane.add(labelForce);
		force = new JTextField();
        force.setHorizontalAlignment(JTextField.CENTER);
		force.setFont(new Font("Arial", Font.PLAIN, 24));
		force.setBounds(350, 400, 40, 30);
		contentPane.add(force);

		// Dextérité
		labelDexterite = new JLabel("Dextérité : ");
		labelDexterite.setFont(new Font("Arial", Font.PLAIN, 24));
		labelDexterite.setBounds(105, 450, 200, 30);
		contentPane.add(labelDexterite);
		dexterite = new JTextField();
        dexterite.setHorizontalAlignment(JTextField.CENTER);
		dexterite.setFont(new Font("Arial", Font.PLAIN, 24));
		dexterite.setBounds(350, 450, 40, 30);
		contentPane.add(dexterite);

		// Résistance
		labelResistance = new JLabel("Résistance : ");
		labelResistance.setFont(new Font("Arial", Font.PLAIN, 24));
		labelResistance.setBounds(105, 500, 200, 30);
		contentPane.add(labelResistance);
		resistance = new JTextField();
        resistance.setHorizontalAlignment(JTextField.CENTER);
		resistance.setFont(new Font("Arial", Font.PLAIN, 24));
		resistance.setBounds(350, 500, 40, 30);
		contentPane.add(resistance);

		// Constitution
		labelConstitution = new JLabel("Constitution : ");
		labelConstitution.setFont(new Font("Arial", Font.PLAIN, 24));
		labelConstitution.setBounds(105, 550, 200, 30);
		contentPane.add(labelConstitution);
		constitution = new JTextField();
        constitution.setHorizontalAlignment(JTextField.CENTER);
		constitution.setFont(new Font("Arial", Font.PLAIN, 24));
		constitution.setBounds(350, 550, 40, 30);
		contentPane.add(constitution);

		// Initiative
		labelInitiative = new JLabel("Initiative : ");
		labelInitiative.setFont(new Font("Arial", Font.PLAIN, 24));
		labelInitiative.setBounds(105, 600, 200, 30);
		contentPane.add(labelInitiative);
		initiative = new JTextField();
        initiative.setHorizontalAlignment(JTextField.CENTER);
		initiative.setFont(new Font("Arial", Font.PLAIN, 24));
		initiative.setBounds(350, 600, 40, 30);
		contentPane.add(initiative);

		// Affectation
		labelAffectation = new JLabel("Affectation : ");
		labelAffectation.setFont(new Font("Arial", Font.PLAIN, 24));
		labelAffectation.setBounds(450, 400, 160, 30);
		contentPane.add(labelAffectation);

		choixAffectation = new Choice();
		choixAffectation.setFont(new Font("Arial", Font.PLAIN, 20));
		choixAffectation.setBounds(610, 400, 250, 30);
        choixAffectation.setBackground(Color.WHITE);
		choixAffectation.add("BDE");
		choixAffectation.add("Bibliothèque");
		choixAffectation.add("Quartier administratif");
		choixAffectation.add("Halle industrielle");
		choixAffectation.add("Halle sportive");
		contentPane.add(choixAffectation);

		// type de stratégie
		labelStrategie = new JLabel("Stratégie : ");
		labelStrategie.setFont(new Font("Arial", Font.PLAIN, 24));
		labelStrategie.setBounds(450, 450, 160, 30);
		contentPane.add(labelStrategie);
		choixStrategie = new Choice();
		choixStrategie.setFont(new Font("Arial", Font.PLAIN, 20));
		choixStrategie.setBounds(610, 450, 250, 30);
        choixStrategie.setBackground(Color.WHITE);
		choixStrategie.add("Offensive");
		choixStrategie.add("Défensive");
		choixStrategie.add("Aléatoire");
		contentPane.add(choixStrategie);

		// Réserviste ?
        labelReserviste = new JLabel("Réserviste : ");
        labelReserviste.setFont(new Font("Arial", Font.PLAIN, 24));
		labelReserviste.setBounds(450, 500, 160, 30);
        contentPane.add(labelReserviste);
        choixReserviste = new Choice();
        choixReserviste.setFont(new Font("Arial", Font.PLAIN, 20));
        choixReserviste.setBounds(610, 500, 250, 30);
        choixReserviste.setBackground(Color.WHITE);
        choixReserviste.add("OUI");
        choixReserviste.add("NON");
        contentPane.add(choixReserviste);

		JButton ok = new JButton("OK");
		ok.setFont(new Font("Arial", Font.PLAIN, 30));
        ok.setFocusable(false);
		ok.setBounds(450, 550, 410, 80);
		contentPane.add(ok);

		JButton validation = new JButton("VALIDER");
		validation.setFont(new Font("Arial", Font.PLAIN, 30));
        validation.setFocusable(false);
		validation.setBounds(900, 650, 360, 80);
		contentPane.add(validation);

		// ++++++++++++++++++++++++++++++++++++++++++ Habillage ++++++++++++++++++++++++++++++++++++++++++++
		// panneau configuration
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBounds(80, 300, 810, 430);
		contentPane.add(panel);

	}
}
