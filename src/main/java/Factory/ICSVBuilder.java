package Factory;
import Exception.CSVException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<T> {
   public <T> List getCSVStatesCodeList(Reader reader, Class<T> csvStatesClass) throws CSVException;

   public <T> Iterator getCSVFileIterator(Reader reader, Class csvStatesClass) throws CSVException;
}