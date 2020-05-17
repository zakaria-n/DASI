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
        
        JsonObject container = new JsonObject();
        String homepage = (String) request.getAttribute("homepage");
        container.addProperty("homepage", homepage);
        if (consultations != null) {
            JsonArray ja = new JsonArray();
            for (Consultation c : consultations)
            {
                JsonObject jsonConsultation = new JsonObject();
                jsonConsultation.addProperty("date", c.getDate().toString());
                jsonConsultation.addProperty("heureDebut", c.getHeureDebut());
                jsonConsultation.addProperty("heureFin", c.getHeureFin());
                jsonConsultation.addProperty("commentaire", c.getCommentaire()); 
                ja.add(jsonConsultation);
            }

            container.add("consultations", ja);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }    
}
