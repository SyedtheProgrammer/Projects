import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

public class FileMenuHandler implements ActionListener {
	static int lcounter = 0;
	
    JFrame jframe;

    public FileMenuHandler(JFrame jf) {
        jframe = jf;
    }

    public void actionPerformed(ActionEvent event) {
        String menuName;
        //Creating 4 options in the menu: Open, Play Game, Instructions, Quit
        menuName = event.getActionCommand();
        if (menuName.equals("Open"))
            openFile();
        else if (menuName.equals("Play Game")) {
            // Read in "input.txt" and start the game
        
        	//File inputFile = new File("D:\\Eclipse\\Project3\\src\\input3.txt");
        	File inputFile = new File("input3.txt");
            readSource(inputFile);
        }
        
        else if (menuName.equals("Quit"))
            System.exit(0);
        
        else if (menuName.equals("Instructions")) {
        	JOptionPane.showMessageDialog(null, "RULES: \n 1) Your answer must contain at least five characters. \n 2) You are allowed to use one letter" 
        			+ " more than once. \n 3) If you use all of the letter then you will be awarded extra points. \n" +
        			" 4) To end game you must either type stop or guess all correct words");
        }

    }

    private void openFile() {
        JFileChooser chooser;
        int status;
        chooser = new JFileChooser();
        status = chooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION)
            readSource(chooser.getSelectedFile());
        else
            JOptionPane.showMessageDialog(null, "Open File dialog canceled");
    }

    private void readSource(File chosenFile) {
    
        String chosenFileName = chosenFile.getAbsolutePath();
        boolean replay = true;
        
        while (replay) {
        String[] chararray = null;
        String letterstouse = null;
        UnsortedWordList unsorted = new UnsortedWordList();
        SortedWordList sorted = new SortedWordList();
        TextFileInput inFile = new TextFileInput(chosenFileName);
        String line;
  
        int temp = 0;
        int totalwords = 0;
        
        line = inFile.readLine();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                String wordString = tokenizer.nextToken();
                try {
                    if (containsUppercase(wordString)) {
                        throw new IllegalWordException("Word must contain only lowercase letters: " + wordString);
                    }
                    Word word = new Word(wordString);
                    unsorted.add(word);
                    temp++;
                } catch (IllegalWordException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(letterstouse == null) letterstouse = line; 
     
            
            chararray = convertToLetterArray(letterstouse);
            line = inFile.readLine();
        }// While CLOSED 
        
        //GUI
        PuzzleGUI puzzle = new PuzzleGUI(letterstouse);
        jframe.setVisible(true);
        // Transfer words from unsorted list to sorted list
        WordNode currentNode = unsorted.first.next;
        while (currentNode != null) {
            sorted.add(currentNode.data);
            currentNode = currentNode.next;
        }
        int score = 0;

        // Create the GUI
        puzzle.setVisible(true);
        
        while (true) {
            // Get user input
            String inputWord = JOptionPane.showInputDialog(null, "Enter a word with at least 5 letters that are shown to the left");
            
            
            // Null test and if the user decides to stop
            if (inputWord == null || inputWord.equalsIgnoreCase("STOP")) {
                JOptionPane.showMessageDialog(null, "You scored " + score);
                System.exit(0);
            }
            
            inputWord = inputWord.toLowerCase();
            
            if (inputWord == letterstouse) inputWord = JOptionPane.showInputDialog(null, "Incorrect guess. Try again!");
            
            lettersfound(inputWord, chararray);
            
            // Check if the input word is in the sorted list
            WordNode current = sorted.first.next;
            boolean found = false;
            while (current != null) {
                if (current.data.getdata().equalsIgnoreCase(inputWord)) {
                    found = true;
                    break;
                }
                current = current.next;
            }//While Closed
            
          
            // Update score and GUI
            if (found) {
            	if ((temp - 2) == totalwords) {
                    int response = JOptionPane.showConfirmDialog(null,
                            "CONGRATULATIONS YOU HAVE WON! WOULD YOU LIKE TO PLAY AGAIN?");
                    if (response == JOptionPane.YES_OPTION) { //Let the user choose an option
                        replay = true;
                    } else {
                        replay = false;
                    }
                    break;
                }

                if (lcounter == chararray.length) {//check if all letters are found in the user input
                    score += 3;
                    puzzle.updateScore(score);
                    puzzle.updateCorrectWords(inputWord);
                    sorted.add(new Word(inputWord)); // Add the correct word to the sorted list
                    JOptionPane.showMessageDialog(null, "Congratulations! You used all letters from the list. Your score is now: " + score);
                }
                else {
                	lcounter =0;
                    score++;
                    puzzle.updateCorrectWords(inputWord);
                    puzzle.updateScore(score);
                    sorted.add(new Word(inputWord)); // Add the correct word to the sorted list
                    JOptionPane.showMessageDialog(null, "Correct guess! Your score is now: " + score);
                }
                totalwords++;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect guess. Try again!");
            }
        }
        }//While Closed

        


    }

    private String[] convertToLetterArray(String letterstouse) {
    	// Remove any whitespace from the string
        letterstouse = letterstouse.replaceAll("\\s+", "");

        // Initialize an array to store each letter
        String[] letterArray = new String[letterstouse.length()];

        // Iterate through each character of the string and store it in the array
        for (int i = 0; i < letterstouse.length(); i++) {
            letterArray[i] = String.valueOf(letterstouse.charAt(i));
        }

        return letterArray; 
	}

	public static boolean Tests(String findMe, String[] charallowed) {
        //Testing if the letters are less than 5
        if (findMe.length() < 5) return true;

        //Testing if the letters are found in the charallowed array
        if (lettersfound(findMe, charallowed)) return true;
        
        //Testing if
        if (!findMe.contains("l")) return true;
        
        
        
        return false;
	}
	
	//Checking whether characters in findMe exist in charallowed
	public static boolean lettersfound(String findMe, String[] charallowed) {
	    // Array to keep track of found letters
	    boolean[] foundLetters = new boolean[26]; // Assuming only lowercase letters are allowed

	    for (int i = 0; i < findMe.length(); i++) {
	        String currentLetter = findMe.substring(i, i + 1).toLowerCase(); // Convert to lowercase for case-insensitive comparison
	        char currentChar = currentLetter.charAt(0);
	        int index = currentChar - 'a'; // Calculate the index of the letter in the array

	        // If the letter is already found, skip it
	        if (foundLetters[index]) {
	            continue;
	        }

	        // Check if the current letter is among the allowed letters
	        boolean found = false;
	        for (int j = 0; j < charallowed.length; j++) {
	            if (currentLetter.equals(charallowed[j])) {
	                found = true;
	                lcounter++;
	                foundLetters[index] = true; // Mark the letter as found in the array
	                break;
	            }
	        }
	        if (!found) {
	            return true; // If a letter in findMe is not found in charallowed, return true
	        }
	    }
	    return false; // All letters in findMe are found in charallowed
	}


    //For exceptionHandling checking if there is a word that contains capital letter in the list
    private boolean containsUppercase(String word) {
        char[] characters = word.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (Character.isUpperCase(characters[i])) {
                return true;
            }
        }
        return false;
    }
}
 