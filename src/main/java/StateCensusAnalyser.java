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

public class StateCensusAnalyser {
    List<IndiaCensusDAO> indiaCensusDAOList = new ArrayList<>();
    Map<String, IndiaCensusDAO> indiaCensusDAOMap = new HashMap<>();

    //  METHOD TO TAKE LIST FROM STATE CENSUS CSV FILE
    public int CensusCSVData(String getPath) throws StateCensusAnalyserException {
        int totalRecords = 0;
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
        return totalRecords;
    }

    //  METHOD TO TAKE LIST FROM STATE CODE CSV FILE
    public int CensusCodeCSVData(String getPath) throws StateCensusAnalyserException, CSVException {
        int totalRecords = 0;
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStatesCodeCensusIndia> csvStatesCodeCensusIndiaIterator = csvBuilder.getCSVFileIterator(reader, CSVStatesCodeCensusIndia.class);
            while (csvStatesCodeCensusIndiaIterator.hasNext()) {
                IndiaCensusDAO indiaCensusDAO = new IndiaCensusDAO(csvStatesCodeCensusIndiaIterator.next());
                indiaCensusDAOList = new ArrayList<>(indiaCensusDAOMap.values());
            }
            totalRecords = indiaCensusDAOMap.size();
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalRecords;
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES
    public String getStateWiseSortedCensusData() throws StateCensusAnalyserException {
        if (indiaCensusDAOList == null || indiaCensusDAOList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing(census -> census.state);
        this.censusSort(censusCSVComparator, (Map<Object, Object>) indiaCensusDAOList);
        String sortedStateCensusJson = new Gson().toJson(indiaCensusDAOList);
        return sortedStateCensusJson;
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES CODE
    public String getStateWiseSortedStateCode() throws StateCensusAnalyserException {
        if (indiaCensusDAOList == null || indiaCensusDAOList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_STATE_CODE_DATA, "No State Data");
        Comparator<IndiaCensusDAO> censusCSVCodeComparator = Comparator.comparing(census -> census.stateCode);
        this.censusSort(censusCSVCodeComparator, (Map<Object, Object>) indiaCensusDAOList);
        return new Gson().toJson(indiaCensusDAOList);
    }

    // METHOD TO SORT CSV FILE USING MAP, ALPHABETICALLY
    private <T> void censusSort(Comparator censusCSVComparator, Map<Object, Object> csvMap) {
        IntStream.range(0, csvMap.size() - 1)
                .flatMap(csvLengthIteration -> IntStream.range(0, csvMap.size() - 1))
                .forEachOrdered(csvDataIteration -> {
                    T censusCSV1 = (T) csvMap.get(csvDataIteration);
                    T censusCSV2 = (T) csvMap.get(csvDataIteration + 1);
                    if (censusCSVComparator.compare(censusCSV1, censusCSV2) > 0) {
                        csvMap.put(csvDataIteration, censusCSV2);
                        csvMap.put(csvDataIteration + 1, censusCSV1);
                    }
                });
    }
}
