/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
/**
 *
 * @author Usuario
 */

public class MetodosSQL {
    private Connection conexion;
    private PreparedStatement sentenciaPreparada;
    private ResultSet resultado;
    private int tamano = tamanoCatalogo()+1;
    private int tamanoUsuarios = tamanoUsuarios();
    private int tamanoTransaccion = tamanoTransacciones();
    
    public boolean registrarProducto(InputStream is, String nombre, double precio){
         boolean registro = false;
      try {
           
            conexion = Conexion.conectar();
            String consulta = "INSERT INTO catagolo (id,nombre,fecha,precio,imagen) VALUES (?,?,?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            System.out.println("HOLAAAAAAAAAAAAA"+ precio);
            sentenciaPreparada.setInt(1, tamano);
            sentenciaPreparada.setString(2, nombre);
             sentenciaPreparada.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            sentenciaPreparada.setDouble(4, (double) precio);
           
            sentenciaPreparada.setBinaryStream(5, is);
            
           

            int resultadoInsercion = sentenciaPreparada.executeUpdate();

            if (resultadoInsercion > 0) {
                registro = true; 
                System.out.println("Se hizo el alta del producto");
            } else {
                registro = false; 
                System.out.println("NO se hizo el alta del producto");
            }

            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }

        System.out.println("Valor del registro: " + registro);
        return registro;
    }
    
    public double dineroUsuario(String id){
        double dinero = 0;
        try {
            conexion = Conexion.conectar();
            String consulta = "SELECT dinero FROM usuario WHERE id = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, id);
            resultado =  sentenciaPreparada.executeQuery();
            if(resultado.next()){// El usuario fue encontrado y obtenemos el nombre 
                dinero = resultado.getDouble("dinero");
            }else{
                dinero = 0; //EL usuario no fue encontrado y NO obtenemos el nombre
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
      return dinero;
    }
    
    public double precio(String id){
        double precio = 0;
        try {
            conexion = Conexion.conectar();
            String consulta = "SELECT precio FROM catagolo WHERE id = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, id);
            resultado =  sentenciaPreparada.executeQuery();
            if(resultado.next()){// El usuario fue encontrado y obtenemos el nombre 
                precio = resultado.getDouble("precio");
            }else{
                precio = 0; //EL usuario no fue encontrado y NO obtenemos el nombre
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
           return precio;
    }
    
    public Date fecha(String id){
    
        Date fecha = null;
        try {
            conexion = Conexion.conectar();
            String consulta = "SELECT fecha FROM catagolo WHERE id = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, id);
            resultado =  sentenciaPreparada.executeQuery();
            if(resultado.next()){// El usuario fue encontrado y obtenemos el nombre 
                fecha = resultado.getDate("fecha");
            }else{
                fecha = null; //EL usuario no fue encontrado y NO obtenemos el nombre
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
        
        return fecha;
    }
    
    public String enlazar(){
        String connect = null;
        try {
            
             conexion = Conexion.conectar();
        if (conexion == null){
                 
                connect = "<h1>Conexion no establecida</h1>";
                }else{
                connect = "<h1>Conexion establecida</h1>";
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
        
       
        
        return connect;
    }
    
    public int tamanoUsuarios(){
       int usuarioLenght = 0;
       try {
            conexion = Conexion.conectar();
            String consulta = "SELECT COUNT(id) as UsuarioTotal FROM usuarios";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            resultado =  sentenciaPreparada.executeQuery();
            if(resultado.next()){// El TAMAÑO fue encontrado y obtenemos el nombre 
                usuarioLenght = resultado.getInt("UsuarioTotal");
                 System.out.println(usuarioLenght+" USUARIO");
                
            }else{
                usuarioLenght = 0; 
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
        
        
        return usuarioLenght;
    
    }
    
    public int tamanoTransacciones(){
       int transaccionLenght = 0;
       try {
            conexion = Conexion.conectar();
            String consulta = "SELECT COUNT(id) as ProductoTotal FROM transacciones";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            resultado =  sentenciaPreparada.executeQuery();
            if(resultado.next()){// El TAMAÑO fue encontrado y obtenemos el nombre 
                transaccionLenght = resultado.getInt("ProductoTotal");
                
            }else{
                transaccionLenght = 0; 
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
        
        
        return transaccionLenght;
    
    }
    
     public int tamanoCatalogo(){
       int catalogoLenght = 0;
       try {
            conexion = Conexion.conectar();
            String consulta = "SELECT COUNT(id) as ProductoTotal FROM catagolo";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            resultado =  sentenciaPreparada.executeQuery();
            if(resultado.next()){// El TAMAÑO fue encontrado y obtenemos el nombre 
                catalogoLenght = resultado.getInt("ProductoTotal");
                
            }else{
                catalogoLenght = 0; 
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
        
        
        return catalogoLenght;
    
    }
    
    public String nombreCatalogo(String id){
        
       String catalogoName = null;
       try {
            conexion = Conexion.conectar();
            String consulta = "SELECT nombre FROM catagolo WHERE id = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, id);
            resultado =  sentenciaPreparada.executeQuery();
            
            if(resultado.next()){// El NOMBRE fue encontrado y obtenemos el nombre 
                catalogoName = resultado.getString("Nombre");
                
            }else{
                catalogoName = null; 
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
        
        
        return catalogoName;
    
    }
    
    public String imgCatalogo(String id){
        byte[] bytes = null;
       java.sql.Blob blob = null;
       try {
            conexion = Conexion.conectar();
            String consulta = "SELECT imagen FROM catagolo WHERE id = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, id);
            resultado =  sentenciaPreparada.executeQuery();
            if(resultado.next()){
                blob = resultado.getBlob("imagen");
                bytes = blob.getBytes(1, (int) blob.length());
                
            }else{
                blob = null; 
            }
            
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
        
        String imgString;
        imgString = Base64.getEncoder().encodeToString(bytes);
        System.out.println(bytes + " blooob");
        return imgString;
    
    }
    
     public boolean registrarTransaccion(int usuarioID, String productID) {
        boolean registro = false;

        try {
            conexion = Conexion.conectar();
            String consulta = "INSERT INTO usuarios (id,usuarioID,fecha,productID) VALUES (?,?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(consulta);
             System.out.println(tamanoUsuarios);
            sentenciaPreparada.setInt(1, tamanoTransaccion);
            sentenciaPreparada.setInt(2, usuarioID);
            sentenciaPreparada.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            sentenciaPreparada.setString(4, productID);
            

            int resultadoInsercion = sentenciaPreparada.executeUpdate();

            if (resultadoInsercion > 0) {
                registro = true; // EL usuario se ah registrado
                System.out.println("Se hizo el alta de la transaccion");
            } else {
                registro = false; // EL usuario NO se ah registrado
                System.out.println("NO se hizo el alta de la transaccion");
            }

            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }

        System.out.println("Valor del registro: " + registro);
        return registro;
    }
    
    
    
    public boolean registrarUsuario(String nombre, String apellidos, String contrasena, String usuarioGenerado) {
        boolean registro = false;

        try {
            conexion = Conexion.conectar();
            String consulta = "INSERT INTO usuarios (id,nombre,apellidos,contrasena,usuario_generado, dinero) VALUES (?,?,?,?,?,?)";
            sentenciaPreparada = conexion.prepareStatement(consulta);
             System.out.println(tamanoUsuarios);
            sentenciaPreparada.setInt(1, tamanoUsuarios);
            sentenciaPreparada.setString(2, nombre);
            sentenciaPreparada.setString(3, apellidos);
            sentenciaPreparada.setString(4, contrasena);
            sentenciaPreparada.setString(5, usuarioGenerado);
            sentenciaPreparada.setDouble(6, (double) 500);

            int resultadoInsercion = sentenciaPreparada.executeUpdate();

            if (resultadoInsercion > 0) {
                registro = true; // EL usuario se ah registrado
                System.out.println("Se hizo el alta del usuario");
            } else {
                registro = false; // EL usuario NO se ah registrado
                System.out.println("NO se hizo el alta del usuario");
            }

            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }

        System.out.println("Valor del registro: " + registro);
        return registro;
    }

    public boolean buscarUsuarioRepetido(String correo) {
        boolean usuarioRepetido = false;
        try {
            conexion = Conexion.conectar();
            String consulta = "SELECT usuario_generado FROM usuarios WHERE usuario_generado = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, correo);
            resultado = sentenciaPreparada.executeQuery();

            if (resultado.next()) {
                usuarioRepetido = true; // El usuario esta registrado en la BD
            } else {
                usuarioRepetido = false; // El usuario NO esta registrado en la BD
            }

            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }

        System.out.println("El valor del usuario Repetido en el METODO es: " + usuarioRepetido);
        return usuarioRepetido;
    }

    public boolean buscarUsuarioInicioSesion(String usuario, String contrasena) {
        boolean iniciarSesion = false;

        try {
            conexion = Conexion.conectar();
            String consulta = "SELECT usuario_generado,contrasena FROM usuarios WHERE usuario_generado = ? AND contrasena = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, usuario);
            sentenciaPreparada.setString(2, contrasena);
            resultado = sentenciaPreparada.executeQuery();
            System.out.println("usuario: " + usuario);
            System.out.println("contraseña: " + contrasena);
            if (resultado.next()) {
                iniciarSesion = true;//El usuario puede iniciar Sesion por que esta registrado en la BD
            } else {
                iniciarSesion = false;//El usuario NO puede iniciar sesion por que no esta registrado en la BD
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }

        System.out.println("El valor de iniciarSesion en el metodo es: " + iniciarSesion);
        return iniciarSesion;
    }

    
    
    public double buscarDinero(String usuario) {
        double dinero = 0;
        try {
            conexion = Conexion.conectar();
            String consulta = "SELECT dinero FROM usuarios WHERE usuario_generado = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, usuario);
            resultado =  sentenciaPreparada.executeQuery();
            if(resultado.next()){
                dinero = resultado.getDouble("dinero");
            }else{
                dinero = 0; 
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
        
        System.out.println("El valor del nombre en los Metodos SQL es : "  + dinero);
        return dinero;
    }
    
     public int buscarUsuarioID(String usuario) {
        int id = 0;
        try {
            conexion = Conexion.conectar();
            String consulta = "SELECT id FROM usuarios WHERE usuario_generado = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, usuario);
            resultado =  sentenciaPreparada.executeQuery();
            if(resultado.next()){
                id = resultado.getInt("dinero");
            }else{
                id = 0; 
            }
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " +e);
        }finally{
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error" + e);
            }
        }
        
        System.out.println("El valor del id en los Metodos SQL es : "  + id);
        return id;
    }
    
    public byte[] getBlobAsBytes(ResultSet rs, String columnIndex) throws SQLException {
        Blob blob = rs.getBlob(columnIndex);
       
        if (blob == null) {
            return null;
         }
        return blob.getBytes(1, (int) blob.length());
    }
}
