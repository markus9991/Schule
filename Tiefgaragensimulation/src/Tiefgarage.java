
public class Tiefgarage 
{
	private int freiePlaetze;
	
	public Tiefgarage(int freiePlaetze) 
	{
		this.freiePlaetze = freiePlaetze;
		
		System.out.println("Tiefgarage initialisiert");
	}

	public int getFreiePlaetze()
	{
		return this.freiePlaetze;
	}
	
	public boolean einfahren(Auto a)
	{
		if(this.freiePlaetze > 0)
		{
			this.freiePlaetze--;
			
			System.out.println(a.getAutoName() + "fährt ein");
			System.out.println("Freie Plätze: " + this.freiePlaetze);
			
			return true;
			
		}
		return false;
	}
	
	public void ausfahren(Auto a)
	{
		this.freiePlaetze++;
		
		System.out.println(a.getAutoName() + "fährt aus");
		System.out.println("Freie Plätze: " + this.freiePlaetze);
	}
}