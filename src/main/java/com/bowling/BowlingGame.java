package com.bowling;


public class BowlingGame {

	private Turn currentTurn = new Turn();

	public void roll(int pinsDown) {
		currentTurn.setPinsDown(pinsDown);
		if (currentTurn.isFinished()) {
			prepareNewTurn();
		}
	}

	public int score() {
		int score = 0;

		Turn turn = currentTurn;
		while (turn != null) {
			score += turn.score();
			turn = turn.previousTurn();
		}
		return score;
	}

	private void prepareNewTurn() {
		currentTurn = new Turn(currentTurn);
	}
}
