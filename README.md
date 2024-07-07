# Mancala Game

Simple overview of use/purpose.

## Description
A text-based version recreation of the classic Mancala board game in Java. It provides a command-line interface for playing the game between two players. The game follows the standard rules of Mancala and provides a comprehensive set of features, including stone movement, capturing, and winning conditions.

# Objective:
The goal of the game is to capture more stones than your opponent.

# Setup:
Mancala is played on a board with two rows of six small pits (also called houses) and two large pits at the ends, which are called the "stores". Each player controls the six pits closest to them.

At the beginning of the game, each of the small pits contains a certain number of stones. Common starting configurations are 3, 4, or 5 stones per pit.

# Gameplay:
Turns: Players take turns to play. The player who starts picks any of their pits and scoops up all the stones from that pit. They then distribute the stones, placing one in each pit anti-clockwise, skipping their opponent's store.

Store: If the last stone lands in the player's store, they get another turn. This encourages players to aim for their own store.

Empty Pit: If the last stone is dropped into an empty pit on the player's side, they capture that stone and all the stones in the opposite pit (the pit directly opposite on the opponent's side). All captured stones go into the player's store.

Winning: The game ends when all six pits on one player's side are empty. The remaining stones on the opponent's side are captured by the player who still has stones. The player with the most stones in their store wins the game.

# Additional Rules:
Empty Side: If a player's side is empty at the start of their turn, the opponent captures all remaining stones on their side.

Variations: Different regions and cultures have variations of the game with additional rules or different starting setups.

# Strategy Tips:
Sow Strategically: Think carefully about which pit to choose for your move. Consider the current game state and anticipate future moves.

Capture Stones: Aim to create situations where you can capture stones from your opponent's side. This not only gives you more stones but also denies your opponent.

Watch Your Store: Keep an eye on your store and your opponent's store. Making strategic moves to fill your store can give you extra turns.

Plan Ahead: Anticipate your opponent's moves and plan your moves accordingly. Think a few steps ahead to set up advantageous positions.

Mancala is a game of skill and strategy, so with practice, you can improve your gameplay and develop effective strategies. Enjoy playing!

## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program.
You need gradle to run the program.

### Executing program

* How to build and run the program
* Step-by-step bullets
```
use code blocks for commands:
gradle build
gradle echo

```

* Sample Game:
```

```
## Limitations

What isn't done? What things cause errors?  

## Author Information

Rylee Glover
1222174
rglove02@uoguelph.ca

## Development History

Keep a log of what things you accomplish when.  You can use git's tagging feature to tag the versions or you can reference commits.

18 Nov, 2023 - 4 commit
- Adding overview to README
- Updating README and TextUI
- Deleting extra files
- Updating README, Test Doc and fixing connection between stores and players

16 Nov, 2023 - 2 commits
- Updating code for Tests and adding tests
- Adding and fixing code for tests

15 Nov, 2023 - 3 commits
- Fixing code for test cases
- Updating file structure
- Updating file structure

07 Nov, 2023 - 1 commit
- Uploading files

## Acknowledgments



