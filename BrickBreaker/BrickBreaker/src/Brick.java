import java.awt.Color;
import java.awt.Graphics;


public class Brick extends GameObj {
	int x;
	int y;
	
	int width;
	int height;
	
	boolean exists;
	
	private Color color;
	
	Color one = Color.GREEN;
	Color two =Color.BLUE;
	Color three = Color.RED;
	
	public Brick(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		color = one;
		exists = true;
	}
	
	public boolean exists() {
		return exists;
	}
	
	public void set(int n) {
		exists = false;
		if(n != 0) {
			exists = true;
		}
		if(n == 1) {
			color = three;
		}
		if(n == 2) {
			color = two;
		}
		if(n == 3) {
			color = one;
		}
	}
	
	public void updateColor() {
		if(color.equals(one)) {
			color = two;
		}
		else if(color.equals(two)) {
			color = three;
		}
		else if(color.equals(three)) {
			exists = false;
		}
	}

	@Override
	public void draw(Graphics g) {
		if(exists) {
			g.setColor(color);
			g.fillRect(x, y, width, height);
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
		}
		
	}

}
