import javax.swing.*;
import java.io.File;
import java.util.StringTokenizer;

public class Project3 {	

    public static void main(String[] args) {
    	JOptionPane.showMessageDialog(null, "To start a default game go to file and click on play game. If have a separate .txt file " +
    " make sure that the first line \n being letters that user is allowed to use where rest are the solutions. Keep in mind capital letters are not allowed!");
    	String letterstouse = "";
        PuzzleGUI puzzle = new PuzzleGUI(letterstouse);
       
    }
    }