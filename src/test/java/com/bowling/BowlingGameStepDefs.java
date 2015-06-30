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

	@Then("^the score should be (\\d+)$")
	public void the_score_should_be(int arg1) throws Throwable {
		assertEquals(0, bowlingGame.score());
	}

}
