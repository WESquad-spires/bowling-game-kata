package com.bowling;

import static java.lang.Math.min;

public class Frame {

	private static final int MAX_INNER_SCORE = 10;
	private static final int LAST_FRAME_INDEX = 10;
	private static final int MAX_TOTAL_SCORE = 30;
	private static final int UNDEFINED = -1;
	private final int[] pinsDown = new int[] { UNDEFINED, UNDEFINED, UNDEFINED };
	private Frame nextFrame;
	private final int index;

	public Frame(Frame previousFrame) {
		previousFrame.nextFrame = this;
		this.index = previousFrame.index + 1;
	}

	public Frame() {
		super();
		this.index = 1;
	}

	public void setPinsDown(int pinsDown) {
		int rollIndex = nextRollIndex();
		this.pinsDown[rollIndex] = pinsDown;
	}

	private int nextRollIndex() {
		if (!hasRolled()) {
			return 0;
		} else if (!hasRolledTwice()) {
			return 1;
		} else {
			return 2;
		}
	}

	public boolean isFinished() {
		return isLastFrame() ? hasRolledThreeTimes() : hasRolledTwice() || isMaxScore();
	}

	private boolean isLastFrame() {
		return index == LAST_FRAME_INDEX;
	}

	public int score() {
		int score = innerScore();
		if (nextFrame != null) {
			if (isSpare()) {
				score += nextFrame.roll1Score();
			} else if (isStrike()) {
				score += nextFrame.score();
			}
		}
		score = min(score, MAX_TOTAL_SCORE);
		return score;
	}

	private int innerScore() {
		return roll1Score() + roll2Score() + roll3Score();
	}

	private int roll1Score() {
		return !hasRolled() ? 0 : pinsDown[0];
	}

	private int roll2Score() {
		return !hasRolledTwice() ? 0 : pinsDown[1];
	}

	private int roll3Score() {
		return !hasRolledThreeTimes() ? 0 : pinsDown[2];
	}

	public boolean isSpare() {
		return hasRolledTwice() && isMaxScore();
	}

	public boolean isStrike() {
		return hasRolled() && !hasRolledTwice() && isMaxScore();
	}

	private boolean isMaxScore() {
		return innerScore() == MAX_INNER_SCORE;
	}

	private boolean hasRolled() {
		return pinsDown[0] != UNDEFINED;
	}

	private boolean hasRolledTwice() {
		return pinsDown[1] != UNDEFINED;
	}

	private boolean hasRolledThreeTimes() {
		return pinsDown[2] != UNDEFINED;
	}

	public int index() {
		return index;
	}

	public int totalScore() {
		int totalScore = score();
		if (nextFrame != null) {
			totalScore += nextFrame.totalScore();
		}
		return totalScore;
	}
}