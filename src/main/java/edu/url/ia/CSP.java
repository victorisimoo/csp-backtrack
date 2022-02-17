package edu.url.ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSP <V, D> {

    private List<V> variables;
    private Map<V, List<D>> domains;
    private Map<V, List<Constraint<V, D>>> constraints = new HashMap<>();

    //Class constructor
    public CSP(List<V> variables, Map<V, List<D>> domains){
        this.variables = variables;
        this.domains = domains;

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
        return backTrack(new HashMap<>());
    }

    public Map<V, D> backTrack(Map<V, D> assigment){
        //Condición de escape: si la asignación es completa (si cada variable tiene un valor)
        if(variables.size() == assigment.size()){
            return assigment;
        }

        // Seleccionar una variable no asignada
        V unassigned =  variables.stream()
                .filter(v -> !assigment.containsKey(v))
                .findFirst().get();

        for (D value : domains.get(unassigned)){
            //System.out.println("Variable: " + unassigned + " valor: " +value);

            //Probar una asignación
            //1. Crear una copia de la asignación anterior
            Map<V, D> localAssiment = new HashMap<>(assigment);

            //2. Probar (asignar un valor)
            localAssiment.put(unassigned, value);

            //3. Verificar la consistencia de la asignación
            if(consistent(unassigned, localAssiment)){
                Map<V, D> result = backTrack(localAssiment);
                if(result != null){
                    return result;
                }
            }
        }
        return null;
    }

}
