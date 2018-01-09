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


public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private static final String TAG = "a";
    private ArrayList<String> wordList = new ArrayList<String>();;
    private HashSet<String> wordSet = new HashSet<String>();
    private HashMap lettersToWord = new HashMap();


    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            List<String> list = new ArrayList<String>();
            if (lettersToWord.containsKey(sortLetters(word))){
                list.add(word);
                lettersToWord.put(sortLetters(word),list);

            } else {
                lettersToWord.put(sortLetters(word),list);
            }
            

            //Log.d(TAG,"got word:"+word);
        }

       //getAnagrams("post");

        lettersToWord.put()

    }

    public boolean isGoodWord(String word, String base) {
        return true;
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
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i<wordList.size();i++){
            if (word.length()==wordList.get(i).length() && word.equals(sortLetters(wordList.get(i)))){
                String anagram = word;
                result.add(anagram);
            }
        }

        return result;
    }


    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
