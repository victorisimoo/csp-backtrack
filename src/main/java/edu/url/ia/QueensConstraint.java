package edu.url.ia;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class QueensConstraint extends Constraint<Integer, Integer>{
    private List<Integer> columns;

    public QueensConstraint(List<Integer> columns) {
        super(columns);
        this.columns = columns;
    }

    @Override
    public boolean satisfied(Map<Integer, Integer> assigment) {
        for (Entry<Integer, Integer> item: assigment.entrySet()){
            int q1c = item.getKey();
            int q1r = item.getValue();

            for (int q2c = q1c + 1; q2c <= columns.size(); q2c++){
                if(assigment.containsKey(q2c)){
                    int q2r = assigment.get(q2c);
                    if(q1r == q2r){
                        return false;
                    }

                    if(Math.abs(q1r - q2r) == Math.abs(q1c - q2c)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}