import java.util.Arrays;
import java.util.Random;

public class Batch {

	int[] cards = new int[52];

	/**
	 * Creates an array of 52 integer
	 */
	public Batch() {
		for (int i = 0; i < cards.length; i++) {
			cards[i] = i;
		}
	}

	/**
	 * Select 5 random integer, sorts them, convert them to cards and return
	 * them
	 * 
	 * @return hand of cards
	 */
	public Card[] getSortedHand() {
		Random rnd = new Random();
		int[] hand = new int[5];
		// picking 5 random int
		for (int i = 0; i < hand.length; i++) {
			int zz = rnd.nextInt(cards.length - i);
			int card = cards[zz];
			hand[i] = card;
			int x = cards[cards.length - 1 - i];
			cards[cards.length - 1 - i] = cards[zz];
			cards[zz] = x;
		}
		// sort the ints
		Arrays.sort(hand);
		Card[] handCards = new Card[5];

		// convert the ints to the right card
		for (int i = 0; i < hand.length; i++) {
			handCards[i] = new Card(hand[i] / 13, hand[i] % 13);
		}

		return handCards;
	}

}
