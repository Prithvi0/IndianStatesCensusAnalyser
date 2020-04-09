package Factory;

import Exception.CSVException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class CSVSingleResponsibiltyAnalyser<T> implements ICSVBuilder {
    @Override
    //  GENERIC METHOD TO READ AND ITERATE CSV CONTENTS
    public Iterator<T> getCSVFileIterator(Reader reader, Class csvStatesClass) throws CSVException {
        try {
            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvStatesClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<T> csvToBean = csvToBeanBuilder.build();
            Iterator<T> csvStatesDataIterator = csvToBean.iterator();
            return csvStatesDataIterator;
        } catch (IllegalStateException e) {
            try {
                throw new CSVException(CSVException.ExceptionType.UNABLE_TO_PARSE, e.getCause());
            } catch (CSVException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    //  GENERIC METHOD TO PARSE LIST OF CSV CONTENTS
    public List<T> getCSVFileList(Reader reader, Class csvStatesClass) throws CSVException {
        CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        csvToBeanBuilder.withType(csvStatesClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        return csvToBeanBuilder.build().parse();
    }
}