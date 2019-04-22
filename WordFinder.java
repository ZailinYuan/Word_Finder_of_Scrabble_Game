// Name: Zailin Yuan
// USC NetID: zailinyu 7247888150
// CS 455 PA4 
// Fall 2017

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/**
 * WordFinder class
 * 
 * Program to find the anagram of a input word and its subset words and 
 * compute the score for each anagram word according to an dictionary and
 * a score table. 
 * 
 * @author User
 *
 */
public class WordFinder
{
   /**
    * The main method choose which dictionary to load according to the command line
    * (if no command line, sowpods.txt in the same directory is expected to be default)
    * and invoke the GUI.
    * 
    * Depends on Mapper class and anagramDictionary class.
    * 
    * @param args the file name specified in the command line.
    * @throws FileNotFoundException
    */
   public static void main(String[] args) throws FileNotFoundException
   {
      String fileName = "";
      
      if(args.length < 1)
      {
         console("sowpods.txt");
      }
      else
      {
         fileName = args[0];
         console(fileName);         
      }
   }
   
   /**
    * GUI of the program. 
    * Allow user to enter rack to find the corresponding words and scores.
    * Continue for user to input racks until "." is input to quit.
    * 
    * @param fileName the dictionary to be loaded.
    * @throws FileNotFoundException
    */
   private static void console(String fileName) throws FileNotFoundException
   {
      // specify how to quit the program
      System.out.println("Type . to quit.");
      
      // load dictionary
      AnagramDictionary anaDict = new AnagramDictionary(fileName);
      
      // get rack from user from keyboard
      Scanner keyBoardInput = new Scanner(System.in);
      
      // search starts in loop until "." is entered.
      for(;;)
      {
         System.out.print("Rack? ");
         
         // input rack from keyboard
         String keyBoardInputStr = keyBoardInput.next();
         
         // type "." to quit
         if(keyBoardInputStr.equals("."))
         {
            break;
         }
         
         // sort the rack
         String sortedRack = sortInputWord(keyBoardInputStr);
      
         // get result
         Mapper getResults = new Mapper();
         getResults.getAllWordsOf(sortedRack, anaDict);
      
         // IO: show results scratch
         System.out.println("We can make " + getResults.getNumWords() + 
         " words from \"" + sortedRack + "\"");
         System.out.println("All of the words with their scores (sorted by score):");
      
         // IO: show scores and words
         Map<String, Integer> results = getResults.getMap();
         LinkedList<Map.Entry<String, Integer>> list = sortResults(results);
         for(Map.Entry<String, Integer> entry: list)
         {
            System.out.println(entry.getValue() + ": " + entry.getKey());
         }
      }
      keyBoardInput.close();
   }
   
   /**
    * The method to sort the input rack in alphabetic order.
    * 
    * @param word the rack user input.
    * @return the sorted rack.
    */
   private static String sortInputWord(String word)
   {
      // transform the rack into array.
      char[] sortBoard = word.toCharArray();
      Arrays.sort(sortBoard);
      
      // get the sorted string of sorted rack.
      String sortedWword = "";
      for(char i: sortBoard)
      {
         sortedWword = sortedWword + i;
      }
      
      return sortedWword;
   }
   
   /**
    * The method to sort the output results so that the result listed decreasingy according 
    * to the score and for same scored words listed in order of alphabetic order. 
    * 
    * @param results the words and scores got from the rack input.
    * @return the sorted results.
    */
   private static LinkedList<Map.Entry<String, Integer>> sortResults(Map<String, Integer> results)
   {
      // sort the results according to score and alphabetic order.
      LinkedList<Map.Entry<String, Integer>> list = 
            new LinkedList<Map.Entry<String, Integer>>(results.entrySet());
      
      Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() 
            {
               public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
               {
                  return (o2.getValue()).compareTo( o1.getValue());
               }
            });
      
      return list;
   }
   
}
