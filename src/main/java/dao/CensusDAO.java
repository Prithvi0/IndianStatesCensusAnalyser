package dao;

import dto.CSVCensusUS;
import dto.CSVStateCensusIndia;
import dto.CSVStatesCodeCensusIndia;
import service.StateCensusAnalyser;

import java.util.Comparator;

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

    //  CENSUS SORTING IN ASCENDING ORDER
    public static Comparator<? super CensusDAO> getCensusSortComparator(StateCensusAnalyser.SortingMode mode) {
        if (mode.equals(StateCensusAnalyser.SortingMode.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(StateCensusAnalyser.SortingMode.STATECODE))
            return Comparator.comparing(census -> census.stateCode);
        if (mode.equals(StateCensusAnalyser.SortingMode.POPULATION))
            return Comparator.comparing(census -> census.population);
        if (mode.equals(StateCensusAnalyser.SortingMode.DENSITY))
            return Comparator.comparing(census -> census.densityPerSqKm);
        if (mode.equals(StateCensusAnalyser.SortingMode.AREA))
            return Comparator.comparing(census -> census.areaInSqKm);
        return null;
    }

    //  METHOD TO GENERATE SPECIFIC US DTO
    public Object getCensusDTO(StateCensusAnalyser.COUNTRY country) {
        if (country.equals(StateCensusAnalyser.COUNTRY.INDIA))
            return new CSVStateCensusIndia(state, stateCode, population, areaInSqKm, densityPerSqKm);
        if (country.equals(StateCensusAnalyser.COUNTRY.US))
            return new CSVCensusUS(state, stateCode, population, areaInSqKm, densityPerSqKm);
        return null;
    }
}