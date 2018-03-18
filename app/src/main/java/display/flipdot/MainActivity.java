package display.flipdot;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

public class MainActivity extends Activity {

    private Gpio mGpio;
    private Gpio columnSelectorGpios[];
    private BlinkRunnable myBlinkRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hey");
        DisplayController dCtrl = new DisplayController();

        //Column Selector Pins on FP2800
        //A0, A1, A2, B0, B1

        dCtrl.setColumnDataToPins(columnSelectorGpios, 5);
        /*try {
            PeripheralManager manager = PeripheralManager.getInstance();
            mGpio = manager.openGpio("BCM21");
            mGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            mGpio.setActiveType(Gpio.ACTIVE_HIGH);
            myBlinkRunnable = new BlinkRunnable(mGpio);
            new Thread(myBlinkRunnable).start();
            //mGpio.setValue(true);
        } catch(IOException e) {

        }*/
    }
}
