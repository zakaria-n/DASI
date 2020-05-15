/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
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
public class DisplayProfileAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession(); 
        Service service = new Service();
        ProfilAstral profile = null;
        Long id = (Long) session.getAttribute("idClient");
        if(id!=null) {
            Client client = service.rechercherClientParId(id);
            profile = service.showProfilAstral(client);
        }else {
            id = (Long) session.getAttribute("idEmploye");
            if(id!=null) {
                Employe e = service.rechercherEmployeParId(id);
                long clientId = service.getCurrentConsultationClient(e);
                Client client = service.rechercherClientParId(clientId);
                profile = service.showProfilAstral(client);
            }
        }
        
        
        request.setAttribute("profile", profile);
        
    }
}
