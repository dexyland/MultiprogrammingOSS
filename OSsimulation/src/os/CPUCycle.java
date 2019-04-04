package os;

public interface CPUCycle {
	void notifyCPU();
	void register(PQcallback pq);
}
