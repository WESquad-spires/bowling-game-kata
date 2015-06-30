Feature: Bowling game
	As a player
	In order to know my score at the end of the game
	I want to know my score after each roll
	
	Rule 1 : a turn is over when all pins are down, or if players rolled 2 times
	Rule 2 : a spare is when all pins are down after 2 rolls
	Rule 3 : if previous turn was a spare, first roll points are multiplied by 2
	Rule 4 : a strike is when all pins are down at the first roll of the turn
	Rule 5 : if previous turn was a strike, next turn points are multiplied by 2
	
	Background:
		Given a bowling game
	
	Scenario: Score must be equal to zero at the beginning of the game
		When I haven't played yet
		Then the score should be 0
	
	Scenario: Score after one roll
		When I roll and 3 pins fall
		Then the score should be 3
	
	Scenario: Score after two rolls
		When I roll and 3 pins fall
		And I roll and 6 pins fall
		Then the score should be 9
	
	Scenario: Score after three rolls without spare
		When I roll and 3 pins fall
		And I roll and 6 pins fall
		And I roll and 4 pins fall
		Then the score should be 13