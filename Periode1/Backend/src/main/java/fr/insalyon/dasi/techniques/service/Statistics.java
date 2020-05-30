/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.techniques.service;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

/**
 *
 * @author zakaria
 */
public class Statistics {

    private List<Medium> top5;
    private HashMap<Medium, Integer> consultationsParMedium;
    private HashMap<Employe, Integer> clientsParEmploye;

    public Statistics() {
    }

    public Statistics(List<Medium> top5, HashMap<Medium, Integer>consultationsParMedium, HashMap<Employe, Integer> clientsParEmploye) {
        this.top5 = top5;
        this.consultationsParMedium = consultationsParMedium;
        this.clientsParEmploye = clientsParEmploye;
    }

    public List<Medium> getTop5() {
        return top5;
    }

    public HashMap<Medium, Integer> getConsultationsParMedium() {
        return consultationsParMedium;
    }

    public HashMap<Employe, Integer> getClientsParEmploye() {
        return clientsParEmploye;
    }

    public void setTop5(List<Medium> top5) {
        this.top5 = top5;
    }

    public void setConsultationsParMedium(HashMap<Medium, Integer>consultationsParMedium) {
        this.consultationsParMedium = consultationsParMedium;
    }

    public void setClientsParEmploye(HashMap<Employe, Integer> clientsParEmploye) {
        this.clientsParEmploye = clientsParEmploye;
    }

    
}
