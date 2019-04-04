package os;

public class Process {
	private int id;
	private String status;
	private int memory;
	private int additionalMemory;
	private int time;
	private long enterTime;
	private int timeElapsed;
	
	public Process(){
		this.id = -1;
		this.status = "waiting";
		this.memory = -1;
		this.additionalMemory = -1;
		this.time = -1;
		this.enterTime = -1;
		this.timeElapsed = 0;
	}
	
	public Process(int id, int memory, int addmem, int time){
		this.id = id;
		this.status = "waiting";
		this.memory = memory;
		this.additionalMemory = addmem;
		this.time = time;
		this.enterTime = 0;
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
	
	public long getEnterTime(){
		return this.enterTime;
	}
	
	public int getElapsedTime(){
		return this.timeElapsed;
	}

	public void setStatus(String status){
		this.status = status;
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
	
	public void setEnterTime(long time){
		this.enterTime = time;
	}
	
	public void setElapsedTime(int time){
		this.timeElapsed = time;
	}
	
	public void printProcess(){
		System.out.println("Process: " + this.getId());
		System.out.println("Memory required: " + this.getMemory());
		System.out.println("Time required: " + this.getTime());
		System.out.println("Status: " + this.getStatus());
		System.out.println("Time elapsed: " + this.getElapsedTime());
	}
}