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
    
    private static final long serialVersionUID = -1906119232858974564L;
    
    private int[] snakeXLength = new int[750];  //array with position of snake body in the x-axis
    private int[] snakeYLength = new int[750];  //array with position of snake body in the y-axis

    //number of moves the player has played for
    private int moves = 0;  

    //the direction of the key pressed, initially all the directions is zero
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    //The image of the snake face in each direction
    private ImageIcon upMouth;
    private ImageIcon downMouth;
    private ImageIcon rightMouth;
    private ImageIcon leftMouth;

    //The initial length of snake
    private int lengthOfSnake = 3;

    //The speed with which the snake moves
    private Timer timer;
    private int delay = 100;

    //The image of the snake's body
    private ImageIcon snakeImage;

    //The title image
    private ImageIcon titleImage;

    //Variable to store the final score and show it once the game is over
    private int final_Score = 0;

    //The position of the apple in the X-axis 
    //Both the position of the x-axis and the y-axis is the multiple of 25, as the image we have is 25 pixel by 25 pixel
    //When the coordinates of the head of the snake matches that of the apple, the player gains points
    private int[] appleXPos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};

    //The position of the apple in the y-axis
    private int[] appleYPos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

    //Variable for the image of the apple
    private ImageIcon appleImage;

    //The random function
    private Random random = new Random();

    //Finding the random position of the apple 
    //34 is the number of positions of the apple in the x-axis
    //23 is the number of positions of the apple in the y-axis
    private int xPos = random.nextInt(34);
    private int yPos = random.nextInt(23);

    //The score in each game
    private int scores = 0;

    //Constructor
    public Game(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        //the timer starts
        timer.start();
    }

    //The program starts here with the paint method
    public void paint(Graphics g){

        //If moves is zero, i.e. when the game just starts, the position of the snake is mentioned
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

        //When the game starts, the snake is right faced
        rightMouth = new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

        //This tells the program how to paint the snake
        //This program runs till the length of the snake
        //The image mentioned in the bracket is the image of the snake's face/body.
        for(int a =0; a<lengthOfSnake; a++){

            //if right key is pressed and a = 0 i.e. the head of the snake, then right face is painted.
            if(a==0 && right){
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }

            //if left key is pressed and a = 0 i.e. the head of the snake, then left face is painted.
            if(a==0 && left){
                leftMouth = new ImageIcon("leftmouth.png"); 
                leftMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }

            //if up key is pressed and a = 0 i.e. the head of the snake, then up face is painted.
            if(a==0 && up){
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }

            //if down key is pressed and a = 0 i.e. the head of the snake, then down face is painted.
            if(a==0 && down){
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }

            //if it is not the head of the snake, then, draw the image of the snake body
            if(a!= 0){
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
            }
            
        }

        //draw apple
        appleImage = new ImageIcon("apple.png");
        appleImage.paintIcon(this, g, appleXPos[xPos], appleYPos[yPos]);

        //if the coordinate of the snake's head and that of apple matches, then the score adds, length of the snake increases and another random point for the apple is generated
        if((appleXPos[xPos] == snakeXLength[0]) && (appleYPos[yPos] == snakeYLength[0])){
            lengthOfSnake++;
            xPos = random.nextInt(34);
            yPos = random.nextInt(23);
            scores += 5;
        }

        //if the coordinates of the body of the snake and the head of the snake meet at any point, it ends the game and all the variables are restored to the initial state. 
        for(int b = 1; b<lengthOfSnake; b++){
            if((snakeXLength[b] == snakeXLength[0]) && (snakeYLength[b] == snakeYLength[0])){
                right = false;
                left = false;
                up = false;
                down = false;
                final_Score = scores;

                g.setColor(Color.white);
                
                //Message is flashed
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

        //if the right arrow key is entered
        if(right){
            //the position in the y axis of the snake
            for(int r = lengthOfSnake-1; r>=0; r--){
                snakeYLength[r+1] = snakeYLength[r];
            }

            //the position in the x axis of the snake
            for(int r = lengthOfSnake-1; r>=0; r--){
                if(r == 0)
                //if it is the head of the snake, then, the cooridnate is increased by 25
                    snakeXLength[r] = snakeXLength[r] + 25;
                else
                    snakeXLength[r] = snakeXLength[r-1];

                    //if the snake crosses the right panel, it enters again from the left panel
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
                //if it is the head of the snake, then, the cooridnate is decreased by 25
                    snakeXLength[r] = snakeXLength[r] - 25;
                else
                    snakeXLength[r] = snakeXLength[r-1];
                     //if the snake crosses the left panel, it enters again from the right panel
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

                //if it is the head of the snake, then, the cooridnate is decreased by 25
                if(r == 0)
                    snakeYLength[r] = snakeYLength[r] - 25;
                else
                    snakeYLength[r] = snakeYLength[r-1];
                     //if the snake crosses the up panel, it enters again from the down panel
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

                //if it is the head of the snake, then, the cooridnate is increased by 25
                if(r == 0)
                    snakeYLength[r] = snakeYLength[r] + 25;
                else
                    snakeYLength[r] = snakeYLength[r-1];
                     //if the snake crosses the down panel, it enters again from the up panel
                if(snakeYLength[r] > 625)
                    snakeYLength[r] = 75;
            }
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        //if space key is pressed, then restart the game
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            final_Score = 0;
            moves = 0;
            scores = 0;
            lengthOfSnake = 3;
            repaint();;
        }

        //if right key is pressed, then increase the moves, set right as true, and all others as false.
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            
            right = true;

            //but, if the left is already true, which means the snake is going in the left direction, pressing the right key won't make the snake turn right.
            //In that case, right will remain as false and left will remain as true
            if(!left)
                right = true;
            else{
                right = false;
                left = true;
            }

            up = false;
            down = false;


        }

        //Same as mentioned above
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