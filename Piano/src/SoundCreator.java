import peripheral.ILThresholdListener;
import peripheral.LightThresholdSensor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/*
* Thread that listens for thresholds... all this could by done also inside processing... i choose that for fastness*/
public class SoundCreator extends Thread implements ILThresholdListener {

    /**
     * variables to hold the lightsensors
     */
    LightThresholdSensor s1;
    LightThresholdSensor s2;
    LightThresholdSensor s3;
    LightThresholdSensor s4;
    LightThresholdSensor s5;
    LightThresholdSensor s6;
    /**
     * queues to hold incoming values
     */
    private BlockingQueue<Integer> sl1;
    private BlockingQueue<Integer> sl2;
    private BlockingQueue<Integer> sl3;

    private BlockingQueue<Integer> sl4;
    private BlockingQueue<Integer> sl5;
    private BlockingQueue<Integer> sl6;


    /**
     * constructor
     */
    public SoundCreator() {
        init();
        s1 = Sensors.getInstance().getSPOT("4AD6").getLightSensor();
        s2 = Sensors.getInstance().getSPOT("5EF0").getLightSensor();
        s3 = Sensors.getInstance().getSPOT("4884").getLightSensor();
        s4 = Sensors.getInstance().getSPOT("4AD7").getLightSensor();
        s5 = Sensors.getInstance().getSPOT("5952").getLightSensor();
        s6 = Sensors.getInstance().getSPOT("40EA").getLightSensor();

        s1.setThresholdsTime(400, 700, 1000);
        s2.setThresholdsTime(400, 700, 1000);
        s3.setThresholdsTime(400, 700, 1000);
        s4.setThresholdsTime(400, 700, 1000);
        s5.setThresholdsTime(400, 700, 1000);
        s6.setThresholdsTime(400, 700, 1000);
        s1.addThresholdListener(this);
        s2.addThresholdListener(this);
        s3.addThresholdListener(this);
        s4.addThresholdListener(this);
        s5.addThresholdListener(this);
        s6.addThresholdListener(this);


    }

    public void init() {
        /**
         * add required sunspots
         */
        Sensors.getInstance().addSPOT("4AD6", 37);
        Sensors.getInstance().addSPOT("5EF0", 37);
        Sensors.getInstance().addSPOT("4884",37);
        Sensors.getInstance().addSPOT("4AD7", 37);
        Sensors.getInstance().addSPOT("5952", 37);
        Sensors.getInstance().addSPOT("40EA",37);
        /**
         * initialize queues
         */
        sl1 = new LinkedBlockingQueue<Integer>();
        sl2 = new LinkedBlockingQueue<Integer>();
        sl3 = new LinkedBlockingQueue<Integer>();
        sl4 = new LinkedBlockingQueue<Integer>();
        sl5 = new LinkedBlockingQueue<Integer>();
        sl6 = new LinkedBlockingQueue<Integer>();


    }

    public void run() {
        while (true) {
            /**
             * take values and analyze so that processing would choose sound to play
             */
            if ((!(sl1.isEmpty())) && (!(sl2.isEmpty())) && (!(sl3.isEmpty()))&&(!(sl1.isEmpty())) && (!(sl2.isEmpty())) && (!(sl3.isEmpty()))) {
                try {
                    int value1 = sl1.take();
                    int value2 = sl2.take();
                    int value3 = sl3.take();
                    int value4 = sl4.take();
                    int value5 = sl5.take();
                    int value6 = sl6.take();

                    final int[] values = new int[6];
                    for (int i = 0; i < values.length; i++) {
                        values[i] = -1;
                    }

                    if ((value1 == 0)) {
                        values[0] = 0;
                    }
                    if ((value1 > 40) && (value1 < 150)) {
                        values[0] = 1;
                    }
                    if ((value2 == 0)) {
                        values[1] = 0;
                    }
                    if ((value2 > 40) && (value2 < 150)) {
                        values[1] = 1;
                    }
                    if ((value3 == 0)) {
                        values[2] = 0;
                    }
                    if ((value3 > 40) && (value3 < 150)) {
                        values[2] = 1;
                    }
                    if ((value4 == 0)) {
                        values[3] = 0;
                    }
                    if ((value4 > 40) && (value4 < 150)) {
                        values[3] = 1;
                    }
                    if ((value5 == 0)) {
                        values[4] = 0;
                    }
                    if ((value5 > 40) && (value5 < 150)) {
                        values[4] = 1;
                    }
                    if ((value6 == 0)) {
                        values[5] = 0;
                    }
                    if ((value6 > 40) && (value6 < 150)) {
                        values[5] = 1;
                    }


                    Between.getInstance().setMessage(values);
                    Between.getInstance().updateStatus();


                    Thread.sleep(100);

                    sl1.clear();
                    sl2.clear();
                    sl3.clear();
                    sl4.clear();
                    sl5.clear();
                    sl6.clear();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * callback
     * @param lightThresholdSensor the sensor
     * @param i    the threshold value
     */
    public void thresholdExceed(LightThresholdSensor lightThresholdSensor, int i) {
        try {
            if (lightThresholdSensor == s1) {
                sl1.put(i);
            } else if (lightThresholdSensor == s2) {
                sl2.put(i);
            } else if (lightThresholdSensor == s3) {
                sl3.put(i);
            } else if (lightThresholdSensor == s4) {
                sl4.put(i);
            } else if (lightThresholdSensor == s5) {
                sl5.put(i);
            } else if (lightThresholdSensor == s6) {
                sl6.put(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
