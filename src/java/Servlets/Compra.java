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
public class Compra extends HttpServlet {

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
            
            String txtUsuario = request.getParameter("txtUsuario");
            
            
            int count = metodos.tamanoCatalogo();
            String nombre = null;
            double precio = 0;
        
            boolean compra = false;
            
            for(int i=1; i<=count; i++){
                String btnComprar = request.getParameter("btnComprar"+i);
                if (btnComprar != null){
                    nombre = metodos.nombreCatalogo(String.valueOf(i));
                    precio = metodos.precio(String.valueOf(i));
                    
                    
                    metodos.registrarTransaccion(metodos.buscarUsuarioID(txtUsuario), String.valueOf(i));
                    compra = true;
                    
                    
            
                    
                }
            }
           
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Comprando"+nombre+"</title>");            
            out.println("</head>");
            out.println("<body bgcolor = \"FFF0C9\">");
        
            out.println("<br><table cellpadding='10' cellspacing='10' border style=\"background: rgba(255, 255, 255, 0.9); border: 1px solid rgba(100, 200, 0, 0.3);\">");
            out.println("<tbody style=\"background: rgba(255, 255, 255, 0.9); border: 1px solid rgba(100, 200, 0, 0.3);\">");
            out.println("<tr>\n" +
"                    <td align=\"center\">\n" +
"                        <h1>Tu Compra de "+nombre+ "...</h1>\n" +
"                        <hr width=30% size=\"2\" color=\"black\">\n" +
"\n" +
"                        \n" +
"\n" +
"                        <table RULES=\"none\">\n" +
"                            \n" +
"                            <tr>\n");
             out.println(txtUsuario);
           
             out.println("<form action = 'CatalogoServlet'>");
             out.println("Fue exitosa :D, ahora tu saldo es de: ");
             out.println("<input type ='submit' value = 'Regresar al catalogo' name = 'btnRegresar'>" );
              out.println("<style>"); 
               out.println("input{");
            out.println(" background: plum;");
            out.println("border: 1px");
           
            out.println("}");
               out.println("</style>"); 
             out.println("</form> ");
            out.println("</tbody> </th> </tr>");
           
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
