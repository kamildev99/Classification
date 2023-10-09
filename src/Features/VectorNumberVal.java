package Features;

public class VectorNumberVal implements Comparable<VectorNumberVal> {

    private Features featureVector;
    private Double distance;

    public VectorNumberVal(Features featureVector, Double distance) {
        this.featureVector = featureVector;
        this.distance = distance;
    }

    public Features getFeatureVector() {
        return this.featureVector;
    }
    public double getDistance() {
        return distance;
    }


    @Override
    public int compareTo(VectorNumberVal o) {
        if (this.getDistance() < o.getDistance()) {
            return -1;
        } else if (this.getDistance() > o.getDistance()) {
            return 1;
        }

        return 0;
    }


}
