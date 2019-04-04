package os;

public interface PQcallback extends CPUPipe{
	void wakeUpLTS(long time);
	void startPrioritySystem();
	int getPQ0SIZE();
	int getPQ1SIZE();
	int getPQ2SIZE();
	int getFQSIZE();
	int getINQSIZE();
	int getMEMORY();
}

interface CPUPipe {
	boolean cpu2queue(Process p);
	Process queue2cpu();
}
