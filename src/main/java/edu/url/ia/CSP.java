package edu.url.ia;

import java.util.*;
import java.util.stream.Collectors;

public class CSP <V, D> {

    private List<V> variables;
    private Map<V, List<D>> originalDomains;
    private Map<V, List<D>> currentDomains;
    private Map<V, List<Constraint<V, D>>> constraints = new HashMap<>();

    //Class constructor
    public CSP(List<V> variables, Map<V, List<D>> domains){
        this.variables = variables;
        this.originalDomains = domains;

        for (V variable: variables) {
            constraints.put(variable, new ArrayList<Constraint<V, D>>());

            //Cada variable debe de tener un dominio asignado
            if(!domains.containsKey(variable)){
                throw new IllegalArgumentException("La variable: " + variable + " no cuenta con un dominio");
            }
        }
    }

    //Method for add constraints
    public void addConstraint(Constraint<V, D> constraint){
        for (V variable: constraint.variables) {
            //La variable que se encuentra en el constraint también sea parte del CSP
            if(!this.variables.contains(variable)){
                throw new IllegalArgumentException("La variable: " + variable + " no se encuentra en el CSP");
            }
            constraints.get(variable).add(constraint);
        }
    }

    //Método que verifica si la relación es consistente.
    public boolean consistent(V variable, Map<V, D> assigment){
        for (Constraint<V, D> constraint: this.constraints.get(variable)) {
            if(!constraint.satisfied(assigment)){
                return false;
            }
        }
        return true;
    }

    public Map<V, D> backTrack(){
        return backTrack(new HashMap<>(), this.originalDomains);
    }

    public Map<V, D> backTrack(Map<V, D> assigment, Map<V, List<D>> currentDomains){
        if (variables.size() == assigment.size()) {
            return assigment;
        }
        V unassigned = variables.stream().filter(v -> !assigment.containsKey(v)).findFirst().get();
        this.currentDomains = currentDomains;
        for (D value: this.currentDomains.get(unassigned)) {
            //System.out.println("Variable: " + unassigned + " = "+value);
            Map<V, D> localAssigment = new HashMap<>(assigment);
            localAssigment.put(unassigned, value);
            if(consistent(unassigned, localAssigment)){
                ArrayList<D> actualDomain = new ArrayList<>();
                actualDomain.add(value);
                this.currentDomains.put(unassigned, actualDomain);
                if(!checkConsistency(unassigned)){
                    return null;
                }
                Map<V, D> result = backTrack(localAssigment, currentDomains);
                if(result != null){
                    return result;
                }
                this.currentDomains = currentDomains;
            }
        }
        return null;
    }

    public boolean checkConsistency(V checkVariable){
        Queue<ArcConsistency<V, D>> inconsistencies = new ArrayDeque<>();
        for(Constraint<V, D> constraint: this.constraints.get(checkVariable)){
            var inconsistencyOrigin = constraint.variables.stream().filter(v -> v != checkVariable).findFirst().get();
            inconsistencies.add(new ArcConsistency<V, D>(inconsistencyOrigin, checkVariable, constraint));
        }

        return AC3(inconsistencies);
    }

    private boolean AC3(Queue<ArcConsistency<V, D>> inconsistencies){
        while(!inconsistencies.isEmpty()){
            var actualARC = inconsistencies.poll();
            if(Revise(actualARC)){
                if(this.currentDomains.get(actualARC.getOrigin()).size() == 0){
                    return false;
                }
                for (Constraint<V, D> constraint : this.constraints.get(actualARC.getOrigin())){
                    var inconsistencyOrigin = constraint.variables.stream().filter(v -> v != actualARC.getOrigin()).findFirst().get();
                    if(inconsistencyOrigin != actualARC.getDestiny()){
                        inconsistencies.add(new ArcConsistency<V, D>(inconsistencyOrigin, actualARC.getOrigin(), constraint));
                    }
                }
            }
        }
        return true;
    }

    private boolean Revise(ArcConsistency<V, D> arc){
        boolean isValid;
        Map<V, D> newAssigment = new HashMap<>();
        for (var value: this.currentDomains.get(arc.getOrigin())){
            isValid = false;
            for (var testValue: this.currentDomains.get(arc.getDestiny())){
                newAssigment.put(arc.getOrigin(), value);
                newAssigment.put(arc.getDestiny(), testValue);
                if(arc.getConstraint().satisfied(newAssigment)){
                    isValid = true;
                    break;
                }
            }
            if(!isValid){
                List<D> newDomain = this.currentDomains.get(arc.getOrigin()).stream().filter(d -> d != value).collect(Collectors.toList());
                this.currentDomains.put(arc.getOrigin(), newDomain);
                return true;
            }
        }
        return false;
    }
}
