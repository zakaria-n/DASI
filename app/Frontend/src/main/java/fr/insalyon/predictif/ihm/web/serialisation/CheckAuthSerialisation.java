/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zakaria
 */
public class CheckAuthSerialisation extends Serialisation {
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String userType = null;
        HttpSession session = request.getSession(); 
        Long id = (Long) session.getAttribute("idClient");
        if(id!=null) {
            userType = "client";
        }else{
            id = (Long) session.getAttribute("idEmploye");
            if(id!=null) {
                userType = "employe";
            }
        }
        
        JsonObject container = new JsonObject();
        if(request.getAttribute("notLoggedIn")!=null) 
        {
            container.addProperty("notLoggedIn", (Boolean) request.getAttribute("notLoggedIn"));
        }
        container.addProperty("userType", userType);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
