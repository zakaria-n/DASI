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
            Object clientId = request.getAttribute("client");
            connexion = (clientId != null);
            if (clientId != null) {
                container.addProperty("client", (long) clientId);
            }
        }
        else if (userType.equals("employe")) {
            Object employeId = request.getAttribute("employe");
            connexion = (employeId != null);
                if (employeId != null) {
                    container.addProperty("employe", (long) employeId);
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
