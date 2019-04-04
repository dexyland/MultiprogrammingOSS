package os;

public class CPU implements CPUCycle, Runnable {

	private Thread t;
	private long time;

	private PQcallback callback;
	
	private Process current;
	
	public CPU() {
		this.startCPU();
		this.time = 0;
		this.current = new Process();
	}
	
	@Override
	public void run() {
	}
	
	public void startCPU(){
		System.out.println("Starting CPU.");
	    if (t == null) {
	    	t = new Thread (this, "CPUThread");
	        t.start();
	    }
	}
	
	public void runCPU(){
		this.current.setElapsedTime(this.current.getElapsedTime() + 1);
		/*System.out.println("Working on: " + this.current.getId() + 
				                " TIME: " + this.current.getTime() + 
				        " ELAPSED TIME: " + this.current.getElapsedTime());*/
	}
	
	private void STS(){
		if (this.current.getId() != -1) {
			this.runCPU();
			
			if (time - this.current.getEnterTime() == 4 || this.current.getElapsedTime() >= this.current.getTime()) {
				while(!this.callback.cpu2queue(current));
				
				this.current = this.callback.queue2cpu();
				this.current.setEnterTime(time);
			}
		} else {
			this.current = this.callback.queue2cpu();
			this.current.setEnterTime(time);
		}
	}

	@Override
	public void notifyCPU() {
		this.time++;
		this.STS();
	}

	@Override
	public void register(PQcallback pq) {
		/*  */
		callback = pq;
		System.out.println("Registered cpu callback!");
	}
}