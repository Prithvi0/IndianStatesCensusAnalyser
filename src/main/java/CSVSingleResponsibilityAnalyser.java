import Exception.StateCensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class CSVSingleResponsibilityAnalyser {
    // GENERIC METHOD TO READ AND ITERATE CSV CONTENTS
    static  <T> Iterator<T> getCSVStateCensusDataIterator(Reader reader, Class<T> csvDataClass) throws StateCensusAnalyserException {
        try {
            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvDataClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<T> csvToBean = csvToBeanBuilder.build();
            Iterator<T> csvStatesDataIterator = csvToBean.iterator();
            return csvStatesDataIterator;
        } catch (IllegalStateException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.getCause());
        }
    }
}