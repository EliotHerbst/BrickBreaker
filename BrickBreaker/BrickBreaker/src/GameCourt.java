/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {
    public static final int COURT_WIDTH = 750;
    public static final int COURT_HEIGHT = 750;
    private int ballVelocityX = 1;
    private int ballVelocityY = 1;
	private static final int INTERVAL = 5;
	
	private Brick[][] bricks;
	private Ball ball;
	private Paddle paddle;
	
	private int bricksWidth;
	
	private int ballWidth;
	private int paddleWidth;
	private int paddleHeight;	
	
	private boolean playing;
	
	
	public GameCourt() {
		super();
		init();
	}
	
	private void init() {
		this.setBackground(Color.orange);
		ballWidth = COURT_HEIGHT / 50;
		
		ball = new Ball(COURT_WIDTH / 2 - ballWidth / 2,COURT_HEIGHT / 2 - ballWidth / 2,ballWidth);
		
		paddleWidth = ballWidth * 10;
		paddleHeight = ballWidth;
		
		
		
		int x = 0;
		int y = 0;
		int difX = COURT_WIDTH / 10;
		
		int difY = COURT_HEIGHT / 24;
		
		bricksWidth = difX * 9;
		
		bricks = new Brick[6][10];
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 10; j++) {
				bricks[i][j] = new Brick(x, y, difX, difY);
				x += difX;
			}
			x = 0;
			y += difY;
		}
		playing = false;
		
		paddle = new Paddle(COURT_WIDTH / 2 - (ballWidth * 10) / 2, COURT_HEIGHT * 9 / 10 - ballWidth, ballWidth * 10, ballWidth,  0, bricksWidth - 2 * paddleWidth/4 );
		
		Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

		
	}
	
	public void setBricks(int[] arr) {
		int n = 0;
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 10; j++) {
				bricks[i][j].set(arr[n]);
				n++;
			}
		}
	}
	
	public void setBall(Point position) {
		ball = new Ball(position.x, position.y, ballWidth);
	}
	
	public void reset() {
		playing = false;
		ball = new Ball(COURT_WIDTH / 2 - ballWidth / 2,COURT_HEIGHT / 2 - ballWidth / 2,ballWidth);
		int x = 0;
		int y = 0;
		int difX = COURT_WIDTH / 10;
		
		int difY = COURT_HEIGHT / 24;
		
		bricksWidth = difX * 9;
		
		bricks = new Brick[6][10];
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 10; j++) {
				bricks[i][j] = new Brick(x, y, difX, difY);
				x += difX;
			}
			x = 0;
			y += difY;
		}
		paddle = new Paddle(COURT_WIDTH / 2 - (ballWidth * 10) / 2, COURT_HEIGHT * 9 / 10 - ballWidth, ballWidth * 10, ballWidth,  0, bricksWidth - 2 * paddleWidth/4 );

	}
	
	public void startPlaying() {
		if(!playing) {
			playing = true;
		}
		else {
			playing = false;
		}
	}
	
	 /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
    	if(!playing) {
    		return;
    	}
    	
    	//Update Paddle Position
    	Point p = MouseInfo.getPointerInfo().getLocation();
    	Point l = getLocationOnScreen();
    	int locX = l.x;
    	int x = p.x;
    	paddle.setX(x - locX - (COURT_WIDTH / 2 - (ballWidth * 10) / 2) / 4);
    	
    	//Move Ball

    	checkBallCollision();
    	checkPaddleCollision();
    	Point pos = ball.getPosition();
    	ball.setPosition(pos.x + ballVelocityX, pos.y + ballVelocityY);

    	
    	
            repaint();
    }
	
	private void checkPaddleCollision() {
		Point pos = paddle.getPosition();
		int x = pos.x;
		int y = pos.y;
		
		Point bal = ball.getPosition();
		int ballX = bal.x;
		int ballY = bal.y;
		
		if(ballX >= x - ballWidth/2 && ballX <= x + paddleWidth + ballWidth/2 && ballY + ballWidth/2 >= y && ballY + ballWidth/2 < y + paddleHeight) {
			ballVelocityY *= -1;
		}
		
	}

	private void checkBallCollision() {
		Point pos = ball.getPosition();
		int x = pos.x;
		int y = pos.y;
		
		int brickHeight = COURT_HEIGHT / 24;
		int brickWidth = COURT_WIDTH / 10;
		
		if(y - ballWidth /4 >= COURT_HEIGHT) {
			ballVelocityY *= -1;
		}
		if(x - ballWidth / 4 >= bricksWidth) {
			ballVelocityX *= -1;
		}
		
		if(x + ballWidth /4 <= 0) {
			ballVelocityX *= -1;
		}
		
		int row = x / brickWidth;
		if(row > 9) {
			row = 9;
		}
		for(int i = 0; i < 6; i++) {
			if(y - ballWidth / 4 < brickHeight + (brickHeight * i +1)) {
				Brick b = bricks[i][row];
				if(b.exists()) {
					ballVelocityY *= -1;
					b.updateColor();
					return;
				}
				else {
					if(row != 0) {
						Brick c = bricks[i][row -1];
						if(c.exists && x - ballWidth/2 <= brickWidth * (row)) {
							System.out.println("yes");
							ballVelocityX *= -1;
							ballVelocityY *= -1;
							c.updateColor();
							return;
						}
					}
					if(row != 9) {
						Brick c = bricks[i][row+1];
						if(c.exists && x + ballWidth/2 >= brickWidth * (row + 1)) {
							System.out.println("yes");
							ballVelocityX *= -1;
							ballVelocityY *= -1;
							c.updateColor();
							return;
						}
					}
				}
			}
		}
		
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ball.draw(g);
		paddle.draw(g);
		for(int i = 0; i < bricks.length; i++) {
			for(int j = 0; j < bricks[0].length; j++) {
				bricks[i][j].draw(g);
			}
		}
	}
}