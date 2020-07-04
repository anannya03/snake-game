import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Game extends JPanel implements KeyListener, ActionListener{
    /**
     *
     */
    private static final long serialVersionUID = -1906119232858974564L;
    /**
     *
     */
    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];

    private int moves = 0;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon upMouth;
    private ImageIcon downMouth;
    private ImageIcon rightMouth;
    private ImageIcon leftMouth;

    private int lengthOfSnake = 3;

    private Timer timer;
    private int delay = 100;

    private ImageIcon snakeImage;

    private ImageIcon titleImage;

    private int final_Score = 0;

    private int[] appleXPos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};

    private int[] appleYPos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

    private ImageIcon appleImage;

    private Random random = new Random();

    private int xPos = random.nextInt(34);
    private int yPos = random.nextInt(23);

    private int scores = 0;

    public Game(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){

        if(moves == 0){

            snakeXLength[2] = 50;
            snakeXLength[1] = 75;
            snakeXLength[0] = 100;

            snakeYLength[2] = 100;
            snakeYLength[1] = 100;
            snakeYLength[0] = 100;
        }

        //draw title image border
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);

        //draw the title image
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        //draw border for game
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);

        //draw background for the game
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        //draw scores
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores:" +scores, 780, 30);

        //draw length
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length:" +lengthOfSnake, 780, 50);

        rightMouth = new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

        for(int a =0; a<lengthOfSnake; a++){
            if(a==0 && right){
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a==0 && left){
                leftMouth = new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a==0 && up){
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a==0 && down){
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            if(a!= 0){
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            
        }

        //draw apple
        appleImage = new ImageIcon("apple.png");
        appleImage.paintIcon(this, g, appleXPos[xPos], appleYPos[yPos]);

        if((appleXPos[xPos] == snakeXLength[0]) && (appleYPos[yPos] == snakeYLength[0])){
            lengthOfSnake++;
            xPos = random.nextInt(34);
            yPos = random.nextInt(23);
            scores += 5;
        }

        for(int b = 1; b<lengthOfSnake; b++){
            if((snakeXLength[b] == snakeXLength[0]) && (snakeYLength[b] == snakeYLength[0])){
                right = false;
                left = false;
                up = false;
                down = false;
                final_Score = scores;

                g.setColor(Color.white);
                
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 40));
                g.drawString("FINAL SCORE:" + final_Score, 280, 370);

                g.setFont(new Font("arial", Font.BOLD, 25));
                g.drawString("Enter SPACE to Restart", 350, 430);
            }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        timer.start();
        
        if(right){
            for(int r = lengthOfSnake-1; r>=0; r--){
                snakeYLength[r+1] = snakeYLength[r];
            }
            for(int r = lengthOfSnake-1; r>=0; r--){
                if(r == 0)
                    snakeXLength[r] = snakeXLength[r] + 25;
                else
                    snakeXLength[r] = snakeXLength[r-1];
                if(snakeXLength[r] > 850)
                    snakeXLength[r] = 25;
            }
            repaint();
        }
        if(left){
            
            for(int r = lengthOfSnake-1; r>=0; r--){
                snakeYLength[r+1] = snakeYLength[r];
            }

            for(int r = lengthOfSnake-1; r>=0; r--){
                if(r == 0)
                    snakeXLength[r] = snakeXLength[r] - 25;
                else
                    snakeXLength[r] = snakeXLength[r-1];
                if(snakeXLength[r] < 25)
                    snakeXLength[r] = 850;
            }
            repaint();
        }
        if(up){
            
            for(int r = lengthOfSnake-1; r>=0; r--){
                snakeXLength[r+1] = snakeXLength[r];
            }

            for(int r = lengthOfSnake-1; r>=0; r--){
                if(r == 0)
                    snakeYLength[r] = snakeYLength[r] - 25;
                else
                    snakeYLength[r] = snakeYLength[r-1];
                if(snakeYLength[r] <75)
                    snakeYLength[r] = 625;
            }
            repaint();
        }
        if(down){
            
            for(int r = lengthOfSnake-1; r>=0; r--){
                snakeXLength[r+1] = snakeXLength[r];
            }

            for(int r = lengthOfSnake-1; r>=0; r--){
                if(r == 0)
                    snakeYLength[r] = snakeYLength[r] + 25;
                else
                    snakeYLength[r] = snakeYLength[r-1];
                if(snakeYLength[r] > 625)
                    snakeYLength[r] = 75;
            }
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            final_Score = 0;
            moves = 0;
            scores = 0;
            lengthOfSnake = 3;
            repaint();;
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            
            right = true;
            if(!left)
                right = true;
            else{
                right = false;
                left = true;
            }

            up = false;
            down = false;


        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves++;
            
            left = true;
            if(!right)
                left = true;
            else{
                left = false;
                right = true;
            }
            
            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves++;
            
            up = true;
            if(!down)
                up = true;
            else{
                up = false;
                down = true;
            }
            
            left = false;
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            
            down = true;
            if(!up)
                down = true;
            else{
                down = false;
                up = true;
            }
            
            left = false;
            right = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            
            right = true;
            if(!left)
                right = true;
            else{
                right = false;
                left = true;
            }
            
            up = false;
            down = false;


        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

}