import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, KeyListener {
	static final int BOARD_WIDTH = 1200;
	static final int BOARD_LENGTH = 800;
	static final int PADDLE_WIDTH = 125;
	static final int PADDLE_LENGTH = 20;
	static final int BRICK_LENGTH = 40;
	static final int BRICK_WIDTH = 80;
	int paddleX = 525;
	int paddleY = 750;
	int ballX = 200;
	int ballY = 400;
	int ballVelocityX = 1;
	int ballVelocityY = 1;
	int paddleVelocity = 25;
	int score = 0;
	boolean initialize = true;
	int bricks[][] = new int[15][10];
	Timer t = new Timer(2, this);

	public Board() {
		t.start();
		this.addKeyListener(this);
		this.setFocusable(true);
		// INITIALIZE BRICK ARRAY
		// 1 = PRESENT, 0 = DESTROYED
		if (initialize) {
			for (int i = 0; i < bricks.length; i++) {
				for (int j = 0; j < bricks[0].length; j++) {
					bricks[i][j] = 1;
				}
			}
		}
		initialize = false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		// MAKE BACKGROUND BLACK
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_LENGTH);
		g.setColor(Color.WHITE);
		// DRAW SCORE
		g.drawString("Score: " + Integer.toString(score), 1150, 10);
		// DRAW PADDLE
		g.fillRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_LENGTH);
		// DRAW BALL
		g.fillOval(ballX, ballY, 10, 10);
		// PAINT BRICKS
		for (int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[0].length; j++) {
				if (bricks[i][j] == 1) {
					g.drawRect(i * BRICK_WIDTH, j * BRICK_LENGTH, BRICK_WIDTH, BRICK_LENGTH);
				}
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// MOVE PADDLE LEFT
		if (key == KeyEvent.VK_LEFT) {
			if (paddleX > 0) {
				paddleX -= paddleVelocity;
			}
			repaint();
		}
		// IF BALL HITS RIGHT
		if (key == KeyEvent.VK_RIGHT) {
			if (paddleX < BOARD_WIDTH - 125) {
				paddleX += paddleVelocity;
			}
			repaint();
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent arg0) {
		// MOVE BALL
		ballX += ballVelocityX;
		ballY += ballVelocityY;
		// CHANGE DIRECTION OF BALL ONCE ITS HITS THE WALLS
		if (ballX < 0 || ballX > BOARD_WIDTH - 10) {
			ballVelocityX *= -1;
		}
		// IF BALL HITS TOP
		if (ballY < 0) {
			ballVelocityY *= -1;
		}
		// IF BALL HITS PAPDLE
		for (int i = 0; i < PADDLE_WIDTH; i++) {
			if (ballY + 10 > paddleY && ballX == paddleX + i) {
				ballVelocityY *= -1;
			}
		}
		repaint();

		// IF BALL HITS FLOOR
		if (ballY > BOARD_LENGTH) {
			System.exit(0);
		}

		// IF BALL HITS BRICK
		for (int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[0].length; j++) {
				for (int p = 0; p < BRICK_WIDTH; p++) {
					if (bricks[i][j] == 1 && ballX == i * BRICK_WIDTH + p && ballY == j * BRICK_LENGTH + BRICK_LENGTH
							|| bricks[i][j] == 1 && ballX == i * BRICK_WIDTH + p && ballY == j * BRICK_LENGTH) {
						bricks[i][j] = 0;
						ballVelocityY *= -1;
						repaint();
						score++;
					}
				}
			}
		}

	}
}
