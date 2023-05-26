
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

        // delay used for type writer. change to 0 to remove effect.
        int delay = 5;
        int difficulty = 1;
        printIntro(delay);
        boolean run = true;

        while (run) {
            char menuOption = menu(delay);
            ArrayList<String> userWords = new ArrayList<>();

            switch (menuOption) {
                case 'c': // Make custom word search

                    userWords = getWords();
                    difficulty = difficulty();
                    wordSearch.generate(userWords, difficulty);
                    wordSearch.print();
                    break;
                    case 'l': // Pick a list of words to generate a word search
                    userWords = populatePuzzleWords(delay);
                    difficulty = difficulty();
                    wordSearch.generate(userWords, difficulty);
                    wordSearch.print();
                    break;
                case 's': // Pick a list of words to generate a word search

                    wordSearch.showSolution();
                    break;
                case 'q': // Quit
                    System.out.println("Thanks for using the best word search puzzle generator!");
                    run = false;
                    break;
            }
            // Display puzzle
            // List puzzles

        }
    }

    /*------------------------------------------------------------------------- */
    /*
     * This method prints out the intro to the program. It gives the user an idea of
     * what the program does and how to use it.
     */
    public static void printIntro(int delay) {
        typeWriter(delay, "Welcome to the best word search puzzle generator in the world!\n");
        typeWriter(delay, "You can use your own list of words or pick from a pre-made ");
        typeWriter(delay, "list to generate a new word search puzzle!\n");
    }

    public static char menu(int delay) {
        Scanner input = new Scanner(System.in);
        char menuChoice = ' ';
        boolean valid = false;
        while (!valid) {
            
            typeWriter(delay, "\nWhat would you like to do?\n");
            typeWriter(delay, "Custom: Make a custom puzzle (press C)");
            typeWriter(delay, "List: Pick a from a pre-made list of words to generate (press L)");
            typeWriter(delay, "Solution: Print the solution for the current puzzle (press S)");
            typeWriter(delay, "Quit (press Q)\n");
            
            menuChoice = input.nextLine().toLowerCase().charAt(0);

            // test for valid response
            if (menuChoice == 'c' || menuChoice == 'l' || menuChoice == 's' || menuChoice != 'q') {
                valid = true;
            } else {
                System.out.println("\nI did not understand your choice, please try again");
            }
        }
        
        return menuChoice;
    } // end menu

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

         
        return userWords;
    } // getWords

    /*------------------------------------------------------------------------- */
    /* Get the words from the user to create the puzzle */
    public static int difficulty() {
            System.out.println("What would you like the puzzles difficulty to be?");
            System.out.println("choose a number between 5 and 100");
            // test string
            int difficulty = inputTestInt(5, 1000);
           return difficulty;
    } // end difficulty

    /*------------------------------------------------------------------------- */
    /* test that user input is int */
    public static int inputTestInt(int rangeLow, int rangeHigh) {
        Scanner input = new Scanner(System.in);
        boolean test = false;
        int userInput = -9999;

        do {
            try {
                userInput = input.nextInt();
                if (userInput >= rangeLow && userInput <= rangeHigh) {
                    test = true;
                } else {
                    System.out.println("Error! The number you entered is out of bounds");
                }
            } catch (Exception e) {
                System.out.println("Error! try again \n" + input.nextLine() + "\n is not valid");
                test = false;
            }
        } while (!test);

        return userInput;
    } // end inputTestInt

    /*------------------------------------------------------------------------- */
    /* populate puzzle with a pre-made list */
    public static ArrayList<String> populatePuzzleWords(int delay) {
        Scanner input = new Scanner(System.in);
        typeWriter(delay, "What list would you like to use?");
        typeWriter(delay, "\tSafari Animals");
        typeWriter(delay, "\tPresidents of the United States");
        typeWriter(delay, "\tCountries");

        ArrayList<String> wordList = new ArrayList<String>();
        String[] safariAnimals = { "Lion", "Leopard", "Buffalo", "Rhinoceros", "Giraffe",
                "Cheetah", "Hippopotamus", "Crocodile", "Zebra", "Wildebeest", "Warthog", "Hyena",
                "Impala", "Gazelle", "Sable", "Kudu", "Baboon", "Ostrich", "Elephant" };

        String[] presidents = { "George Washington", "John Adams", "Thomas Jefferson",
                "James Madison", "James Monroe", "John Quincy Adams", "Andrew Jackson",
                "Martin Van Buren", "William Henry Harrison", "John Tyler", "James K Polk",
                "Zachary Taylor", "Millard Fillmore", "Franklin Pierce", "James Buchanan",
                "Abraham Lincoln", "Andrew Johnson", "Ulysses S Grant", "Rutherford B Hayes",
                "James A Garfield", "Chester A Arthur", "Grover Cleveland", "Benjamin Harrison",
                "William McKinley", "Theodore Roosevelt", "William Howard Taft", "Woodrow Wilson",
                "Warren G Harding", "Calvin Coolidge", "Herbert Hoover", "Franklin D Roosevelt",
                "Harry S Truman", "Dwight D Eisenhower", "John F Kennedy", "Lyndon B Johnson",
                "Richard Nixon", "Gerald Ford", "Jimmy Carter", "Ronald Reagan",
                "George H W Bush", "Bill Clinton", "George W Bush", "Barack Obama",
                "Donald Trump", "Joe Biden" };

        String[] countries = { "Russia", "Canada", "China", "United States", "Brazil", "Australia",
                "India", "Argentina", "Kazakhstan", "Algeria", "DRC", "Saudi Arabia", "Mexico",
                "Indonesia", "Sudan", "Libya", "Iran", "Mongolia", "Peru", "Niger", "Angola",
                "Mali", "South Africa", "Colombia", "Ethiopia", "Bolivia", "Mauritania", "Egypt",
                "Tanzania", "Nigeria" };

        char listChoice;
        String[] list = new String[0];
        listChoice = input.nextLine().toLowerCase().charAt(0);
        if (listChoice == 's' || listChoice == 'p' || listChoice == 'c') {
            switch (listChoice) {
                case 's':
                    list = safariAnimals;
                    break;
                case 'p':
                    list = presidents;
                    break;
                case 'c':
                    list = countries;
                    break;
            }

            for (int i = 0; i < list.length; i++) {
                wordList.add(list[i].toUpperCase());
            }
        } else {
            System.out.println("\nI did not understand your choice, please try again");
        }
         
        return wordList;
    } // end populatePuzzleWords

    /*------------------------------------------------------------------------- */
    /* add type writer effect to print statements */
    public static void typeWriter(int delay, String line) {
        for (int i = 0; i < line.length(); i++) {
            System.out.print(line.charAt(i));
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    } // end typeWriter
}
