package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Conector2DBMysql {

    private Connection conn = null;
    Conector2DBMysql() {
        crearConexion();
    }


    private void crearConexion(){
        try {
            /*conn = DriverManager.getConnection("jdbc:mysql://db4free.net/dbprueba2023?" +
                            "user=userdb2023y&password=pass2023");*/

            conn = DriverManager.getConnection("jdbc:mysql://db4free.net/dbprueba2023", "userdb2023", "pass2023");
            // Do something with the Connection


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
    public ArrayList<Departamento> consultarDepartamentos() {

        // assume that conn is an already created JDBC connection (see previous examples)

        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Departamento> deptos = new ArrayList<Departamento>();

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM departamento");
            while(rs.next()) {
                Departamento depto = new Departamento();
                depto.setNombre(rs.getString("nombre"));
                depto.setPresupuesto(rs.getInt("presupuesto"));
                depto.setId(rs.getInt("id"));
                deptos.add(depto);
            }



        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
            return deptos;
        }
    }
}
