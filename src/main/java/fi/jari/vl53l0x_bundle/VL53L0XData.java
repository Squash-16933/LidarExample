package fi.jari.vl53l0x_bundle;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nakki
 *
 */
public class VL53L0XData implements Runnable {

	private boolean running = true;
	private boolean recording = false;
	private long recordTime = 10000;
	private Instant recordingStarted;

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		VL53L0XDevice sensor = null;
		try {
			sensor = new VL53L0XDevice(0x29, 30);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			int previousDist = -1;
			while (isRunning() && sensor != null) {
				int mm = sensor.range();
				if (previousDist != mm) {
					System.out.println(String.format("Distance: %d mm", mm));
				}
				previousDist = mm;
				try {
					Thread.sleep(50L);
				} catch (InterruptedException iex) {

				}
			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
			System.out.println("Fuq");
		}

	}

	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		setRunning(false);
	}

}
