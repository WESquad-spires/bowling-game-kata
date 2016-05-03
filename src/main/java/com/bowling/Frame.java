package com.bowling;

public class Frame {

	private static final int PINS_COUNT = 10;
	private static final int MAX_FRAME_COUNT = 10;

	private Frame nextFrame;
	private final int index;
	private int[] pinsDown = new int[3];
	private int rollIndex = 0;

	public Frame(Frame previousFrame) {
		previousFrame.nextFrame = this;
		this.index = previousFrame.index + 1;
	}

	public Frame() {
		this.index = 1;
	}

	public void roll(int pinsDown) {
		this.pinsDown[rollIndex] = pinsDown;
		rollIndex++;
	}

	public boolean isFinished() {
		if (isLastFrame() && (isStrike() || isSpare())) {
			return rollCountIs(3);
		}
		return rollCountIs(2) || allPinsDown();
	}

	boolean isLastFrame() {
		return index == MAX_FRAME_COUNT;
	}

	public int score() {
		int score = pinsDownTotal();
		if (nextFrame != null) {
			if (isSpare()) {
				score += nextFrame.pinsDown[0];
			} else if (isStrike()) {
				score += nextFrame.twoRollsScore();
			}
		}
		return score;
	}

	private int twoRollsScore() {
		int score = pinsDown[0];
		if (!isStrike() || isLastFrame()) {
			score += pinsDown[1];
		} else if (nextFrame != null) {
			score += nextFrame.pinsDown[0];
		}
		return score;
	}

	private int pinsDownTotal() {
		int pinsDownTotal = 0;
		for (int pinsDown : this.pinsDown) {
			pinsDownTotal += pinsDown;
		}
		return pinsDownTotal;
	}

	public boolean isSpare() {
		return !isStrike() && pinsDown[0] + pinsDown[1] == PINS_COUNT;
	}

	public boolean isStrike() {
		return pinsDown[0] == PINS_COUNT;
	}

	private boolean allPinsDown() {
		return pinsDownTotal() == PINS_COUNT;
	}

	private boolean rollCountIs(int count) {
		return rollIndex == count;
	}

	public int totalScore() {
		int totalScore = score();
		if (nextFrame != null) {
			totalScore += nextFrame.totalScore();
		}
		return totalScore;
	}
}