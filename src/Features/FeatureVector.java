package Features;


import Data.Article;
import Measures.Levensthein;
import Measures.NGramMeassure;
import Metrics.Metric;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static Features.Features.normalizeVector;

public class FeatureVector {


    private Article document;

    public FeatureVector(Article document){
        this.document = document;
    }

    public Article getDocument() {
        return document;
    }


    public static Double calculateDistance(Features c1, Features c2, String metric, String measure, Map<String, Boolean> checked){

        List<Double> numA = new ArrayList<>();
        List<Double> numB = new ArrayList<>();

        Random rand = new Random(25);

        if(checked.get("wordsCount")) {
              numA.add((double) c1.getWordsCount());
              numB.add((double) c2.getWordsCount());
        }

        if(checked.get("wordsCountSentences")) {

            numA.add(c1.getWordsCountAtSentence());
            numB.add(c2.getWordsCountAtSentence());
        }


        if(checked.get("avgBigLetter")){
             numA.add(c1.getAvgBigLetter());
             numB.add(c2.getAvgBigLetter());
        }

        if(checked.get("countSentences30")){

             numA.add(c1.getCountSentences30());
             numB.add(c2.getCountSentences30());

        }

        if(checked.get("interpunctions")){
              numA.add(c1.getInterpunctions());
              numB.add(c2.getInterpunctions());
        }

        if(measure.equals("Levenshtein")) {
            if (checked.get("capital")) {
                numA.add(0.0);
                numB.add(Levensthein.Levenshtein(c1.getCapital(), c2.getCapital()));

            }

            if (checked.get("currency")) {
                numA.add(0.0);
                numB.add(Levensthein.Levenshtein(c1.getCurrency(), c2.getCurrency()));

            }

            if (checked.get("topics")) {
                String Topics1 = c1.getTopics().toString();
                String Topics2 = c2.getTopics().toString();
                numA.add(0.0);
                numB.add(Levensthein.Levenshtein(Topics1, Topics2));
            }

            if (checked.get("authors")) {
                String A1 = c1.getAuthor().toString();
                String A2 = c2.getAuthor().toString();
                numA.add(0.0);
                numB.add(Levensthein.Levenshtein(A1, A2));
            }
        } else{
            if (checked.get("capital")) {
                 numA.add(0.0);
                 numB.add(1.0 - NGramMeassure.calculateTrigram(c1.getCapital(), c2.getCapital()));

            }

            if (checked.get("currency")) {
                 numA.add(0.0);
                 numB.add(1.0 - NGramMeassure.calculateTrigram(c1.getCurrency(), c2.getCurrency()));
            }

            if (checked.get("topics")) {
                String Topics1 = c1.getTopics().toString();
                String Topics2 = c2.getTopics().toString();

                numA.add(0.0);
                numB.add(1.0 - NGramMeassure.calculateTrigram(Topics1, Topics2));


            }

            if (checked.get("authors")) {

                String A1 = c1.getAuthor().toString();
                String A2 = c2.getAuthor().toString();

                numA.add(0.0);
                numB.add(1.0 - NGramMeassure.calculateTrigram(A1, A2));
            }
        }


        double normalizeNumA = normalizeVector(numA) > 0.0 ? normalizeVector(numA) : 1.0;
        double normalizeNumB = normalizeVector(numB) > 0.0 ? normalizeVector(numB) : 1.0;


        if(metric == "Euculidean"){
            for(int i =0; i<numA.size(); i++){
                numA.set(i, numA.get(i)/normalizeNumA);
                numB.set(i, numB.get(i)/normalizeNumB);
            }

            //System.out.println(Metric.Euclidean(numA, numB));
            return Metric.Euclidean(numA, numB);
        }
        else if(metric == "Chebyschev"){
            return Metric.Chebyshev(numA, numB);
        }
        else if(metric == "Manhattan"){
            for(int i =0; i<numA.size(); i++){
                numA.set(i, numA.get(i)/normalizeNumA);
                numB.set(i, numB.get(i)/normalizeNumB);
            }

            return Metric.Manhattan(numA, numB);
        }
        else{
            return 0.0;
        }

    }


}
