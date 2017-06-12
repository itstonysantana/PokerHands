/** Hassan Sabree
 * COSC 117-502
 * Dr. Steven Lauterburg
 * Project 3: Poker Hands
 */


import java.util.Scanner;
import java.util.Arrays;

public class PokerHands {

	public static void main(String[] args) {

		computeHandOdds();
		/********************************************************/
	}

	public static void computeHandOdds() {
		
		Scanner stdin = new Scanner( System.in );
		
		/*
		 * Ask the user (1) how many hands should be considered and (2) whether
		 * or not details about each hand should be displayed during processing.
		 * Store the information in appropriately named variables.
		 */
		
		System.out.println("How many hands should be considered?");
		int numHands;
		numHands = stdin.nextInt();
		
		String verifyDetails;
		System.out.println("Would you like to see the details of each hand? y/n");
		verifyDetails = stdin.next();
		boolean displayDetails;
		
		if (verifyDetails.equalsIgnoreCase("y"))
			displayDetails = true;
		else
			displayDetails = false;

		/*
		 * I100nitialize counters used to keep track of how many hands of various
		 * types are dealt. There are nine different hand types. See
		 * http://en.wikipedia.org/wiki/List_of_poker_hands
		 */
		
		int numStraightFlush = 0, numQuads = 0, numFullBoat = 0, numFlush = 0, numStraight = 0;
		int numTrips = 0, numTwoPair = 0, numOnePair = 0, numHighCard = 0;
		
		// note: quads is another name for four of a kind
		// note: full boat is another name for full house
		// note: trips is another name for three of a kind

		// This is the main loop. It keeps going as long there are more hands to
		// process.
		int handCount = 0;
		while (handCount < numHands) {

			/*
			 * (1) create a new deck object, (2) shuffle the deck by calling the
			 * deck objects shuffle method, and (3) output a message indicating
			 * that a new deck has been created, if the user indicated that
			 * details should be displayed.
			 */

			CardDeck deck = new CardDeck();
			deck.shuffleDeck();
			
			if (displayDetails == true) {
				System.out.println("New Deck");

			}

			/*
			 * Repeats the following as long as there are enough cards in the
			 * deck to deal a 5-card hand: (1) deal a 5-card hand from the deck,
			 * (2) sort the hand in descending order for easier processing (note
			 * you will write the code to do this in a separate method below),
			 * (3) determine the type of hand (e.g., "Four of a kind") and
			 * increment the corresponding counter. A separate method should be
			 * used for each of the seven hand types. (4) display the hand and
			 * its type, if the user indicated that details should be displayed.
			 */
			do {
				String result = "";
				Card[] hand1 = deck.dealCards(5);
				// override dealt hand
				// hand1 = createHand("C4", "C4", "C4", "S2", "S2");
				sortCards(hand1);
				if (isStraightFlush(hand1)) {
					numStraightFlush++;
					result = "Straight Flush";
				} else if (isQuads(hand1)) {
					numQuads++;
					result = "Four of a Kind";
				} else if (isFullBoat(hand1)) {
					numFullBoat++;
					result = "Full House";
				} else if (isFlush(hand1)) {
					numFlush++;
					result = "Flush";
				} else if (isStraight(hand1)) {
					numStraight++;
					result = "Straight";
				} else if (isTrips(hand1)) {
					numTrips++;
					result = "Three of a Kind";
				} else if (isTwoPair(hand1)) {
					numTwoPair++;
					result = "Two Pair";
				} else if (isOnePair(hand1)) {
					numOnePair++;
					result = "One Pair";
				} else {
					numHighCard++;
					result = "High Card";
				}
				if (displayDetails == true) {
					System.out.print(Arrays.toString(hand1));
					System.out.println(" - " + result);
				}
				handCount++;
			} while (handCount < numHands && deck.numCards() > 5);
		}

		/*
		 * Write a single statement here that calls the displayHandOdds method
		 * to compute and display the results.
		 */
		
		displayHandOdds(numHands, numStraightFlush, numQuads, numFullBoat,
				numFlush, numStraight, numTrips, numTwoPair, numOnePair,
				numHighCard);
	}

	/********************************************************/
	public static void displayHandOdds(int numHands, int numStraightFlush,
			int numQuads, int numFullBoat, int numFlush, int numStraight,
			int numTrips, int numTwoPair, int numOnePair, int numHighCard) {
		/*
		 * Write code here that computes the observed percentages for each hand
		 * type (e.g., "Four of a kind"). In other words, compute the percentage
		 * of the total number of hands for that type.
		 */
		double[] observed = new double[9];
		observed[0] = (double) numStraightFlush / numHands * 100;
		observed[1] = (double) numQuads / numHands * 100;
		observed[2] = (double) numFullBoat / numHands * 100;
		observed[3] = (double) numFlush / numHands * 100;
		observed[4] = (double) numStraight / numHands * 100;
		observed[5] = (double) numTrips / numHands * 100;
		observed[6] = (double) numTwoPair / numHands * 100;
		observed[7] = (double) numOnePair / numHands * 100;
		observed[8] = (double) numHighCard / numHands * 100;

		double[] expected = { 0.0015, 0.0240, 0.1441, 0.1965, 0.3925, 2.1128,
				4.7539, 42.2569, 50.1177 };
		String[] type = { "Straight Flush", "Quads", "Full House", "Flush",
				"Straight", "Trips", "Two Pair", "One Pair", "High Card" };

		/*
		 * Write code here that displays a properly aligned table consisting of
		 * three columns. Column 1 should display the hand type. Column 2 should
		 * display the "observed" percentage of hands dealt for the hand type
		 * listed in column 1. Column 3 should display the "expected" percentage
		 * of hands for the hand type. The expected percentages do not need to
		 * be calculated; you can find the proper values on the Internet.
		 */
		
		System.out.println("Hand Type         Observed    Expected ");
		System.out.println("--------------    --------    -------- ");
		for (int i = 0; i < 9; i++) {
			System.out.printf("%-15s %8.4f%% %-2s %7.4f%%\n", type[i],
					observed[i], "", expected[i]);
		}

	}

	/********************************************************/
	public static boolean isStraightFlush(Card[] hand) {
		/*
		 * Write code here that determines whether or not the hand is a straight
		 * flush. The method's return type is boolean, so the method should
		 * return true or false as appropriate.
		 */
		boolean ifStraightFlush = false;

		if (isFlush(hand) == true && isStraight(hand) == true) {
			ifStraightFlush = true;
		}

		return ifStraightFlush; 
	}

	/********************************************************/
	public static boolean isQuads(Card[] hand) {
		/*
		 * Write code here that determines whether or not the hand contains four
		 * of a kind (also known as quads). The method's return type is boolean,
		 * so the method should return true or false as appropriate.
		 */
		boolean ifQuads = false;
		int tracker = 0;
		for (int i = 1; i < hand.length - 1; i++) {
			if (hand[0].worth == hand[i].worth)
				tracker++;

		}
		if (tracker == 3)
			ifQuads = true;
		tracker = 0;

		for (int j = 2; j < hand.length; j++) {
			if (hand[1].worth == hand[j].worth)
				tracker++;
		}
		if (tracker == 3)
			ifQuads = true;

		return ifQuads; 
	}

	/********************************************************/
	public static boolean isFullBoat(Card[] hand) {
		/*
		 * Write code here that determines whether or not the hand is a full
		 * house (also known as a full boat). The method's return type is
		 * boolean, so the method should return true or false as appropriate.
		 */
		boolean ifFullHouse = false;

		if (hand[0].worth == hand[1].worth && hand[0].worth == hand[2].worth) {
			if (hand[3].worth == hand[4].worth) {
				ifFullHouse = true;
			}

		} else if (hand[0].worth == hand[1].worth) {

			if (hand[2].worth == hand[3].worth
					&& hand[2].worth == hand[4].worth) {
				ifFullHouse = true;
			}
		}

		return ifFullHouse;
	}

	/********************************************************/
	public static boolean isTrips(Card[] hand) {
		/*
		 * Write code here that determines whether or not the hand contains
		 * three of a kind (also know as trips or a set). The method's return
		 * type is boolean, so the method should return true or false as
		 * appropriate.
		 */
		boolean ifTrips = false;
		int tracker = 0;
		
		for (int i = 1; i < hand.length - 2; i++) {
			if (hand[0].worth == hand[i].worth)
				tracker++;
		}
		
		if (tracker == 2)
			ifTrips = true;
		tracker = 0;

		for (int j = 2; j < hand.length - 1; j++) {
			if (hand[1].worth == hand[j].worth)
				tracker++;
		}
		
		if (tracker == 2)
			ifTrips = true;
		tracker = 0;
		for (int k = 3; k < hand.length; k++) {
			if (hand[2].worth == hand[k].worth)
				tracker++;
		}
		
		if (tracker == 2)
			ifTrips = true;

		return ifTrips;
	}

	/********************************************************/
	public static boolean isFlush(Card[] hand) {
		/*
		 * Write code here that determines whether or not the hand is a flush.
		 * The method's return type is boolean, so the method should return true
		 * or false as appropriate.
		 */

		boolean ifFlush = false;
		if (hand[0].suit == hand[1].suit && hand[0].suit == hand[2].suit
				&& hand[0].suit == hand[3].suit && hand[0].suit == hand[4].suit) {
			ifFlush = true;
		}

		return ifFlush;
	}

	/********************************************************/
	public static boolean isStraight(Card[] hand) {
		/*
		 * Write code here that determines whether or not the hand is a
		 * straight. The method's return type is boolean, so the method should
		 * return true or false as appropriate.
		 */
		boolean ifStraight = false;
		
		if (hand[1].worth == hand[0].worth - 1
				&& hand[2].worth == hand[1].worth - 1
				&& hand[3].worth == hand[1].worth - 2
				&& hand[4].worth == hand[1].worth - 3) {
			ifStraight = true;
		} else if (hand[1].worth == hand[2].worth + 1
				&& hand[1].worth == hand[3].worth + 2
				&& hand[1].worth == hand[4].worth + 3 && hand[0].worth == 14) {
			ifStraight = true;
		}

		return ifStraight; // this line will be replaced by your code
	}

	/********************************************************/
	public static boolean isTwoPair(Card[] hand) {
		/*
		 * Write code here that determines whether or not the hand contains two
		 * pairs. The method's return type is boolean, so the method should
		 * return true or false as appropriate.
		 */
		boolean ifTwoPair = false;
		
		if (hand[0].worth == hand[1].worth && hand[2].worth == hand[3].worth) {
			ifTwoPair = true;
		}
		
		if (hand[0].worth == hand[1].worth && hand[3].worth == hand[4].worth) {
			ifTwoPair = true;
		}
		
		if (hand[1].worth == hand[2].worth && hand[3].worth == hand[4].worth) {
			ifTwoPair = true;
		}

		return ifTwoPair;
	}

	/********************************************************/
	public static boolean isOnePair(Card[] hand) {
		/*
		 * Write code here that determines whether or not the hand contains one
		 * pair. The method's return type is boolean, so the method should
		 * return true or false as appropriate.
		 */
		boolean ifOnePair = false;
		
		if (hand[0].worth == hand[1].worth) {
			ifOnePair = true;
		}
		
		if (hand[1].worth == hand[2].worth) {
			ifOnePair = true;
		}
		
		if (hand[2].worth == hand[3].worth) {
			ifOnePair = true;
		}
		
		if (hand[3].worth == hand[4].worth) {
			ifOnePair = true;
		}

		return ifOnePair; 
	}

	/********************************************************/
	public static void sortCards(Card[] hand) {
		/*
		 * Write code here that sorts the hand array in decreasing order.
		 * Remember that arrays are reference types. Since this method has a
		 * void return type, you need to directly update the hand array.
		 */

		int a, b, first;
		Card temp;  
	     for ( a = hand.length - 1; a > 0; a -- )  {
	          first = 0;   
	          for(b = 1; b <= a; b ++)   
	          {
	               if( hand[b].worth < hand[first].worth )         
	                 first = b;
	          }
	          
	          temp = hand[first];  
	          hand[first] = hand[a];
	          hand[a] = temp; 
	     }

	}

	/********************************************************/
	/*
	 * This method is not necessary for the final program, but can be useful
	 * when testing your program. For example, four-of-a-kind does not happen
	 * very often, but you can create one to test your code for four-of-a-kind.
	 */
	public static Card[] createHand(String c0, String c1, String c2, String c3,
			String c4) {
		Card card0 = new Card(c0.charAt(0), c0.charAt(1));
		Card card1 = new Card(c1.charAt(0), c1.charAt(1));
		Card card2 = new Card(c2.charAt(0), c2.charAt(1));
		Card card3 = new Card(c3.charAt(0), c3.charAt(1));
		Card card4 = new Card(c4.charAt(0), c4.charAt(1));
		Card[] hand = { card0, card1, card2, card3, card4 };
		return hand;
		}

}



	


