import de.hamster.debugger.model.Territorium;import de.hamster.model.HamsterException;import de.hamster.model.HamsterInitialisierungsException;import de.hamster.model.HamsterNichtInitialisiertException;import de.hamster.model.KachelLeerException;import de.hamster.model.MauerDaException;import de.hamster.model.MaulLeerException;import de.hamster.debugger.model.Hamster;public class hamsterprogramm implements de.hamster.model.HamsterProgram {public void main() {
  Hamster paul = new Hamster();
  paul.init(0, 0, Hamster.OST, 0);
  ernteEineReiheUndLaufeZurueck(paul);
  while(weitereReiheExistiert(paul)) {
    begibDichInNaechsteReihe(paul);
    ernteEineReiheUndLaufeZurueck(paul);
  }
}

boolean weitereReiheExistiert(Hamster hamster) {
  hamster.linksUm();
  if (hamster.vornFrei()) {
    rechtsUm(hamster);
    return true;
  } else {
    rechtsUm(hamster);
    return false;
  }
}

void begibDichInNaechsteReihe(Hamster hamster) {
  hamster.linksUm();
  hamster.vor();
  hamster.linksUm();
}

void ernteEineReiheUndLaufeZurueck(Hamster hamster) {
  ernteEineReihe(hamster);
  kehrt(hamster);
  laufeZurueck(hamster);
}

void ernteEineReihe(Hamster hamster) {
  sammle(hamster);
  while (hamster.vornFrei()) {
    hamster.vor();
    sammle(hamster);
  }
}

void laufeZurueck(Hamster hamster) {
  while (hamster.vornFrei()) {
    hamster.vor();
  }
}

void sammle(Hamster hamster) {
  while (hamster.kornDa()) {
    hamster.nimm();
  }
}

void rechtsUm(Hamster hamster) {
  kehrt(hamster);
  hamster.linksUm();
}

void kehrt(Hamster hamster) {
  hamster.linksUm();
  hamster.linksUm();
}}