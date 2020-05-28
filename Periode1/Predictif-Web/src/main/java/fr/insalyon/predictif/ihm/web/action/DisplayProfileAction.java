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
        String name;
        String gender;
        Client client = null;
        Long id = (Long) session.getAttribute("idClient");
        if(id!=null) {
            client = service.rechercherClientParId(id);
            profile = service.showProfilAstral(client);
        }else {
            id = (Long) session.getAttribute("idEmploye");
            if(id!=null) {
                Employe e = service.rechercherEmployeParId(id);
                long clientid = service.getCurrentConsultationClient(e);
                if (clientid != -1)
                {
                    client = service.rechercherClientParId(clientid);
                    profile = service.showProfilAstral(client); 
                }                
            }
            else {
                request.setAttribute("notLoggedIn", true);
            }
        }
            name = client.getPrenom() + " " + client.getNom();
            gender = client.getGenre();
            request.setAttribute("name", name);
            request.setAttribute("gender", gender);
        
        request.setAttribute("profile", profile);
        
    }
}
