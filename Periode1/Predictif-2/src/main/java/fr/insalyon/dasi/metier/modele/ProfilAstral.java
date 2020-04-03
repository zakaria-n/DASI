package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author DASI Team
 */

@Embeddable
public class ProfilAstral implements Serializable {
    private String signeZodiaque;
    private String astroChinois;
    private String couleurBonheur;
    private String animalTotem;

    protected ProfilAstral() {
    }

    public ProfilAstral(String signeZodiaque, String astroChinois, String couleurBonheur, String animalTotem) {
        this.signeZodiaque = signeZodiaque;
        this.astroChinois = astroChinois;
        this.couleurBonheur = couleurBonheur;
        this.animalTotem = animalTotem;
    }

  

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public String getAstroChinois() {
        return astroChinois;
    }

    public void setAstroChinois(String astroChinois) {
        this.astroChinois = astroChinois;
    }

    public String getCouleurBonheur() {
        return couleurBonheur;
    }

    public void setCouleurBonheur(String couleurBonheur) {
        this.couleurBonheur = couleurBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    

    @Override
    public String toString() {
        return "ProfilAstral : "  + ", nom=" + signeZodiaque + 
                ", astroChinois=" + astroChinois + ", "+ "couleurBonheur=" + 
                couleurBonheur + "animalTotem=" + animalTotem;
    }
    

}