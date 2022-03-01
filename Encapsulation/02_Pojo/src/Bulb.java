public class Bulb {
    public boolean bulbOn;
    public boolean bulbOff;
    public boolean bulbOnFor50Percent;

   public Bulb (boolean bulbOn) {}

    public boolean isBulbOn() {
        return bulbOn;
    }

    public void setBulbOn(boolean bulbOn) {
        this.bulbOn = bulbOn;
    }

    public boolean isBulbOff() {
        return bulbOff;
    }

    public void setBulbOff(boolean bulbOff) {
        this.bulbOff = bulbOff;
    }

    public boolean isBulbOnFor50Percent() {
        return bulbOnFor50Percent;
    }

    public void setBulbOnFor50Percent(boolean bulbOnFor50Percent) {
        this.bulbOnFor50Percent = bulbOnFor50Percent;
    }
}
