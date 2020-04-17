package dao;

import dto.CSVCensusUS;
import dto.CSVStateCensusIndia;
import dto.CSVStatesCodeCensusIndia;

public class CensusDAO {
    public String state;
    public Integer population;
    public Double areaInSqKm;
    public Double densityPerSqKm;
    public Integer srNo;
    public String stateName;
    public Integer tin;
    public String stateCode;

    public CensusDAO(CSVStateCensusIndia csvStateCensusIndia) {
        state = csvStateCensusIndia.state;
        population = csvStateCensusIndia.population;
        areaInSqKm = csvStateCensusIndia.areaInSqKm;
        densityPerSqKm = csvStateCensusIndia.densityPerSqKm;
    }

    public CensusDAO(CSVStatesCodeCensusIndia csvStatesCodeCensusIndia) {
        srNo = csvStatesCodeCensusIndia.srNo;
        stateName = csvStatesCodeCensusIndia.stateName;
        tin = csvStatesCodeCensusIndia.tin;
        stateCode = csvStatesCodeCensusIndia.stateCode;
    }

    public CensusDAO(CSVCensusUS csvCensusUS) {
        stateCode = csvCensusUS.stateId;
        state = csvCensusUS.state;
        population = csvCensusUS.population;
        areaInSqKm = csvCensusUS.area;
        densityPerSqKm = csvCensusUS.populationDensity;
    }

    private double getTotalArea() {
        return areaInSqKm;
    }

    private double getPopulationDensity() {
        return this.densityPerSqKm;
    }

    private double getPopulation() {
        return this.population;
    }
}