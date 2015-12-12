import java.util.Arrays;

// karte batch -> hand  tools  test klasse
// batch dynamisch

public class Tools {

	static private int valueofPTP;

	public static void printHand(Card[] hand) {
		for (Card i : hand) {
			System.out.println(i.toString());

		}

	}

	/**
	 * This method checks if the given hand contains a flash and returns the
	 * appropriate value
	 * 
	 * @param hand
	 *            a Array with 5 card (a hand) in it
	 * @return if the given Array contains an flash
	 */
	public static boolean isFlush(Card[] hand) {

		for (int i = 0; i < hand.length - 1; i++) {
			if (hand[i].getColor() != hand[i + 1].getColor()) {
				return false;
			}
		}
		return true;
	}

	public static boolean isDoubleTripleOrPoker(Card[] hand, int amount) {

		int exp = 0;
		int counter = 0;

		switch (amount) {
		case 2:
			exp = 1;
			break;
		case 3:
			exp = 3;
			break;
		case 4:
			exp = 6;
			break;
		}

		for (int i = 0; i < (hand.length - 1); i++) {
			for (int j = (i + 1); j < hand.length; j++) {
				if (hand[i].getValue() == hand[j].getValue())
					counter++;
				valueofPTP = hand[j].getValue();

				if (counter == (exp)) {
					return true;
				}

			}
		}
		return false;
	}

	public static boolean isStraight(Card[] hand) {

		int[] values = new int[hand.length];

		for (int i = 0; i < hand.length; i++) {
			values[i] = hand[i].getValue();
		}
		Arrays.sort(values);
		for (int i = 0; i < values.length - 1; i++) {
			if (values[i + 1] - values[i] != 1) {

				return false;
			}
		}
		return true;
	}

	// tp...2 fh....3
	public static boolean isTwoPairsOrFullHouse(Card[] hand, int what) {

		int counter = 0;

		if (isDoubleTripleOrPoker(hand, what) == false) {
			return false;
		}

		Card[] oHand = new Card[3];

		for (int i = 0; i < hand.length; i++) {
			if (hand[i].getValue() != valueofPTP) {
				oHand[counter] = hand[i];
				counter++;
			}
		}
		if (oHand[2] == null) {
			oHand[2] = new Card(-1, -1);
		}
		if (isDoubleTripleOrPoker(oHand, 2) == true) {
			return true;
		}

		return false;
	}

	public static boolean isStraightFlush(Card[] hand) {

		if ((isFlush(hand) && isStraight(hand)) == true) {
			return true;
		}
		return false;
	}

	public static boolean isRoyalFlush(Card[] hand) {

		for (int i = 0; i < hand.length - 1; i++) {
			if (hand[i].getColor() != hand[i + 1].getColor()) {
				return false;
			}
		}
		if (isFlush(hand) == false) {
			return false;
		}
		if (hand[0].getValue() == 8) {
			return true;
		}
		return false;
	}
}
