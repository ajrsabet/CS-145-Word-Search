import java.security.SecureRandom;
import java.util.ArrayList;

public class WordSearchManager {
    private char[][] wordSearch;
    private char[][] wordSearchSolution;
    private ArrayList<String> userWords;

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
        this.wordSearch = new char[size][size];
        this.userWords = words;

        // fill all blank space with ~'s
        fillBlanks();

        // Add each word to the word search
        addWord(size);

        this.wordSearchSolution = this.wordSearch;
    }

    /*------------------------------------------------------------------------- */
    /*
     * This method adds a word to the puzzle
     */
    private void addWord(int size) {
        SecureRandom randNum = new SecureRandom();
        int xAxis;
        int yAxis;
        int wordDirection = 1;

        // create a temp array to test if a word can fit
        
        for (int i = 0; i < this.userWords.size(); i++) {
            String thisWord = this.userWords.get(i);
            System.out.println(thisWord);
            
            boolean wordSuccess = false;
            do {
                char[][] tempArr = wordSearch;        
                switch (wordDirection) {
                    case 1: // virtical
                        // set start possition to keep the word in the array
                        xAxis = randNum.nextInt(size);
                        yAxis = randNum.nextInt(size - thisWord.length());
                        for (int j = 0; j < thisWord.length(); j++) {
                            if (tempArr[yAxis][xAxis] != '*' &&
                                    tempArr[yAxis][xAxis] != thisWord.charAt(j)) {
                                System.out.println("Word entry rejected: " + thisWord);
                                wordSuccess = false;
                                tempArr = wordSearch;
                                break;
                            } else {
                                tempArr[yAxis][xAxis] = thisWord.charAt(j);
                                yAxis++;
                                wordSuccess = true;
                            }
                        }
                        if (wordSuccess == true){
                            wordSearch = tempArr;
                        }
                        break;
                    case 2: // horizontal
                        // set start possition to keep the word in the array
                        xAxis = randNum.nextInt(size - thisWord.length());
                        yAxis = randNum.nextInt(size);
                        for (int j = 0; j < thisWord.length(); j++) {
                            if (tempArr[yAxis][xAxis] != '*' &&
                                    tempArr[yAxis][xAxis] != thisWord.charAt(j)) {
                                System.out.println("Word entry rejected: " + thisWord);
                                wordSuccess = false;
                                tempArr = wordSearch;
                                break;
                            } else {
                                tempArr[yAxis][xAxis] = thisWord.charAt(j);
                                xAxis++;
                                wordSuccess = true;
                            }
                        }
                        if (wordSuccess == true){
                            wordSearch = tempArr;
                        }
                        break;
                    case 3: // diagnal up
                        // set start possition to keep the word in the array
                        xAxis = randNum.nextInt(size - thisWord.length());
                        yAxis = randNum.nextInt(size - thisWord.length()) + thisWord.length();
                        for (int j = 0; j < thisWord.length(); j++) {
                            if (tempArr[yAxis][xAxis] != '*' &&
                                    tempArr[yAxis][xAxis] != thisWord.charAt(j)) {
                                System.out.println("Word entry rejected: " + thisWord);
                                wordSuccess = false;
                                tempArr = wordSearch;
                                break;
                            } else {
                                tempArr[yAxis][xAxis] = thisWord.charAt(j);
                                xAxis++;
                                yAxis--;
                                wordSuccess = true;
                            }
                        }
                        if (wordSuccess == true){
                            wordSearch = tempArr;
                        }
                        break;
                    case 4: // diagnal down
                        // set start possition to keep the word in the array
                        xAxis = randNum.nextInt(size - thisWord.length());
                        yAxis = randNum.nextInt(size - thisWord.length());
                        for (int j = 0; j < thisWord.length(); j++) {
                            if (tempArr[yAxis][xAxis] != '*' &&
                                    tempArr[yAxis][xAxis] != thisWord.charAt(j)) {
                                System.out.println("Word entry rejected: " + thisWord);
                                wordSuccess = false;
                                tempArr = wordSearch;
                                break;
                            } else {
                                tempArr[yAxis][xAxis] = thisWord.charAt(j);
                                xAxis++;
                                yAxis++;
                                wordSuccess = true;
                            }
                        }
                        if (wordSuccess == true){
                            wordSearch = tempArr;
                        }
                        break;
                    default:
                        break;
                }

            } while (wordSuccess == false);

            // change next word's direction
            if (wordDirection == 4) {
                wordDirection = 1;
            } else {
                wordDirection++;
            }
        }
    }

    /*------------------------------------------------------------------------- */
    private void fillBlanks() {
        for (int i = 0; i < this.wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                this.wordSearch[i][j] = '*';
                // get random char
                // if (this.wordSearch[i][j] == ' ') {
                // this.wordSearch[i][j] = (char)(randNum.nextInt(26) + 65);
                // }
            }
        }
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
