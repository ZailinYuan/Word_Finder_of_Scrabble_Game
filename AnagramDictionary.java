// Name: Zailin Yuan
// USC NetID: zailinyu 7247888150
// CS 455 PA4 
// Fall 2017

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 * 
 * Only depends on the dictionary that loaded.
 */

public class AnagramDictionary
{
   private Map<String,Set<String>> anagramDictionary;  // the anagram 
   // version of dictionary

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException 
   {
      // initialize the invariant variable, load the dictionary
      anagramDictionary = new TreeMap<>();
      File dictFile = new File(fileName);
      Scanner readWords = new Scanner(dictFile);
      
      //scan and sort each word in the word list in the dictionary
      //in alphabetic order.
      while(readWords.hasNext())
      {
         // transform each word into char array
         String dictWord = readWords.next();
         char[] word = dictWord.toCharArray();
         Arrays.sort(word);
         
         //sort word
         String sortedWord = "";
         for(char i: word)
         {
            sortedWord = sortedWord + i;
         }
         
         //check if the word is the anagram of an existing word in the
         //map. If it is, add the word to the set of the anagram words,
         //otherwise create new list in the map.
         if(anagramDictionary.containsKey(sortedWord))
         {
            anagramDictionary.get(sortedWord).add(dictWord);
         }
         else
         {
            Set<String> wordSet = new HashSet<>();
            wordSet.add(dictWord);
            anagramDictionary.put(sortedWord, wordSet);
         }
      }
      
      readWords.close();
   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) 
   {
      // if the dictionary contains corresponding words, return the words.
      if(anagramDictionary.containsKey(s))
      {
          return new ArrayList<String>(anagramDictionary.get(s)); 
      }
      
      // if the dictionary contains NO corresponding word, return void ArrayList
      else
      {
         return new ArrayList<String>();
      }
   }
   
}
