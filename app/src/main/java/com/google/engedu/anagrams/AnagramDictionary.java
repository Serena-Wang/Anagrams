/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Collections;



public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 5;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private static final String TAG = "a";
    private ArrayList<String> wordList = new ArrayList<String>();;
    private HashSet<String> wordSet = new HashSet<String>();
    private HashMap<String,List> lettersToWord = new HashMap<String,List>();
    private HashMap<Integer,List> sizeToWords = new HashMap<Integer, List>();


    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
           // List<String> list = new ArrayList<String>();
            if (lettersToWord.containsKey(sortLetters(word))){
                lettersToWord.get(sortLetters(word)).add(word);
                //list.add(word);
                //lettersToWord.put(sortLetters(word),list);

            } else {
                List<String> list = new ArrayList<String>();
                list.add(word);
                lettersToWord.put(sortLetters(word),list);
            }



            if (sizeToWords.containsKey(word.length())){
                sizeToWords.get(word.length()).add(word);
                //listSize.add(word);
                //sizeToWords.put(word.length(),listSize);

            } else {
                List<String> list = new ArrayList<String>();
                list.add(word);
                sizeToWords.put(word.length(),list);
                //sizeToWords.put(word.length(),listSize);
            }

            //Log.d(TAG,"got word:"+word);
        }

       //getAnagrams("post");

    }

    public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(word) && !word.contains(base)) {
            return  true;
        }
        return false;
    }

    public String sortLetters(String input){
        char[] temp = input.toCharArray();
        Arrays.sort(temp);
        String output = new String(temp);
        return output;

    }

    public List<String> getAnagrams(String targetWord) {
        String word = sortLetters(targetWord);
        Log.d(TAG, "sort letters:"+word);
        word= word.toLowerCase();
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i<wordList.size();i++){
            if (word.length()==wordList.get(i).length() && word.equals(sortLetters(wordList.get(i)))){
                String anagram = wordList.get(i);
                result.add(anagram);
            }
        }
        Log.d(TAG, "anagrams:"+result.toString());
        return result;
    }


    public List<String> getAnagramsWithOneMoreLetter(String word) {
        List<String> result = new ArrayList<String>();
        if (word!=null) {
            word = word.toLowerCase();
            for (char c = 'a'; c <= 'z'; c++) {
                //Log.d(TAG,"in a to z loop");
                String newWord = word + c;
                String sorted = sortLetters(newWord);
                if (lettersToWord.containsKey(sorted)) {
                    List<String> target = lettersToWord.get(sorted);
                    result.addAll(target);
                }
            }
            Log.d(TAG, "1 more angram"+result.toString());
        }
        return result;

    }

    public String pickGoodStarterWord() {
        List<String> domain = sizeToWords.get(DEFAULT_WORD_LENGTH);
        Log.d(TAG, "domain:"+domain.toString());
        int startingPoint = (int)((Math.random())*domain.size());

        String target=null;

       // Collections.rotate(wordList, startingPoint);
        for (int i=startingPoint; i<domain.size();i++){
            target=domain.get(startingPoint);
            Log.d(TAG, "in starting point loop, starting point is:" + startingPoint+"size is:"+domain.size());
            if (getAnagramsWithOneMoreLetter(domain.get(i)).size()>MIN_NUM_ANAGRAMS){
                target=domain.get(i);
                return target;
            }
        }

        /*for (int j=startingPoint;j<=wordList.size();j++){
            if (getAnagramsWithOneMoreLetter(wordList.get(j)).size()<minAnagrams){
                minAnagrams=getAnagramsWithOneMoreLetter(wordList.get(j)).size();
                target=wordList.get(j);
            }
        }
        for (int i=0;i<=startingPoint;i++){
            if (getAnagramsWithOneMoreLetter(wordList.get(i)).size()<minAnagrams){
                minAnagrams=getAnagramsWithOneMoreLetter(wordList.get(i)).size();
                target=wordList.get(i);
            }
        }*/
        Log.d(TAG, "target:"+target);
        return null;
    }
}
