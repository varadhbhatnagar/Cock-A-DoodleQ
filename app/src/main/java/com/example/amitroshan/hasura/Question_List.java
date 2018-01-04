package com.example.amitroshan.hasura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * Created by amitroshan on 30/07/17.
 */

public class Question_List {

    static String randomValue;
    static String randomKey;

    public static void returnQuestions(){

        Map<String,String> list=new HashMap<>();

        //String questions[] = new String[13];
        //String answers[] = new String[13];

        list.put("Capital of India ?","New Delhi");
        list.put("Number of letters in the English Alphabet ( in words) ?","26");
        list.put("Full form of USA ?","United States of America");
        list.put("Highest mountain in the world ?","Mount Everest");
        list.put("Current president of India ?","Ram Nath Kovind");
        list.put("Current president of USA ?","Donald Trump");
        list.put("What is the the only Even prime number ?","2");
        list.put("Which country has the highest population ?","China");
        list.put("What is the full form of CPU ?","Central Processing Unit");
        list.put("What is the full name of Sachin Tendulkar ?","Sachin Ramesh Tendulkar");
        list.put("What is the deepest point on Earth ?","Marina Trench");
        list.put("Which city is known as the Oxford of the East ?","Pune");
        list.put("25 + 34 / 2 * 5 ?","110");

        Random generator = new Random();
        Object[] values = list.values().toArray();
        Object[] keys = list.keySet().toArray();
        int ran = generator.nextInt(values.length);

        randomValue = (String)values[ran];
        randomKey = (String)keys[ran];
    }
}
