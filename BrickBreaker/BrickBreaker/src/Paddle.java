import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Paddle extends GameObj {
	int x;
	int y;
	int width;
	int height;
	int leftBound;
	int rightBound;
	
	Color color;
	
	
	public Paddle(int x, int y, int width, int height, int leftBound, int rightBound) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		color = Color.BLACK;
		this.leftBound = leftBound;
		this.rightBound = rightBound;
	}
	
	public Point getPosition() {
		return new Point(x, y);
	}
	
	public void setX(int x) {
		this.x = x;
		if(x < leftBound) {
			this.x = leftBound;
		}
		if(x > rightBound) {
			this.x = rightBound;
		}
	}
	
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);

	}

}
