package adapter;

import Exception.*;
import Factory.CSVBuilderFactory;
import Factory.ICSVBuilder;
import dao.CensusDAO;
import dto.CSVCensusUS;
import dto.CSVStateCensusIndia;
import org.apache.commons.collections.map.HashedMap;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {

    public abstract Map<String, CensusDAO> CensusCSVData(String... csvFilePath) throws CSVException, StateCensusAnalyserException;

    public <E> Map<String, CensusDAO> CensusCSVData(Class<E> censusCSVClass, String... getPath) throws CSVException, StateCensusAnalyserException {
        Map<String, CensusDAO> censusDAOMap = new HashedMap();
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath[0]));
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> csvStateCensusIterable = () -> csvFileIterator;
            if (censusCSVClass.getName().equals("dto.CSVStateCensusIndia"))
                StreamSupport.stream(csvStateCensusIterable.spliterator(), false)
                        .map(CSVStateCensusIndia.class::cast)
                        .forEach(CSVStateCensus -> censusDAOMap.put(CSVStateCensus.state, new CensusDAO(CSVStateCensus)));
            if (censusCSVClass.getName().equals("dto.CSVCensusUS"))
                StreamSupport.stream(csvStateCensusIterable.spliterator(), false)
                        .map(CSVCensusUS.class::cast)
                        .forEach(UsCSVData -> censusDAOMap.put(UsCSVData.state, new CensusDAO(UsCSVData)));
            return censusDAOMap;
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        }
    }
}