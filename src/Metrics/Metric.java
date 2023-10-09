package Metrics;

import java.util.ArrayList;
import java.util.List;

public class Metric {
    public static double Euclidean(List<Double> v1, List<Double> v2){
        double distance = 0;

        for(int i=0; i<v1.size(); i++){
            distance += Math.pow((v1.get(i).doubleValue() - v2.get(i).doubleValue()), 2);
        }

        return Math.sqrt(distance);


    }

    public static double Manhattan(List<Double> v1, List<Double> v2){
        double distance = 0;

        for(int i=0; i<v1.size(); i++){
            distance += Math.abs((v1.get(i).doubleValue() - v2.get(i).doubleValue()));
        }
        return distance;
    }

    public static double Chebyshev(List<Double> v1, List<Double> v2){
        List<Double> tmp = new ArrayList<Double>();
        double value = 0;

        for(int i=0; i<v1.size(); i++){
            if(value  < Math.abs(v1.get(i).doubleValue() - v2.get(i).doubleValue())){
                value = Math.abs(v1.get(i).doubleValue() - v2.get(i).doubleValue());
            }
            tmp.add(Math.abs(v1.get(i).doubleValue() - v2.get(i).doubleValue()));

        }

        return  value;
    }
}
