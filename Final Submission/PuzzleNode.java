/* PuzzleNode.java saves each generated puzzle */

import java.util.ArrayList;

public class PuzzleNode {
    private char[][] puzzle = new char[0][0];
    private ArrayList<String> puzzleWords;
    private int wordAttempts = 0;
    private int difficulty = 1;

    /*------------------------------------------------------------------------- */
    // Default constructor
    public PuzzleNode() {

    }

    /*------------------------------------------------------------------------- */
    // Create a new node
    public PuzzleNode(int diffIn, ArrayList<String> wordsIn, char[][] puzzleIn, int attemptsIn) {
        this.puzzle = puzzleIn;
        this.puzzleWords = wordsIn;
        this.difficulty = diffIn;
        this.wordAttempts = attemptsIn;
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
    public int getPuzzleSize() {
        return this.puzzle.length;
    }
}
