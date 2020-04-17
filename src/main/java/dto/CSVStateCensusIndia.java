package dto;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensusIndia extends CSVStatesCodeCensusIndia {

    public CSVStateCensusIndia() { }

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public Integer population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public Double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public Double densityPerSqKm;

    public String stateCode = super.stateCode;

    public CSVStateCensusIndia(String state, String stateCode, Integer population, Double areaInSqKm, Double densityPerSqKm) {
        this.state = state;
        this.stateCode = stateCode;
        this.areaInSqKm = areaInSqKm;
        this.population = population;
        this.densityPerSqKm = densityPerSqKm;
    }
}
