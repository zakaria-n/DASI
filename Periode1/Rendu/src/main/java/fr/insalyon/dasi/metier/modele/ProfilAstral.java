package fr.insalyon.dasi.metier.modele;
import fr.insalyon.dasi.technique.service.AstroTest;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public ProfilAstral(String nom, Date date) {
        AstroTest astro = new AstroTest();
        List<String> result;
        try {
            result = astro.getProfil(nom, date);
            this.signeZodiaque = result.get(0);
            this.astroChinois = result.get(1);
            this.couleurBonheur = result.get(2);
            this.animalTotem = result.get(3);
        } catch (IOException ex) {
            Logger.getLogger(ProfilAstral.class.getName()).log(Level.SEVERE, null, ex);
        } 
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
        return "ProfilAstral : " + "nom=" + signeZodiaque + 
                ", astroChinois=" + astroChinois + ", "+ "couleurBonheur=" + 
                couleurBonheur + "animalTotem=" + animalTotem;
    }
    

}
