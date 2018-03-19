package display.flipdot;

import android.util.Log;

import com.google.android.things.pio.Gpio;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Tobias Engelberg on 18.03.18.
 */

public class DisplayController {


    private boolean[] toBitArray(int n) {
        boolean[] bits = new boolean[7];
        for (int i = 6; i >= 0; i--) {
            bits[i] = (n & (1 << i)) != 0;
        }
        return bits;
    }


    public void setLineDataToPins(Boolean lineData[], Gpio lineGpios[]) {

    }

    /*
        The column needs to be selected. It is a multiplexed signal, so we need
        multipled Pins to select the right column.
     */
    public void setColumnDataToPins(Gpio columnSelectorGpios[], int column) {
        boolean bits[] = this.toBitArray(column);
        Log.w("INFO" , column + " = " + Arrays.toString(bits));
    }

    /*
        This will set the Data-Pin on FP2800A IC.
        It handles if the output is on source or sink mode, when E is enabled.
     */
    public void setData(Gpio dataGpio, Boolean data) {
        this.setGpio(dataGpio, data);
    }

    /*
        Needs to enable the output to source or sink mode (depends on Data-Pin).
    */
    public void setEnable(Gpio enableGpio, Boolean enable) {
        this.setGpio(enableGpio, enable);
    }

    public boolean setGpio(Gpio IGpio, Boolean value) {
        try {
            IGpio.setValue(value);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
