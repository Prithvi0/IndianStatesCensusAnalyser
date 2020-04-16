import com.google.gson.Gson;
import dao.IndiaCensusDAO;
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

    List<IndiaCensusDAO> indiaCensusDAOList = new ArrayList<>();
    Map<String, IndiaCensusDAO> indiaCensusDAOMap = new HashMap<>();

    //  METHOD TO TAKE LIST FROM STATE CENSUS CSV FILE
    public int CensusCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCensusIndia> csvStateCensusIndiaIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensusIndia.class);
            int totalEntries = 0;
            while (csvStateCensusIndiaIterator.hasNext()) {
                totalEntries++;
                IndiaCensusDAO indiaCensusDAO = new IndiaCensusDAO(csvStateCensusIndiaIterator.next());
            }
                return totalEntries;
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        } catch (CSVException | IOException e) {
            e.printStackTrace();
        }
        return this.indiaCensusDAOList.size();
    }

    //  METHOD TO TAKE LIST FROM STATE CODE CSV FILE
    public int CensusCodeCSVData(String getPath) throws StateCensusAnalyserException, CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator csvStatesCodeCensusIndiaIterator = csvBuilder.getCSVFileIterator(reader, CSVStatesCodeCensusIndia.class);
            Iterable<CSVStatesCodeCensusIndia> csvStatesCodeCensusIndiaIterable = () -> csvStatesCodeCensusIndiaIterator;
            StreamSupport.stream(csvStatesCodeCensusIndiaIterable.spliterator(), false)
                    .map(CSVStatesCodeCensusIndia.class::cast)
                    .forEach(csvStatesCodeCensusIndia -> indiaCensusDAOMap.put(csvStatesCodeCensusIndia.getStateName(), new IndiaCensusDAO(csvStatesCodeCensusIndia)));
            return this.indiaCensusDAOMap.size();
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.indiaCensusDAOMap.size();
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES
    public String getStateWiseSortedCensusData() throws StateCensusAnalyserException {
        if (indiaCensusDAOList == null || indiaCensusDAOList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing(census -> census.state);
        this.censusSort(censusCSVComparator);
        return new Gson().toJson(indiaCensusDAOList);
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES CODE
    public String getStateWiseSortedStateCode() throws StateCensusAnalyserException {
        if (indiaCensusDAOList == null || indiaCensusDAOList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_STATE_CODE_DATA, "No Census State Code Data");
        Comparator<IndiaCensusDAO> censusCSVCodeComparator = Comparator.comparing(census -> census.stateCode);
        this.censusSort(censusCSVCodeComparator);
        return new Gson().toJson(indiaCensusDAOList);
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES POPULATION
    public String getStateWiseSortedPopulation() throws StateCensusAnalyserException{
        if(indiaCensusDAOList == null || indiaCensusDAOList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Population Data");
        Comparator<IndiaCensusDAO> indiaCensusDaoComparator = Comparator.comparing(census -> census.population);
        this.censusSort(indiaCensusDaoComparator);
        return new Gson().toJson(this.indiaCensusDAOList);
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES DENSITY (PER KILOMETER SQUARE)
    public String getDensityInSqKmWiseSortedCensusData() throws StateCensusAnalyserException {
        if (indiaCensusDAOList == null || indiaCensusDAOList.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Density/kmSq Data");
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.censusSort(censusComparator);
        return new Gson().toJson(this.indiaCensusDAOList);
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES AREA (PER KILOMETER SQUARE)
    public String getAreaInSqKmWiseSortedCensusData() throws StateCensusAnalyserException {
        if (indiaCensusDAOList == null || indiaCensusDAOList.size() == 0) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Km. Sq. Area Data");
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        this.censusSort(censusComparator);
        return new Gson().toJson(this.indiaCensusDAOList);
    }

    // METHOD TO SORT CSV FILE, ALPHABETICALLY
    private <T extends IndiaCensusDAO> void censusSort(Comparator<T> censusCSVComparator) {
        IntStream.range(0, indiaCensusDAOList.size() - 1)
            .flatMap(csvLengthIteration -> IntStream.range(0, indiaCensusDAOList.size() - 1))
            .forEachOrdered(csvDataIteration -> {
                T censusCSV1 = (T) indiaCensusDAOList.get(csvDataIteration);
                T censusCSV2 = (T) indiaCensusDAOList.get(csvDataIteration + 1);
                if (censusCSVComparator.compare(censusCSV1, censusCSV2) > 0) {
                    indiaCensusDAOList.set(csvDataIteration, censusCSV2);
                    indiaCensusDAOList.set(csvDataIteration + 1, censusCSV1);
                }
            });
    }
    }