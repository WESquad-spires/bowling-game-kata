package com.bowling;

public class BowlingGame {

	private Frame initialFrame = new Frame();
	private Frame currentFrame = initialFrame;

	public void roll(int pinsDown) {
		if (currentFrame.isFinished()) {
			prepareNewFrame();
		}
		currentFrame.setPinsDown(pinsDown);
	}

	public int score() {
		int score = initialFrame.totalScore();
		return score;
	}

	private void prepareNewFrame() {
		currentFrame = new Frame(currentFrame);
	}

	public Frame currentFrame() {
		return currentFrame;
	}
}
