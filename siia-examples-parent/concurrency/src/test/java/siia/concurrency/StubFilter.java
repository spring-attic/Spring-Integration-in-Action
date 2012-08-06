package siia.concurrency;

import java.util.List;

import org.springframework.integration.file.filters.FileListFilter;

/**
 * @author Marius Bogoevici
 */
public class StubFilter implements FileListFilter {

    @Override
    public List filterFiles(Object[] files) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
