Feature: Bowling game
	As a player
	In order to know my score at the end of the game
	I want to know my score after each roll
	
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