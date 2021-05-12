package TareaHilos;

public class NuevoHilos implements Runnable 
{
  String name; // nombre del hilo
  Thread t;
  double met, suma = 0, cuad = 0, sum = 0;
  double med = 0;
  double vec[] = new double[10];
  NuevoHilos(String threadname, int m, double v[]) {
	  name = threadname;
	  met = m;
	  vec = v;
	  t = new Thread(this, name);
	  System.out.println("Nuevo hilo: " + t);
	  t.start(); // Comienza el hilo
  }
  // Este es el punto de entrada del hilo.
  public void run() {
    try {
    	if(met==1) {
    		for(int i = 0; i<vec.length; i++) {
    			suma+=vec[i];
    		}
    	}
    	
    	if(met==2) {
    		for(int i = 0; i<vec.length; i++) {
    			cuad+=vec[i]*vec[i];
    		}
    	}
    	
    	if(met==3) {
    		for(int i = 0; i<vec.length; i++) {
    			sum+=vec[i];
    		}
    		 med = sum/vec.length;
    	}
    	if(met==1)
        System.out.println("La suma del vector es:" + suma);
    	if(met==2)
            System.out.println("La suma de los cuadrados del vector es:" + cuad);
    	if(met==3)
            System.out.println("La media del vector es:" + med);
        Thread.sleep(1000);
      
    }
    catch (InterruptedException e) {
      System.out.println(name + "Interrupcion del hilo hijo" +name);
    }
    System.out.println("Sale del hilo hijo " + name);
  }
}