public class Sieb {

	public static void main(String[] args) {

		int anz = 120;
		int currentelement = 0;

		// boolsches Array erstellen
		boolean[] isprim = new boolean[anz];

		for (int i = 2; i < isprim.length; i++) {
			// anfangs wird jede Zahl für eine Primzahl gehalten
			isprim[i] = true;
		}

		sieben(isprim);

		for (boolean b : isprim) {
			//ausgabe
			if (b == true) {
				System.out.print(currentelement + "|");
			}
			currentelement++;
		}

	}

	public static void sieben(boolean[] array) {

		for (int i = 2; i < array.length; i++) {

			if (array[i] == true) {
				int j = i;
				do {
					// Jedes Vielfache von i wird innerhalb der Grenze als
					// falsch markiert
					j = j + i;
					if (j < array.length) {
						// Ist die Zahl ein Vielfaches einer
						// Primzahl, dann wird sie mit false
						// markiert
						array[j] = false;
					}
				} while (j <= array.length);
			}
		}
	}

}
