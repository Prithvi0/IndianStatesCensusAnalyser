import com.google.gson.Gson;
import dao.CensusDAO;
import dto.CSVCensusUS;
import dto.CSVStateCensusIndia;
import dto.CSVStatesCodeCensusIndia;
import Exception.CSVException;
import Exception.StateCensusAnalyserException;
import Factory.CSVBuilderFactory;
import Factory.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {
    //  LIST AND MAP DECLARATION
    List<CensusDAO> censusDAOList = new ArrayList<>();
    Map<String, CensusDAO> censusDAOMap = new HashMap<>();

    //  METHOD TO TAKE LIST FROM INDIAN STATE CENSUS CSV FILE
    public int CensusCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCensusIndia> csvStateCensusIndiaIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensusIndia.class);
            int totalEntries = 0;
            while (csvStateCensusIndiaIterator.hasNext()) {
                totalEntries++;
                CensusDAO censusDAO = new CensusDAO(csvStateCensusIndiaIterator.next());
            }
                return totalEntries;
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        } catch (CSVException | IOException e) {
            e.printStackTrace();
        }
        return this.censusDAOList.size();
    }

    //  METHOD TO TAKE LIST FROM INDIAN STATE CODE CSV FILE
    public int CensusCodeCSVData(String getPath) throws StateCensusAnalyserException, CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator csvStatesCodeCensusIndiaIterator = csvBuilder.getCSVFileIterator(reader, CSVStatesCodeCensusIndia.class);
            Iterable<CSVStatesCodeCensusIndia> csvStatesCodeCensusIndiaIterable = () -> csvStatesCodeCensusIndiaIterator;
            StreamSupport.stream(csvStatesCodeCensusIndiaIterable.spliterator(), false)
                    .map(CSVStatesCodeCensusIndia.class::cast)
                    .forEach(csvStatesCodeCensusIndia -> censusDAOMap.put(csvStatesCodeCensusIndia.getStateName(), new CensusDAO(csvStatesCodeCensusIndia)));
            return this.censusDAOMap.size();
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.censusDAOMap.size();
    }

    //  METHOD TO TAKE LIST FROM US CENSUS CSV FILE
    public int USCensusData(String getPath) throws StateCensusAnalyserException,CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVCensusUS> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CSVCensusUS.class);
            Iterable<CSVCensusUS> csvStateCodeIterable = () -> csvFileIterator;
            StreamSupport.stream(csvStateCodeIterable.spliterator(),false)
                    .map(CSVCensusUS.class::cast)
                    .forEach(usCSVData -> censusDAOMap.put((usCSVData).getState(),new CensusDAO(usCSVData)));
            return this.censusDAOMap.size();
        } catch (IOException e) {
            throw new CSVException(CSVException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new CSVException(CSVException.ExceptionType.INCORRECT_FILE, e.getCause());
        }
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON INDIAN STATES
    public String getStateWiseSortedCensusData() throws StateCensusAnalyserException {
        if (censusDAOList == null || censusDAOList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing(census -> census.state);
        this.censusSort(censusCSVComparator);
        return new Gson().toJson(censusDAOList);
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON INDIAN STATES CODE
    public String getStateWiseSortedStateCode() throws StateCensusAnalyserException {
        if (censusDAOList == null || censusDAOList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_STATE_CODE_DATA, "No Census State Code Data");
        Comparator<CensusDAO> censusCSVCodeComparator = Comparator.comparing(census -> census.stateCode);
        this.censusSort(censusCSVCodeComparator);
        return new Gson().toJson(censusDAOList);
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON INDIAN STATES POPULATION
    public String getStateWiseSortedPopulation() throws StateCensusAnalyserException{
        if(censusDAOList == null || censusDAOList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Population Data");
        Comparator<CensusDAO> indiaCensusDaoComparator = Comparator.comparing(census -> census.population);
        this.censusSort(indiaCensusDaoComparator);
        return new Gson().toJson(this.censusDAOList);
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON INDIAN STATES DENSITY (PER KILOMETER SQUARE)
    public String getDensityInSqKmWiseSortedCensusData() throws StateCensusAnalyserException {
        if (censusDAOList == null || censusDAOList.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Density/kmSq Data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.censusSort(censusComparator);
        return new Gson().toJson(this.censusDAOList);
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON INDIAN STATES AREA (PER KILOMETER SQUARE)
    public String getAreaInSqKmWiseSortedCensusData() throws StateCensusAnalyserException {
        if (censusDAOList == null || censusDAOList.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Km. Sq. Area Data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        this.censusSort(censusComparator);
        return new Gson().toJson(this.censusDAOList);
    }

    // METHOD TO SORT CSV FILE, ALPHABETICALLY
    private <T extends CensusDAO> void censusSort(Comparator<T> censusCSVComparator) {
        IntStream.range(0, censusDAOList.size() - 1)
            .flatMap(csvLengthIteration -> IntStream.range(0, censusDAOList.size() - 1))
            .forEachOrdered(csvDataIteration -> {
                T censusCSV1 = (T) censusDAOList.get(csvDataIteration);
                T censusCSV2 = (T) censusDAOList.get(csvDataIteration + 1);
                if (censusCSVComparator.compare(censusCSV1, censusCSV2) > 0) {
                    censusDAOList.set(csvDataIteration, censusCSV2);
                    censusDAOList.set(csvDataIteration + 1, censusCSV1);
                }
            });
    }
}