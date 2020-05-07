package fr.insalyon.predictif.ihm.web.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zakaria
 */
public abstract class Action {
    
    public abstract void executer(HttpServletRequest request);
}
