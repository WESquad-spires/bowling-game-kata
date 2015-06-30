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

  Scenario: Score after one roll
    When I roll and 3 pins fall
    Then the score should be 3

  Scenario: Score after two rolls
    When I roll and 3 pins fall
    And I roll and 6 pins fall
    Then the score should be 9

  Scenario: Score after three rolls
    When I roll and 3 pins fall
    And I roll and 6 pins fall
    And I roll and 4 pins fall
    Then the score should be 13

  Scenario: Score after a spare and then one roll
    When I roll and 4 pins fall
    And I roll and 6 pins fall
    And I roll and 4 pins fall
    Then the score should be 18

  Scenario: Score after a spare and then tows rolls, but not a new spare
    When I roll and 4 pins fall
    And I roll and 6 pins fall
    And I roll and 4 pins fall
    And I roll and 2 pins fall
    Then the score should be 20

  Scenario: Score after one strike and then two rolls, but not a spare
    When I roll and 10 pins fall
    And I roll and 1 pins fall
    And I roll and 3 pins fall
    Then the score should be 18

  Scenario: Score after two strikes and then one roll
    When I roll and 10 pins fall
    And I roll and 10 pins fall
    And I roll and 3 pins fall
    Then the score should be 36

  Scenario Outline: Score after a specific serie of rolls
    When I roll and <pins down> pins fall
    Then the score should be <expected score>
    
    Examples:
    	| pins down									| expected score	|
    	| 3											| 3					|
    	| 3,6										| 9					|
    	| 3,6,4										| 13				|
    	| 4,6,4										| 18				|
    	| 4,6,4,2									| 20				|
    	| 10,1,3									| 18				|
    	| 10,10,3									| 36				|
    	| 1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1	| 110				|
    	| 10,10,10,10,10,10,10,10,10,10,10,10		| 300				|
