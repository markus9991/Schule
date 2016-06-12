public class Auto extends Thread
{
	private Thread t;
	private String name;
	private Tiefgarage tg;
	
	public Auto(String name, Tiefgarage tg)
	{
		this.name = name;
		this.tg = tg;
	}
	
	public String getAutoName()
	{
		return name;
	}
	
	public void start()
	{
		System.out.println(name + " startet");
		if (t == null)
	      {
	         t = new Thread (this, name);
	         t.start ();
	      }
	}
	
	public void run()
	{
		synchronized(tg)
		{
			int rnd;
			
			while(!tg.einfahren(this))
			{
				try 
				{
					rnd = (int) (Math.random() * 10000);
					Thread.sleep(rnd);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			rnd = (int) (Math.random() * 10000);
			
			tg.ausfahren(this);
		}
	}
}