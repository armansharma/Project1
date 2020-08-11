/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author bhara
 */
public class UnoPlayView extends GroupOfCards {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        String name1;
        String name2;
        System.out.println("Player1: ");
        System.out.println("Enter your name: ");
        name1 = input.nextLine();
       
        System.out.println("Player2: ");
        System.out.println("Enter your name: ");
        name2 = input.nextLine();
        
        ArrayList<GroupOfCards> player1Deck = new ArrayList<>();
        ArrayList<GroupOfCards> player2Deck = new ArrayList<>();
        int win; 
        GroupOfCards  topCard = new GroupOfCards();
        int index;
        String displayColour;
        
        gameLoop:
        while (true){
            player1Deck.clear();
            player2Deck.clear();
            win = 0;
            displayColour = topCard.colour;

            System.out.println("Welcome to UNO Card Game");
            draw(5, player1Deck);
            draw(5, player2Deck);

            //A for loop for deciding the turns of the players 
            for (boolean playersTurn = true; win == 0; playersTurn ^= true)
            {
                System.out.println("The top card is: " + topCard.getFace());
                
                // players turn
                if (playersTurn) 
                {
                    // Displaying player1's deck of cards 
                    System.out.println("Draw! Its your turn");
                    for (int i = 0; i < player1Deck.size(); i++)
                    {
                        System.out.println(String.valueOf(i + 1) +
                        ((GroupOfCards) player1Deck.get(i) ).getFace());
                    }
                    System.out.println(String.valueOf(player1Deck.size() + 1 )+ 
                                       String.valueOf(player1Deck.size() + 2));
                    // It Repeats every time the user doesn't need to input a number again and again 
                    do
                    {
                        System.out.println("Enter the number of your choice : ");
                        input = new Scanner(System.in);
                    } while (!input.hasNextInt() );
                   
                    index = input.nextInt() - 1;

                    // Game logic 
                    if (index == player1Deck.size() )
                        draw(1, player1Deck);
                    else if (index == player1Deck.size() + 1)
                        break gameLoop;
                    else if ( ((GroupOfCards) player1Deck.get(index)).canPlace(topCard, displayColour) )
                    {
                        topCard = (GroupOfCards) player1Deck.get(index);
                        player1Deck.remove(index);
                        displayColour = topCard.colour;
                        // Producing the action of special cards conatining wild, draw 2 , wild Draw4                       
                        if (GroupOfCards.size >= 10)
                        {
                            playersTurn = false;

                            switch (GroupOfCards.size)
                            {
                                //This is the case of draw 2
                                case 12: 
                                System.out.println("chosing 2 cards...");
                                draw(2,player2Deck);
                                break;
                                
                                case 13:
                                do
                                {
                                    System.out.println("Enter the color you like: ");
                                    input = new Scanner(System.in);
                                } while (!input.hasNext("Red|red|Green|green|Blue|blue|Yellow|yellow") ); 
                                if (input.hasNext("Red|red") )
                                    displayColour = "Red";
                                else if (input.hasNext("Green|green") )
                                    displayColour = "Green";
                                else if (input.hasNext("Blue|blue") )
                                    displayColour = "Blue";
                                else if (input.hasNext("Yellow|yellow") )
                                    displayColour = "Yellow";

                                System.out.println("You chose " + displayColour);
                                // This is the case for Wild draw 4 
                                if (topCard.size == 14) 
                                {
                                    System.out.println("chosing 4 cards");
                                    draw(4,player2Deck);
                                }
                                break;
                            }
                        }
                    } else System.out.println("Invalid choice. Next player turn");


                } else  //player 2's turn 
                {
                    System.out.println("player2's turn!"+ String.valueOf(player2Deck.size() ) 
                                        + "Total cards left!" + ((player2Deck.size() == 1)) );
                    // Finding a card to place
                    for (index = 0; index < player2Deck.size(); index++)
                    {
                        if ( ((GroupOfCards) player2Deck.get(index)).canPlace(topCard, displayColour) ) // Searching for playable cards
                            break; 
                    }

                    if (index == player2Deck.size() )
                    {
                         System.out.println("No cards left. You have to draw some!");
                         draw(1,player2Deck);
                    } else 
                    {
                         topCard = (GroupOfCards) player2Deck.get(index);
                         player2Deck.remove(index);
                         displayColour = topCard.colour;
                         System.out.println("You choose " + topCard.getFace());

                         // Must do as part of each turn because topCard can stay the same through a round
                         if (topCard.value >= 10)
                         {
                             playersTurn = true; // Skipping turn

                             switch (index)
                             {
                                 case 12: // Draw 2
                                 System.out.println("chosing 2 cards");
                                 draw(2,player1Deck);
                                 break;
                                 
                                 
                                 // Wild cards
                                 case 13: case 14:
                                // Picking a random color that's not none
                                 do 
                                 {
                                     displayColour = new GroupOfCards().colour;
                                 } while ("none".equals(displayColour));

                                 System.out.println("New color is " + displayColour);
                                 if (GroupOfCards.size == 14) // Wild draw 4
                                 {
                                     System.out.println("chosing 4 cards");
                                     draw(4,player1Deck);
                                 }
                                 break;
                             }
                         }
                    }
                        
                    // If the decks are empty
                    if (player1Deck.isEmpty())
                        win = 1;
                    else if (player2Deck.isEmpty())
                        win = -1;
                }

            }

            // Condition for displaying the Results of the game  
            if (win == 1)
                System.out.println("Congrats!You won");
            else 
                System.out.println("You lose! Better Luck Next Time");

            System.out.print("Play again? ");
            input = new Scanner(System.in);

            if (input.next().toLowerCase().contains("n") )
                break;
        }

        System.out.println("Have a nice Day");
    }
    // A method which draws the card for the players when necessary
    public static void draw(int cards, ArrayList<GroupOfCards> playersdeck)
    {
        for (int i = 0; i < cards; i++)
            playersdeck.add(new GroupOfCards() );
    }
}
