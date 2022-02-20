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
        List<String> variables = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA");

        //Dominios
        Map<String, List<String>> domains = new HashMap<>();
        for (var variable: variables){
            domains.put(variable, List.of("Cyan", "Magenta", "Yellow", "Key"));
        }

        //Restrictions
        CSP<String, String> problema = new CSP<>(variables, domains);
        problema.addConstraint(new AustraliaColoringConstraint("A", "B"));
        problema.addConstraint(new AustraliaColoringConstraint("A", "C"));
        problema.addConstraint(new AustraliaColoringConstraint("B", "C"));
        problema.addConstraint(new AustraliaColoringConstraint("C", "D"));
        problema.addConstraint(new AustraliaColoringConstraint("C", "W"));
        problema.addConstraint(new AustraliaColoringConstraint("D", "K"));
        problema.addConstraint(new AustraliaColoringConstraint("D", "E"));
        problema.addConstraint(new AustraliaColoringConstraint("D", "F"));
        problema.addConstraint(new AustraliaColoringConstraint("E", "F"));
        problema.addConstraint(new AustraliaColoringConstraint("E", "G"));
        problema.addConstraint(new AustraliaColoringConstraint("F", "H"));
        problema.addConstraint(new AustraliaColoringConstraint("H", "I"));
        problema.addConstraint(new AustraliaColoringConstraint("G", "I"));
        problema.addConstraint(new AustraliaColoringConstraint("I", "J"));
        problema.addConstraint(new AustraliaColoringConstraint("I", "O"));
        problema.addConstraint(new AustraliaColoringConstraint("J", "N"));
        problema.addConstraint(new AustraliaColoringConstraint("K", "L"));
        problema.addConstraint(new AustraliaColoringConstraint("L", "M"));
        problema.addConstraint(new AustraliaColoringConstraint("K", "P"));
        problema.addConstraint(new AustraliaColoringConstraint("Q", "P"));
        problema.addConstraint(new AustraliaColoringConstraint("Q", "R"));
        problema.addConstraint(new AustraliaColoringConstraint("R", "S"));
        problema.addConstraint(new AustraliaColoringConstraint("R", "U"));
        problema.addConstraint(new AustraliaColoringConstraint("R", "V"));
        problema.addConstraint(new AustraliaColoringConstraint("V", "U"));
        problema.addConstraint(new AustraliaColoringConstraint("V", "AA"));
        problema.addConstraint(new AustraliaColoringConstraint("U", "AA"));
        problema.addConstraint(new AustraliaColoringConstraint("Z", "AA"));
        problema.addConstraint(new AustraliaColoringConstraint("Z", "U"));
        problema.addConstraint(new AustraliaColoringConstraint("Z", "S"));
        problema.addConstraint(new AustraliaColoringConstraint("Z", "Y"));
        problema.addConstraint(new AustraliaColoringConstraint("S", "Y"));
        problema.addConstraint(new AustraliaColoringConstraint("S", "X"));
        problema.addConstraint(new AustraliaColoringConstraint("S", "T"));
        problema.addConstraint(new AustraliaColoringConstraint("X", "Y"));
        problema.addConstraint(new AustraliaColoringConstraint("X", "T"));
        problema.addConstraint(new AustraliaColoringConstraint("T", "W"));
        problema.addConstraint(new AustraliaColoringConstraint("X", "W"));

        //Solucion
        Map<String, String> solution = problema.backtrack();
        System.out.println(solution);

    }
}
