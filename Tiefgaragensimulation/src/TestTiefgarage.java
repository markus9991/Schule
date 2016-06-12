public class TestTiefgarage 
{
	public static void main(String[] args)
	{
		Tiefgarage tg = new Tiefgarage(1);
		Auto a;
		
		for(int i = 0; i < 10; i++)
		{
			a = new Auto("Auto"+i, tg);
			a.start();
		}
	}
}