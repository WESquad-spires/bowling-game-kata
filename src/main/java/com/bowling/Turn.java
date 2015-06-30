package com.bowling;

public class Turn {

	private static final int MAX_SCORE = 10;
	private static final int UNDEFINED = -1;
	private final Turn previousTurn;
	private int pinsDown1 = UNDEFINED;
	private int pinsDown2 = UNDEFINED;

	public Turn(Turn previousTurn) {
		this.previousTurn = previousTurn;
	}

	public Turn() {
		this(null);
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
		return getRoll1Score() + getRoll2Score();
	}

	private int getRoll1Score() {
		int score = !hasRolled() ? 0 : pinsDown1;
		if (shouldDoubleRoll1()) {
			score += score;
		}
		return score;
	}

	private int getRoll2Score() {
		int score = !hasRolledTwice() ? 0 : pinsDown2;
		if (shouldDoubleRoll2()) {
			score += score;
		}
		return score;
	}

	private boolean shouldDoubleRoll1() {
		return previousTurn != null && (previousTurn.isSpare() || previousTurn.isStrike());
	}

	private boolean shouldDoubleRoll2() {
		return previousTurn != null && previousTurn.isStrike();
	}

	private boolean isSpare() {
		return hasRolledTwice() && isMaxScore();
	}

	private boolean isStrike() {
		return hasRolled() && !hasRolledTwice() && isMaxScore();
	}

	private boolean isMaxScore() {
		return score() == MAX_SCORE;
	}

	private boolean hasRolled() {
		return pinsDown1 != UNDEFINED;
	}

	private boolean hasRolledTwice() {
		return pinsDown2 != UNDEFINED;
	}

	public Turn previousTurn() {
		return previousTurn;
	}
}