/* WordSearchManager.java creates and displays the puzzle with the word input from TestMain.java */

import java.security.SecureRandom;
import java.util.ArrayList;

public class WordSearchManager {
    private char[][] wordSearch;
    private ArrayList<String> userWords;
    private char fillChar = '.';
    private int wordPlacementAttempts = 0;
    private int difficulty = 1;

    /*------------------------------------------------------------------------- */
    // Default constructor
    public WordSearchManager() {
    }

    /*------------------------------------------------------------------------- */
    /*
     * This method does the bulk of the work. It will prompt the user for how many
     * words and what the words are and generate a word search based on those words.
     */
    public void generate(ArrayList<String> words, int difficultyChoice) {
        this.difficulty = difficultyChoice;
        SecureRandom randNum = new SecureRandom();
        this.wordPlacementAttempts = 0;
        int size = 10;
        this.userWords = words;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).length() > size) {
                size = words.get(i).length();
            }
        }
        char[][] tempPuzzle = new char[size][size];
        boolean sizeTest = false;
        // int resizePuzzleCount = 0;

        while (!sizeTest) {

            // track how many attempts it took to place all of the words
            int placementAttempts = 0;
            int wordDirection = 0;

            // fill all blank space with ~'s
            fillBlanks(tempPuzzle);

            for (int i = 0; i < words.size(); i++) {
                String thisWord = words.get(i).replace(" ", "");
                boolean wordSuccess = false;
                wordDirection = randNum.nextInt(4);

                // loop the following code to repeat if the word is not added
                do {
                    placementAttempts++;

                    // create a random start location for the next word
                    int xAxis = randNum.nextInt(size);
                    int yAxis = randNum.nextInt(size);

                    // test if word fits
                    wordSuccess = testWordFit(tempPuzzle, xAxis, yAxis, wordDirection, thisWord);

                    // if the test is successful, add word to puzzle
                    if (wordSuccess == true) {
                        addWord(tempPuzzle, xAxis, yAxis, wordDirection, thisWord);
                        // change the direction for the next word placement
                    }

                    // size test failed, loop exit
                    if (placementAttempts > words.size() * difficultyChoice) {
                        break;
                    }
                } while (!wordSuccess);

                // size test successful
                if (i == words.size() - 1) {
                    sizeTest = true;
                }
                // size test failed, loop exit
                if (placementAttempts > (words.size() * difficultyChoice)) {
                    this.wordPlacementAttempts += placementAttempts;
                    size++;
                    tempPuzzle = new char[size][size];
                    break;
                }
            }
        } // end while (!sizeTest)

        // save puzzle
        this.wordSearch = tempPuzzle;
    } // end of generate

    /*------------------------------------------------------------------------- */
    // fill blanks with a specified symbol
    private void fillBlanks(char[][] tempPuzzle) {
        for (int i = 0; i < tempPuzzle.length; i++) {
            for (int j = 0; j < tempPuzzle[i].length; j++) {
                tempPuzzle[i][j] = fillChar;
            }
        }
    } // end of fillBlanks

    /*------------------------------------------------------------------------- */
    /* Test if the word will fit before writing it to the array */
    private boolean testWordFit(char[][] tempPuzzle, int xAxis, int yAxis, int wordDirection, String thisWord) {
        boolean wordTest = true;
        for (int j = 0; j < thisWord.length(); j++) {
            try {
                if (tempPuzzle[yAxis][xAxis] != fillChar &&
                        tempPuzzle[yAxis][xAxis] != thisWord.charAt(j)) {
                    return false;
                }
                // increment char location based on word direction
                switch (wordDirection) {
                    case 0: // horizontal
                        xAxis++;
                        break;
                    case 1: // Vertical
                        yAxis++;
                        break;
                    case 2: // Diagonal down
                        xAxis++;
                        yAxis++;
                        break;
                    case 3: // Diagonal up
                        xAxis++;
                        yAxis--;
                        break;
                    default:
                        return false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
        return wordTest;
    } // end of testWordFit

    /*------------------------------------------------------------------------- */
    /* Add word after it has been tested */
    private char[][] addWord(char[][] tempPuzzle, int xAxis, int yAxis, int wordDirection, String thisWord) {
        try {
            for (int j = 0; j < thisWord.length(); j++) {

                tempPuzzle[yAxis][xAxis] = thisWord.charAt(j);
                switch (wordDirection) {
                    case 0: // horizontal
                        xAxis++;
                        break;
                    case 1: // Vertical
                        yAxis++;
                        break;
                    case 2: // Diagonal down
                        xAxis++;
                        yAxis++;
                        break;
                    case 3: // Diagonal up
                        xAxis++;
                        yAxis--;
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return tempPuzzle;
    } // end of addWord

    /*------------------------------------------------------------------------- */
    /* This method prints the current word search that has been generated. */
    public void print() {
        SecureRandom randNum = new SecureRandom();
        char[][] tempPuzzle = this.wordSearch;
        // tempPuzzle = randomLetters(tempPuzzle);
        for (int i = 0; i < tempPuzzle.length; i++) {
            for (int j = 0; j < tempPuzzle[i].length; j++) {
                if (tempPuzzle[i][j] == fillChar) {
                    System.out.print((char) (randNum.nextInt(26) + 65) + "  ");
                } else {
                    System.out.print(tempPuzzle[i][j] + "  ");
                }
            }
            System.out.println("");
        }

        for (int i = 0; i < userWords.size(); i += 0) {
            if (i + 3 <= userWords.size()) {
                System.out.printf("\t%-25s%-25s%-25s\n", userWords.get(i), userWords.get(i + 1),
                        userWords.get(i + 2));
                i += 3;
            } else if (i + 2 <= userWords.size()) {
                System.out.printf("\t%-25s%-25s\n", userWords.get(i), userWords.get(i + 1));
                i += 2;
            } else {
                System.out.printf("\t%-25s\n", userWords.get(i));
                i++;
            }
        }
        System.out.println("It took " + this.wordPlacementAttempts + " attempts to place words to");
        System.out.println("generate this " +tempPuzzle.length+ " x "+ tempPuzzle.length + " puzzle"); 
        System.out.println("with " + userWords.size() + " words on difficulty " + difficulty);
    } // end of print

    /*------------------------------------------------------------------------- */
    /*
     * This method prints the solution to the word search that has been generated.
     */
    public void showSolution() {
        for (int i = 0; i < this.wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                System.out.print(this.wordSearch[i][j] + "  ");
            }
            System.out.println("");
        }
    } // end of showSolution
}
