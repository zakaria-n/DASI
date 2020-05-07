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
import javax.servlet.http.HttpSession;

/**
 *
 * @author zakaria
 */
public class InscrireClientAction extends Action {
    
     @Override
    public void executer(HttpServletRequest request) {
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String date = request.getParameter("date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         try {
             Date datenaissance = sdf.parse(date);
         } catch (ParseException ex) {
             Logger.getLogger(InscrireClientAction.class.getName()).log(Level.SEVERE, null, ex);
         }
        String email = request.getParameter("email");
        String adresse = request.getParameter("adresse");
        String password = request.getParameter("password");
        
        Service service = new Service();
        Client client = new Client();

        request.setAttribute("client", client);
        
        // Gestion de la Session: ici, enregistrer l'ID du Client authentifié
        HttpSession session = request.getSession();
        if (client != null) {
            session.setAttribute("idClient", client.getId());
        }
        else {
            session.removeAttribute("idClient");
        }
    }
}
