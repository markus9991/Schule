
public class ThreadC extends Thread{
	
	String name;
	
	public ThreadC(String name){
		System.out.println(name+" initialisiert!");
		this.name=name;
	}
	
	public void run(){
	try{
			System.out.println(name+" startet");
			for( int i=10;i>0;i--){
				System.out.println(name+" sagt:"+i);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
