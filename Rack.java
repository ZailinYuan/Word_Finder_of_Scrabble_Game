// Name: Zailin Yuan
// USC NetID: zailinyu 7247888150
// CS 455 PA4 
// Fall 2017


import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The rack class
 * 
 * Find and return all the subsets of words that the input rack contains.
 * @author User
 *
 */
public class Rack 
{
   String rack;
   
   public Rack(String rack)
   {
      this.rack = rack;
   }
   
   /**
    * Find all the subsets of the rack and return results.
    * 
    * @return all the subset of the rack
    */
   public ArrayList<String> getSubset()
   {
      char[] wordHandle = rack.toCharArray();
      
      
      // create unique set of the rack
      Map<Character,Integer> wordAndMult = new TreeMap<Character,Integer>();
      int uniquenessCounter = 0;  // count the uniqueness of each letter in the rack
      
      for(int i=0; i<wordHandle.length; i++)
      {
         if(wordAndMult.containsKey(wordHandle[i]))
         {
            uniquenessCounter++;
            wordAndMult.put(wordHandle[i],uniquenessCounter);
         }
         else
         {
            uniquenessCounter = 1;
            wordAndMult.put(wordHandle[i], uniquenessCounter);
         }
      }
      
      // create all need string, array for finding the subsets.
      String uniqueStr = "";
      int[] mult = new int[wordAndMult.size()];
      int i = 0; // index of mult
      for(char keys: wordAndMult.keySet())
      {
         uniqueStr = uniqueStr + keys;
         mult[i] = wordAndMult.get(keys);
         i++;
      }
      
      // find the subsets
      int alpha = 0; // initial index of recursion
      return allSubsets(uniqueStr,mult,alpha);
   }
   

   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    *      unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length()
    *      0 <= k <= unique.length()
    * @param unique a string of unique letters
    * @param mult the multiplicity of each letter from unique.  
    * @param k the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}
