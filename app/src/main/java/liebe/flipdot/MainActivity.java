package liebe.flipdot;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *xxw
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends Activity {

    private Gpio mGpio;
    private BlinkRunnable myBlinkRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hey");
        try {
            PeripheralManager manager = PeripheralManager.getInstance();
            mGpio = manager.openGpio("BCM21");
            mGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            mGpio.setActiveType(Gpio.ACTIVE_HIGH);
            myBlinkRunnable = new BlinkRunnable(mGpio);
            new Thread(myBlinkRunnable).start();
            //mGpio.setValue(true);
        } catch(IOException e) {

        }
    }
}
