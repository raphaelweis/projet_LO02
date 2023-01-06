package projet_lo02.controller;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import projet_lo02.model.Etudiant;
import projet_lo02.model.Partie;
import projet_lo02.view.GUI;

public class ControllerGUI {

    private GUI gui;
    private Partie partie;
    private int keyEtudiantSelectionne;

    public ControllerGUI(GUI gui, Partie partie){
        this.gui = gui;
        this.partie = partie;

        this.gui.getBoutonMaitreGobi().addActionListener(new EcouteurEtudiants(this.gui.getJoueur().getEquipe(), 20));

        ArrayList<JButton> boutonsElite = this.gui.getBoutonsElite();
        Iterator<JButton> itBoutonsElite = boutonsElite.iterator();
        int keyElite = 16;
        while(itBoutonsElite.hasNext()){
            itBoutonsElite.next().addActionListener(new EcouteurEtudiants(this.gui.getJoueur().getEquipe(), keyElite));
            keyElite ++;
        }

        ArrayList<JButton> boutonsEtudiant = this.gui.getBoutonsEtudiant();
        Iterator<JButton> itBoutonsEtudiant = boutonsEtudiant.iterator();
        int keyEtudiant = 1;
        while(itBoutonsEtudiant.hasNext()){
            itBoutonsEtudiant.next().addActionListener(new EcouteurEtudiants(this.gui.getJoueur().getEquipe(), keyEtudiant));
            keyEtudiant ++;
        }

        this.gui.getBoutonOk().addActionListener(new EcouteurOk(this.gui.getJoueur().getEquipe()));
        this.gui.getBoutonValidation().addActionListener(new EcouteurValidation());
        this.gui.getBoutonRandom().addActionListener(new EcouteurRandomiser());

    }

	final class EcouteurEtudiants implements ActionListener {

        private HashMap<Integer, Etudiant> equipe;
        private int key;

        public EcouteurEtudiants(HashMap<Integer, Etudiant> equipe, int key){
            this.equipe = equipe;
            this.key = key;
        }

        public void actionPerformed(ActionEvent e){
            keyEtudiantSelectionne = this.key;
            Etudiant etu = this.equipe.get(this.key);
            gui.getConfigPersonnage().setText(etu.getType() + " " + this.key);
            gui.getForce().setText(Integer.toString(etu.getForce()));
            gui.getDexterite().setText(Integer.toString(etu.getDexterite()));
            gui.getResistance().setText(Integer.toString(etu.getResistance()));
            gui.getConstitution().setText(Integer.toString(etu.getConstitution()));
            gui.getInitiative().setText(Integer.toString(etu.getInitiative()));
            try{
                gui.getChoixZone().select(etu.getZone().toString());
            } catch(NullPointerException exception){gui.getChoixZone().select(0);}
            try{
                gui.getChoixStrategie().select(etu.getNomStrategie());
            } catch(NullPointerException exception){gui.getChoixStrategie().select(0);}
            try{
                gui.getChoixReserviste().select(etu.getStringReserviste());
            } catch(NullPointerException exception){gui.getChoixStrategie().select(0);}
        }

    }

    final class EcouteurOk implements ActionListener {
        
        private HashMap<Integer, Etudiant> equipe;

        public EcouteurOk(HashMap<Integer, Etudiant> equipe){
            this.equipe = equipe;
        }

        public void actionPerformed(ActionEvent e){
            Etudiant etu = this.equipe.get(keyEtudiantSelectionne);
            try{
                if(Integer.parseInt(gui.getForce().getText()) <= etu.getMaximumForce()
                && Integer.parseInt(gui.getForce().getText()) >= etu.getMinimumForce()
                && Integer.parseInt(gui.getDexterite().getText()) <= etu.getMaximumDexterite()
                && Integer.parseInt(gui.getDexterite().getText()) >= etu.getMinimumDexterite()
                && Integer.parseInt(gui.getResistance().getText()) <= etu.getMaximumResistance()
                && Integer.parseInt(gui.getResistance().getText()) >= etu.getMinimumResistance()
                && Integer.parseInt(gui.getConstitution().getText()) <= etu.getMaximumConstitution()
                && Integer.parseInt(gui.getConstitution().getText()) >= etu.getMinimumConstitution()
                && Integer.parseInt(gui.getInitiative().getText()) <= etu.getMaximumInitiative()
                && Integer.parseInt(gui.getInitiative().getText()) >= etu.getMinimumInitiative()
                && gui.getChoixZone().getSelectedIndex() != 0
                && gui.getChoixStrategie().getSelectedIndex() != 0
                && gui.getChoixReserviste().getSelectedIndex() != 0
                ){
                    etu.setForce(Integer.parseInt(gui.getForce().getText()));
                    etu.setDexterite(Integer.parseInt(gui.getDexterite().getText()));
                    etu.setResistance(Integer.parseInt(gui.getResistance().getText()));
                    etu.setConstitution(Integer.parseInt(gui.getConstitution().getText()));
                    etu.setInitiative(Integer.parseInt(gui.getInitiative().getText()));
                    etu.setZone(partie.getListZones().get(gui.getChoixZone().getSelectedIndex() - 1));
                    etu.setStrategie(gui.getChoixStrategie().getSelectedIndex());
                    etu.setReserviste(gui.getChoixReserviste().getSelectedIndex() - 1);
                    gui.getConfigPersonnage().setText("");
                    gui.getForce().setText("");
                    gui.getDexterite().setText("");
                    gui.getResistance().setText("");
                    gui.getConstitution().setText("");
                    gui.getInitiative().setText("");
                    gui.getChoixZone().select(0);
                    gui.getChoixStrategie().select(0);
                    gui.getChoixReserviste().select(0);
                    gui.getSoldePoints().setText(Integer.toString(gui.getJoueur().getSoldePoints()));
                }
            } catch(NumberFormatException ex){}
        }

    }

    final class EcouteurValidation implements ActionListener {
        
        public EcouteurValidation(){}

        public void actionPerformed(ActionEvent e){
            HashMap<Integer, Etudiant> equipe = gui.getJoueur().getEquipe();
            if(checkZones(equipe) == true && gui.getPseudoJoueur().getText() != ""){
                gui.getJoueur().setPseudo(gui.getPseudoJoueur().getText());
                gui.getMainFrame().dispose();
            }
        }

        // check if every unit has a zone
        public boolean checkZones(HashMap<Integer, Etudiant> equipe){
            for(int key : equipe.keySet()){
                if(equipe.get(key).getZone() == null){
                    return false;
                }
            }
            return true;
        }

    }

    final class EcouteurRandomiser implements ActionListener {

        public EcouteurRandomiser(){}

        public void actionPerformed(ActionEvent e){
            gui.getJoueur().randomizeEquipe();
            gui.getSoldePoints().setText(Integer.toString(gui.getJoueur().getSoldePoints()));
        }
    }

}
