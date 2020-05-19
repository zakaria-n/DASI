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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author zakaria
 */
public class PredictionsSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> predictions = (List<String>)request.getAttribute("predictions");
        System.out.println(predictions);
        JsonObject container = new JsonObject();
        if(predictions!=null) {
            JsonObject predicts = new JsonObject();  
            predicts.addProperty("love", predictions.get(0));
            predicts.addProperty("health", predictions.get(1));
            predicts.addProperty("work", predictions.get(2));
            container.add("predictions", predicts);
        } 
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
