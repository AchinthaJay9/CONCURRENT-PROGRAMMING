import java.applet.Applet;
import java.awt.Graphics;
public class ClockApplet extends Applet implements Runnable {
	private Thread clockThread;
	private String name;
	public ClockApplet(String name) {
		this.name = name;
		
	}	
	@Override
	public void start() {
		if (clockThread == null) {
			clockThread = new Thread(this, name);
			clockThread.start();
		}
	}	
	@Override
	public void stop() {
		clockThread = null;
	}
	@Override
	public void paint(Graphics g) {
		//TODO following code is incomplete
		String str = "Get the time";
		g.drawString(str, 100, 100);
	}
	@Override
	public void run() {
		for(;;) {
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/*
 * <applet code="ClockApplet.class" width="300" height="300">  
 * </applet>  
 */
