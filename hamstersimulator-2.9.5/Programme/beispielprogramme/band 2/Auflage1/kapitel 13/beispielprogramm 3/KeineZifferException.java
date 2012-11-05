// Fehlertyp: ein String repraesentiert keine Ziffer
import de.hamster.debugger.model.Territorium;import de.hamster.model.HamsterException;import de.hamster.model.HamsterInitialisierungsException;import de.hamster.model.HamsterNichtInitialisiertException;import de.hamster.model.KachelLeerException;import de.hamster.model.MauerDaException;import de.hamster.model.MaulLeerException;import de.hamster.debugger.model.Hamster;class KeineZifferException extends Exception {
  
  String str;
  
  KeineZifferException(String str) {
    super(str);
    this.str = str;
  }
  
  String getString() {
    return this.str;
  }
}