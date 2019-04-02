import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 */

/**
 * @author Dejan
 *
 */

interface wakeUpLTS{
	void wakeMeUp();
	void startLTS();
}

public class OS {
	public static void main(String[] args) {
		Timer timer = new Timer();
		wakeUpLTS callback = new LTS();
		processQueue pq = new processQueue();
		memory memoryController = new memory();
		memoryController.startMemoryController();
		timer.register(callback);
		timer.startTimer();
		callback.startLTS();
		pq.startPrioritySystem();
	}

}

class LTS implements wakeUpLTS, Runnable{
	private Thread t;
	public void wakeMeUp(){
		System.out.println("Long term scheduler woke up!");
	}
	
	public void startLTS(){
		System.out.println("Starting Long term scheduler!");
	    if (t == null) {
	    	t = new Thread (this, "LTSThread");
	        t.start();
	    }
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

class Timer implements Runnable{
	public long time;
	private Thread t;
	private wakeUpLTS callback;
	
	public Timer(){
		this.time = 0;
	}
	
	public long getTime(){
		return this.time;
	}
	
	/* no setter for the time since since it runs independently */
	
	public void register(wakeUpLTS function){
		callback = function;
		System.out.println("Registered callback!");
	}
	
	public void startTimer(){
		System.out.println("Starting timer");
	    if (t == null) {
	    	t = new Thread (this, "timerThread");
	        t.start();
	    }
	}

	@Override
	public void run() {
		this.time = 0;
		
		while(true){
			time++;
			
			/* Waking up long term scheduler every 100ms */
			if (time%100 == 0){
				callback.wakeMeUp();
			}
			
			if (time == 10000){
				break;
			}
		}
		
	}
}

class memory implements Runnable{
	/* array of 256 members - 256MB */
	public int locations[];
	public int memoryOccupied;
	
	private Thread t;
	
	public memory(){
		/* Setting first 16 locations (16MB) to  0 - OS used
		 *                        and others to -1 - free locations */
		/*for (int i = 0; i < 256; i++){
			if (i <= 15){
				locations[i] = 0;
			}
			else {
				locations[i] = -1;
			}
			
		}*/
		memoryOccupied = 16;
	}
	
	public int memoryAvailable(){
		return (int)Math.ceil(memoryOccupied/256.0)*100;
	}
	
	public void startMemoryController() {
		System.out.println("Starting memory controller.");
	    if (t == null) {
	    	t = new Thread (this, "PQsystemThread");
	        t.start();
	    }
	}

	@Override
	public void run() {
		while(true){
			System.out.println("MEMORYYYY");
		}
		
	}
}

class Process {
	private int id;
	private int priority;
	private int memory;
	private int time;
	
	public Process(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getPriority(){
		return this.id;
	}
	
	public int getMemory(){
		return this.id;
	}
	
	public int getTime(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setPriority(int priority){
		this.priority = priority;
	}
	
	public void setMemory(int memory){
		this.memory = memory;
	}
	
	public void setTime(int time){
		this.time = time;
	}
}


/* This class implements multiple level feedback queue which receives jobs 
 * from long term scheduler which are prioritized and stored in proper queue. 
 * short term scheduler takes first job from priority 0 queue if there is any.
 * System periodically takes care of upgrading and downgrading processes. */

class processQueue implements Runnable{
	
	/* Priority queues that will be used for storing processes */
	
	/* Priority queues */
	
	public Queue<Process> priorityQueue0;
	public Queue<Process> priorityQueue1;
	public Queue<Process> priorityQueue2;
	
	/* Queue for storing jobs received from long term scheduler  */
	
	public Queue<Process> inputQueue;

	/* Thread class handling threading operations */
	
	private Thread t;
	
	/**/
	
	private boolean queueAvailable;
	
	/* class processQueue constructor */
	
	public processQueue(){
		
		/* Creating priority queues that will be used for storing processes */
		
		this.priorityQueue0 = new LinkedList<>();
		this.priorityQueue1 = new LinkedList<>();
		this.priorityQueue2 = new LinkedList<>();
		this.inputQueue = new LinkedList<>();
		queueAvailable = true;

		System.out.println("Created priority queues!");
	}
	
	public void startPrioritySystem() {
		System.out.println("Starting priority system.");
	    if (t == null) {
	    	t = new Thread (this, "PQsystemThread");
	        t.start();
	    }
	}
	
	/* Long term scheduler call this function to add a job into a queue */
	
	public void addJob(Process P) {
		this.inputQueue.add(P);
		System.out.println("Job added to queue!");
	}
	
	/* Short term scheduler takes a job from a queue if accessing queues is enabled. 
	 * Priority queue system can disable access when upgrading and downgrading processes. */
	
	public Process getJob() {
		Process retProcess = null;
		
		/* If access to queues is enabled, first process that entered
		 * priorityqueue0 is sent to short term scheduler if there is any */
		
		if (queueAvailable) {
			if (!priorityQueue0.isEmpty()) {
				retProcess = priorityQueue0.remove();
				System.out.println("Job taken from queue!");
			} else {
				System.out.println("Priority queue is empty. No jobs to take!");
			}
		} else {
			System.out.println("Access to queues denied!");
		}
		
		return retProcess;
	}
	
	/* Print queue details */
	
	private void printQueueDetails() {
		System.out.println("Priority queues information:");
		System.out.println("Jobs waiting: " + inputQueue.size());
		System.out.println("Queue0: " + priorityQueue0.size() + " jobs");
		System.out.println("Queue1: " + priorityQueue1.size() + " jobs");
		System.out.println("Queue2: " + priorityQueue2.size() + " jobs");
		
		int total = inputQueue.size() + priorityQueue0.size()
			  + priorityQueue1.size() + priorityQueue2.size();
		System.out.println("Total jobs in queues: " + total);
	}

	@Override
	public void run() {
		System.out.println("Priority system active!");
		
		while(true){
			System.out.println("QUEUEEEEE");
		}
	}
}
