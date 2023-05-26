import java.security.SecureRandom;
import java.util.ArrayList;

public class WordSearchManager {
    private char[][] wordSearch;
    private char[][] wordSearchSolution;
    private ArrayList<String> userWords;
    private char fillChar = '.';

    /*------------------------------------------------------------------------- */
    // Default constructor
    public WordSearchManager() {
    }

    /*------------------------------------------------------------------------- */
    /*
     * This method does the bulk of the work. It will prompt the user for how many
     * words and what the words are and generate a word search based on those words.
     */
    public void generate(int size, ArrayList<String> words) {
        SecureRandom randNum = new SecureRandom();
        
        // track how many attempts it took to place all of the words
        int placementAttemps = 0;
        int wordDirection;

        this.wordSearch = new char[size][size];
        this.userWords = words;

        // fill all blank space with ~'s
        fillBlanks();

        for (int i = 0; i < this.userWords.size(); i++) {
            String thisWord = this.userWords.get(i);

            boolean wordSuccess = false;

            // change the direction for the next word placement
            wordDirection = randNum.nextInt(3);
            
            // loop the following code to repeat if the word is not added
            do {
                placementAttemps++;
                
                // create a random start location for the next word
                int xAxis = randNum.nextInt(size);
                int yAxis = randNum.nextInt(size);

                // test if word fits
                wordSuccess = testWordFit(xAxis, yAxis, wordDirection, thisWord);

                // if the test is successful, add word to puzzle
                if (wordSuccess == true) {
                    addWord(xAxis, yAxis, wordDirection, thisWord);
                }

            } while (wordSuccess == false);
            
        }
        
        System.out.println("It took " + placementAttemps + " attempts to place " + this.userWords.size() + " words");
        this.wordSearchSolution = this.wordSearch;
        randomLetters(randNum);
    }

    /*------------------------------------------------------------------------- */
    // fill blanks with a specified symbol
    private void fillBlanks() {
        for (int i = 0; i < this.wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                this.wordSearch[i][j] = fillChar;
            }
        }
    }

    /*------------------------------------------------------------------------- */
    // replace the fill symbol with random letters
    private void randomLetters(SecureRandom randNum) {
        for (int i = 0; i < this.wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                // get random char
                if (this.wordSearch[i][j] == fillChar) {
                    this.wordSearch[i][j] = (char) (randNum.nextInt(26) + 65);
                }
            }
        }
    }

    /*------------------------------------------------------------------------- */
    /* Test if the word will fit before writing it to the array */
    private boolean testWordFit(int xAxis, int yAxis, int wordDirection, String thisWord) {
        boolean wordTest = true;

        for (int j = 0; j < thisWord.length(); j++) {
            try {
                if (this.wordSearch[yAxis][xAxis] != fillChar &&
                        this.wordSearch[yAxis][xAxis] != thisWord.charAt(j)) {

                    System.out.println("Word entry rejected: " + thisWord);
                    return false;
                }
                
                // incrament char location based on word direction
                switch (wordDirection) {
                    case 0: // horizontal
                        xAxis++;
                        break;
                    case 1: // Virtical
                        yAxis++;
                        break;
                    case 2: // Diagnal down
                        xAxis++;
                        yAxis++;
                        break;
                    case 3: // Diagnal up
                        xAxis++;
                        yAxis--;
                        break;
                    default:
                        System.out.println("Word direction rejected: " + wordDirection);
                        return false;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Word is out of bounds: " + e);
                return false;
            }
        }

        System.out.println("Test Successful");
        return wordTest;
    }

    /*------------------------------------------------------------------------- */
    /* Add word after it has been tested */
    private void addWord(int xAxis, int yAxis, int wordDirection, String thisWord) {
        try {
            for (int j = 0; j < thisWord.length(); j++) {
                // System.out.println("addWord X Coord: " + xAxis + " Y Coord: " + yAxis);
                // System.out.println("Word Direction " + wordDirection);
                // System.out.println("Add Letter " + thisWord.charAt(j));

                this.wordSearch[yAxis][xAxis] = thisWord.charAt(j);
                switch (wordDirection) {
                    case 0: // horizontal
                        xAxis++;
                        break;
                    case 1: // Virtical
                        yAxis++;
                        break;
                    case 2: // Diagnal down
                        xAxis++;
                        yAxis++;
                        break;
                    case 3: // Diagnal up
                        xAxis++;
                        yAxis--;
                        break;
                    // default:
                    // System.out.println("Word direction rejected: " + wordDirection);
                    // break;
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Word is out of bounds: " + e);
        }

        System.out.println("Word added successfully");
    }

    /*------------------------------------------------------------------------- */
    /* This method prints the current word search that has been generated. */
    public void print() {
        for (int i = 0; i < this.wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                System.out.print(this.wordSearch[i][j] + " ");
            }
            System.out.println("");
        }

    }

    /*------------------------------------------------------------------------- */
    /*
     * This method prints the solution to the word search that has been generated.
     */
    public void showSolution() {
        for (int i = 0; i < this.wordSearchSolution.length; i++) {
            for (int j = 0; j < wordSearchSolution[i].length; j++) {
                System.out.print(this.wordSearchSolution[i][j] + "  ");
            }
            System.out.println("");
        }
    }
}
