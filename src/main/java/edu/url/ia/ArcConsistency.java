package edu.url.ia;

public class ArcConsistency <V, D> {

    private V origin;
    private V destiny;
    private Constraint<V, D> constraint;

    public ArcConsistency(V origin, V destiny, Constraint<V, D> constraint){
        this.origin = origin;
        this.destiny = destiny;
        this.constraint = constraint;
    }

    public V getOrigin(){ return origin; }
    public V getDestiny(){return destiny;}
    public Constraint<V, D> getConstraint() {return constraint;}

}
