package display.flipdot;

import android.util.Log;

import com.google.android.things.pio.Gpio;

import java.io.IOException;

/**
 * Created by tengelberg on 16.03.18.
 */

public class BlinkRunnable implements Runnable {

    private Gpio enableGpio;
    private Gpio dataGpio;
    private Gpio yellowGpio;
    private Gpio blackGpio;

    private boolean toggle = false;

    public BlinkRunnable(Gpio enableGpio, Gpio dataGpio, Gpio yellowGpio, Gpio blackGpio) {
        this.enableGpio = enableGpio;
        this.dataGpio = dataGpio;
        this.yellowGpio = yellowGpio;
        this.blackGpio = blackGpio;
    }

    @Override
    public void run() {
        while(true) {
            try {
                toggle = !toggle;
                if(toggle) {
                    //To yellow
                    Log.w("S", "YELLOW!");
                    dataGpio.setValue(false);
                    yellowGpio.setValue(true);
                } else {
                    //To black
                    Log.w("S", "BLACK!");
                    dataGpio.setValue(true);
                    blackGpio.setValue(true);
                }
                //Thread.sleep(1000);
                Log.w("S", "SET!");
                enableGpio.setValue(true);
                Thread.sleep(5);
                Log.w("E", "Enable");
                enableGpio.setValue(false);
                blackGpio.setValue(false);
                yellowGpio.setValue(false);
                Log.w("E", "Disable");
                Thread.sleep(500);

            } catch (Exception e) {

            }
        }
    }

    /*public static void main(String args[]) {
        (new Thread(new BlinkRunnable())).start();
    }*/
}
