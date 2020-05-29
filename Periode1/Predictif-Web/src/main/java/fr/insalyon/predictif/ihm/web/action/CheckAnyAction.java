/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.predictif.ihm.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zakaria
 */
public class CheckAnyAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession(); 
        Long id = (Long) session.getAttribute("idEmploye");
        if(id==null) {
            id = (Long) session.getAttribute("idClient");
            if (id==null)
            {
                request.setAttribute("notLoggedIn", true);                
            }
        }
    }
    
}
