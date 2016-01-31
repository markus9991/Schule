import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class Poker {

	static Card[] cards = new Card[52];
	static Card[] hand = new Card[5]; // { new Card(1,1),new Card(1,2),new Card(3,3),new Card(1,4),new Card(2,5) };//
	public static void main(String[] args) {
		
		
		int howoften=1000000;
		Batch batch = new Batch();
		Card[] hand =  new Card[5];
		int[] results=new int[10];
		
		for( int i=0;i<howoften;i++){
			hand=batch.getSortedHand();
			
			if(Tools.isRoyalFlush(hand)){
				results[9]++;
				
			}
			else if(Tools.isStraightFlush(hand)){
				results[8]++;
				
			}
			else if(Tools.isDoubleTripleOrPoker(hand, 4)){
				results[7]++;
				
				
			}
			else if(Tools.isTwoPairsOrFullHouse(hand, 3)){
				results[6]++;
				
			}
			else if(Tools.isFlush(hand)){
				results[5]++;
				
			}
			else if(Tools.isStraight(hand)){
				results[4]++;
				
			}
			else if(Tools.isDoubleTripleOrPoker(hand, 3)){
				results[3]++;
				
			}
			else if(Tools.isTwoPairsOrFullHouse(hand, 2)){
				results[2]++;
				
			}
			else if(Tools.isDoubleTripleOrPoker(hand, 2)){
				results[1]++;
				
			}else{results[0]++;}
		}
		
		int x=results[0]+results[1]+results[2]+results[3]+results[4]+results[5]+results[6]+results[7]+results[8]+results[9];
		
		System.out.println("Auswertung");
		System.out.println("=====================================================");
		System.out.println("Kombinationen\t\tAnzahl\t\tProzent");
		System.out.println("=====================================================");
		System.out.println("High Card: \t\t" + results[0]+"\t\t"+(double)((double)results[0]*100/x));
		System.out.println("Erziehlte Paare: \t" + results[1]+"\t\t"+(double)((double)results[1]*100/x));
		System.out.println("Erziehlte Zwei Paare: \t" + results[2]+"\t\t"+(double)((double)results[2]*100/x));
		System.out.println("Erziehlte Drillinge: \t" + results[3]+"\t\t"+(double)((double)results[3]*100/x));
		System.out.println("Erziehlte Straight: \t" + results[4]+"\t\t"+(double)((double)results[4]*100/x));
		System.out.println("Erziehlte Flush: \t" + results[5]+"\t\t"+(double)((double)results[5]*100/x));
		System.out.println("Erziehlte Full House: \t" + results[6]+"\t\t"+(double)((double)results[6]*100/x));
		System.out.println("Erziehlte Vierlinge: \t" + results[7]+"\t\t"+(double)((double)results[7]*100/x));
		System.out.println("Erziehlte Str. Flush: \t" + results[8]+"\t\t"+(double)((double)results[8]*100/x));
		System.out.println("Erziehlte Royal Flush: \t" + results[9]+"\t\t"+(double)((double)results[9]*100/x));
		System.out.println("Summe:\t\t\t"+x);
		
		// Database db=new Database();
		// db.createTable();
		// db.insertData(results);
		
		
		System.out.println("end");

//		int count = 0;
//
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 13; j++) {
//				cards[count] = new Card(i, j);
//				count++;
//			}
//
//		}
//		hand = drawCards(cards);
//		// show colour and value of drawn cards
//		for (Card i : hand) {
//			System.out.print(i.toString());
//
//		}
//
//		System.out.println(isDoubleTripleOrPoker(hand, 2));
//		System.out.println(isTwoPairsOrFullHouse(hand, 2));
//		System.out.println(isDoubleTripleOrPoker(hand, 3));
//		System.out.println(isStraight(hand));
//		System.out.println(isFlush(hand));
//		System.out.println(isTwoPairsOrFullHouse(hand, 3));
//		System.out.println(isDoubleTripleOrPoker(hand, 4));
//		System.out.println(isStraightFlush(hand));
//		System.out.println(isRoyalFlush(hand));

		//
		//
		//
		//
		// System.out.println(isRoyalFlush(hand));

	}

	/**
	 * This Method picks 5 cards out of 52 randomly and return them in a array
	 * 
	 * @param cards
	 *            the 52 card deck
	 * @return the 5 card deck (hand)
	 */
	private static Card[] drawCards(Card[] cards) {
		Random rnd = new Random();
		Card[] hand = new Card[5];
		for (int i = 0; i < 5; i++) {
			int zz = rnd.nextInt(cards.length - i);
			hand[i] = cards[zz];
			Card x = cards[cards.length - 1 - i];
			cards[cards.length - 1 - i] = cards[zz];
			cards[zz] = x;
		}
		return hand;
	}

	// /**
	// * This Method determines the color of a card and returns: 0...hearts
	// * 1...diamonds 2...spades 3...clubs
	// *
	// * @param value
	// * the card which color should be determined
	// * @return color of the card
	// */
	// private static int getColour(int value) {
	// int temp = value / 13;
	// return temp;
	// }
	//
	// /**
	// * This Method returns the value of a card
	// *
	// * @param value
	// * the card which value should be determined
	// * @return
	// */
	// private static int getValue(int value) {
	// return value % 13;
	// }

	/**
	 * This method checks if the given hand contains a flash and returns the
	 * appropriate value
	 * 
	 * @param hand
	 *            a Array with 5 card (a hand) in it
	 * @return if the given Array contains an flash
	 */
	private static boolean isFlush(Card[] hand) {
		for (int i = 0; i < hand.length; i++) {
			for (int j = 0; j < hand.length; j++) {
				if (i != j && hand[i].getColor() != hand[j].getColor()) {
					// once a colour is different ,a flash isnt possoble any
					// more
					return false;
				}
			}
		}
		return true;
	}

	public static int isDoubleTripleOrPoker(Card[] hand, int amount) {
		int temp = -1;
		for (int i = 0; i < hand.length; i++) {
			int counter = 1;
			for (int j = 0; j < hand.length; j++) {
				if (counter == (amount)) {
					return temp;
				}
				if (hand[i].getValue() == hand[j].getValue() && i != j) {
					counter++;
					temp = hand[j].getValue();

				}
			}
		}
		return -1;
	}

	public static boolean isStraight(Card[] hand) {

		int[] values = new int[5];

		Arrays.sort(values);

		for (int i = 0; i < values.length - 1; i++) {
			if ((values[i] - values[i + 1] != -1)) {
				return false;
			}
		}
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].getColor() != hand[i + 1].getColor()) {
				return false;
			}
		}
		return true;
	}

	// tp...2 fh....3
	public static boolean isTwoPairsOrFullHouse(Card[] hand, int what) {
		int firstpair = isDoubleTripleOrPoker(hand, what);
		int counter = 0;

		if (firstpair == -1) {
			return false;
		}

		Card[] oHand = new Card[3];

		for (int i = 0; i < hand.length; i++) {
			if (hand[i].getValue() != firstpair) {
				oHand[counter] = hand[i];
				counter++;
			}
		}
		if (what == 3) {
			what = 2;
		}

		if (isDoubleTripleOrPoker(oHand, what) != -1) {
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
		int[] values = new int[5];

		Arrays.sort(values);

		for (int i = 0; i < hand.length; i++) {
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
