import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 */

/**
 * @author Dejan
 *
 */

interface PQcallback{
	void wakeUpLTS();
	void startPrioritySystem();
}

public class OS {
	public static void main(String[] args) {
		Timer timer = new Timer();
		PQcallback pq = new processQueue();
		
		timer.register(pq);
		timer.startTimer();
		pq.startPrioritySystem();
	}

}

class Timer implements Runnable{
	public long time;
	private Thread t;
	private PQcallback callback;
	
	public Timer(){
		this.time = 0;
	}
	
	public long getTime(){
		return this.time;
	}
	
	/* no setter for the time since since it runs independently */
	
	public void register(PQcallback function){
		
		/*  */
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
			
			/* Each iteration increases time representing the passing of 1mS. */
			
			/* Waking up long term scheduler every 100mS */
			
			if (time%100 == 0){
				callback.wakeUpLTS();
			}
			
			if (time == 10000){
				break;
			}
		}
		
	}
}

class Memory implements Runnable{
	
	/* Array representing memory. Each location will store ID of a process 
	 * occupying it, '0' if OS is occupying it or '-1' if location is free. */
	
	public int[] locations;
	
	/* Used to calculate if more than 80% of memory is full */
	
	public int memoryOccupied;
	
	private Thread t;
	
	public Memory(){
		
		/* array of 256 members - 256MB */
		
		this.locations = new int[256];
		
		/* Initializing memory and storing OS at first 16 locations (16MB) */
		
		for (int i = 0; i < 256; i++){
			if (i <= 15){
				this.locations[i] = 0;
			}
			else {
				this.locations[i] = -1;
			}
			
		}
		
		this.memoryOccupied = 16;
	}
	
	public int memoryAvailable(){
		return (int)Math.ceil(this.memoryOccupied/256.0)*100;
	}
	
	public void startMemoryController() {
		System.out.println("Starting memory controller.");
	    if (t == null) {
	    	t = new Thread (this, "MemoryThread");
	        t.start();
	    }
	}
	
	public void requestMemory(int ID, int memory) {
		
	}

	@Override
	public void run() {
		while(true){
			System.out.println("Memory Occupied: " + this.memoryOccupied);
		}
		
	}
}

class Process {
	private int id;
	private String status;
	private int memory;
	private int additionalMemory;
	private int time;
	private int timeElapsed;
	
	
	public Process(){
		this.id = -1;
		this.status = "waiting";
		this.memory = -1;
		this.additionalMemory = -1;
		this.time = -1;
		this.timeElapsed = 0;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public int getMemory(){
		return this.memory;
	}
	
	public int getAdditionalMemory(){
		return this.additionalMemory;
	}
	
	public int getTime(){
		return this.time;
	}
	
	public int getElapsedTime(){
		return this.timeElapsed;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setPriority(String status){
		this.status = status;
	}
	
	public void setMemory(int memory){
		this.memory = memory;
	}
	
	public void setAdditionalMemory(int memory){
		this.additionalMemory = memory;
	}
	
	public void setTime(int time){
		this.time = time;
	}
	
	public void setElapsedTime(int time){
		this.timeElapsed = time;
	}
}


/* This class implements multiple level feedback queue which receives jobs 
 * from long term scheduler which are prioritized and stored in proper queue. 
 * short term scheduler takes first job from priority 0 queue if there is any.
 * System periodically takes care of upgrading and downgrading processes. */

class processQueue implements PQcallback, Runnable{
	
	/**/
	
	private Memory memoryController;
	
	/* Priority queues that will be used for storing processes */
	
	/* Priority queues */
	
	public Queue<Process> priorityQueue0;
	public Queue<Process> priorityQueue1;
	public Queue<Process> priorityQueue2;
	
	/* Queue for storing jobs received from long term scheduler  */
	
	public Queue<Process> inputQueue;

	/* Queue for storing jobs that require memory from long term scheduler */
	
	public Queue<Process> noMemoryQueue;
	
	/* Thread class handling threading operations */
	
	private Thread t;
	
	/* Allow short term scheduler to get a job from queue. Prevents data race between queue 
	 * scheduler which upgrades and downgrades processes priority and short term scheduler.*/
	
	private boolean queueAvailable;
	
	/* class processQueue constructor */
	
	public processQueue(){
		
		/* Creating memory controller */
		
		memoryController = new Memory();
		memoryController.startMemoryController();
		
		/* Creating priority queues that will be used for storing processes */
		
		this.priorityQueue0 = new LinkedList<>();
		this.priorityQueue1 = new LinkedList<>();
		this.priorityQueue2 = new LinkedList<>();
		this.inputQueue = new LinkedList<>();
		this.noMemoryQueue = new LinkedList<>();
		queueAvailable = true;

		System.out.println("Created priority queues!");
	}
	
	public void wakeUpLTS() {
		System.out.println("LTS woke up!");
		
		if (!this.noMemoryQueue.isEmpty()) {
			if (this.memoryController.memoryAvailable() < 80) {
				Process job = new Process();
				
				/* Removes the first process that entered */
				job = this.noMemoryQueue.remove();
				
				this.memoryController.requestMemory(job.getId(), job.getMemory());
			}
		}
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
		
		/* If access to queues is enabled, job is taking following priority rule. */
		
		if (this.queueAvailable) {
			if (!this.priorityQueue0.isEmpty()) {
				retProcess = this.priorityQueue0.remove();
				System.out.println("Job taken from queue 0!");
			} else if (!this.priorityQueue1.isEmpty()) {
				retProcess = this.priorityQueue1.remove();
				System.out.println("Job taken from queue 1!");
			} else if (!this.priorityQueue2.isEmpty()) {
				retProcess = this.priorityQueue2.remove();
				System.out.println("Job taken from queue 2!");
			} else {
				System.out.println("Priority queues are empty. No jobs to take!");
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
