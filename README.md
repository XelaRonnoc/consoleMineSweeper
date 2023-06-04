# ConsoleMineSweeper

## Requirements and Purpose

### MVP:

-   Recreate a simplified version of the game Minesweeper to be played in the java console
-   The game should be able to randomly generate 10 mines in a 10x10 grid

    -   Grid will be represented by a 2 dimensional array (or array like structure)

-   The user will be able to enter a command that represents a coordinate to check a location for a mine

    -   (e.g "00" or "19" or "X: 0, Y: 9")

-   After every guess the application should "redraw" the 2d grid. Releaving either a number from 0-8 depending on how many mines surround that location (based on the cooridnate)
-   If the user selects a mine, the game will respond "boom!" and the game will be lost
-   Game over screen and exit the application

-   If every non-mine square has been revealed, the game is won
-   Render the grid to the console after every user command

-   Allow for the user to configure number of mines and grid size via a configuration.json file OR command line arguments
-   Keep track of win/loss in a file
-   Discovering an empty square should reveal all squares around it, and cascade into other nearby empty squares

### Purpose

The purpose of this project was to improve my skill as a Java program in an enjoyable way by building a simple game that could be exapanded signidicantly down the track. Additionally, this project aimmed to revise the OOP principles and Practices I had learnt in University and help apply these principles to a project outside of uni allowing me to hone and develop these within the project with each iteration. This Project was an easier starting point to develop the majority of the game logic required for the more difficult Graphical mine sweeper project (https://github.com/XelaRonnoc/graphicalMineSweeper)

### Stack

This project is a Java project utilising JUnit for unit testing. This was chosen as the objective was to improve both my Java and my OOP. Building a game is a natural way to develop OOP skills due to the nature of games lending them to the practices of OOP. Developing this project with a CLI initially let me work on the desing and logic of the project without having to worry about any additionaly combersom imports that come with utilising JFrame and JPanel to make a GUI version.

## Design Goals

-   To produce a CLI mine sweeper game that provides a classic and intuitive minesweeper expereince except in the console and set the foundation for a GUI iteration of the project in the future.

-   Additionally the goal of building this application utilising OOP was to allow for a more modular application that was easily able to be expanded upon as well as tested efffectivley. Ontop of this, this project is the first of two. The second being a GUI mine sweeper game that implements much of the same logic and functionality. As such I made this CLI game with this GUI project in mind. This made OOP the ovbvious approach as it allowed me to adapt much of the inner functionality of the game with minimal changes, only adapting pieces for the graphical interface and changing some tests to work with this within that later project.

## Features

-   A CLI menu start and end menu:
    -   allows players to set difficulty, restart the game and exit the program all from the GUI
-   A CLI main gameplay loop:
    -   providing a simple and easy to understand method of interacting with the game board simply typing in the coordinates of the cell you wish to reveal.
-   Auto Save to file:
    -   Allowing you to keep track of your best games!
-   Cascading reveals:
    -   Like the original, click on a clear section of mines... and boom! A whole section of the map opens up
-   Randomly placed Mines:
    -   So no two games are alike!
-   Set your map to your liking:
    -   with a maximum size of 10x10 and any number of mines you want, the expereince is truly customisable to your desires.

## Known Issues

    TODO:

## Future Goals

-   All future improvements will be put towards the GUI minesweeper project

## Challenges Faced

-   Maintaining loose coupling between cell and grid objects was somewhat difficult especially in regard to maintaining performance on larger maps during a large cascade. Which due to the significant number of Cells having to be checked each click utilising a recursive function made this quite slow. This was not so much an issue with CLI but was obvious it would be an issue for the GUI implementation
    -   This was overcome by ensuring that upon game load all the intensive checking of cells for bomb locations was already done, meaning that upon click a cell was already aware if it could cascade or not and as such this would allow the recursive function to avoid excessive nested loops of it's own, instead having this completed in the setup stage of the application also allowing for the cells to remain unaware of the grid object.

## Related projects

-   Graphical Mine Sweeper, check out it's repo to see the the intial GUI implementation of this game https://github.com/XelaRonnoc/graphicalMineSweeper
