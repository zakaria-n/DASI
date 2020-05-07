/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zakaria
 */
public class InscrireClientAction extends Action {
    
     @Override
    public void executer(HttpServletRequest request) {
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String genre = request.getParameter("genre");
        String date = request.getParameter("date");
        Date dateNaissance = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateNaissance = sdf.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(InscrireClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }         
        String adresse = request.getParameter("adresse");
        
        Client client = new Client(nom,  prenom,  email,  password, 
             phone, genre,  dateNaissance, adresse);
        
        Service service = new Service();
        Long id = service.inscrireClient(client);
        
        if(id!=null) {
            request.setAttribute("inscrit", client); 
        }
    }
}
