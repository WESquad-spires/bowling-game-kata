Feature: Bowling game
	As a player
	In order to know my score at the end of the game
	I want to know my score after each roll
	
	Background:
		Given a bowling game
	
	Scenario: Score must be equal to zero at the beginning of the game
		When I haven't played yet
		Then the score should be 0
	
	Scenario: Score after one simple roll without strike nor spare
		When I roll and knock down 3 and 4 pins
		Then the score should be 7