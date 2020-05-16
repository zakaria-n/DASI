/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.service.Service;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sophiecrowley
 */
public class AfficherConsultations extends Action {
         @Override
    public void executer(HttpServletRequest request) {
        List<Consultation> consultations = new ArrayList<>();
        Service service = new Service();
        HttpSession session = request.getSession();
        Object id =  session.getAttribute("idClient");
        if(id!=null) {
            System.out.println("client");
            Client c = service.rechercherClientParId((long) id);
            consultations = service.showClientConsultations(c);
        }
        else {
            id = session.getAttribute("idEmploye");
            System.out.println(id);
            if(id!=null) {
                Employe e = service.rechercherEmployeParId((long) id);
                long clientId = service.getCurrentConsultationClient(e);
                System.out.println(clientId);
                if(clientId!=-1) {
                    Client c = service.rechercherClientParId(clientId);
                    consultations = service.showClientConsultations(c);                   
                }
            }
            
        }
        request.setAttribute("consultations", consultations);
        
        
    }
    
}
