/**
 *
 */
package fi.jari.vl53l0x_bundle;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiBcmPin;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;

/**
 * @author Nakki
 *
 */
public class VL53L0XData implements Runnable {

	private boolean running = true;
	private boolean recording = false;
	private GpioController gpio;
	private GpioPinDigitalOutput led;
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
		GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
		gpio = GpioFactory.getInstance();
		if (led == null) {
			led = gpio.provisionDigitalOutputPin(RaspiBcmPin.GPIO_22, "LED-light");
		}
//		I2CBus bus = null;
		VL53L0XDevice sensor = null;
		try {
//			bus = I2CFactory.getInstance(I2CBus.BUS_1);
			sensor = new VL53L0XDevice(0x29, 30);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			int previousDist = -1;
			while (isRunning() && sensor != null) {
				int mm = sensor.range();
				if (previousDist != mm) {
					System.out.println(String.format("Distance: %d mm", mm));
					if (mm > 60 && mm < 200 && mm != 0) {
						led.setState(PinState.HIGH);
						if (!recording) {
							record();
						}
					} else if (mm > 100) {
						if (recording) {
							stopRecordAndPack();
						}
						led.setState(PinState.LOW);
					}
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

	private void record() {
		recording = true;
		ProcessBuilder pb = new ProcessBuilder("/home/jari/video/record.sh", String.valueOf(recordTime));
		recordingStarted = Instant.now();
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void stopRecordAndPack() {
		recording = false;
		Instant recordingStopped = Instant.now();

		while (Duration.between(recordingStarted, recordingStopped).toMillis() < recordTime) {
			recordingStopped = Instant.now();
			sleep(1000);
		}
		ProcessBuilder pb = new ProcessBuilder("/home/jari/video/pack.sh");
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getDevices(I2CBus bus) throws IOException {
		Integer[] addresses = { 24, 25, 26, 27, 34, 35, 36, 37 };
		List<I2CDevice> devices = new ArrayList<>();
		for (Integer address : addresses) {
			devices.add(bus.getDevice(address));
		}

		System.out.println(devices);
		I2CDevice test = devices.get(0);
		System.out.println(test.read());
		test.write((byte) 11, (byte) 1);

	}

	public void stop() {
		setRunning(false);
	}

}
