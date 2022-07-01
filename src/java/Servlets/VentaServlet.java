/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

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
public class VentaServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vender un producto</title>");            
            out.println("</head>");
            out.println("<link href=\"Estilos.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<body bgcolor=\"FFF0C9\">");
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
            out.println("<form action='Venta' method = 'post' enctype = \"multipart/form-data\" >");
            
            out.println("Nombre:");
            out.println("<input type ='text' name='nombreVenta' required>" );
            out.println("<br>Precio:");
            out.println("<br><input type ='number' value = '0' name='precioVenta' required>" );
            out.println("<br>Ingresa la imagen de tu producto:");
            out.println("<br><input type ='file' name='file' accept=\"image/png, image/jpeg\" size = \"50\" required>" );
            out.println("<br><input type ='submit' name='submitVenta' required>" );
            out.println("</tbody>");
            out.println("</form> </th> </tr>");
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
