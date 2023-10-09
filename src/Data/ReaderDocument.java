package Data;

import java.util.ArrayList;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReaderDocument {


    public static String GetDate(String text){
        Pattern TAG_DATE = Pattern.compile("<DATE>(.+?)</DATE>", Pattern.DOTALL);
        Matcher matcher = TAG_DATE.matcher(text);

        String tmp = "";
        while (matcher.find()) {
            tmp += matcher.group(1) + " ";
            //titles.add(matcher.group(1));
        }
        return tmp;
    }

    public static List<String> GetTopics(String text){
        Pattern TAG_TOPICS = Pattern.compile("<TOPICS>(.+?)</TOPICS>", Pattern.DOTALL);
        Matcher matcher = TAG_TOPICS.matcher(text);
        Pattern TAG_D = Pattern.compile("<D>(.+?)</D>", Pattern.DOTALL);
        Matcher matcher_2 = TAG_D.matcher(text);
        List<String> tmp = new ArrayList<String>();
        while (matcher.find()) {
            //tmp.add(matcher.group(1));
            matcher_2 =  TAG_D.matcher(matcher.group(1));
            while(matcher_2.find()){

                tmp.add(matcher_2.group(1));
            }
            //titles.add(matcher.group(1));
        }
        return tmp;
    }

    public static List<String> GetPlaces(String text){
        //(.+?)<D>(.+?)</D>
        Pattern TAG_PLACES = Pattern.compile("<PLACES>(.+?)</PLACES>", Pattern.DOTALL);
        Matcher matcher = TAG_PLACES.matcher(text);
        Pattern TAG_D = Pattern.compile("<D>(.+?)</D>", Pattern.DOTALL);
        Matcher matcher_2 = TAG_D.matcher(text);

        List<String> tmp = new ArrayList<String>();
        while (matcher.find()) {
            //tmp.add(matcher.group(1));
            matcher_2 =  TAG_D.matcher(matcher.group(1));
            while(matcher_2.find()){

                tmp.add(matcher_2.group(1));
            }

            //titles.add(matcher.group(1));
        }
        return tmp;

    }

    public static List<String> GetPeople(String text){
        Pattern TAG_PEOPLE = Pattern.compile("<PEOPLE>(.+?)</PEOPLE>", Pattern.DOTALL);
        Matcher matcher = TAG_PEOPLE.matcher(text);
        List<String> tmp = new ArrayList<String>();
        while (matcher.find()) {
            tmp.add(matcher.group(1));
            //titles.add(matcher.group(1));
        }
        return tmp;
    }

    public static String GetTitle(String text){
        Pattern TAG_TITLES = Pattern.compile("<TITLE>(.+?)</TITLE>", Pattern.DOTALL);
        Matcher matcher = TAG_TITLES.matcher(text);
        String tmp = "";
        while (matcher.find()) {
            tmp+=matcher.group(1) + " ";
            //titles.add(matcher.group(1));
        }
        return tmp;
    }

    public static List<String> GetAuthors(String text){
        Pattern TAG_AUTHORS = Pattern.compile("<AUTHOR>(.+?)</AUTHOR>", Pattern.DOTALL);
        Matcher matcher = TAG_AUTHORS.matcher(text);
        List<String> tmp = new ArrayList<String>();
        while (matcher.find()) {
            tmp.add(matcher.group(1));
            //titles.add(matcher.group(1));
        }
        return tmp;
    }

    public static String GetText(String text){
        Pattern TAG_TEXT = Pattern.compile("<BODY>(.+?)</BODY>", Pattern.DOTALL);
        Matcher matcher = TAG_TEXT.matcher(text);
        String tmp = "";
        while (matcher.find()) {
            tmp+=matcher.group(1) + " ";
            //titles.add(matcher.group(1));
        }
        return tmp;
    }

    public static String GetDateLine(String text){
        Pattern TAG_DATELINE = Pattern.compile("<DATELINE>(.+?)</DATELINE>", Pattern.DOTALL);
        Matcher matcher = TAG_DATELINE.matcher(text);
        String tmp = "";
        while (matcher.find()) {
            tmp+=matcher.group(1) + " ";
            //titles.add(matcher.group(1));
        }
        return tmp;
    }




}
