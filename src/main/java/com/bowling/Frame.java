package com.bowling;

public class Frame {

	private static final int PINS_COUNT = 10;
	private static final int MAX_FRAME_COUNT = 10;

	private final int index;
	private Frame nextFrame;
	private int[] pinsDown = new int[3];
	private int rollCount = 0;

	public Frame(Frame previousFrame) {
		previousFrame.nextFrame = this;
		index = previousFrame.index + 1;
	}

	public Frame() {
		index = 1;
	}

	public void roll(int pinsDown) {
		this.pinsDown[rollCount] = pinsDown;
		rollCount++;
	}

	public boolean isFinished() {
		if (isLastFrame() && (isStrike() || isSpare())) {
			return rollCountIs(3);
		}
		return rollCountIs(2) || allPinsDown();
	}

	public boolean isLastFrame() {
		return index == MAX_FRAME_COUNT;
	}

	public boolean isSpare() {
		return !isStrike() && firstRollScore() + secondRollScore() == PINS_COUNT;
	}

	public boolean isStrike() {
		return firstRollScore() == PINS_COUNT;
	}

	private int firstTwoRollsScore() {
		int score = firstRollScore();
		if (!isStrike() || isLastFrame()) {
			score += secondRollScore();
		} else if (hasNextFrame()) {
			score += nextFrame.firstRollScore();
		}
		return score;
	}

	private int totalPinsDown() {
		int pinsDownTotal = 0;
		for (int pinsDown : this.pinsDown) {
			pinsDownTotal += pinsDown;
		}
		return pinsDownTotal;
	}

	private boolean allPinsDown() {
		return totalPinsDown() == PINS_COUNT;
	}

	private int firstRollScore() {
		return pinsDown[0];
	}

	private int secondRollScore() {
		return pinsDown[1];
	}

	private boolean rollCountIs(int count) {
		return rollCount == count;
	}

	private boolean hasNextFrame() {
		return nextFrame != null;
	}

	public int cumulatedScore() {
		int totalScore = totalPinsDown() + bonus();
		if (hasNextFrame()) {
			totalScore += nextFrame.cumulatedScore();
		}
		return totalScore;
	}

	private int bonus() {
		int bonus = 0;
		if (hasNextFrame()) {
			if (isSpare()) {
				bonus = nextFrame.firstRollScore();
			} else if (isStrike()) {
				bonus = nextFrame.firstTwoRollsScore();
			}
		}
		return bonus;
	}
}