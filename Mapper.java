// Name: Zailin Yuan
// USC NetID: zailinyu 7247888150
// CS 455 PA4 
// Fall 2017


import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Mapper class
 * 
 * Treat the sorted rack from WordFinder according to the  
 * anagram dictionary from anagramDictionary. Getting the subsets of the
 * rack and find the words and scores. Create a map to store the results.
 * Depending on anagramDictionary class, Rack class and scoreTable class.
 * 
 * @author User
 *
 */
public class Mapper
{
   private Map<String, Integer> results; // the map that store the results
   private ArrayList<String> allWords;  // the list that store all the 
                                         // anagram words
   
   // constructor to initialize invariant variables
   public Mapper()
   {
      results = new TreeMap<>();
      allWords = new ArrayList<String>();
   }
   
   /**
    * The method is to find the subsets of the rack, get all the words that 
    * valid, get scores for every word and create the result map.
    * 
    * @param sortedRack the sorted rack from GUI
    * @param anaDict the anagram dictionary from GUI
    */
   public void getAllWordsOf(String sortedRack, AnagramDictionary anaDict) 
   {
      // get the subset of the rack
      Rack rack = new Rack(sortedRack);
      ArrayList<String> rackComb = rack.getSubset();
      
      ListIterator<String> iter = rackComb.listIterator();
      
      // get all valid words for the rack
      while(iter.hasNext())
      {
         allWords.addAll(anaDict.getAnagramsOf(iter.next()));
      }
      
      // create the result map
      drawMap();
   }
   
   /**
    * Count the number of anagram words of the rack. 
    * 
    * @return the number of anagram words of the rack. 
    */
   public int getNumWords()
   {
      return allWords.size();
   }
   
   /**
    * The results map
    * 
    * @return the results map
    */
   public Map<String, Integer> getMap()
   {
      return results;
   }
   
   /**
    * The method is to create the result map according to all
    * the anagram words and scores.  
    * 
    */
   private void drawMap()
   {
      // count the score for every anagram word
      ScoreTable score = new ScoreTable();
      
      // create the map
      ListIterator<String> iter = allWords.listIterator();
      while(iter.hasNext())
      {
         String temp = iter.next();
         results.put(temp, score.countScore(temp));
      }
   }
   
}
