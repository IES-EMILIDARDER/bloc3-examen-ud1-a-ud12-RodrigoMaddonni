package examen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main5 {
    
    public static void main(String[] args) {
        List<Vehicle> vehicles = Arrays.asList(
            new Vehicle("4321-JKL", "Ford",     "Focus",   2019, 17000),
            new Vehicle("8765-MNO", "Hyundai",  "Ioniq 5", 2023, 42000),
            new Vehicle("2109-PQR", "Peugeot",  "308",     2016, 14000)
        );

        final String arxiu = "c:\\temp\\mysql.con";

        GestorBBDD gestorBBDD = new GestorBBDD(arxiu);

         try ( Connection conn = gestorBBDD.getConnectionFromFile() ) {
            vehicles.stream()
                 .forEach( v -> { 
                                     try {
                                            gestorBBDD.executaSQL(conn, "INSERT INTO vehicles(matricula, marca, model, any, preu) VALUES (?, ?, ?, ?, ?)",
                                                                  v.getMatricula(), v.getMarca(), v.getModel(), v.getAny(), v.getPreu());
                                         } catch (SQLException e) {
                                             try {
                                                if (e.getSQLState().equals("23000") && e.getErrorCode() == 1062)
                                                    gestorBBDD.executaSQL( conn, "UPDATE vehicles SET marca = ?, model = ?, any = ?, preu = ? WHERE matricula = ?",
                                                                           v.getMarca(), v.getModel(), v.getAny(), v.getPreu(), v.getMatricula());
                                                else 
                                                    throw new RuntimeException (e);  // en un 'forEach' cal disparar aquesta exception
                                             } catch (SQLException e2) {
                                                 throw new RuntimeException (e2);  // en un 'forEach' cal disparar aquesta exception
                                             }
                                         }
                                    }
                         );

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
