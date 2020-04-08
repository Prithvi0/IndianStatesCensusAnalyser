package Factory;

import Exception.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class CSVSingleResponsibiltyAnalyser<T> implements ICSVBuilder {
    @Override
    //  GENERIC METHOD TO READ AND ITERATE CSV CONTENTS
    public Iterator <T> getCSVFileIterator(Reader reader, Class csvStatesClass) throws StateCensusAnalyserException {
        try {
            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvStatesClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<T> csvToBean = csvToBeanBuilder.build();
            Iterator<T> csvStatesDataIterator = csvToBean.iterator();
            return csvStatesDataIterator;
        } catch (IllegalStateException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.getCause());
        }
    }
}