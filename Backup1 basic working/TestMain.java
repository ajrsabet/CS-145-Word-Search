
/*
* Programmer: Adam Sabet
* Class: CS145 with Jaramiah Ramsey
* Date Due: 5/23/2023
* Assignment 1: Word Search Generator

* This program will do the following: 
    * 
* For extra credit I: 
    * 
*/

import java.util.ArrayList;
import java.util.Scanner; // program uses the Scanner for the library

public class TestMain {
    public static void main(String[] args) {
        WordSearchManager wordSearch = new WordSearchManager();
        printIntro();

        //ArrayList<String> userWords = getWords();
        ArrayList<String> userWords = new ArrayList<>();
        userWords.add("sepideh");
        userWords.add("adam");
        userWords.add("nahid");
        userWords.add("toby");
        userWords.add("soha");
        userWords.add("brad");
        userWords.add("hannah");
        userWords.add("allen");
        userWords.add("benjamin");
        userWords.add("heidi");

       wordSearch.generate(20, userWords);
        wordSearch.showSolution();
    }

    /*------------------------------------------------------------------------- */
    /*
     * This method prints out the intro to the program. It gives the user an idea of
     * what the program does and how to use it.
     */
    public static void printIntro() {
        System.out.println("How big will the word search be?");
    }

    
    /*------------------------------------------------------------------------- */
    /* Get the words from the user to create the puzzle */
    public static ArrayList<String> getWords() {
        Scanner input = new Scanner(System.in);
        ArrayList<String> userWords = new ArrayList<String>();
        boolean moreWords = true;

        while(moreWords == true) {
            System.out.println("What words would you like to add?");
            //test string
            String nextWord = input.nextLine().toUpperCase();
            userWords.add(nextWord);
            System.out.println("Would you like to add another word?");
            char userChoice = input.nextLine().toLowerCase().charAt(0);
            if (userChoice == 'n') {
                moreWords = false;
            }
        }

        System.out.println(userWords.toString());
        input.close();
        return userWords;
    }
    /*------------------------------------------------------------------------- */
    /* test that user input is int */
    public static int inputTestInt(int rangeLow, int rangeHigh, Scanner input) {
        boolean test = false;
        int userInput = -9999;
        do {
            try {
                userInput = input.nextInt();
                if (userInput >= rangeLow && userInput <= rangeHigh) {
                    test = true;
                }

            } catch (Exception e) {
                System.out.println("Error! try again \n" + input.nextLine() + "\n is not valid");
                test = false;
            }
        } while (!test);
        return userInput;
    }
}
