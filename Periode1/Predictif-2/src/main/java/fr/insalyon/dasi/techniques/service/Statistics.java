/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.techniques.service;

import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.util.List;
import java.util.SortedMap;

/**
 *
 * @author zakaria
 */
public class Statistics {

    private List<Medium> top5;
    private SortedMap<Integer, Medium> consultationsParMedium;
    private SortedMap<Integer, Employe> clientsParEmploye;

    public Statistics() {
    }

    public Statistics(List<Medium> top5, SortedMap<Integer, Medium> consultationsParMedium, SortedMap<Integer, Employe> clientsParEmploye) {
        this.top5 = top5;
        this.consultationsParMedium = consultationsParMedium;
        this.clientsParEmploye = clientsParEmploye;
    }

    public List<Medium> getTop5() {
        return top5;
    }

    public SortedMap<Integer, Medium> getConsultationsParMedium() {
        return consultationsParMedium;
    }

    public SortedMap<Integer, Employe> getClientsParEmploye() {
        return clientsParEmploye;
    }

    public void setTop5(List<Medium> top5) {
        this.top5 = top5;
    }

    public void setConsultationsParMedium(SortedMap<Integer, Medium> consultationsParMedium) {
        this.consultationsParMedium = consultationsParMedium;
    }

    public void setClientsParEmploye(SortedMap<Integer, Employe> clientsParEmploye) {
        this.clientsParEmploye = clientsParEmploye;
    }

    
}
