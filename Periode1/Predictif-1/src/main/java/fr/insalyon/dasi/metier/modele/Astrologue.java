/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

/**
 *
 * @author sophiecrowley
 */
public class Astrologue extends Medium {
    private String formation;
    private int promotion;

    public Astrologue(String denomination, String genre, String presentation, 
            String formation, int promotion) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }
}
