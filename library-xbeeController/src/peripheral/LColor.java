package peripheral;


public class LColor {

    private int red;
    private int green;
    private int blue;

    /**
     * constructor
     *
     * @param r value of red
     * @param g value of green
     * @param b value of blue
     */
    public LColor(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    /**
     * @return the value of red color
     */
    public int red() {
        return red;
    }

    /**
     * @return the value of the green color
     */
    public int green() {
        return green;
    }

    /**
     * @return the value of blue value
     */
    public int blue() {
        return blue;
    }


}
