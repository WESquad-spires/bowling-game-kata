package com.bowling;

public class Frame {

	private static final int MAX_SCORE = 10;
	private static final int UNDEFINED = -1;
	private final Frame previousFrame;
	private int pinsDown1 = UNDEFINED;
	private int pinsDown2 = UNDEFINED;
	private Frame nextFrame;

	public Frame(Frame previousFrame) {
		this.previousFrame = previousFrame;
		this.previousFrame.setNextFrame(this);
	}

	public Frame() {
		super();
		previousFrame = null;
	}

	public void setPinsDown(int pinsDown) {
		if (!hasRolled()) {
			pinsDown1 = pinsDown;
		} else {
			pinsDown2 = pinsDown;
		}
	}

	public boolean isFinished() {
		return hasRolledTwice() || isMaxScore();
	}

	public int score() {
		int score = roll1And2Score();
		if (isSpare()) {
			score += nextFrame.roll1Score();
		}
		if (isStrike()) {
			score += nextFrame.roll1And2Score();
		}
		return score;
	}

	private int roll1Score() {
		return !hasRolled() ? 0 : pinsDown1;
	}

	private int roll2Score() {
		return !hasRolledTwice() ? 0 : pinsDown2;
	}

	private boolean isSpare() {
		return hasRolledTwice() && isMaxScore();
	}

	private boolean isStrike() {
		return hasRolled() && !hasRolledTwice() && isMaxScore();
	}

	private boolean isMaxScore() {
		return roll1And2Score() == MAX_SCORE;
	}

	private int roll1And2Score() {
		return roll1Score() + roll2Score();
	}

	private boolean hasRolled() {
		return pinsDown1 != UNDEFINED;
	}

	private boolean hasRolledTwice() {
		return pinsDown2 != UNDEFINED;
	}

	public Frame previousFrame() {
		return previousFrame;
	}

	private void setNextFrame(Frame nextFrame) {
		this.nextFrame = nextFrame;
	}

	public Frame nextFrame() {
		return nextFrame;
	}
}