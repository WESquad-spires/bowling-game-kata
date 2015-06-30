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
		if (pinsDown1 == UNDEFINED) {
			this.pinsDown1 = pinsDown;
		} else {
			this.pinsDown2 = pinsDown;
		}
	}

	public boolean isFinished() {
		return pinsDown2 != UNDEFINED || score() == MAX_SCORE;
	}

	public int score() {
		return getRoll1Score() + getRoll2Score();
	}

	public int getRoll1Score() {
		int score = pinsDown1 == UNDEFINED ? 0 : pinsDown1;
		if (previousTurn != null && previousTurn.isSpare()) {
			score += score;
		}
		return score;
	}

	private boolean isSpare() {
		return pinsDown2 != UNDEFINED && score() == MAX_SCORE;
	}

	public int getRoll2Score() {
		return pinsDown2 == UNDEFINED ? 0 : pinsDown2;
	}

	public Turn previousTurn() {
		return previousTurn;
	}
}