package Factory;
import Exception.CSVException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<T> {
   //  GENERIC METHODS TO READ AND ITERATE CSV CONTENTS
   public Iterator<T> getCSVFileIterator(Reader reader, Class<T> csvStatesClass) throws CSVException;
   public List<T> getCSVFileList(Reader reader, Class<T> csvStatesClass) throws CSVException;
}