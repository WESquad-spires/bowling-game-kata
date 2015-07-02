package com.bowling;

public class BowlingGame {

	private Frame currentFrame = new Frame();

	public BowlingGame() {
		System.out.println("Initializing a new Bowling Game");
	}

	public void roll(int pinsDown) {
		currentFrame.setPinsDown(pinsDown);
		if (currentFrame.isFinished()) {
			prepareNewFrame();
		} else {
			System.out.println("Game is finished !");
		}
	}

	public int score() {
		int score = 0;

		Frame frame = currentFrame;
		while (frame != null) {
			int frameScore = frame.score();
			score += frameScore;
			System.out.println("Frame " + frame.index() + " score : " + frameScore + " ; Total=" + score);
			frame = frame.previousFrame();
		}
		return score;
	}

	private void prepareNewFrame() {
		currentFrame = new Frame(currentFrame);
	}
}
