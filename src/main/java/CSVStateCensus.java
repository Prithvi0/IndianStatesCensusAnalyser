import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus extends CSVStatesCode {
    @CsvBindByName(column = "State", required = true)
    private String state;

    @CsvBindByName(column = "Population", required = true)
    private String population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    private String areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private String densityPerSqKm;

    // METHOD TO RETURN DETAILS FROM CSV FILE
    @Override
    public String toString() {
        return "CSVStateCensus{" +
                "state='" + state + '\'' +
                ", population='" + population + '\'' +
                ", areaInSqKm='" + areaInSqKm + '\'' +
                ", densityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
    // METHODS TO GET AND SET THE DATA FROM THE CSV FILE
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(String densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    public String compareToIgnoreCase(CSVStateCensus census2) {
        return state;
    }
}