/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import SQL.MetodosSQL;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class Registro extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            MetodosSQL metodos = new MetodosSQL();
       
            String txtNombre = request.getParameter("nombreRegistro");
            String txtApellidos = request.getParameter("apellidosRegistro");
            String txtContrasena = request.getParameter("passwordRegistro");
            String txtCorreo = request.getParameter("correoRegistro");
            
             out.println("<!DOCTYPE html>");
             out.println("<html>");
             out.println("<body bgcolor = \"FFF0C9\">");
        
             out.println("<br><table cellpadding='10' cellspacing='10' border style=\"background: rgba(255, 255, 255, 0.9); border: 1px solid rgba(100, 200, 0, 0.3);\">");
            out.println("<tbody style=\"background: rgba(255, 255, 255, 0.9); border: 1px solid rgba(100, 200, 0, 0.3);\">");
            out.println("<tr>\n" +
"                    <td align=\"center\">\n" +
"                        <h1>Sube tu producto</h1>\n" +
"                        <hr width=30% size=\"2\" color=\"black\">\n" +
"\n" +
"                        \n" +
"\n" +
"                        <table RULES=\"none\">\n" +
"                            \n" +
"                            <tr>\n");
            
             
             boolean usuarioRepetido = metodos.buscarUsuarioRepetido(txtCorreo);

             if (usuarioRepetido == true) { //EL usuario ya esta registrado en la BD
             out.println("<h1>El correo ya esta registrado<h1>");
             out.println("volver a <a href =\"registro.html\">registro </a> ");
            
             } else {
            
                 boolean registro = metodos.registrarUsuario(txtNombre, txtApellidos, txtContrasena, txtCorreo);
                 if (registro == true) {//El usuario se ha registrado
                out.println("<h1>El usuario se ha registrado con exito<h1>");
                out.println("Ahora <a href =\"index.html\">inicia sesion </a> ");
                
            } else {
                out.println("El usuario no se ha registrado");
                out.println("volver a <a href =\"registro.html\">registro </a> ");
                
            }
            System.out.println("El valor de registro en SERVLET es: " + registro);
            
        }

        out.println("</tbody>");
        out.println("</th> </tr>");
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
