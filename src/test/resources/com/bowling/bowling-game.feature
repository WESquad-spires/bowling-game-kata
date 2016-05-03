Feature: Bowling game
  As a player
  In order to know my score at the end of the game
  I want to know my score after each roll
  
  Rule 1 : a frame is over when all pins are down, or if players rolled 2 times
  Rule 2 : a spare is when all pins are down after 2 rolls
  Rule 3 : if previous frame was a spare, first roll points are multiplied by 2
  Rule 4 : a strike is when all pins are down at the first roll of the frame
  Rule 5 : if previous frame was a strike, next frame points are multiplied by 2
  Rule 6 : the game is finished after 10 frames

  Background: 
    Given a bowling game

  Scenario: Score must be equal to zero at the beginning of the game
    When I haven't played yet
    Then the score should be 0

  Scenario Outline: Score is the sum of pins down
    When I roll and <pins down> pins fall
    Then the score should be <expected score>
    Examples:
    	| pins down	| expected score	|
    	| 3			| 3					|
    	| 3,6		| 9					|
    	| 3,6,4		| 13				|

  Scenario: 10 pins down in two rolls is called a spare
    When I roll and 4 pins fall
    And I roll and 6 pins fall
    Then there is a spare

  Scenario Outline: After a spare, next roll counts twice
    When I roll and <pins down> pins fall
    Then the score should be <expected score>
    Examples:
    	| pins down		| expected score	|
    	| 4,6,4,2		| 20				|
    	| 4,3,4,6,6,2	| 31				|

  Scenario: 10 pins down in one rolls is called a strike
    When I roll and 10 pins fall
    Then there is a strike

  Scenario Outline: After a strike next frame counts twice
    When I roll and <pins down> pins fall
    Then the score should be <expected score>
    Examples:
    	| pins down		| expected score	|
    	| 10,4,2		| 22				|
    	| 4,3,10,6,2	| 33				|

  Scenario: Score after one strike and then two rolls, but not a spare
    When I roll and 10 pins fall
    And I roll and 1 pins fall
    And I roll and 3 pins fall
    Then the score should be 18

  Scenario: Score after two strikes and neither spare nor strike frame
    When I roll and 10 pins fall
    And I roll and 10 pins fall
    And I roll and 3 pins fall
    And I roll and 0 pins fall
    Then the score should be 39

  Scenario: Score after three strikes and neither spare nor strike frame
    When I roll and 10 pins fall
    And I roll and 10 pins fall
    And I roll and 10 pins fall
    And I roll and 3 pins fall
    And I roll and 0 pins fall
    Then the score should be 69

  Scenario Outline: Score after a specific serie of rolls
    When I roll and <pins down> pins fall
    Then the score should be <expected score>
    
    Examples:
    	| pins down									| expected score	|
    	| 4,6,4										| 18				|
    	| 4,6,4,2									| 20				|
    	| 10,1,3									| 18				|
    	| 10,10,3,0									| 39				|
    	| 1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1	| 110				|
    	| 10,10,10,10,10,10,10,10,10,10,10,10		| 300				|
