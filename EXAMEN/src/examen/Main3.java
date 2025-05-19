package examen;

import java.util.Arrays;
import java.util.List;

public class Main3 {

    public static void main(String[] args) {
        List<Vehicle> vehicles = Arrays.asList(
            new Vehicle("1234-HJK", "Toyota",     "Corolla", 2010, 12000),
            new Vehicle("1230-BCN", "Toyota",     "Yaris",   2010, 12200),
            new Vehicle("5678-KLM", "Volkswagen", "Golf",    2018, 18000),
            new Vehicle("5669-HYM", "Volkswagen", "Polo",    2020, 21000),
            new Vehicle("9012-NPR", "Seat",       "Ibiza",   2015, 10000),
            new Vehicle("3456-STU", "Tesla",      "Model 3", 2022, 40000),
            new Vehicle("7890-VWX", "Renault",    "Clio",    2012,  9000)
        );
        incrementaPreu calcul = ((v1, p1, v2) -> {double nouPreu = v2.getPreu() + v2.getPreu() * p1 / 100;
                                                                  v1.setPreu(nouPreu);
                                                                 });
        
        vehicles.stream().forEach(v -> calcul.biFunction(v, 10.0, v));
        vehicles.stream().forEach(n -> System.out.println(n));
        
    }
}

