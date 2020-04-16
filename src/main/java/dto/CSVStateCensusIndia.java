package dto;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensusIndia {
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public Integer population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public Double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public Double densityPerSqKm;

    // METHODS TO GET THE DATA FROM THE CSV FILE
    public String getState() {
        return state;
    }

    public Integer getPopulation() {
        return population;
    }

    public Double getAreaInSqKm() {
        return areaInSqKm;
    }

    public Double getDensityPerSqKm() {
        return densityPerSqKm;
    }
}
