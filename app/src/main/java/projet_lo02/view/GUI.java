package projet_lo02.view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import projet_lo02.model.Joueur;

public class GUI extends JFrame {
	// les composants de la vue
	private JPanel contentPane;
	private TextField configPersonnage = new TextField();
	private TextField force;
	private TextField dexterite;
	private TextField resistance;
	private TextField constitution;
	private TextField initiative;
	private Choice choice;
	private Choice strategy;
	private JCheckBox reserviste;
	private TextField soldePoints;
	private TextField pseudoJoueur;
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
		this.contentPane.setBackground(Color.CYAN);
		this.setContentPane(contentPane);
		this.contentPane.setLayout(null); // politique de placement des composants dans la fenêtre
		this.setBounds(100, 100, 1240, 810);
		// +++++++++++++++++++++++++++++++++++++ config personnage  ++++++++++++++++++++++++++++++++++++++++++++
		this.configPersonnage.setBackground(Color.YELLOW);
		this.configPersonnage.setForeground(Color.BLACK);
		this.configPersonnage.setFont(new Font("Tahoma", Font.ITALIC, 20));
		this.configPersonnage.setBounds(426, 331, 360, 34);
		contentPane.add(configPersonnage);
		// +++++++++++++++++++++++++++++++++++++ Maitre Gobi  +++++++++++++++++++++++++++++++++++++++++++++++++
		// Panel du maitre Gobi
		JPanel panelMaitre = new JPanel();
		panelMaitre.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelMaitre.setBackground(Color.CYAN);
		panelMaitre.setBounds(79, 102, 90, 122);
		panelMaitre.setLayout(new GridLayout(1, 1, 0, 0)); // politique de placement des composants dans ce panel
		Image img1 = new ImageIcon(this.getClass().getResource("/images/maitreGobi.png")).getImage();
		JButton jb1 = new JButton(); // pour représenter un personnage, utilisation d'un JButton
        img1 = img1.getScaledInstance(panelMaitre.getWidth(), panelMaitre.getHeight(), Image.SCALE_SMOOTH);
        jb1.setIcon(new ImageIcon(img1));
		panelMaitre.add(jb1);
		jb1.setForeground(Color.CYAN);
		contentPane.add(panelMaitre);
		// Etiquette Capitaine Gobi
		JLabel lblNewLabel = new JLabel("Capitaine Gobi");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(51, 75, 128, 24);
		contentPane.add(lblNewLabel);
		// +++++++++++++++++++++++++++++++++++++ Les élites +++++++++++++++++++++++++++++++++++++++++++++++++++
		// Idem pour les élites
		JPanel panelElite = new JPanel();
		panelElite.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelElite.setBackground(Color.CYAN);
		panelElite.setBounds(354, 102, 274, 122);
		panelElite.setLayout(new GridLayout(1, 4, 0, 0));
		Image img2 = new ImageIcon("ressources\\elite.png").getImage();
		JButton[] jb = new JButton[4];
		for (int i = 0; i < 4; i++) {
			jb[i] = new JButton();
			jb[i].setIcon(new ImageIcon(img2));
			panelElite.add(jb[i]);
		}
		contentPane.add(panelElite);
		// Etiquette Les Elites
		JLabel lblNewLabel_1 = new JLabel("Les Elites");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(449, 77, 83, 21);
		contentPane.add(lblNewLabel_1);
		// +++++++++++++++++++++++++++++++++++++ Les étudiants +++++++++++++++++++++++++++++++++++++++++++++++++
		// Idem pour les étudiants de base
		JPanel panelEtu = new JPanel();
		panelEtu.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelEtu.setBackground(Color.CYAN);
		panelEtu.setBounds(830, 102, 274, 411);
		panelEtu.setLayout(new GridLayout(4, 4, 2, 0));
		contentPane.add(panelEtu);
		// Etiquette Les étudiants de base
		JLabel lblNewLabel_2 = new JLabel("Les Etudiants de base");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(885, 76, 192, 20);
		contentPane.add(lblNewLabel_2);
		Image img3 = new ImageIcon("ressources\\etudiant.png").getImage();
		JButton[] jb2 = new JButton[15];
		for (int i = 0; i < 15; i++) {
			jb2[i] = new JButton();
			jb2[i].setIcon(new ImageIcon(img3));
			panelEtu.add(jb2[i]);
		}
		// +++++++++++++++++++++++++++++++++++++++++ Joueur +++++++++++++++++++++++++++++++++++++++++++++++++++
		JLabel lblNewLabel_3 = new JLabel("Joueur");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_3.setBounds(254, 24, 76, 37);
		contentPane.add(lblNewLabel_3);
		pseudoJoueur = new TextField();
		pseudoJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(pseudoJoueur.getText());
				joueur.setPseudo(pseudoJoueur.getText());
			}
		});
		pseudoJoueur.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pseudoJoueur.setBounds(336, 24, 152, 37);
		contentPane.add(pseudoJoueur);
		// +++++++++++++++++++++++++++++++++++++++++ Programme  ++++++++++++++++++++++++++++++++++++++++++++++++
		JLabel lblNewLabel_13 = new JLabel("Programme");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_13.setBounds(562, 24, 130, 37);
		contentPane.add(lblNewLabel_13);
		programme = new Choice();
		programme.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = programme.getItem(programme.getSelectedIndex());
				System.out.println(str);
				joueur.setBranche(str);
			}
		});
		programme.setFont(new Font("Tahoma", Font.PLAIN, 24));
		programme.setBounds(698, 27, 72, 34);
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
		JLabel lblNewLabel_4 = new JLabel("Points à distribuer");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_4.setBounds(72, 309, 192, 26);
		contentPane.add(lblNewLabel_4);
		soldePoints = new TextField();
		soldePoints.setFont(new Font("Tahoma", Font.PLAIN, 24));
		soldePoints.setText(Integer.toString(joueur.getSoldePoints()));
		soldePoints.setBounds(297, 304, 61, 37);
		contentPane.add(soldePoints);

		// Force
		JLabel lblNewLabel_5 = new JLabel("Force");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_5.setBounds(193, 389, 61, 37);
		contentPane.add(lblNewLabel_5);
		force = new TextField();
		force.setFont(new Font("Tahoma", Font.PLAIN, 24));
		force.setText("0");
		force.setBounds(297, 389, 61, 37);
		contentPane.add(force);

		// Dextérité
		JLabel lblNewLabel_6 = new JLabel("Dext�rit�");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_6.setBounds(159, 432, 105, 37);
		contentPane.add(lblNewLabel_6);
		dexterite = new TextField();
		dexterite.setFont(new Font("Tahoma", Font.PLAIN, 24));
		dexterite.setText("0");
		dexterite.setBounds(297, 432, 61, 37);
		contentPane.add(dexterite);

		// Résistance
		JLabel lblNewLabel_7 = new JLabel("R�sistance");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_7.setBounds(147, 479, 117, 37);
		contentPane.add(lblNewLabel_7);
		resistance = new TextField();
		resistance.setFont(new Font("Tahoma", Font.PLAIN, 24));
		resistance.setText("0");
		resistance.setBounds(297, 475, 61, 37);
		contentPane.add(resistance);

		// Constitution
		JLabel lblNewLabel_8 = new JLabel("Constitution");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_8.setBounds(136, 522, 128, 37);
		contentPane.add(lblNewLabel_8);
		constitution = new TextField();
		constitution.setFont(new Font("Tahoma", Font.PLAIN, 24));
		constitution.setText("0");
		constitution.setBounds(297, 518, 61, 37);
		contentPane.add(constitution);

		// Initiative
		JLabel lblNewLabel_9 = new JLabel("Initiative");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_9.setBounds(171, 569, 93, 29);
		contentPane.add(lblNewLabel_9);
		initiative = new TextField();
		initiative.setFont(new Font("Tahoma", Font.PLAIN, 24));
		initiative.setText("0");
		initiative.setBounds(297, 561, 61, 37);
		contentPane.add(initiative);

		// Affectation
		JLabel lblNewLabel_10 = new JLabel("Affectation");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_10.setBounds(422, 389, 117, 37);
		contentPane.add(lblNewLabel_10);
		choice = new Choice();
		choice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		choice.setBounds(562, 392, 224, 31);
		choice.add("BDE");
		choice.add("Bibliothèque");
		choice.add("Quartier administratif");
		choice.add("Halle industrielle");
		choice.add("Halle sportive");
		contentPane.add(choice);

		// type de stratégie
		JLabel lblNewLabel_11 = new JLabel("Strat�gie");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_11.setBounds(443, 452, 96, 37);
		contentPane.add(lblNewLabel_11);
		strategy = new Choice();
		strategy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		strategy.setBounds(562, 455, 224, 31);
		strategy.add("Offensive");
		strategy.add("Défensive");
		strategy.add("Aléatoire");
		contentPane.add(strategy);

		// Réserviste ?
		reserviste = new JCheckBox("R�serviste");
		reserviste.setBackground(Color.CYAN);
		reserviste.setForeground(Color.BLACK);
		reserviste.setFont(new Font("Tahoma", Font.PLAIN, 24));
		reserviste.setBounds(579, 513, 187, 26);
		contentPane.add(reserviste);

		JButton ok = new JButton("OK");
		ok.setBounds(711, 545, 59, 45);
		contentPane.add(ok);
		ok.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton validation = new JButton("VALIDER");
		validation.setFont(new Font("Tahoma", Font.PLAIN, 24));
		validation.setBounds(830, 523, 270, 83);
		contentPane.add(validation);

		// ++++++++++++++++++++++++++++++++++++++++++ Habillage ++++++++++++++++++++++++++++++++++++++++++++
		// panneau configuration
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBounds(51, 345, 769, 261);
		contentPane.add(panel);
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	}
}
