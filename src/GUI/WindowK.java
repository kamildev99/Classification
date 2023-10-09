package GUI;


import Data.Article;
import Data.Classified;
import Data.Document;
import Data.ReaderDocument;
import Features.Features;
import MethodKNN.MethodKNN;
import QualityMeasures.QualityMeasures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class WindowK {

    public JPanel panel1;
    private JTextField kParameterTextField;
    private JRadioButton chebyshevRadioBtn;
    private JRadioButton euculidesRadioBtn;
    private JRadioButton manhattanRadioBtn;
    private JRadioButton nGramRadioBtn;
    private JCheckBox wordNumberCheckBox;
    private JCheckBox wordsSentenceCheckBox;
    private JCheckBox capitalCheckBox;
    private JCheckBox currencyCheckBox;
    private JCheckBox avgBigLetterCheckBox;
    private JCheckBox avgSignsCheckBox;
    private JCheckBox numberSentAtFragCheckBox;
    private JCheckBox authorCheckBox;
    private JCheckBox topicsCheckBox;
    private JButton classificationBtn;
    private JLabel resultJLabel;
    private JLabel metricJLabel;
    private JLabel textMeasureJLabel;
    private JLabel kParamaeterJLabel;
    private JLabel testSetJLabel;
    private JLabel learnSetJLabel;
    private JLabel featuresJLabel;
    private JTextField testSetTextField;
    private JRadioButton levenshteinRadioBtn;
    private JLabel processingLabel;
    private JTextField learnSetTextField;

    private ButtonGroup groupMetric = new ButtonGroup();
    private ButtonGroup groupTextMetric = new ButtonGroup();
    //private CheckboxGroup groupFeatures = new CheckboxGroup();

    ResultWindow result;


    public WindowK(){
        groupMetric.add(euculidesRadioBtn);
        groupMetric.add(chebyshevRadioBtn);
        groupMetric.add(manhattanRadioBtn);
        groupTextMetric.add(nGramRadioBtn);
        groupTextMetric.add(levenshteinRadioBtn);



        metricJLabel.setFont(metricJLabel.getFont().deriveFont(16.0f));
        textMeasureJLabel.setFont(textMeasureJLabel.getFont().deriveFont(16.0f));
        featuresJLabel.setFont(featuresJLabel.getFont().deriveFont(16.0f));
        kParamaeterJLabel.setFont(kParamaeterJLabel.getFont().deriveFont(16.0f));
        //learnSetJLabel.setFont(resultJLabel.getFont().deriveFont(16.0f));
        testSetJLabel.setFont(testSetJLabel.getFont().deriveFont(16.0f));


        kParameterTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value =  kParameterTextField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    kParameterTextField.setEditable(true);
                } else {
                    kParameterTextField.setEditable(false);

                }
            }
        });




        testSetTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value =   testSetTextField.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    testSetTextField.setEditable(true);
                } else {
                    testSetTextField.setEditable(false);

                }
            }
        });


        classificationBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

                boolean checkedCheckBox = false;

                checkedFeatures.put("wordsCount", wordNumberCheckBox.isSelected());
                checkedFeatures.put("wordsCountSentences", wordsSentenceCheckBox.isSelected());
                checkedFeatures.put("avgBigLetter", avgBigLetterCheckBox.isSelected());
                checkedFeatures.put("interpunctions", avgSignsCheckBox.isSelected());
                checkedFeatures.put("countSentences30", numberSentAtFragCheckBox.isSelected());
                checkedFeatures.put("capital", capitalCheckBox.isSelected());
                checkedFeatures.put("currency", currencyCheckBox.isSelected());
                checkedFeatures.put("topics", topicsCheckBox.isSelected());
                //checkedFeatures.put("firstSentence", firstSentenceCheckBox.isSelected());
                checkedFeatures.put("authors", authorCheckBox.isSelected());

                for(Map.Entry<String, Boolean> it : checkedFeatures.entrySet()){
                    if(it.getValue() == true){
                        checkedCheckBox = it.getValue();
                        break;
                    }

                }


                if ((groupTextMetric.getSelection()==null)){

                    JOptionPane.showMessageDialog(panel1, "Brak wybranej miary podobieństwa.", "Warning", JOptionPane.WARNING_MESSAGE);

                }
                else if((groupMetric.getSelection()==null)){
                    JOptionPane.showMessageDialog(panel1, "Brak wybranej metryki.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(!checkedCheckBox){
                    JOptionPane.showMessageDialog(panel1, "Brak wybranej cechy.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else{

                    while (processingLabel.getText().equals("Przetwarzanie...")) {
                        processingLabel.setText("Przetwarzanie...");
                    }

                    

                    /*System.out.println(wordNumberCheckBox.isSelected());
                    System.out.println(wordsSentenceCheckBox.isSelected());
                    System.out.println(avgBigLetterCheckBox.isSelected());
                    System.out.println(avgSignsCheckBox.isSelected());
                    System.out.println(numberSentAtFragCheckBox.isSelected());
                    System.out.println(capitalCheckBox.isSelected());
                    System.out.println(currencyCheckBox.isSelected());
                    System.out.println(topicsCheckBox.isSelected());
                    System.out.println(authorCheckBox.isSelected());*/

                    Knumber = Integer.parseInt(kParameterTextField.getText());
                    ratioTest = Integer.parseInt(testSetTextField.getText());


                    if (euculidesRadioBtn.isSelected()) {
                        metrics = "Euculidean";
                    } else if (chebyshevRadioBtn.isSelected()) {
                        metrics = "Chebyschev";
                    } else {
                        metrics = "Manhattan";
                    }


                    if (levenshteinRadioBtn.isSelected()) {
                        measures = "Levenshtein";
                    } else {
                        measures = "Trigram";
                    }

                    try {
                        result.frame.dispose();

                    } catch (NullPointerException e) {

                    }

                    Scanner s = null;
                    try {
                        s = new Scanner(new File(String.valueOf(fileStopterms)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    while (s.hasNext()) {
                        stopterms.add(s.next());
                    }
                    s.close();


                    System.out.println(stopterms.toString());

                    URL url = getClass().getResource("/documents/");
                    final File folder = new File(url.getPath());
                    Document doc = new Document();

                    try {
                        doc.listFilesForFolder(folder);
                    } catch (java.io.IOException IOException) {
                        //System.out.println(url.getPath().toString());
                        System.out.println(IOException);
                    }

                    for (String text : doc.articles) {
                        documents.add(new Article(ReaderDocument.GetDate(text), ReaderDocument.GetTopics(text),
                                ReaderDocument.GetPlaces(text), ReaderDocument.GetPeople(text), ReaderDocument.GetTitle(text),
                                ReaderDocument.GetAuthors(text), ReaderDocument.GetText(text)));

                    }



                    RemoveOtherDocuments(documents, places);
                    SetsDivision(ratioTest);
                    extracionFeatures();


                    System.out.println(documents.size());

                    System.out.println(documents.size());
                    System.out.println(trainingDocuments.size());
                    System.out.println(testDocuments.size());

                    methodKNN(Knumber, metrics, measures, checkedFeatures);

                    //Statistics();
                    QualityMeasures.Statistics(classifiedDocuemnts);


                    result = new ResultWindow(classifiedDocuemnts);

                    documents.clear();
                    trainingDocuments.clear();
                    testDocuments.clear();
                    trainingVectors.clear();
                    testVectors.clear();
                    classifiedDocuemnts.clear();
                    stopterms.clear();

                    processingLabel.setText("Ukończono");

                }
            }
        });

    }



    private static java.util.List<Article> documents = new ArrayList<Article>();
    private static java.util.List<Article> trainingDocuments = new ArrayList<Article>();
    private static java.util.List<Article> testDocuments = new ArrayList<Article>();

    private static java.util.List<Features> trainingVectors = new ArrayList<Features>();
    private static java.util.List<Features> testVectors = new ArrayList<Features>();

    private static java.util.List<Classified> classifiedDocuemnts = new ArrayList<Classified>();

    private String metrics;
    private String measures;



    private static java.util.List<String> places =  Arrays.asList("west-germany, japan, france, uk, usa, canada");

    URL urlStop = getClass().getResource("/stopwords/stopwords.txt");
    File fileStopterms = new File(urlStop.getPath());



    private static MethodKNN knnAlgorithm = new MethodKNN();

    private static java.util.List<Classified> documentsClassied = new ArrayList<Classified>();

    static int Knumber = 0;
    static int ratioTest = 0;
    static Map<String, Boolean> checkedFeatures = new LinkedHashMap<>();

    private static double accuracy=0;



    public static void SetsDivision(int ratioTest) {

        Random random = new Random(45);
        final int testSetSize = documents.size() * (100 - ratioTest) / 100;
        //int testSetSize = documents.size() * (100 - ratioTest) / 100;

        testDocuments = new ArrayList<>();
        trainingDocuments = new ArrayList<>(documents);

        System.out.println("wielkosc: " + testSetSize);
        for (int i = 0; i < testSetSize; i++) {
            int number = random.nextInt(trainingDocuments.size());
            testDocuments.add(trainingDocuments.get(number));
            trainingDocuments.remove(number);
           // testDocuments.add(trainingDocuments.get(trainingDocuments.size()-1));
            //trainingDocuments.remove(trainingDocuments.size()-1);

           /* try{testDocuments.add(trainingDocuments.get(trainingDocuments.size()-1));
                trainingDocuments.remove(trainingDocuments.size()-1);
            } catch(IndexOutOfBoundsException e){

            }*/


        }

    }

    public static void RemoveOtherDocuments(java.util.List<Article> article, java.util.List<String> places) {

        List<Article> tmp = new ArrayList<Article>();

        for (Article it : article) {
            if (it.getPlaces().size() == 1) {
                for (String place1 : it.getPlaces()) {
                    for (String place2 : places) {
                        if (place2.contains(place1)) {
                            tmp.add(it);

                        }
                    }
                }
            }
        }

        documents =  tmp;
    }


    public static void extracionFeatures(){

        for(Article it : trainingDocuments){
            trainingVectors.add(new Features(it, stopterms));
        }

        for(Article it : testDocuments){
            testVectors.add(new Features(it, stopterms));
        }

        for(Features it : trainingVectors){
            it.setFeatures();
        }

        for(Features it : testVectors){
            it.setFeatures();
        }


    }


    public static void methodKNN(int Knumber, String metric, String textMeasure, Map<String, Boolean> checked){


        for(Features it : testVectors){

            String correctPlace = it.getArticle().getPlaces().get(0);


            String classifyPlace = "";

            if(knnAlgorithm.Classification(it, trainingVectors, Knumber, metric, textMeasure, checked) == null ||
                    knnAlgorithm.Classification(it, trainingVectors, Knumber, metric, textMeasure, checked).isEmpty()){
                classifyPlace = "";

            }else{
                classifyPlace = knnAlgorithm.Classification(it, trainingVectors, Knumber, metric, textMeasure, checked);

            }


            classifiedDocuemnts.add(new Classified(it.getArticle(), classifyPlace, correctPlace));
        }

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Projekt 1 - Klasyifkacja k-NN");
        WindowK windowK = new WindowK();
        frame.setContentPane(windowK.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }






    private static java.util.List<String> stopterms = new List<String>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<String> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(String s) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends String> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends String> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public String get(int index) {
            return null;
        }

        @Override
        public String set(int index, String element) {
            return null;
        }

        @Override
        public void add(int index, String element) {

        }

        @Override
        public String remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<String> listIterator() {
            return null;
        }

        @Override
        public ListIterator<String> listIterator(int index) {
            return null;
        }

        @Override
        public List<String> subList(int fromIndex, int toIndex) {
            return null;
        }
    };



}



