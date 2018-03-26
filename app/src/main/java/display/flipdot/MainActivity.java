package display.flipdot;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;
import java.lang.reflect.Array;

public class MainActivity extends Activity {

    private Gpio mGpio;
    private Gpio dataGpio;
    private Gpio rowSelectorGpios[];
    private BlinkRunnable myBlinkRunnable;

    private void setDefaultGpioBehaviour(Gpio gpio) {
        try {
            gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            gpio.setActiveType(Gpio.ACTIVE_HIGH);
        } catch(IOException e) {
            Log.e("IO", "Pin default behaviour could not be set.");
        }
    }

    private void setInitiallyHighGpioBehavior(Gpio gpio) {
        try {
            gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            gpio.setActiveType(Gpio.ACTIVE_LOW);
        } catch(IOException e) {
            Log.e("IO", "Pin default behaviour could not be set.");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayController dCtrl = new DisplayController();

        try {
            PeripheralManager manager = PeripheralManager.getInstance();
            /*mGpio = manager.openGpio("BCM21");
            this.setDefaultGpioBehaviour(mGpio);
            myBlinkRunnable = new BlinkRunnable(mGpio);
            new Thread(myBlinkRunnable).start();*/

            //Row Selector Pins
            //ROW_A0, ROW_A1, ROW_A2, ROW_A3, ROWS_TOP, ROWS_BOTTOM
            /*rowSelectorGpios = new Gpio[6];
            rowSelectorGpios[0] = manager.openGpio("BCM26");
            rowSelectorGpios[1] = manager.openGpio("BCM19");
            rowSelectorGpios[2] = manager.openGpio("BCM13");
            rowSelectorGpios[3] = manager.openGpio("BCM6");
            rowSelectorGpios[4] = manager.openGpio("BCM21");
            rowSelectorGpios[5] = manager.openGpio("BCM20");

            setDefaultGpioBehaviour(rowSelectorGpios[0]);
            setDefaultGpioBehaviour(rowSelectorGpios[1]);
            setDefaultGpioBehaviour(rowSelectorGpios[2]);
            setDefaultGpioBehaviour(rowSelectorGpios[3]);
            setDefaultGpioBehaviour(rowSelectorGpios[4]);
            setDefaultGpioBehaviour(rowSelectorGpios[5]);

            dCtrl.setRow(rowSelectorGpios, 1, true);*/

            //Column Selector Pins
            //A0, A1, A2, B0, B1

            //Set Data Pin
            /*dataGpio = manager.openGpio("BCM16");
            this.setDefaultGpioBehaviour(dataGpio);*/

            //Demo
            Gpio dataPin = manager.openGpio("BCM26");
            Gpio enablePin = manager.openGpio("BCM12");
            Gpio a0Pin = manager.openGpio("BCM7");
            Gpio a1Pin = manager.openGpio("BCM8");
            Gpio a2Pin = manager.openGpio("BCM25");
            Gpio a3Pin = manager.openGpio("BCM11");
            Gpio a4Pin = manager.openGpio("BCM9");
            Gpio toYellow = manager.openGpio("BCM16");
            Gpio toBlack = manager.openGpio("BCM5");

            this.setDefaultGpioBehaviour(dataPin);
            this.setDefaultGpioBehaviour(enablePin);
            this.setDefaultGpioBehaviour(a0Pin);
            this.setDefaultGpioBehaviour(a1Pin);
            this.setDefaultGpioBehaviour(a2Pin);
            this.setDefaultGpioBehaviour(a3Pin);
            this.setDefaultGpioBehaviour(a4Pin);
            this.setDefaultGpioBehaviour(toYellow);
            this.setDefaultGpioBehaviour(toBlack);

            dataPin.setValue(false);
            toYellow.setValue(false);
            toBlack.setValue(false);
            a1Pin.setValue(true);
            a3Pin.setValue(true);

            myBlinkRunnable = new BlinkRunnable(enablePin, dataPin, toYellow, toBlack);
            new Thread(myBlinkRunnable).start();

        } catch(IOException e) {
        }
    }
}
