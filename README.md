# ConsoleMineSweeper
My take on a console version of the classic compute game minesweeeper equiped with all the feautres (minus flagging potential mines) you would expect from mine sweeper including saving high scores, ability to select number of mines and map size (limited to 10x10 for readabilities sake).

TO DO: 
- Pull input handling into it's own class
- sperate GridSingleton into Grid and GridSingleton where Grid singleton just contains a reference to the Grid instance
- change the API so that it no longer stores game state and instead Grid stores game state
- switch error handling from Optional Emptys back to try catch blocks (reduces performance but increases usability for front end and provides better feedback to frontend regarding exception types)
- update Main so that the all function utilising user inputs are called utilising the one submit input function to make the API higher level and less manual
- In Cell class remove cells reference to GridSingleton, assign the cells array of neighbors from grid instead of from within cell therefore making the class more loosley coupled to grid and more modular 
- bring logic changes over to the graphicalMineSweeper version
- See graphicalMineSweeper all further improvements will be made to this repository in order to make a more accurate re-creation
