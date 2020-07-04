import java.awt.Color;

import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
        //Create the JFrame
        JFrame myFrame = new JFrame();
        //Create object of the Game class
        Game myGame = new Game();
        //The dimensions of the frame
        myFrame.setBounds(10, 10, 900, 700);
        //The background color of the JFrame
        myFrame.setBackground(Color.DARK_GRAY);
        //The frame is set to be not resizable
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Add the class to the frame object
        myFrame.add(myGame);
        
    }
}
