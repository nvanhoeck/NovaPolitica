package tools;

/**
 * Coordinate wordt gebruikt om vormen te tekenen met coordinaten binnen het canvas op basis van px of % (=type).
 * Wordt ook gebruikt om locaties te bepalen.
 */
public class Coordinate implements Comparable{
    private double x;
    private double y;
    private Type type;

    public Coordinate(double x, double y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {
        Coordinate c = (Coordinate) o;
        if(this.getY()< c.getY()){
            return -1;
        }else if(this.getY() == c.getY()) {
            if(this.getY()==0) {
                if (this.getX() < c.getX()) {
                    return -1;
                }else {
                    return 1;
                }
            }else {
                if (this.getX() < c.getX()) {
                    return 1;
                }else {
                    return -1;
                }
            }
        }else {
            return 1;
        }
    }

    public enum Type{
        PIXELBASED("Based on pixels"), PERCENTBASED("Based on Percent");

        private String desc;

        Type(String desc) {
            this.desc = desc;
        }
    }


}
