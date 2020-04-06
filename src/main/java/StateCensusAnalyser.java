import Exception.StateCensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {

    int totalEntries;   // INITIALISING A VARIABLE TO STORE ALL CSV ENTRIES COUNT

    public int CensusCSVData(String csvPath) throws IOException, StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvPath))
        ) {
            // USE OF POJO FILE TO ITERATE AND PRINT ENTRIES OF CSV FILE
            CsvToBean<CSVStateCensus> csvStateCensuses = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStateCensus> csvStateCensusIterator = csvStateCensuses.iterator();
            while (csvStateCensusIterator.hasNext()) {
                // READ ALL THE CSV CONTENTS INTO MEMORY
                CSVStateCensus stateCensus = csvStateCensusIterator.next();
                System.out.println("State : " + stateCensus.getState());
                System.out.println("Population : " + stateCensus.getPopulation());
                System.out.println("AreaInSqKm : " + stateCensus.getAreaInSqKm());
                System.out.println("DensityPerSqKm : " + stateCensus.getDensityPerSqKm());
                System.out.println("================================================");
                totalEntries++;
            }
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getMessage());
        }
        System.out.println(totalEntries);
        return totalEntries;
    }
}