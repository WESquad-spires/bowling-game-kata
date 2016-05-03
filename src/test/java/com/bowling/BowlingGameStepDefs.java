package com.bowling;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BowlingGameStepDefs {

	private BowlingGame bowlingGame;

	@Given("^a new bowling game$")
	public void a_new_bowling_game() {
		bowlingGame = new BowlingGame();
	}

	@When("^I haven't played yet$")
	public void i_haven_t_played_yet() {
	}

	@When("^I roll and (.*) pins fall$")
	public void i_roll_and_pins_fall(List<Integer> allPinsDown) {
		for (int pinsDown : allPinsDown) {
			bowlingGame.roll(pinsDown);
		}
	}

	@Then("^the score should be (\\d+)$")
	public void the_score_should_be(int score) {
		assertThat(bowlingGame.score()).isEqualTo(score);
	}

	@Then("^there is a spare$")
	public void there_is_a_spare() {
		assertThat(bowlingGame.currentFrame().isSpare()).isTrue();
	}

	@Then("^there is a strike$")
	public void there_is_a_strike() {
		assertThat(bowlingGame.currentFrame().isStrike()).isTrue();
	}

	@Then("^the game is finished$")
	public void the_game_is_finished() throws Throwable {
		assertThat(bowlingGame.isFinished()).isTrue();
	}
}
