
package scrublegame;

import java.util.Scanner;


public class ScrubleGame 
{
    private static String PlayerOne;
    private static String PlayerTwo;
// declaring a variable to hold a played word
   private static String playedWord="";  
   //declaring avariable to hold alphabets left
   private static String alphabetList="abcdefghijklmnopqrstuvwxyz";
   //declaring a variable that will store used letters
   private static String usedLetters="";
   private static boolean StartGame;
   
   private static String currentPlyer;
   
   private static int playerNumber=1;
   
   //declaring a variable to update alphates
   private static boolean updateAlphabets=true;
   
   //variables for player Score
   private static int p1Score=0;
   private static int p2Score=0;
   
   
    public static void main(String[] args) 
    {
        StartGame = true; 
        System.out.print("Welcome to my Scruble Game"+
                          "\n enter (y) to play the Game or any key to exit : ");
        if (new Scanner(System.in).next().equalsIgnoreCase("y"))
        {
        //prompt for player one name 
         System.out.print("\n Enter player one Name : "); 
         PlayerOne=new Scanner(System.in).next();
         
         //prompt for player two name 
         System.out.print("\n Enter player two Name : "); 
         PlayerTwo=new Scanner(System.in).next();
         
         //calling a method to start a game 
         startGame();
        }
        else
        {
         //exiting the program
        System.exit(0);
        }
    }

    private static void startGame() 
    {
      while(!playedWord.equals("###"))
      {
          //to determine current player
               currentPlayer();
               
       System.out.print("\nRemaining Alphabets : "+updateAlphabets());
       System.out.print("\n"+currentPlyer+" please enter a word : ");
       playedWord=new Scanner(System.in).next();
      
       //calling a method to validate plyed word
       validatingPlayedWord();
     
      }
      //calling amethod to display scores
      playerScore();
        
    }

    private static String updateAlphabets() 
    {
        if(updateAlphabets==true)
        {
        //for loop to loop through the alphabets list
        for(int i=0;i<alphabetList.toCharArray().length;i++)
        {
       // get used letters
            if (playedWord.contains((alphabetList.toCharArray()[i]+"")))
            {
                 usedLetters+=(alphabetList.toCharArray()[i]+"");
             alphabetList= alphabetList.replace((alphabetList.toCharArray()[i]+""), "_");
            }
        }
        
        }
        else
        {
         System.out.print("\n sorry the played word is invalid or it cointains used words");  
         updateAlphabets=false;
        }
      return alphabetList;
    }
    
// this method determines the current player
    private static void currentPlayer() 
    {
      if (playerNumber==1) 
      {
      currentPlyer= PlayerOne;
      playerNumber+=1;
      }
      else
      {
      currentPlyer= PlayerTwo;
      playerNumber-=1;
      } 
    }
//this method removes vowels from plyed word
    private static void trimVowels()
    {
       String vowels="aeiou";
       for(int i=0;i<playedWord.toCharArray().length;i++)
    {
       if(vowels.contains((playedWord.toCharArray()[i]+"")))
       {
       playedWord=playedWord.replace((playedWord.toCharArray()[i]+""), " ");
               
       }
       
    }

    }

    private static void validatingPlayedWord() 
    {
      System.out.print("\n enter (y)if the players agree on the played word or any key to disagree :"); 
      if(new Scanner(System.in).next().equalsIgnoreCase("y"))
              {
                   usedLetterSearch();
//              updateAlphabets=true;

         //calling a method
        trimVowels();
       //creating a method to check for played 
       usedLetterSearch();
       
       //calling a method to determine player score
       determinePlayerScore();
              }
              else
              {
              playedWord="";
              }
    }

    private static void usedLetterSearch() 
    {
            //loop to loo through our used letters
        for(int i=0;i<usedLetters.toCharArray().length;i++)
        {
        if(playedWord.contains((usedLetters.toCharArray()[i]+"")))
        {
        updateAlphabets=false;
        }
        
        }
    }

    private static void playerScore() 
    {
        System.out.print(PlayerOne+"Score"+p1Score+
                        "\n"+PlayerTwo+"Score"+p2Score);
    }
             //determining player score
    private static void determinePlayerScore() 
    {
               // checking the name of the current player
        if(currentPlyer.equals(PlayerOne)&&!playedWord.equals("###"))
        {
        p1Score+=1;
                
        
        }
         if(currentPlyer.equals(PlayerTwo)&&!playedWord.equals("###"))
        {
        p2Score+=1;
                
        
        }
    }
    
}
