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
