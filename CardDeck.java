/** Hassan Sabree
 * COSC 117-502
 * Dr. Steven Lauterburg
 * Project 3: Poker Hands
 */

import java.util.Arrays;
import java.util.Random;
public class CardDeck {
	// fields
		private Card[] deck; // a deck of Card objects
		private Random randGen; // a Random object used by shuffleDeck
		private int nextCard;

		// constructor
		public CardDeck() {
			// set nextCard to 0. We'll be dealing from the front of the array.
			randGen = new Random();
			
			/*
			 * Write code here that creates 52 Card objects with appropriate values
			 * and assigns them to the 52 cells in the deck.
			 */
			
			deck = new Card[52];
			
			Card Card1=new Card('S', '2');
			Card Card2=new Card('S', '3');
			Card Card3=new Card('S', '4');
			Card Card4=new Card('S', '5');
			Card Card5=new Card('S', '6');
			Card Card6=new Card('S', '7');
			Card Card7=new Card('S', '8');
			Card Card8=new Card('S', '9');
			Card Card9=new Card('S', 'T');
			Card Card10=new Card('S', 'J');
			Card Card11=new Card('S', 'Q');
			Card Card12=new Card('S', 'K');
			Card Card13=new Card('S', 'A');
			Card Card14=new Card('H', '2');
			Card Card15=new Card('H', '3');
			Card Card16=new Card('H', '4');
			Card Card17=new Card('H', '5');
			Card Card18=new Card('H', '6');
			Card Card19=new Card('H', '7');
			Card Card20=new Card('H', '8');
			Card Card21=new Card('H', '9');
			Card Card22=new Card('H', 'T');
			Card Card23=new Card('H', 'J');
			Card Card24=new Card('H', 'Q');
			Card Card25=new Card('H', 'K');
			Card Card26=new Card('H', 'A');
			Card Card27=new Card('C', '2');
			Card Card28=new Card('C', '3');
			Card Card29=new Card('C', '4');
			Card Card30=new Card('C', '5');
			Card Card31=new Card('C', '6');
			Card Card32=new Card('C', '7');
			Card Card33=new Card('C', '8');
			Card Card34=new Card('C', '9');
			Card Card35=new Card('C', 'T');
			Card Card36=new Card('C', 'J');
			Card Card37=new Card('C', 'Q');
			Card Card38=new Card('C', 'K');
			Card Card39=new Card('C', 'A');
			Card Card40=new Card('D', '2');
			Card Card41=new Card('D', '3');
			Card Card42=new Card('D', '4');
			Card Card43=new Card('D', '5');
			Card Card44=new Card('D', '6');
			Card Card45=new Card('D', '7');
			Card Card46=new Card('D', '8');
			Card Card47=new Card('D', '9');
			Card Card48=new Card('D', 'T');
			Card Card49=new Card('D', 'J');
			Card Card50=new Card('D', 'Q');
			Card Card51=new Card('D', 'K');
			Card Card52=new Card('D', 'A');
		
			Card[]totalDeck={Card1, Card2, Card3, Card4, Card5,
						Card6, Card7, Card8, Card9, Card10, Card11,
						Card12, Card13, Card14, Card15, Card16, Card17,
						Card18, Card19, Card20, Card21, Card22, Card23,
						Card24, Card25, Card26, Card27, Card28, Card29,
						Card30, Card31, Card32, Card33, Card34, Card35,
						Card36, Card37, Card38, Card39, Card40, Card41,
						Card42, Card43, Card44, Card45, Card46, Card47,
						Card48, Card49, Card50, Card51, Card52					
							};
			for(int i = 0; i < totalDeck.length; i ++)
				
			{
					deck[i] = totalDeck[i];
				
			}
			
		}

		// returns how many cards are left to deal in the deck
		public int numCards() {
			int tracker= 0;
			
			/*
			 * Write code here that returns how many cards are left to deal in the
			 * deck.
			 */
			
			for(int i =0; i < deck.length; i++){
				
				if(deck[i] != null)
				tracker++;
				
			}
			nextCard = deck.length - tracker;
			return tracker; // this line will be replaced by your code
		}

		// randomly reorders the cards in the deck array
		public void shuffleDeck() {
			/*
			 * Write code here that "shuffles" the deck of cards.
			 */
			int a;
			int b;
			Card temp;
			for(int i = 0; i < deck.length; i ++){
				a = randGen.nextInt(52);
				b = randGen.nextInt(52);
			 temp = deck[a];
			 deck[a] = deck[b];
			 deck[b]= temp;
			 
				
			}
			
		}

		// creates a new array of Card objects containing the next n Cards
		// in the deck.
		public Card[] dealCards(int y) {
			/*
			 * Write code here that (1) creates a new array of Cards (2) copies the
			 * next n Cards from the deck into the new array (3) updates the
			 * nextCard field appropriately (4) and returns the new Card array.
			 */
			
			Card[]hand = new Card[y];
			for(int i = 0; i < hand.length; i ++){
				hand[i] = deck[nextCard];
				deck[nextCard] = null;
				nextCard++;
				
			}
			
			return hand; 
		}

		@Override
		public String toString() {
			return Arrays.toString(deck);
		}

	}



