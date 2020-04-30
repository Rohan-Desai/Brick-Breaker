import javax.swing.JFrame;

public class Main extends JFrame {
	static final int FRAME_PADDING = 20;

	public static void main(String[] args) {
		Board c = new Board();
		JFrame f = new JFrame();
		f.add(c);
		f.setVisible(true);
		f.setFocusable(false);
		f.setSize(Board.BOARD_WIDTH + FRAME_PADDING, Board.BOARD_LENGTH + FRAME_PADDING * 2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Brick Breaker");
	}
}
