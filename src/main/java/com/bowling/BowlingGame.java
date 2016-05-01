package com.bowling;

public class BowlingGame {

	private Frame initialFrame = new Frame();
	private Frame currentFrame = initialFrame;

	public BowlingGame() {
		System.out.println("Initializing a new Bowling Game");
	}

	public void roll(int pinsDown) {
		currentFrame.setPinsDown(pinsDown);
		if (currentFrame.isFinished()) {
			prepareNewFrame();
		}
	}

	public int score() {
		int score = initialFrame.totalScore();
		return score;
	}

	private void prepareNewFrame() {
		currentFrame = new Frame(currentFrame);
	}
}
