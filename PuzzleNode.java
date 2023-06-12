/* PuzzleNode.java saves each generated puzzle */

import java.util.ArrayList;

public class PuzzleNode {
    private char[][] puzzle = new char[0][0];
    private ArrayList<String> puzzleWords;
    private int wordAttempts = 0;
    private int difficulty = 1;
    private String puzzleName;

    /*------------------------------------------------------------------------- */
    // Default constructor
    public PuzzleNode() {

    }

    /*------------------------------------------------------------------------- */
    // Create a new node
    public PuzzleNode(int diffIn, ArrayList<String> wordsIn, char[][] puzzleIn,
            int attemptsIn, String name) {
                
        this.puzzle = puzzleIn;
        this.puzzleWords = wordsIn;
        this.difficulty = diffIn;
        this.wordAttempts = attemptsIn;
        this.puzzleName = name;
    }

    /*------------------------------------------------------------------------- */
    // get puzzle array constructor
    public char[][] getPuzzle() {
        return this.puzzle;
    }

    /*------------------------------------------------------------------------- */
    // Get puzzle words
    public ArrayList<String> getWords() {

        return this.puzzleWords;
    }

    /*------------------------------------------------------------------------- */
    // Get puzzle difficulty
    public int getDifficulty() {
        return this.difficulty;
    }

    /*------------------------------------------------------------------------- */
    // Get word attempts
    public int getWordAttempts() {
        return this.wordAttempts;
    }

    /*------------------------------------------------------------------------- */
    // Get puzzle size
    public int getSize() {
        return this.puzzle.length;
    }

    /*------------------------------------------------------------------------- */
    // Get puzzle size
    public String getName() {
        return this.puzzleName;
    }
}
