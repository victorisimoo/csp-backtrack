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

    public void setOrigin(V origin){ this.origin = origin; }
    public void setDestiny(V destiny) { this.destiny = destiny; }
    public void setConstraint(Constraint<V, D> constraint){ this.constraint = constraint; }
    public V getOrigin(){ return origin; }
    public V getDestiny(){return destiny;}
    public Constraint<V, D> getConstraint() {return constraint;}

}
