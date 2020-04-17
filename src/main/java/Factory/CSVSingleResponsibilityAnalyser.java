package Factory;

import Exception.CSVException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class CSVSingleResponsibilityAnalyser <T> implements ICSVBuilder {

        //  GENERIC METHOD TO READ AND ITERATE CSV CONTENTS
        @Override
        public Iterator<T> getCSVFileIterator(Reader reader, Class csvStatesClass) throws CSVException {
                try {
                        CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                        csvToBeanBuilder.withType(csvStatesClass);
                        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                        CsvToBean<T> csvToBean = csvToBeanBuilder.build();
                        Iterator<T> csvStatesDataIterator = csvToBean.iterator();
                        return csvStatesDataIterator;
                } catch (IllegalStateException e) {
                        throw new CSVException(CSVException.ExceptionType.UNABLE_TO_PARSE, e.getCause());
                }
        }
}