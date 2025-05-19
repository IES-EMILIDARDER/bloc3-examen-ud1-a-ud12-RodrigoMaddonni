package examen;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main4 {

    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        final String arxiu = "c:\\temp\\mysql.con";
        int anyFiltrat = 2020;
        GestorBBDD gestorBBDD = new GestorBBDD(arxiu);
        
        try (Connection conn = gestorBBDD.getConnectionFromFile();
                ResultSet resultSet = gestorBBDD.executaQuerySQL(conn, "SELECT matricula, marca, model, any, preu FROM vehicles where any >= ?", anyFiltrat) ) {  
                while (resultSet.next())
                    afageixVehicle(vehicles, new Vehicle(resultSet.getString("matricula"),
                                                    resultSet.getString("marca"), 
                                                    resultSet.getString("model"),
                                                    resultSet.getInt("any"),
                                                    resultSet.getDouble("preu")));
        } catch (SQLException e) {
            System.err.println("Error carregant vehicles BBDD: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error conectant BBDD: " + e.getMessage());
        }
        
        // Imprimir
        vehicles.stream().forEach(v -> System.out.println(v));
    }
    
    
    
    // Metodo para agregar vehículo
    private static void afageixVehicle(List<Vehicle> vehicles, Vehicle v) {
        vehicles.add(v);
    }
}