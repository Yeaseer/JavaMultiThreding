public class SensorData10 {
    private final int id;
    private final double reading;

    public SensorData10(int id, double reading) {
        this.id = id;
        this.reading = reading;
    }

    public int getId() {
        return id;
    }

    public double getReading() {
        return reading;
    }

    @Override
    public String toString() {
        return "SensorData{id=" + id + ", reading=" + reading + "}";
    }
}
