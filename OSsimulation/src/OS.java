import gui.*;
import os.*;

public class OS {

	public static void main(String[] args) {
		GUI window = new GUI();
		window.frame.setVisible(true);
		
		while (window.button1 == 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		window.button1 = 0;
		
		Timer timer = new Timer();
		PQcallback pq = new ProcessQueue();
		CPUCycle cpu = new CPU();
	
		cpu.register(pq);
		timer.registerqc(pq);
		timer.registercc(cpu);
		timer.startTimer();
		pq.startPrioritySystem();
		
		System.out.println("System starting");
		
		while (true) {
			if (window.button2 == 1) {
				System.out.println("Press 'Start' for new simulation!");
				while (window.button3 == 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				break;
			}
			
			window.changeTextTimer(timer.getTime());
			window.changeTextWaiting(pq.getINQSIZE());
			window.changeTextProcessing(pq.getPQ0SIZE()+pq.getPQ1SIZE()+pq.getPQ2SIZE());
			window.changeTextFinished(pq.getFQSIZE());
			window.changeTextMemory(pq.getMEMORY());
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
