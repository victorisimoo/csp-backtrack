package edu.url.ia;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App  {
    public static void main( String[] args )  {

        //CSP
        //Variables
        /*List<String> variables = List.of("Western Australia", "Nothern Territory", "Queensland", "South Australia",
                "New South Wales", "Victoria", "Tasmania");

        //Dominios
        Map<String, List<String>> domains = new HashMap<>();
        for (var variable: variables){
            domains.put(variable, List.of("rojo", "verde", "azul"));
        }

        //Restrictions
        CSP<String, String> problema = new CSP<>(variables, domains);
        problema.addConstraint(new AustraliaColoringConstraint("Western Australia", "Nothern Territory"));
        problema.addConstraint(new AustraliaColoringConstraint("Western Australia", "South Australia"));
        problema.addConstraint(new AustraliaColoringConstraint("Nothern Territory", "South Australia"));
        problema.addConstraint(new AustraliaColoringConstraint("Nothern Territory", "Queensland"));
        problema.addConstraint(new AustraliaColoringConstraint("South Australia", "Queensland"));
        problema.addConstraint(new AustraliaColoringConstraint("New South Wales", "Queensland"));
        problema.addConstraint(new AustraliaColoringConstraint("New South Wales", "South Australia"));
        problema.addConstraint(new AustraliaColoringConstraint("Victoria", "South Australia"));
        problema.addConstraint(new AustraliaColoringConstraint("New South Wales", "Victoria"));
        problema.addConstraint(new AustraliaColoringConstraint("Tasmania", "Victoria"));

        //Solucion
        var solution = problema.backTrack();
        System.out.println(solution);*/

        List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        Map<Integer, List<Integer>> rows = new HashMap<>();
        for (int column: columns){
            rows.put(column, List.of(1, 2, 3, 4, 5, 6, 7, 8));
        }
        CSP<Integer, Integer> csp = new CSP<>(columns, rows);
        csp.addConstraint(new QueensConstraint(columns));
        Map<Integer, Integer> solution = csp.backTrack();
        if(!(solution == null)){
            System.out.println(solution);
        }else {
            System.out.println("No solution found!");
        }

    }
}
