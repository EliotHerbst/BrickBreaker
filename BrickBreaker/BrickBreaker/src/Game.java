/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	JButton reset;
	JButton save;
	JButton load;
	GameCourt court;
	
    public void run() {
    	
        final JFrame frame = new JFrame("Brick Breaker");
        frame.setSize(1000, 1000);


    	//frame.setLayout(new GridLayout(1,1));
        // Main playing area
        court = new GameCourt();
        Dimension size = new Dimension(500, 750);
        court.setMinimumSize(size);
        frame.add(court, BorderLayout.CENTER);
        
        court.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent m) { 
                courtClicked(); 
              } 
            });
        
        JPanel buttons = new JPanel();
        buttons.setBackground(Color.WHITE);
        buttons.setLayout(new GridLayout(3,1));
        
        reset = new JButton("Reset");
        buttons.add(reset);
        reset.setBorderPainted(false);
        reset.setBackground(Color.GRAY);
        reset.setForeground(Color.BLACK);
        reset.setFocusPainted(false); 
        reset.getModel().addChangeListener((ChangeEvent e) -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover()) {
               resetRollOver(); 
            } 
            else {
            	resetNotRollOver();
            }
            if(model.isPressed()) {
            	resetClicked();
            }
        }); 
        save = new JButton("Save");
        buttons.add(save);
        save.setBorderPainted(false);
        save.setBackground(Color.GRAY);
        save.setForeground(Color.BLACK);
        save.setFocusPainted(false); 
        save.getModel().addChangeListener((ChangeEvent e) -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover()) {
               saveRollOver(); 
            } 
            else {
            	saveNotRollOver();
            }
            if(model.isPressed()) {
            	saveClicked();
            }
        }); 
        
        load = new JButton("Load");
        buttons.add(load);
        load.setBorderPainted(false);
        load.setBackground(Color.GRAY);
        load.setForeground(Color.BLACK);
        load.setFocusPainted(false); 
       // load.addActionListener()
        load.getModel().addChangeListener((ChangeEvent e) -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover()) {
               loadRollOver(); 
            } 
            else {
            	loadNotRollOver();
            }
            if(model.isPressed()) {
            	loadClicked();
            }
        }); 
        frame.add(buttons, BorderLayout.WEST);


        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.

        // Put the frame on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    
    private void courtClicked() {
    	court.startPlaying();
    }
    
    private void loadClicked() {
    	courtClicked();
    	System.out.println("load");
    	SaveFileWriter.load(court);
    }
    
    private void loadNotRollOver() {
    	load.setBackground(Color.GRAY);
    }
    private void loadRollOver() {
    	load.setBackground(Color.CYAN);
    }
    
    private void saveClicked() {
    	courtClicked();
    	
    }
    
    private void saveNotRollOver() {
    	save.setBackground(Color.GRAY);
    }
    private void saveRollOver() {
    	save.setBackground(Color.CYAN);
    }
    
    private void resetClicked() {
    	court.reset();
    }
    
    private void resetNotRollOver() {
    	reset.setBackground(Color.GRAY);
    }
    private void resetRollOver() {
    	reset.setBackground(Color.CYAN);
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}