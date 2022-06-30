/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;



import SQL.MetodosSQL;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author Usuario
 */
@MultipartConfig
@WebServlet(name = "FileUploadName")
public class Venta extends HttpServlet {
    

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
       
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private String path = "";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        MetodosSQL metodos = new MetodosSQL();
        String name = request.getParameter("nombreVenta");
        double precio =  Double.parseDouble(request.getParameter("precioVenta"));
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        Part filePart = request.getPart("file");
         out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vender un producto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Sube tu venta</h1>");
            out.println("<form action='CatalogoServlet'>");
        String fileName = filePart.getSubmittedFileName();
        out.print("<br><br> file name: "+ fileName);
        OutputStream os = null;
        InputStream is = null;
        
        try{
    
         is = filePart.getInputStream();
         if (metodos.registrarProducto(is, name, precio)){
            out.print("<br>fileUpload");
         }else{
         out.print("<br>not file upload");
         }
         int lenght = (int) filePart.getSize();
         byte[] arr = new byte[lenght];
         is.read(arr);
         is.close();
         
        
        
         
            
         
        }catch(FileNotFoundException e){
            out.println(e + "LOOOOOOOOOOOOOOOOOL");
        }
        out.println("<input type = 'submit' value = 'Regresar al catalogo'> ");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        
        }
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
