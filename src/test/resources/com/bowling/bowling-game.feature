Feature: Compute a bowling game score for one player
  As a bowling game player
  In order to know if I'm the best
  I want to compute my score
  
  Background: 
    Given a new bowling game

  Scenario: Score is zero at the beginning of the game
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

  Scenario: There are only 10 pins to hit
    When I roll and 11 pins fall
    Then following error is thrown: "Where did this one came from ?!"

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

  Scenario Outline: After a strike, next two rolls counts twice
    When I roll and <pins down> pins fall
    Then the score should be <expected score>
    Examples:
    	| pins down			| expected score	|
    	| 10,4,2			| 22				|
    	| 4,3,10,6,2		| 33				|
    	| 4,3,10,10,6,2		| 59				|
    	| 4,3,10,10,10,6,2	| 89				|

  Scenario: A game is 10 frames
    When I roll and 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 pins fall
    Then the game is finished

  Scenario: Play more than 10 frames is not allowed !
    When I roll and 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 pins fall
    Then following error is thrown: "You cheater !"

  Scenario Outline: Last frame has 1 bonus roll if player makes a spare
    When I roll and <pins down> pins fall
    Then the score should be <expected score>
    Examples:
    	| pins down										| expected score	|
    	| 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,8,5		| 15				|
    	| 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,8,10	| 20				|

  Scenario Outline: Last frame has 2 bonus rolls if player makes a strike
    When I roll and <pins down> pins fall
    Then the score should be <expected score>
    Examples:
    	| pins down										| expected score	|
    	| 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,2,2	| 14				|
    	| 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,10,2	| 22				|
    	| 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,10,10,10	| 30				|
    	| 10,10,10,10,10,10,10,10,10,10,10,10			| 300				|
