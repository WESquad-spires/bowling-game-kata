package com.bowling;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BowlingGameStepDefs {

	private BowlingGame bowlingGame;

	@Given("^a bowling game$")
	public void a_bowling_game() throws Throwable {
		bowlingGame = new BowlingGame();
	}

	@When("^I haven't played yet$")
	public void i_haven_t_played_yet() throws Throwable {
	}

	@When("^I roll and (.*) pins fall$")
	public void i_roll_and_pins_fall(List<Integer> allPinsDown) throws Throwable {
		for (int pinsDown : allPinsDown) {
			bowlingGame.roll(pinsDown);
		}
	}

	@Then("^the score should be (\\d+)$")
	public void the_score_should_be(int score) throws Throwable {
		assertThat(bowlingGame.score()).isEqualTo(score);
	}

	@Then("^there is a spare$")
	public void there_is_a_spare() throws Throwable {
		assertThat(bowlingGame.currentFrame().isSpare()).isTrue();
	}
}
