/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
//import swing because it is important if you are creating any gui application
import javax.swing.*;
//import awt for keylistener part
import java.awt.*;
import java.awt.RenderingHints.Key;
//for awt we also need event to track the keys this contain some part of keylistener
import java.awt.event.*;
//than we need arrays , it will track the snake bodies
import java.util.Arrays;
//we need random for spawning food at randomlocation;
import java.util.Random;
//now we will extend panel it with jpanel this time it will contain everything about the snake
//now frame is empty window with only title so we need component that is why we need panel change size and graphics part
public class Panel extends JPanel implements ActionListener{
    
    //setting the width and height
    static int width = 1200;
    static int height = 600;
    //now in this panel we will add some grid
    //size of each unit
    //we are making the greed so that we can track the snake and spawn the food
    static int unit = 50;
    //for checking the game of the at regular interval
    Timer timer ; //check at 160ms if game is ended if ended it will  take further action
    static int delay =160;
    
    //random variable for food spawn will provide us random no
    
    Random random;
    
    //lets make snake body
    int body = 3;//one head and two body parts, it will increase as you eat food
    char dir = 'R';
    boolean flag = false; //state of game game start it will turn to true
    //lets make constructor for this panel class
    //total no of units
    static int size = (width*height/(unit*unit));
    //x and y blocks of the snake in these arrays
    int xsnake[] = new int[size];
    int ysnake[] = new int[size];
    int fx,fy; //cordinates  foodx and foody location
    int score =0; 
    
    Panel(){ //constructor
    
        this.setPreferredSize(new Dimension(width,height)); //this will set the prefarred size of the panel//
        //set clour of the background
        this.setBackground(Color.blue);
        //tomake sure keyboard input goes to the game directly not through system
        this.setFocusable(true); // whenever the window opens it allow s the keyboard input to the game so that i can move my snake properly so that my game is focusanle
        //create the random variable 
        random = new Random();
        //add the keylistener, it adds keylistener to the panel
        this.addKeyListener(new Key()); //so that my keylistener acts according to panel input;
        game_start();
    }
    
    public void game_start(){
        //spawning the food
       spawnfood();
       //setting the game running flag to true
       flag = true; //game has started if it is true
       //starting the timer for delay
       timer = new Timer(delay,this); //timer with delay of 160ms and keep on checking the state of the game
       timer.start();
    }
    
    public void spawnfood(){
      //we need x and y cordinates of the food , i need random x and random y
      fx = random.nextInt((int)(width/unit))*unit; //it creates stores the random integer in fx 
      //we will create random varible , we need it in multiple of 50 ( so i will select it from 1200/50=24  
      // i will select from 0 to 24 and i will multiply it to 50
      fy = random.nextInt((int)(height/unit))*unit;
    }
    
    public void checkHit(){ //check if hit the body or wall if hit it will make flag false
     //checking the snake heads collision with its own body or the walls
     for(int i=body;i>0;i--){ //xsnake=x cordinate of the head/ysnake= y cordinate of the head
        if((xsnake[0]==xsnake[i]) && (ysnake[0]==ysnake[i])){ 
            flag = false;
        }
     }
     ///check hit with walls 
     if(xsnake[0]<0){
     flag = false;
     }
     if(xsnake[0]>width){
     flag= false;
     }
     if(ysnake[0]<0){
     flag = false;
     }
     if(ysnake[0]>height){
     flag= false;
     }
     if(flag==false){
       timer.stop();  
     }
    }
    
    //helper function act as a mediator bw drawing function and the panel
    //intermediate function to call the draw funtion
    public void paintComponent(Graphics graphic){ //vector shape font style colors all this contain in graphics variable
        super.paintComponent(graphic); //pain component attribut , to paint on a certain part not on whole
        draw(graphic);
      }
    
    public void draw(Graphics graphic){
        if(flag){
            //setting the parameters for the food block
            graphic.setColor(Color.red);
            graphic.fillOval(fx,fy,unit,unit); //will come inside 50-50 unit
            
            //setting params for the snake
            for(int i=0;i<body;i++){
                //for the head
                if(i==0){
                    graphic.setColor(Color.green);
                    graphic.fillRect(xsnake[i], ysnake[i], unit, unit);
                }
                //for other part
                else{
                    graphic.setColor(Color.orange);
                    graphic.fillRect(xsnake[i], ysnake[i], unit, unit);
                }
            }
            ///drawing the score part
            graphic.setColor(Color.yellow);
            graphic.setFont(new Font("Comic sans",Font.BOLD,40));
            FontMetrics f = getFontMetrics(graphic.getFont());//to access this font from computer we need this fontmatrix
            //drawstring takes the string to fraw , starting postion in x and the starting position in y
            graphic.drawString("SCORE:"+score,(width-f.stringWidth("SCORE:"+score))/2,graphic.getFont().getSize());
        }
        else{
        gameOver(graphic);
          }
        
    }
    
    
    public void gameOver(Graphics graphic){
         ///drawing the score part
            graphic.setColor(Color.red);
            graphic.setFont(new Font("Comic sans",Font.BOLD,40));
            FontMetrics f = getFontMetrics(graphic.getFont());//to access this font from computer we need this fontmatrix
            //drawstring takes the string to fraw , starting postion in x and the starting position in y
            graphic.drawString("SCORE"+score,(width-f.stringWidth("SCORE:"+score))/2,graphic.getFont().getSize());
            
            //graphics for the game over text
             
            graphic.setColor(Color.red);
            graphic.setFont(new Font("Comic sans",Font.BOLD,80));
            FontMetrics f2 = getFontMetrics(graphic.getFont());//to access this font from computer we need this fontmatrix
            //drawstring takes the string to fraw , starting postion in x and the starting position in y
            graphic.drawString("GAME OVER!"+score,(width-f2.stringWidth("GAME OVER!"+score))/2,height/2);
            
            //graphic for the replay font 
             ///drawing the score part
            graphic.setColor(Color.RED);
            graphic.setFont(new Font("Comic sans",Font.BOLD,40));
            FontMetrics f3 = getFontMetrics(graphic.getFont());//to access this font from computer we need this fontmatrix
            //drawstring takes the string to fraw , starting postion in x and the starting position in y
            graphic.drawString("PRESS R TO REPLAY"+score,(width-f3.stringWidth("PRESS R TO REPLAY"+score))/2,height/2-180);
     }
    
    public void move(){
        //updatting the body parts excepts the head
        for(int i=body;i>=0;i--){
            xsnake[i] = xsnake[i-1]; //part before head
            ysnake[i] = ysnake[i-1];
        }
        //to get the head we wil create switch case
        //for the updation head coordinates
        switch(dir){
            case 'U':
                ysnake[0]=ysnake[0]-unit;  // we are calculating with the help of x and y cordinate system bu this is opposite
                break;
            case 'D':
                ysnake[0]=ysnake[0]+unit;
                break;
            case 'L':
                xsnake[0]=xsnake[0]-unit;
                break;
            case 'R':
                xsnake[0]=xsnake[0]+unit;
                break;    
        }
    }
    
    public void checkScore(){ //if food and head is at same position(coincide) than body++ and score++
        if((fx==xsnake[0]) && (fy==ysnake[0])){
            body++;
            score++;
            spawnfood();
        }
    }
    
    
    public class Key extends KeyAdapter{
        //it allows us a code for keyboard input
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(dir!='R'){
                        dir='L';
                     }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(dir!='L'){
                        dir='R';
                     }
                    break;
                case KeyEvent.VK_UP:
                    if(dir!='D'){
                        dir='U';
                     }
                    break;
                case KeyEvent.VK_DOWN:
                    if(dir!='U'){
                        dir='D';
                     }
                    break;
                case KeyEvent.VK_R:
                    //changing everything to initial value and starting the game
                    if(!flag){          //doing this so that game dont restart in bw
                        score=0;
                        body =3;
                        dir='R';
                        Arrays.fill(xsnake, 0);
                        Arrays.fill(ysnake, 0);
                    }
                    break;
            }
        }
        
    }
    @Override
    public void actionPerformed(ActionEvent arg0){//this part we need for if game is restart(i will set an interval and i will check the state of the game 
        //check if game is running
        if(flag){
            move();
            checkScore();
            checkHit();
        }
        repaint(); //if game is not running repaint the graphics again
    }
    
}
