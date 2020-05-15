/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zakaria
 */
public class DisplayProfileSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        ProfilAstral profile = (ProfilAstral) request.getAttribute("profile");
        JsonObject container = new JsonObject();
        
        if (profile != null)
        {
            JsonObject jsonProfile = new JsonObject();
            jsonProfile.addProperty("signeZodiaque",profile.getSigneZodiaque());
            jsonProfile.addProperty("astroChinois",profile.getAstroChinois());
            jsonProfile.addProperty("couleurBonheur",profile.getCouleurBonheur());
            jsonProfile.addProperty("animalTotem",profile.getAnimalTotem());
            container.add("profile", jsonProfile);
        }
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
