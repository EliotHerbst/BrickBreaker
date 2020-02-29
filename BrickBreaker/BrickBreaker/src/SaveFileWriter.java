import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SaveFileWriter {
	private static String fileLocation = "files/Save.txt";
	
	public static void load(GameCourt g) {
		String file = "";
		try {
			BufferedReader  br = new BufferedReader(new FileReader(fileLocation));
			String s = br.readLine();
			while(s != null) {
				file += s;
				s = br.readLine();
			}
			br.close();
		} catch(IOException e) {
			
		}
		String ballPos = file.substring(0, file.indexOf(";"));
		file = file.substring(0, file.indexOf((";") +1));
		int ballX = Integer.parseInt(ballPos.substring(0, ballPos.indexOf(",")));
		int ballY = Integer.parseInt(ballPos.substring(ballPos.indexOf("," + 1)));
		
		g.setBall(new Point(ballX,ballY));
		String bLength = file.substring(0, file.indexOf(";"));
		int length = Integer.parseInt(bLength);
		file = file.substring(0, file.indexOf(";")+1);
		
		
		int[] arr = new int[length];
		
		for(int i = 0; i < length; i++ ) {
			String s = file.substring(0, file.indexOf(";"));
			int n = Integer.parseInt(s);
			arr[i] = n;
			
			file = file.substring(0, file.indexOf(";"));
		}
		g.setBricks(arr);
		
		System.out.println("done");
		
	}
}
