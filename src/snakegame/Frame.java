package snakegame;
import javax.swing.JFrame;
public class Frame extends JFrame{
	Frame(){
        this.add(new Panel()); //adding panel to the frame 
        this.setTitle("SnakeMania"); //adding title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
////if you open a game the window can differe from user to that is why we are setting the resizable properties to false so that user cannot resize and can work anywhere
        //so that game experiance can be same for 
        //sets the frame to a fixed size 
       this.setResizable(false);
       this.pack(); //it set this window size to prefarable size from the system setting and sets the window accordingly
       this.setVisible(true); //so that it is visible to user that is why it is true
       //if you want the frame to display on the middle of the screen , spawning in the centre of the screen\
       this.setLocationRelativeTo(null);
    }
}
