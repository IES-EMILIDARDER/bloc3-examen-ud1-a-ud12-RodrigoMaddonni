package examen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main1 {
    
    public static void main(String[] args) {
        List<Vehicle> vehicles = Arrays.asList(
            new Vehicle("1234-HJK", "Toyota",     "Corolla", 2010, 12000),
            new Vehicle("5678-KLM", "Volkswagen", "Golf",    2018, 18000),
            new Vehicle("9012-NPR", "Seat",       "Ibiza",   2015, 10000),
            new Vehicle("3456-STU", "Tesla",      "Model 3", 2022, 40000),
            new Vehicle("7890-VWX", "Renault",    "Clio",    2012,  9000)
        );
        ArrayList<String> marcas = new ArrayList<>();
        
        // Filtrar lista por a?o y eliminar duplicados
        List<Vehicle> vehiclesFiltrados = vehicles.stream().distinct().filter(n -> n.getAny() > 2014).collect(Collectors.toList());
        
        // Ordenar por marca
        vehiclesFiltrados.sort((v1, v2) -> v1.getMarca().compareTo(v2.getMarca()));
        
        // Agregar a ArrayList de Marcas
        vehiclesFiltrados.forEach(n -> marcas.add(n.getMarca()));
        
        // Imprimir lista final
        marcas.forEach(n -> System.out.println(n));
    }
}

