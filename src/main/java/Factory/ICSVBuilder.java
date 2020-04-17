package Factory;

import Exception.CSVException;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<T> {
   //  GENERIC METHODS TO READ AND ITERATE CSV CONTENTS
   public Iterator<T> getCSVFileIterator(Reader reader, Class<T> csvStatesClass) throws CSVException;
}