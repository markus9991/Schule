import java.util.Random;

public class Tiefgarage {
	
	private static int anzfrei=3;
	
	public static void main(String[] args){
		
		int fahrzanz= 20; //rnd.nextInt(20);
		
		Tiefgarage Tg =new Tiefgarage();
		
		for(int i=0;i<fahrzanz;i++){
			Auto a=new Auto("Auto "+(i+1) );
			Auto aa=new Auto("Audi "+(i+1) );
			
			a.start();
			aa.start();
			
			try {
				Thread.sleep((long) (Math.random()+5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		
		
		
		
		
	}
	
	public Tiefgarage(){
		System.out.println("Tiefgarage initialisiert");
		System.out.println("===================");
	}
	
	public synchronized static boolean einfahren(String name){
		
		if (anzfrei >0){
			
			System.out.println(name+ " fährt ein");
			anzfrei--;
			System.out.println("freie Plätze verbleibend:"+anzfrei);
			System.out.println("===================");
			return true;
		}
		else{
			System.out.println(name+" kann nicht einfahren, da die Tiefgarage voll ist");
			System.out.println("===================");
			return false;
		}
	}
		
	public synchronized static boolean ausfahren(String name){
		anzfrei++;
		System.out.println(name+" fährt aus");
		System.out.println("freie Plätze verbleibend:"+anzfrei);
		System.out.println("===================");
		return true;
			
	}

	
	
	public static class Auto implements Runnable{

		Thread t;
		String name;
		boolean eing=false;
		
		
		public Auto(String name){
			
			this.name=name;
			System.out.println("Thread "+name+" erstellt");
			System.out.println("===================");
			
		}
		
		@Override
		public void run() {
			
			do{
				try {
					Thread.sleep((long) (Math.random()+10000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				eing=einfahren(name);
			}while(eing==false);
			
			try {
				Thread.sleep((long) (Math.random()+10000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ausfahren(name);		
			
		}
		
		
			
		
		
		public void start(){
			t=new Thread(this,name);
			System.out.println(name+" startet");
			System.out.println("===================");
			t.start();
		}
		
	}
}



