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
import fr.insalyon.dasi.metier.modele.Employe;
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
            JsonArray topJson = new JsonArray();
            List<Medium>  top5 = stats.getTop5();
            for (int p=0; p<top5.size(); p++)
            {
                JsonObject rank = new JsonObject();
                rank.addProperty("rang",p+1 );
                rank.addProperty("denom",top5.get(p).getDenomination());
                topJson.add(rank);
                
            }
            container.add("topfive", topJson);
            
            JsonArray perEmp = new JsonArray();
            for (Employe e : stats.getClientsParEmploye().keySet())
            {
                JsonArray jsonPerEmp = new JsonArray();
                JsonObject emp = new JsonObject();
                emp.addProperty("prenom",e.getPrenom());
                emp.addProperty("nb",""+stats.getClientsParEmploye().get(e));
                jsonPerEmp.add(emp);
                perEmp.add(jsonPerEmp);
            }
            container.add("perEmployee",perEmp);
            
            JsonArray perMed = new JsonArray();
            for (Medium m : stats.getConsultationsParMedium().keySet())
            {
                JsonArray jsonPerMed = new JsonArray();
                JsonObject med = new JsonObject();
                med.addProperty("denom", m.getDenomination());
                med.addProperty("nb",""+stats.getConsultationsParMedium().get(m));
                jsonPerMed.add(med);
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
