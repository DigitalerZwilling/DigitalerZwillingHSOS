/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;


import Cache.ArtikelCache;
import Cache.GelenkCache;
import Cache.HubPodestCache;
import Cache.HubQuerPodestCache;
import Cache.RoboterCache;
import Cache.SektorCache;
import Cache.SensorCache;
import Cache.TransportbandCache;
import Cache.WarentraegerCache;
import Cache.WerkzeugCache;
import DatenKlassen.Element;
import DatenKlassen.Sektor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class index extends HttpServlet {


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Inject ArtikelCache artikelCache; 
    @Inject GelenkCache gelenkCache; 
    @Inject HubPodestCache hubPodestCache; 
    @Inject HubQuerPodestCache hubQuerPodestCache; 
    @Inject RoboterCache roboterCache; 
    @Inject SektorCache sektorCache;
    @Inject SensorCache sensorCache;
    @Inject TransportbandCache transportbandCache;
    @Inject WarentraegerCache warentraegerCache;
    @Inject WerkzeugCache warkzeugCache;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Server Ausgabe</title>");   
            out.println("</head>");
            out.println("<body>");
            
            //Sektoren
            out.println("<h1>Sektoren</h1>");
            for(Element e : this.sektorCache.getAll()){
                out.println(e.toJson());
            }
            out.println("<br><br>");
            
            //Transportbaender
            out.println("<h1>Transportbänder</h1>");
            for(Element e : this.transportbandCache.getAll()){
                out.println(e.toJson());
            }
            out.println("<br><br>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
