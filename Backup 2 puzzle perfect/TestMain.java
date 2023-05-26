
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
        Scanner input = new Scanner(System.in);
        WordSearchManager wordSearch = new WordSearchManager();
        printIntro();
        boolean run = true;

        while (run) {
            char menuOption = menu(input);
            ArrayList<String> userWords = new ArrayList<>();

            switch (menuOption) {
                case 'C': // Make custom word search

                    userWords = getWords();
                    wordSearch.generate(20, userWords);
                    break;
                case 'L': // Pick a list of words to generate a word search

                    break;
                case 'S': // Pick a list of words to generate a word search
                
                    wordSearch.showSolution();
                    break;
                case 'Q': // Quit
                    System.out.println("Thanks for using the best word search puzzle generator!");
                    run = false;
                    break;
            }
            // Display puzzle
            // List puzzles

        }
        input.close();
    }

    /*------------------------------------------------------------------------- */
    /*
     * This method prints out the intro to the program. It gives the user an idea of
     * what the program does and how to use it.
     */
    public static void printIntro() {
        System.out.println("Welcome to the best word search puzzle generator in the world!");
        System.out.println("You can use your own list of words or pick from a pre-made ");
        System.out.println("list to generate a new word search puzzle!");
    }

    public static char menu(Scanner input) {
        char menuChoice = ' ';
        boolean valid = false;
        while (!valid) {

            System.out.println("What would you like to do?");
            System.out.println("Custome: Make a custom puzzle (press C)");
            System.out.println("List: Pick a from a pre-made list of words to generate (press L)");
            System.out.println("Solution: Print the solution for the current puzzle (press S)");
            System.out.println("Quit (press Q)");
            
            menuChoice = input.nextLine().toUpperCase().charAt(0);
            if (menuChoice != 'C' && menuChoice != 'L' && menuChoice != 'S' && menuChoice != 'Q') {
                System.out.println("I did not understand your choice, please try again");
            }
        }

        return 0;
    }

    /*------------------------------------------------------------------------- */
    /* Get the words from the user to create the puzzle */
    public static ArrayList<String> getWords() {
        Scanner input = new Scanner(System.in);
        ArrayList<String> userWords = new ArrayList<String>();
        boolean moreWords = true;

        while (moreWords == true) {
            System.out.println("What words would you like to add?");
            // test string
            String nextWord = input.nextLine().toUpperCase();
            nextWord = nextWord.replaceAll("[^a-zA-Z]", "");  
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

    public static ArrayList<String> populatePuzzleWords(int puzzleChoice) {
        ArrayList<String> wordList = new ArrayList<String>();
        String[] safariAnimals = { "Lion", "Leopard", "Buffalo", "Rhinoceros", "Giraffe", "Cheetah",
                "Hippopotamus", "Crocodile", "Zebra", "Wildebeest", "Warthog", "Hyena", "Impala",
                "Gazelle", "Sable", "Kudu", "Baboon", "Ostrich", "Elephant" };

        for (int i = 0; i < safariAnimals.length; i++) {
            wordList.add(safariAnimals[i].toUpperCase());
        }
        return wordList;
    }
}
