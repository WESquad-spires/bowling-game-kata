package com.bowling;

public class BowlingGame {

	private Frame currentFrame = new Frame();

	public void roll(int pinsDown) {
		currentFrame.setPinsDown(pinsDown);
		if (currentFrame.isFinished()) {
			prepareNewFrame();
		}
	}

	public int score() {
		int score = 0;

		Frame frame = currentFrame;
		while (frame != null) {
			score += frame.score();
			frame = frame.previousFrame();
		}
		return score;
	}

	private void prepareNewFrame() {
		currentFrame = new Frame(currentFrame);
	}
}
