package com.bowling;

import static org.junit.Assert.assertEquals;
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

	@When("^I roll and knock down (\\d+) and (\\d+) pins$")
	public void i_roll_and_knock_down_pins(int pinsDown1, int pinsDown2) throws Throwable {
		bowlingGame.roll(pinsDown1);
		bowlingGame.roll(pinsDown2);
	}

	@Then("^the score should be (\\d+)$")
	public void the_score_should_be(int score) throws Throwable {
		assertEquals(score, bowlingGame.score());
	}

}
