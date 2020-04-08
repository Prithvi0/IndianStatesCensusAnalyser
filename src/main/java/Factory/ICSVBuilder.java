package Factory;

import Exception.*;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<T> {
   public Iterator<T> getCSVFileIterator(Reader reader, Class<T> csvStatesClass) throws StateCensusAnalyserException, CSVException;
}