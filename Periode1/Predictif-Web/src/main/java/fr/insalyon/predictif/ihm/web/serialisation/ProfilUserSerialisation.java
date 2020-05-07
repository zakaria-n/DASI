/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zakaria
 */
public class ProfilUserSerialisation extends Serialisation {
    
     @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userType = (String) request.getAttribute("user");
        Boolean connexion = false;
        JsonObject container = new JsonObject();
                
        if(userType.equals("client")) {
            Client client = (Client)request.getAttribute("client");
            connexion = (client != null);
            if (client != null) {
                JsonObject jsonClient = new JsonObject();
                jsonClient.addProperty("id", client.getId());
                jsonClient.addProperty("nom", client.getNom());
                jsonClient.addProperty("prenom", client.getPrenom());
                jsonClient.addProperty("mail", client.getMail());
                container.add("client", jsonClient);
            }
        }
        else if (userType.equals("employe")) {
            Employe employe = (Employe) request.getAttribute("employe");
            connexion = (employe != null);
                if (employe != null) {
                    JsonObject jsonEmploye = new JsonObject();
                    jsonEmploye.addProperty("id", employe.getId());
                    jsonEmploye.addProperty("nom", employe.getNom());
                    jsonEmploye.addProperty("prenom", employe.getPrenom());
                    jsonEmploye.addProperty("mail", employe.getMail());
                    container.add("employe", jsonEmploye);
                }
        }
        
        container.addProperty("connexion", connexion);
        container.addProperty("user", userType);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
