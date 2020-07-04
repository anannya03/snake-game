import java.awt.Color;

import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Game myGame = new Game();
        obj.setBounds(10, 10, 900, 700);
        obj.setBackground(Color.DARK_GRAY);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(myGame);
        
    }
}
