package com.bowling;

public class BowlingGame {

	private Frame initialFrame = new Frame();
	private Frame currentFrame = initialFrame;

	public void roll(int pinsDown) {
		if (isFinished()) {
			throw new IllegalArgumentException("You cheater !");
		}
		if (currentFrame.isFinished()) {
			prepareNewFrame();
		}
		currentFrame.roll(pinsDown);
	}

	private void prepareNewFrame() {
		currentFrame = new Frame(currentFrame);
	}

	public int score() {
		return initialFrame.totalScore();
	}

	public Frame currentFrame() {
		return currentFrame;
	}

	public boolean isFinished() {
		return currentFrame().isFinished() && currentFrame().isLastFrame();
	}
}
