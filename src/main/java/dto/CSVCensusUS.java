package dto;

import com.opencsv.bean.CsvBindByName;

public class CSVCensusUS {
    @CsvBindByName(column = "State Id", required = true)
    private String stateId;

    @CsvBindByName(column = "State")
    private String state;

    @CsvBindByName(column = "Population")
    private Integer population;

    @CsvBindByName(column = "Housing units", required = true)
    private Integer housingUnits;

    @CsvBindByName(column = "Total area", required = true)
    private Double area;

    @CsvBindByName(column = "Water area", required = true)
    private Double waterArea;

    @CsvBindByName(column = "Land area", required = true)
    private Double landArea;

    @CsvBindByName(column = "Population Density", required = true)
    private Double populationDensity;

    @CsvBindByName(column = "Housing Density", required = true)
    private Double housingDensity;

    public String getStateId() {
        return stateId;
    }

    public String getState() {
        return state;
    }

    public Integer getPopulation() {
        return population;
    }

    public Integer getHousingUnits() {
        return housingUnits;
    }

    public Double getArea() {
        return area;
    }

    public Double getWaterArea() {
        return waterArea;
    }

    public Double getLandArea() {
        return landArea;
    }

    public Double getPopulationDensity() {
        return populationDensity;
    }

    public Double getHousingDensity() {
        return housingDensity;
    }
}