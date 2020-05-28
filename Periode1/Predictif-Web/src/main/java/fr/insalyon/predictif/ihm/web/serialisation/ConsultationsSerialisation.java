/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.metier.modele.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sophiecrowley
 */
public class ConsultationsSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Consultation> consultations = (List<Consultation>)request.getAttribute("consultations");
        String prenom = (String) request.getAttribute("prenomClient");
        String nom = (String) request.getAttribute("nomClient");
        JsonObject container = new JsonObject();
        String homepage = (String) request.getAttribute("homepage");
        container.addProperty("homepage", homepage);
        container.addProperty("prenomClient", prenom);
        container.addProperty("nomClient", nom);
        if (consultations != null) {
            if(consultations.size()>0) {
                String mostRecent = consultations.get(consultations.size()-1).getMedium().getDenomination();
                container.addProperty("mostRecentMedium", mostRecent);            
            }
            JsonArray ja = new JsonArray();
            for (Consultation c : consultations)
            {
                if(c.getHeureDebut()!=null) {
                    String date = c.getDate().toLocaleString();
                    int index = date.indexOf(',');
                    date = date.substring(0, index);
                    JsonObject jsonConsultation = new JsonObject();
                    jsonConsultation.addProperty("date", date);
                    jsonConsultation.addProperty("heureDebut", c.getHeureDebut());
                    jsonConsultation.addProperty("heureFin", c.getHeureFin());
                    jsonConsultation.addProperty("mediumName", c.getMedium().getDenomination());
                    if(c.getCommentaire()!=null) {
                       jsonConsultation.addProperty("commentaire", c.getCommentaire()); 
                    }
                    else {
                        jsonConsultation.addProperty("commentaire", "n/a"); 
                    } 
                    ja.add(jsonConsultation);                    
                }
            }

            container.add("consultations", ja);
            if(request.getAttribute("user")!=null) {
                container.addProperty("user", (String) request.getAttribute("user"));
            }
            if(request.getAttribute("notLoggedIn")!=null) {
                container.addProperty("notLoggedIn", (Boolean) request.getAttribute("notLoggedIn"));
            }
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }    
}
