package MethodKNN;

import Data.Article;
import Features.Features;
import Features.FeatureVector;
import Features.VectorNumberVal;

import java.util.*;

public class MethodKNN {


    List<Features> calculate(Features featureVector,List<Features> trainingVectors, int numberK, String numericalMetric, String textMetric, Map<String, Boolean> checked) {

        List<VectorNumberVal> calculatedFeatureVectors = new ArrayList<VectorNumberVal>();


        for (Features it : trainingVectors) {
            calculatedFeatureVectors.add(new VectorNumberVal(it,
                    FeatureVector.calculateDistance(featureVector, it, numericalMetric,
                            textMetric, checked)));

        }

        Collections.sort(calculatedFeatureVectors);

        calculatedFeatureVectors.subList(numberK, calculatedFeatureVectors.size()).clear();

        List<Features> features = new ArrayList<Features>();
        List<Double> vv = new ArrayList<Double>();

        String pls = "";

        for(VectorNumberVal it : calculatedFeatureVectors){
            features.add(it.getFeatureVector());
            vv.add(it.getDistance());
            pls = pls + it.getFeatureVector().getArticle().getPlaces().get(0) + " ";

        }


        return features;

    }



    String vectorClassify(List<Article> documents) { Map<String, Integer> places = new LinkedHashMap<>();


        places.put("uk", 0);
        places.put("usa", 0);
        places.put("france", 0);
        places.put("west-germany", 0);
        places.put("canada", 0);
        places.put("japan", 0);

        for(int i=0; i<documents.size(); i++){
            int count = 0;
            String place = documents.get(i).getPlaces().get(0);
            //System.out.println(place);
            if(place.equals("usa")){
                count = places.get("usa") + 1;
                places.put("usa", count);
            }
            else if(place.equals("uk")){
                count = places.get("uk") + 1;
                places.put("uk", count);
            }
            else if(place.equals("france")){
                count = places.get("france") + 1;
                places.put("france", count);
            }
            else if(place.equals("west-germany")){
                count = places.get("west-germany") + 1;
                places.put("west-germany", count);
            }
            else if(place.equals("canada")){
                count = places.get("canada") + 1;
                places.put("canada", count);
            }
            else if(place.equals("japan")){
                count = places.get("japan") + 1;
                places.put("japan", count);
            }

        }

        Map.Entry<String, Integer> maksimum  = places.entrySet().iterator().next();

        for(Map.Entry<String, Integer> it : places.entrySet()){
            if(it.getValue() > maksimum.getValue()){
                maksimum = it;
            }
        }

        return maksimum.getKey();


    }



    public String Classification(Features featureVector, List<Features> trainingVectors, int numberK, String numericalMetric, String textMetric, Map<String, Boolean> checked) {

        List<Features> selectedVectors = new ArrayList<Features>();
        selectedVectors = calculate(featureVector, trainingVectors, numberK, numericalMetric, textMetric, checked);

        List<Article> articles = new ArrayList<Article>();

        for(Features it : selectedVectors){
            articles.add(it.getArticle());
        }

        String classifiedPlace = vectorClassify(articles);
        return classifiedPlace;


    }









}
