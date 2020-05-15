/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lenovo
 */
public class DemanderConsultationsAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        Client c = null;
        Medium m = null ;
        Service service = new Service();
        HttpSession session = request.getSession();
        Object id =  session.getAttribute("idClient");
        String mediumName = request.getParameter("medium");
        if(id!=null) {
            //System.out.println("client");
            c = service.rechercherClientParId((long) id);
        }
        if(mediumName!=null) {
            m = service.chercherMedium(mediumName);
        }
        Employe e = service.demanderConsultation(m, c);// try catch?
    }
}
