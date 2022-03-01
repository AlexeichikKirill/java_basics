public class Country {
    public String nameCountry;
    public double population;
    public double area;
    public String capital;
    public boolean accessToTheSea;

    public Country(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setAccessToTheSea(boolean accessToTheSea) {
        this.accessToTheSea = accessToTheSea;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public double getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public String getCapital() {
        return capital;
    }

    public boolean isAccessToTheSea() {
        return accessToTheSea;
    }
}
