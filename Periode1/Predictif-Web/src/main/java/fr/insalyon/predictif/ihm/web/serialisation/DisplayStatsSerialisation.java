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
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.techniques.service.Statistics;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zakaria
 */
public class DisplayStatsSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Statistics stats = (Statistics) request.getAttribute("stats");        
        JsonObject container = new JsonObject();
        
        if (stats != null)
        {
            JsonObject topJson = new JsonObject();
            List<Medium>  top5 = stats.getTop5();
            int i=1;
            for (Medium m : top5)
            {
                topJson.addProperty("medium"+i, m.getDenomination());
                i++;
            }
            container.add("topfive", topJson);
            
            JsonArray perEmp = new JsonArray();
            for (Integer j : stats.getClientsParEmploye().keySet())
            {
                JsonObject jsonPerEmp = new JsonObject();
                jsonPerEmp.addProperty(stats.getClientsParEmploye().get(j).getPrenom(),""+j);
                perEmp.add(jsonPerEmp);
            }
            container.add("perEmployee",perEmp);
            
            JsonArray perMed = new JsonArray();
            for (Integer k : stats.getConsultationsParMedium().keySet())
            {
                JsonObject jsonPerMed = new JsonObject();
                jsonPerMed.addProperty(stats.getConsultationsParMedium().get(k).getDenomination(),""+k);
                perMed.add(jsonPerMed);
            }
            container.add("perMed",perMed);
            
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
        
    }
    
}
