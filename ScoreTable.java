// Name: Zailin Yuan
// USC NetID: zailinyu 7247888150
// CS 455 PA4 
// Fall 2017

/**
 * The ScoreTable class
 * 
 * Contains the score rules, score the anagram word and return the score.
 * 
 * @author User
 *
 */
public class ScoreTable
{
   private static final int AEIOULNSTR_VALUE = 1;
   private static final int DG_VALUE = 2;
   private static final int BCMP_VALUE = 3;
   private static final int FHYVW_VALUE = 4;
   private static final int K_VALUE = 5;
   private static final int JX_VALUE = 8;
   private static final int QZ_VALUE = 10;
   // These are scores for each letter the word contains
   
   /**
    * Score the word and return the score.
    * 
    * @param str the word to be scored
    * @return the score of the word
    */
   public int countScore(String str)
   {
      // put all letters to upper case since both upper and lower case for each letter is
      // the same
      char[] string = str.toUpperCase().toCharArray();
      
      int Score = 0;
      
      // add up the score of each letter that the word contains
      for(char i: string)
      {
         switch(i)
         {
         case 'A': case 'E': case 'I': case 'O': case 'U': case 'L': case 'N': case 'S':
         case 'T': case 'R':    Score = Score + AEIOULNSTR_VALUE; break;
         
         case 'D': case 'G':    Score = Score + DG_VALUE; break;
         
         case 'B': case 'C': case 'M': case 'P':    Score = Score + BCMP_VALUE; break;
         
         case 'F': case 'H': case 'Y': case 'V': case 'W':    Score = Score + FHYVW_VALUE; break;
         
         case 'K':    Score = Score + K_VALUE; break;
         
         case 'J': case 'X':    Score = Score + JX_VALUE; break;
         
         case 'Q': case 'Z':    Score = Score + QZ_VALUE; break;
         }
      }
      
      return Score;
   }
   
}
