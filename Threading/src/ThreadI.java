
public class ThreadI implements Runnable {

	Thread t;
	String name;
	
	public ThreadI(String name){
		System.out.println(name+" initialisiert!");
		this.name=name;
	}
	
	public void start(){
		t=new Thread(this,name);
		System.out.println(name+" startet");
		t.run();
	}
	
	@Override
	public void run() {
		try{
			
			for( int i=10;i>0;i--){
				System.out.println(name+" sagt:"+i);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
