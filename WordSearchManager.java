
/* WordSearchManager.java creates and displays the puzzle with the word input from TestMain.java */
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.security.SecureRandom;

public class WordSearchManager {
    private ArrayList<PuzzleNode> savedPuzzles = new ArrayList<>(0);
    private PuzzleNode thisPuzzle = new PuzzleNode();

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
    public PuzzleNode generate(ArrayList<String> words, int difficulty) {
        // extract the puzzle name from the word list 
        String name = words.get(0);
                words.remove(0);

        SecureRandom randNum = new SecureRandom();
        int wordAttemptCounter = 0;
        int size = 10;
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
                    if (placementAttempts > words.size() * difficulty) {
                        break;
                    }
                } while (!wordSuccess);
                
                // add placement attempts to counter
                wordAttemptCounter += placementAttempts;

                // size test successful
                if (i == words.size() - 1) {
                    sizeTest = true;
                }
                // size test failed, loop exit
                if (placementAttempts > (words.size() * difficulty)) {
                    size++;
                    tempPuzzle = new char[size][size];
                    break;
                }
            }
        } // end while (!sizeTest)

        // save puzzle
        thisPuzzle = new PuzzleNode(difficulty, words, tempPuzzle, wordAttemptCounter, name);
        savedPuzzles.add(thisPuzzle);

        return thisPuzzle;
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
    private boolean testWordFit(char[][] tempPuzzle, int xAxis, int yAxis, int wordDirection,
            String thisWord) {
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
    private void addWord(char[][] tempPuzzle, int xAxis, int yAxis, int wordDirection,
            String thisWord) {
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
    } // end of addWord

    /*------------------------------------------------------------------------- */
    /* This method prints the current word search that has been generated. */
    public void print(PuzzleNode puzzle) {
        thisPuzzle = puzzle;
        String puzzleName = thisPuzzle.getName();
        
            char[][] puzzleArr = thisPuzzle.getPuzzle();
            SecureRandom randNum = new SecureRandom();
            ArrayList<String> puzzleWords = thisPuzzle.getWords();

            System.out.printf("\n%40s\n\n",  puzzleName);
            // tempPuzzle = randomLetters(tempPuzzle);
            for (int i = 0; i < puzzleArr.length; i++) {
                System.out.print("\n\t");
                for (int j = 0; j < puzzleArr[i].length; j++) {
                    if (puzzleArr[i][j] == fillChar) {
                        System.out.print((char) (randNum.nextInt(26) + 65) + "  ");
                    } else {
                        System.out.print(puzzleArr[i][j] + "  ");
                    }
                }
            }

            System.out.print("\n\t");
            for (int i = 0; i < puzzleWords.size(); i += 0) {
                if (i + 3 <= puzzleWords.size()) {
                    System.out.printf("\n\t%-25s%-25s%-25s\t", puzzleWords.get(i),
                            puzzleWords.get(i + 1), puzzleWords.get(i + 2));
                    i += 3;
                } else if (i + 2 <= puzzleWords.size()) {
                    System.out.printf("\n\t%-25s%-25s%-25s\t\n", puzzleWords.get(i), 
                            puzzleWords.get(i + 1), "");
                    i += 2;
                } else {
                    System.out.printf("\n\t%-25s%-25s%-25s\t\n", puzzleWords.get(i), "", "");
                    i++;
                }
            }
        
    } // end of print

    /*------------------------------------------------------------------------- */
    /* This method prints the current word search that has been generated. */
    public void saveToFile(PuzzleNode puzzle) {
        thisPuzzle = puzzle;
        String puzzleName = thisPuzzle.getName();
        
        // test if file exists in the directory
        try {
            PrintStream fileWriter = new PrintStream(new File(puzzleName + "wordSearch.txt"));

            char[][] puzzleArr = thisPuzzle.getPuzzle();
            SecureRandom randNum = new SecureRandom();
            ArrayList<String> puzzleWords = thisPuzzle.getWords();

            fileWriter.printf("\n%40s\n\n",  puzzleName);
            // tempPuzzle = randomLetters(tempPuzzle);
            for (int i = 0; i < puzzleArr.length; i++) {
                for (int j = 0; j < puzzleArr[i].length; j++) {
                    if (puzzleArr[i][j] == fillChar) {
                        fileWriter.print((char) (randNum.nextInt(26) + 65) + "  ");
                    } else {
                        fileWriter.print(puzzleArr[i][j] + "  ");
                    }
                }
                fileWriter.print("\n\t");
            }

            for (int i = 0; i < puzzleWords.size(); i += 0) {
                if (i + 3 <= puzzleWords.size()) {
                    fileWriter.printf("\n\t%-25s%-25s%-25s\t", puzzleWords.get(i),
                            puzzleWords.get(i + 1), puzzleWords.get(i + 2));
                    i += 3;
                } else if (i + 2 <= puzzleWords.size()) {
                    fileWriter.printf("\n\t%-25s%-25s%-25s\t\n", puzzleWords.get(i), 
                            puzzleWords.get(i + 1), "");
                    i += 2;
                } else {
                    fileWriter.printf("\n\t%-25s%-25s%-25s\t\n", puzzleWords.get(i), "", "");
                    i++;
                }
            }
           
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    } // end of print

    /*------------------------------------------------------------------------- */
    // This method prints the solution to the word search that has been generated.
    public void showSolution(PuzzleNode puzzle) {

        char[][] puzzleArr = thisPuzzle.getPuzzle();
        for (int i = 0; i < puzzleArr.length; i++) {
            for (int j = 0; j < puzzleArr[i].length; j++) {
                System.out.print(puzzleArr[i][j] + "  ");
            }
            System.out.println("");
        }
    } // end of showSolution

    /*------------------------------------------------------------------------- */
    // This method prints the solution to the word search that has been generated.
    public void listPuzzles() {

        System.out.printf("|%-8s|%-25s|%-8s|%-8s|%-8s\n",
                "Index", "Puzzle Name", "Size", "Diff", "Word");
        System.out.printf("|%-8s|%-25s|%-8s|%-8s|%-8s\n", "", "", "", "", "Attempts");

        for (int i = 0; i < savedPuzzles.size(); i++) {
            thisPuzzle = savedPuzzles.get(i);

            System.out.printf("|%-8s|%-25s|%-8s|%-8s|%-8s\n", i,
                    thisPuzzle.getName(),
                    thisPuzzle.getSize(),
                    thisPuzzle.getDifficulty()/10,
                    thisPuzzle.getWordAttempts());
        }
    } // end of showSolution

    /*------------------------------------------------------------------------- */
    // This method prints the solution to the word search that has been generated.
    public ArrayList<PuzzleNode> getSavedPuzzles() {

        return savedPuzzles;
    } // end of showSolution
}
