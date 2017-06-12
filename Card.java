/** Hassan Sabree
 * COSC 117-502
 * Dr. Steven Lauterburg
 * Project 3: Poker Hands
 */

public class Card {
	 // fields
    public char suit; // the card's suit (C, D, H, or S)
    public char value; // value appearing on the card (2-9, T, J, Q, K, or A)
    public int worth; // numerical worth of the card used sort cards (2-14
                      // mapped onto values 2 through A)

    // constructor
    public Card(char s, char v) {
        final String values = "23456789TJQKA";
        suit = s;
        value = v;
        worth = values.indexOf(v) + 2;
    }

    @Override
    public String toString() {
        return "" + suit + value;
}
}
