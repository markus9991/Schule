
public class Binaersuche {

	public static void main(String[] args) {
		int[] zahlen = { 0, 3, 4, 4, 5, 7, 8, 22, 54, 55, 55, 100 };
		int gesuchteZahl = 56;

		System.out.println("Position von " + gesuchteZahl + " im Array: "
				+ binaersuche(zahlen, gesuchteZahl, 0, zahlen.length - 1));

	}

	private static int binaersuche(int[] array, int gesucht, int anfang,int ende) {

		int haelfte = anfang+ (ende - anfang) / 2;



		if (array.length == 0){
			return -1;	
		}
		else if (gesucht>array[haelfte]  ){
			return binaersuche(array, gesucht, haelfte + 1, ende);
		}
		else if (gesucht<array[haelfte] && anfang!=haelfte  ){
			return binaersuche(array, gesucht, anfang, haelfte - 1);
		}
		else if (array[haelfte] == gesucht){
			return haelfte;
			}
		else{
			return -1;
			}

	}

}
