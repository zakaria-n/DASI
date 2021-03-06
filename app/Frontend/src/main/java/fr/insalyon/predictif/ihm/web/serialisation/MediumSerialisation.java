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
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author zakaria
 */
public class MediumSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Medium> mediums = (List<Medium>)request.getAttribute("mediums");
        
        JsonObject container = new JsonObject();

        if (mediums != null) {
            JsonArray ja = new JsonArray();
            for (Medium m : mediums)
            {
                JsonObject jsonMedium = new JsonObject();
                jsonMedium.addProperty("type", m.getType());
                jsonMedium.addProperty("denomination", m.getDenomination());
                jsonMedium.addProperty("genre", m.getGenre());
                jsonMedium.addProperty("presentation", m.getPresentation());
                jsonMedium.addProperty("nbConsultations", m.getNbConsultations());
                if(m.getType().equals("Spirite")){
                   Spirite current;
                   current = (Spirite) m;
                   jsonMedium.addProperty("support", current.getSupport());
                }
                if(m.getType().equals("Astrologue")){
                    Astrologue current;
                    current = (Astrologue) m;
                    jsonMedium.addProperty("promotion", current.getPromotion());
                    jsonMedium.addProperty("formation", current.getFormation());
                }
                ja.add(jsonMedium);
            }

            container.add("mediums", ja);
        }
        if(request.getAttribute("user")!=null) {
        container.addProperty("user", (String) request.getAttribute("user"));            
        }
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
