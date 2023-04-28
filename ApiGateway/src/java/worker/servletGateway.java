/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package worker;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author schwandern
 */
public class servletGateway extends HttpServlet {

    WrkHTTP wrk = new WrkHTTP();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        String requestType = new String(request.getParameter("type"));

        if (requestType.equals("getclassement")) {

            try ( PrintWriter out = response.getWriter()) {
                out.print(wrk.getclassement());
                out.flush();
            }
        }
        if (requestType.equals("addEntree")) {
            String score = new String(request.getParameter("score"));
            String name = new String(request.getParameter("name"));
            String fk_user = new String(request.getParameter("fk_user"));

            try ( PrintWriter out = response.getWriter()) {
                out.print(wrk.addEntree(score, name, fk_user));
                out.flush();
            }

        }
        if (requestType.equals("getUser")) {
            String nom = new String(request.getParameter("user"));

            try ( PrintWriter out = response.getWriter()) {
                out.print(wrk.getUserByName(nom));
                out.flush();
            }
        }
        if (requestType.equals("Adduser")) {
            String nom = new String(request.getParameter("user"));
            String password = new String(request.getParameter("password"));

            try ( PrintWriter out = response.getWriter()) {
                out.print(wrk.addUser(nom, password));
                out.flush();
            }
        } else {
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.print("pas de type de Rquête avec le nom ''" + requestType + "'' ");
                out.flush();
            }
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
         //response.addHeader("Access-Control-Allow-Origin", "*");
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
        //response.addHeader("Access-Control-Allow-Origin", "*");

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
