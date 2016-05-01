
public class Test {
	
	static Z�hler z=new Z�hler();
	
	public static void main(String[] args) {
		
		ThreadC t1=new ThreadC("Thread1");
		ThreadI t2=new ThreadI("Thread2");		
		t1.start();
		t2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Jetzt syncronized");
		ThreadCsync t3=new ThreadCsync("Thread3",z);
		ThreadIsync t4=new ThreadIsync("Thread4",z);
		t3.start();
		t4.start();
	}




static class ThreadIsync implements Runnable {

	Thread t;
	String name;
	Z�hler z;
	
	
	public ThreadIsync(String name, Z�hler z){
		System.out.println(name+" initialisiert!");
		this.name=name;
		this.z=z; 
	}
	
	public void start(){
		t=new Thread(this,name);
		System.out.println(name+" startet");
		t.run();
	}
	
	@Override
	public void run() {
		synchronized (z) {
			z.z�hl(name);
		}
	}
	
}



static class ThreadCsync extends Thread {

	String name;
	Z�hler z;

	public ThreadCsync(String name, Z�hler z) {
		System.out.println(name + " initialisiert!");
		this.name = name;
		this.z=z;
	}

	public void run(){
		System.out.println(name+" startet");
			synchronized (z) {
				z.z�hl(name);
			}
		}
	}
	


static class Z�hler{
	public  void z�hl(String name ){
		try{
			
			for( int i=10;i>0;i--){
				System.out.println(name+" sagt:"+i);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
}