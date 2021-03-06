import cs1.Keyboard;
import java.io.*;
import java.util.*;

public class Woo{

    Board woah;
    Player player1, player2;
    int totalTurns=0;
    static String stats="";
    
    public void gameIntro(){
	System.out.println("\n====================================================================\n");
	System.out.println("TAK: A BEAUTIFUL GAME \nbased on the KingKillerChronicles by Patrick Rothfuss\n(literally the greatest series ever written)");
	String[] quotes = {"To play a beautiful game or to not play at all?",
			   "The entire game, not just the fiddling about with stones. The point is not to play as tight as you can. The point is to be bold. To be dangerous. Be elegant. -Bredon",
			   "A well-played game of tak reveals the moving of a mind. -Bredon",
			   "Why would I want to win anything other than a beautiful game? - Bredon",
			   "I call it Bredon’s defense, but that’s what I call any maneuver when I get out of a tight corner by being uncommonly clever. -Bredon"};
	System.out.println(quotes[(int)(Math.random() * 5)]);
	System.out.println("Would you like to read the rules? Type in yes or no.");
	String answer = Keyboard.readString();
	while(!(answer.equals("yes") ||
		answer.equals("y")   ||
		answer.equals("no")  ||
		answer.equals("n"))){
	    System.out.println("Sorry, I do not speak your language. Please say something I can understand. *Sad face*");
	    answer = Keyboard.readString();
	}
	if(answer.equals("yes") || answer.equals("y")){
	    	System.out.println("Welcome youngling! I see you're interested in Tak. I get you. It is indeed a beautiful game.");
		System.out.println("But enough about that. listen here, it's kind of important.\n");
	
		System.out.println("The objective of Tak:");
		System.out.println("Players will enter different commands and use the grid to identify spaces on a board.");
		System.out.println("The first person to create a 'road' with their 'stones' between two opposite sides of the board wins. Roads do not have to be in a straight line. Players will attack, capture and defend their own pieces.\n");

		System.out.println("The 'normal' way to win is creating a road.");
		System.out.println("However, the game also ends when the board is completely filled or at least one player runs out of pieces.");
		System.out.println("In this case, the player with the most regular stones on top of the tiles and stacks wins.\n");

		System.out.println("Meet the pieces:");
		System.out.println("Stone: a stone is your standard piece in Tak. it is counted as a part of roads, and can be moved anywhere, except on capstones and walls. In this game, it is represented as \u25A0 for black and \u25A2 for white.");

		System.out.println("Wall: a wall is a standing stone. It is used to block roads from being formed. It can be moved anywhere, except on other walls and capstones. In addition, it can be flattened by capstones, essentially turning it back into a regular stone. In this game, it is represented as \u25B2 for black and \u25B3 for white.");

		System.out.println("Capstone: a capstone is a special piece that can be moved anywhere, except on other capstones. It has the power to flatten walls back into stones. In this game, it is represented as \u2654 for black and \u265A for white.\n");

		System.out.println("How to input a board location correctly: Follow this format 'row column'");
		System.out.println("Example:");
		System.out.println("Location: 5 3");
		System.out.println("This tells the board that your piece will be placed in the 6th row at the 4th column. This may get tricky, so always keep it in mind.\n");

		System.out.println("Rules:");
		System.out.println("1. Roads are created by going from one end of the board to the other. This means from north to south (or vice versa) or east to west (or vice versa).");
		System.out.println("2. Stones cannot be placed on walls or capstones, and walls and capstones both do not count as part of a road.");
		System.out.println("3. Capstones can flatten walls back into regular stones.\n");

		System.out.println("Well, that's pretty much all you need to know. Now venture forth, into a beautiful world.\n");
	}
	System.out.println("====================================================================\n");
    }

    public void creation(){
	final String[] TITLES = {"Edema Ruh", "Master Arcanist", "Fae", "Master Artificer", "E'lir", "Re'lar",
			   "University Chancellor", "Master Alchemist", "Master Arithmatician", "Master Linguist",
			   "Master Namer", "Master Physicker", "Master Rhetorician", "Master Sympathist", "Scriv",
			   "Giller", "Baron", "Maer"};
	
	// NAME SELECTION
	String p1Name = TITLES[(int)(Math.random() * TITLES.length)];
	System.out.print(p1Name + "'s name is: ");
	p1Name += " " + Keyboard.readString();
	
	String p2Name = TITLES[(int)(Math.random() * TITLES.length)];
	System.out.print(p2Name + "'s name is: ");
        p2Name += " " + Keyboard.readString();

	// COLOR SELECTION
	System.out.println("Which shade of gray are you feeling, " + p1Name + "?");
	System.out.println("0: Black");
	System.out.println("1: White");

	//If the user doesn't select 0 or 1 during color selection phase
	int p1Color = Keyboard.readInt();
	while(p1Color != 0 && p1Color != 1){
	    System.out.println("Black and yellow. Black and yellow. Joking. Black or white? 0 or 1? Oreos or milk? Pandas or pandas?");
	    p1Color = Keyboard.readInt();
	}

	int p2Color = 0;
	if (p1Color == 0){
	    p2Color = 1;
	}
	
	// BOARD SIZE SELECTION
	System.out.println("Board size is: (input a number between 3 and 8 inclusive)");
	int size = Keyboard.readInt();
	while (size < 3 || size > 8) {
	    System.out.println("Yo, that's not a valid size. Please put what I told you to put.");
	    size = Keyboard.readInt();
	}
	
	// INITIALIZE BOARD AND PLAYERS
	player1 = new Player(p1Name, p1Color, size);
	player2 = new Player(p2Name, p2Color, size);
	woah = new Board(size);
	totalTurns = 0;
    }
    
    public boolean winRoad(){
	return (woah.isRoad(0) || woah.isRoad(1));
    }

    public boolean winFullBoard(){
	return (woah.isBoardFull());
    }

    public boolean winNoPiecesLeft(){
	return (player1.noPiecesLeft() || player2.noPiecesLeft());
    }

    public boolean win(){
	return(winRoad() ||
	       winFullBoard() ||
	       winNoPiecesLeft());
    }

    public String getWinner(){
	int p1Color = player1.getColor();
	if(woah.isRoad(0) && woah.isRoad(1)){
	    if(totalTurns % 2 == 0)
		return ("Two roads were created by " + player1.getName() + "! They win the game!");
	    else
		return ("Two roads were created by " + player2.getName() + "! They win the game!");
	}
	else if(winRoad()){
	    if((woah.isRoad(0) && p1Color == 0) ||
	       (woah.isRoad(1) && p1Color == 1))
		return (player1.getName() + " won because they have successfully created a road!");
	    else
		return (player2.getName() +" won because they have successfully created a road!");
	}
	switch(woah.stackWinner(player1, player2)){
	case -1:
	    return ("If the board is filled or if one of the players run out of stones to place, the player with more flatstones on top of stacks wins! " + player1.getName() + "and " + player2.getName() + " both have the same amount of pieces so it is a tie!");
	case 1:
	    return ("If the board is filled or if one of the players run out of stones to place, the player with more flatstones on top of stacks wins! " + player1.getName() + " wins because they have more pieces!");
	case 2:
	    return ("If the board is filled or if one of the players run out of stones to place, the player with more flatstones on top of stacks wins! " + player2.getName() + " wins because they have more pieces!");
	}
	return "";
    }
    
    public void gameEnd(){
	System.out.println(getWinner());
	System.out.println("Would you like statistics of how your game went? Type in yes or no.");
	String answer = Keyboard.readString();
	while(!(answer.equals("yes") ||
		answer.equals("y")   ||
		answer.equals("no")  ||
		answer.equals("n"))){
	    System.out.println("Sorry, I do not speak your language. Please say something I can understand. *Sad face*");
	    answer = Keyboard.readString();
	}
	if(answer.equals("yes") || answer.equals("y")){
	    try{
		PrintWriter outputStream = new PrintWriter("stats.txt");
		woah.calculateNumStacks();
		woah.statsGenerator();
		stats+= "Turns Played: " + totalTurns+"\n";
		stats+= "Black Stones Placed: " + woah.numBlackStones + "\n";
		stats+= "Black Walls Placed: " + woah.numBlackWalls + "\n";
		stats+= "Black Capstones Placed: " + woah.numBlackCapstones + "\n";
		stats+= "Black Stacks Controlled At End: " + woah.numBlackStacks + "\n";
		stats+= "White Stones Placed: " + woah.numWhiteStones + "\n";
		stats+= "White Walls Placed: " + woah.numWhiteWalls + "\n";
		stats+= "White Capstones Placed: " + woah.numWhiteCapstones + "\n";
		stats+= "White Stacks Controlled At End: " + woah.numWhiteStacks + "\n";
		stats+= "Size of the board: " + woah.size + "\n";
		outputStream.print(stats);
		outputStream.close();
		System.out.println("Game stats have been generated! Check them out at stats.txt in this directory.");
	    }
	    catch(Exception e){
		System.out.println("Stats generation failed. But we can print them in the terminal:\n"+stats);
	    }
	    
	}
    }

    public void gameTurn(){
	while (!win()){
	    System.out.println(woah);
	    if(totalTurns == 0)
		player1.firstTurn(woah);
	    else if(totalTurns == 1)
		player2.firstTurn(woah);
	    else if(totalTurns % 2 == 0)
		player1.turn(woah);
	    else
		player2.turn(woah);
	    totalTurns += 1;
	}
    }

    public void runTak(){
	gameIntro();
	creation();
	gameTurn();
	gameEnd();
    }
    
    public static void main (String[] args){
	Woo Tak = new Woo();
	Tak.runTak();
    }
}
