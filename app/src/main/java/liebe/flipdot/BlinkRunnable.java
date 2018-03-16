package liebe.flipdot;

import com.google.android.things.pio.Gpio;

import java.io.IOException;

/**
 * Created by tengelberg on 16.03.18.
 */

public class BlinkRunnable implements Runnable {

    private Gpio mGpio;
    private boolean blink = false;

    public BlinkRunnable(Gpio blinkGpio) {
        this.mGpio = blinkGpio;
    }

    @Override
    public void run() {
        while(true) {
            if (blink) {
                blink = false;
            } else {
                blink = true;
            }
            try {
                mGpio.setValue(blink);
            } catch (IOException e) {

            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
        }
    }

    /*public static void main(String args[]) {
        (new Thread(new BlinkRunnable())).start();
    }*/
}
