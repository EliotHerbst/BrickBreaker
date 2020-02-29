import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ball extends GameObj{
	int x;
	int y;
	int size;
	Color color;
	
	
	public Ball(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		color = Color.MAGENTA;
	}
	
	public Point getPosition() {
		return new Point(x, y);
		
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}


	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, size, size);
		
	}
}
