package adapter;

import Exception.*;
import Factory.CSVBuilderFactory;
import Factory.ICSVBuilder;
import dao.CensusDAO;
import dto.CSVStateCensusIndia;
import dto.CSVStatesCodeCensusIndia;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {
    @Override
    public  Map<String, CensusDAO> CensusCSVData(String... csvFilePath) throws CSVException, StateCensusAnalyserException {
        Map<String, CensusDAO> censusStateMap = super.CensusCSVData(CSVStateCensusIndia.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusStateMap;
        return this.csvStateCodeData(censusStateMap, csvFilePath[1]);
    }

    //READING AND PRINTING DATA FROM STATE CODE CSV FILE
    public static Map<String, CensusDAO> csvStateCodeData(Map<String, CensusDAO> censusDAOMap, String getPath) throws CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStatesCodeCensusIndia> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CSVStatesCodeCensusIndia.class);
            Iterable<CSVStatesCodeCensusIndia> csvStateCodeIterable = () -> csvFileIterator;
            StreamSupport.stream(csvStateCodeIterable.spliterator(), false)
                    .map(CSVStatesCodeCensusIndia.class::cast)
                    .forEach(csvStateCode -> censusDAOMap.put(csvStateCode.stateName, new CensusDAO(csvStateCode)));
            return censusDAOMap;
        } catch (IOException e) {
            throw new CSVException(CSVException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new CSVException(CSVException.ExceptionType.INCORRECT_FILE, e.getCause());
        }
    }
}