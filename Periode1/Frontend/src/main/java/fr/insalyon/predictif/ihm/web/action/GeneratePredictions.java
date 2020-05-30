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
 * @author zakaria
 */
public class GeneratePredictions extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<String> predictions = null;
        Service service = new Service();
        String love = (String) request.getParameter("loveNum");
        int loveNum = Integer.parseInt(love);
        int healthNum = Integer.parseInt((String) request.getParameter("healthNum"));
        int workNum = Integer.parseInt((String) request.getParameter("workNum"));
        Object id = session.getAttribute("idEmploye");
        Client c = null;
        if(id!=null) {
            Employe e = service.rechercherEmployeParId((long) id);
            Consultation consul = e.getConsultations().get(0);
            //long clientId = service.getCurrentConsultationClient(e);
            c = consul.getClient();
            if(c.getId()!=-1) {
                //c = service.rechercherClientParId(clientId);
                predictions = service.generatePrediction(c, loveNum, healthNum, workNum);
            }
        }
        else {
            request.setAttribute("notLoggedIn", true);
        }
        request.setAttribute("predictions", predictions);
        
    }
}
