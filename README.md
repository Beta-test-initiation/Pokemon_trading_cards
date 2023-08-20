# Pokemon Cards Game

## Introduction

This project aims to create a simpler digital version of the popular Pokemon card game using the Java programming
language. The game will feature a user-friendly interface, multiplayer game mode, and a database to store pokemon
information and statistics. The game will be developed using only Java and will not utilize any other technologies for
database implementation.

## Objective

To create a simplified version f the Pokemon card game that is beginner-friendly and easy to understand.

## Features

- Players start with a deck of arbitrary number of cards, which includes only Pokemon cards.
- Players start with a hand of 5 cards, which they draw from their deck at the beginning of the game.
- Each player starts with a basic Pokemon card in play.
- Pokemon cards have **hit points (HP)** and can attack the opponent's Pokemon cards.
- Players take turns playing cards from their hand and attacking their opponent's Pokemon.
- Players can only attack with the Pokemon card in play and they can't evolve it.
- The game ends when one player has no more Pokemon left in play or unable to draw card from the deck.
- The player who wins the game, wins the match.

## Target Audience

The target audience for this project is anyone who is interested in the Pokemon card game but is new to the game and
wants a beginner-friendly version. This project can be used by players of all ages and skill levels.

## Motivation

This project is of interest to me because I am passionate about Pokemon and I am also interested in learning and
practicing Java programming skills. This project provides an opportunity to combine my interests and gain hands-on
experience in game development and database integration. Additionally, the project gives the opportunity to share this
beginner-friendly version of the game with the community, and help more people to enjoy the game.

## Tools and Technologies

- Java programming language
- JavaFX for creating the user interface
- JDBC for storing player information and game progress

## Implementation

- Object-oriented design principles will be used to create classes such as Card, Deck, Player, and Game.
- The game rules will be implemented using if-else statements and switch-case statements.
- Random number generators will be used to simulate the drawing of cards.
- Error handling will be implemented to ensure that the game does not crash in case of unexpected inputs or errors.

## Expected Outcome

- A beginner-friendly Pokemon card game that is easy to understand and play.
- A visually appealing user interface with buttons, labels, and images to represent the cards and players.
- A game that can be saved and loaded from a database.

## User Stories

- As a new player, I want to be able to create a new game and choose my starting deck of Pokemon cards, so that I can
  begin playing the game
- As a player, I want to be able to draw a hand of 5 cards from my deck at the beginning of each game, so that I can
  start playing with a set of cards.
- As a player, I want to be able to see my opponent's Pokemon card and their remaining hit points, so that I can plan my
  attacks and strategy.
- As a player, I want to be able to play cards from my hand and attack my opponent's Pokemon, so that I can try to win
  the game.
- As a player, I want to be able to save my game progress, so that I can continue playing later.
- As a player, I want to be able to see the game rules and instructions, so that I can understand how to play the game.
- As a player, I want to be able to see my own cards and the remaining cards in my deck, so that I can plan my strategy
  and know when to shuffle my deck.
- As a player, I want to be able to see a game over screen when the game is over, so that I know whether I won or lost.
- As a player, I want to be able to save my Pokemon Cards so that I can use them in future games.
- As a player, I want to have the option to load the cards I have previously saved so that I can play with them.


# Instructions for Grader

- You can generate the first required action related to adding a new PokemonCard to a list of Pokemon Cards by clicking 
on the yes button when the screen prompts if you would like to create a new card.
- You can generate the second required action related to adding PokemonCard to Pokemon Cards List by choosing the 
Pokemon for a particular player and attacking the opponent with it.
- You can locate my visual component on the first page (welcome screen).
- You can choose to load the cards previously saved by clicking the yes button when the screen prompts you to load.
- You can save the state of my application by clicking the save button when the screen prompts you to save it.


# Phase 4: Task 2

Application is shutting down. Logging events to console...
- Mon Apr 10 13:18:32 PDT 2023
- A new Card Object has been created
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- A new Card Object has been created
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- A new Card Object has been created
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- A new Card Object has been created
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- A new Card Object has been created
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- A new Card Object has been created
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- A new Card Object has been created
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- A new Card Object has been created
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- Card deck has been shuffled
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- Card deck has been shuffled
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- Player has drawn a card
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- Player has drawn a card
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- Player has drawn a card
----------------------------
- Mon Apr 10 13:18:32 PDT 2023
- Player has successfully drawn their hand
----------------------------
- Mon Apr 10 13:18:38 PDT 2023
- A Card has been removed from hand
----------------------------
- Mon Apr 10 13:18:38 PDT 2023
- A Card has been added to Cards in Play
----------------------------
- Mon Apr 10 13:18:38 PDT 2023
- Getting current player's active pokemon
----------------------------
- Mon Apr 10 13:18:38 PDT 2023
- Getting current player's active pokemon
----------------------------
- Mon Apr 10 13:18:38 PDT 2023
- Getting current player's active pokemon
----------------------------
- Mon Apr 10 13:18:59 PDT 2023
- A Card has been removed from hand
----------------------------
- Mon Apr 10 13:18:59 PDT 2023
- A Card has been added to hand
----------------------------
- Mon Apr 10 13:18:59 PDT 2023
- First Card has been removed from Cards in Play
----------------------------
- Mon Apr 10 13:18:59 PDT 2023
- A Card has been added to Cards in Play


# Phase 4: Task 3
If I had more time, this is how I would refactor my project.
1. I would make a separate modifiable sized class that would display my cards, whether it is the saved cards frame, the
 current playing cards or loading cards being displayed. This would avoid some code duplication.
2. I would make it so that at every turn, instead of repainting the whole frame, I could just modify certain elements 
on the screen. 
3. I would also like to display the current attacks on the same screen instead of having a pop-up window
   for it to make the application flow more slowly.
4. I would also like to modify the interface to just have the save, load and create card functionality on one screen 
instead of making multiple frames so that the user can do any task of their choice. This gives the user more freedom.

