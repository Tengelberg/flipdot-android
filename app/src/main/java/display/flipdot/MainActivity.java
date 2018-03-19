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

    private void setDefaultGpioBehaviour(Gpio Gpio) {
        try {
            Gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            Gpio.setActiveType(Gpio.ACTIVE_HIGH);
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
            mGpio = manager.openGpio("BCM21");
            this.setDefaultGpioBehaviour(mGpio);
            myBlinkRunnable = new BlinkRunnable(mGpio);
            new Thread(myBlinkRunnable).start();

            //Row Selector Pins
            //ROW_A0, ROW_A1, ROW_A2, ROW_A3, ROWS_TOP, ROWS_BOTTOM
            rowSelectorGpios = new Gpio[6];
            rowSelectorGpios[0] = manager.openGpio("BCM26");
            rowSelectorGpios[1] = manager.openGpio("BCM19");
            rowSelectorGpios[2] = manager.openGpio("BCM13");
            rowSelectorGpios[3] = manager.openGpio("BCM6");
            rowSelectorGpios[4] = manager.openGpio("BCM20");
            rowSelectorGpios[5] = manager.openGpio("BCM21");

            //Column Selector Pins
            //A0, A1, A2, B0, B1

            //Set Data Pin
            dataGpio = manager.openGpio("BCM16");
            this.setDefaultGpioBehaviour(dataGpio);

        } catch(IOException e) {

     }




        //dCtrl.setColumnDataToPins(columnSelectorGpios, 5);

    }
}
