import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

public class PuzzleGUI extends JFrame {
    private TextArea myTextArea;
    private TextArea correctWordsArea;

    private TreeSet<String> correctWords; // Store correct words in a TreeSet

    public PuzzleGUI(String letterstouse) {
  
        this.setSize(600, 600);
        this.setLocation(100, 100);
        createFileMenu();
        this.setTitle("Spelling Beehive");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2)); // Two columns layout
        Container myContentPane = this.getContentPane();

        // Initialize text areas
        myTextArea = new TextArea();
        correctWordsArea = new TextArea();

        // Add text areas to the content pane
        myContentPane.add(myTextArea);
        myContentPane.add(correctWordsArea);

        // Initialize TreeSet to store correct words
        correctWords = new TreeSet<>();

        myTextArea.append("Letters you can choose from: " + letterstouse);
        correctWordsArea.append("Correctly guessed words:");
        setVisible(true);

    }
    private void createFileMenu( ) {
        JMenuItem   item;
        JMenuBar    menuBar  = new JMenuBar();
        JMenu       fileMenu = new JMenu("File");
        FileMenuHandler fmh  = new FileMenuHandler(this);

        item = new JMenuItem("Open");    //Open...
        item.addActionListener( fmh );
        fileMenu.add( item );
        
        item = new JMenuItem("Play Game");    //Open...
        item.addActionListener( fmh );
        fileMenu.add( item );
        
        fileMenu.addSeparator();    	   //add a horizontal separator line
        item = new JMenuItem("Instructions");       //Quit
        item.addActionListener( fmh );
        fileMenu.add( item );

        fileMenu.addSeparator();           //add a horizontal separator line
        item = new JMenuItem("Quit");       //Quit
        item.addActionListener( fmh );
        fileMenu.add( item );


        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
      
     }
    // Update the score
    public void updateScore(int score) {
        myTextArea.append("\nYour Score: " + score);
    }

    // Update the list of correct words
    public void updateCorrectWords(String word) {
        correctWords.add(word); // Add the word to the TreeSet

        correctWordsArea.setText(""); // Clear the text area
        // Update the text area with all correct words in alphabetical order
        for (String w : correctWords) {
            correctWordsArea.append(w + "\n");
        }
    }
}


