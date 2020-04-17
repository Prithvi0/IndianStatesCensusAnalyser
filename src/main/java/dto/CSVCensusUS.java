package dto;

import com.opencsv.bean.CsvBindByName;

public class CSVCensusUS {
    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "State")
    public String state;

    @CsvBindByName(column = "Population")
    public Integer population;

    @CsvBindByName(column = "Total area", required = true)
    public Double area;

    @CsvBindByName(column = "Population Density", required = true)
    public Double populationDensity;

    public CSVCensusUS(String stateId, String state, Integer population, Double area, Double populationDensity) {
        this.stateId = stateId;
        this.state = state;
        this.population = population;
        this.area = area;
        this.populationDensity = populationDensity;
    }
}