package examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main2 {

    public static void main(String[] args) {
        try (Stream<String> linies = Files.lines(Paths.get("c:\\temp\\vehicles.csv"))) {

            List<Vehicle> vehicles = 
                   linies.filter(linia -> !linia.isBlank() && !linia.startsWith("#"))
                  .map(linia -> linia.split(","))
                  //.sorted((parts1, parts2) -> parts1[1].compareTo(parts2[1])) // ordenat per nom
                  .map(parts -> new Vehicle(parts[0].trim(),
                                            parts[1].trim(),
                                            parts[2].trim(),
                                            Integer.parseInt(parts[3].trim()),
                                            Double.parseDouble(parts[4].trim())
                                          )
                      )
                  .collect(Collectors.toList());
            
            
            System.out.println("Resultat:");
            
            double nombreTotal = vehicles.stream().filter(n -> n.getPreu() > 15000).count();
            System.out.println(nombreTotal);
            
            double mitjana = vehicles.stream().mapToDouble(n->n.getPreu()).average().orElse(0);
            System.out.println(mitjana);
            
            double mesCar = vehicles.stream().mapToDouble(n -> n.getPreu()).max().orElse(0);
            System.out.println(mesCar);
            
            double mesBarat = vehicles.stream().mapToDouble(n -> n.getPreu()).min().orElse(0);
            System.out.println(mesBarat);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

