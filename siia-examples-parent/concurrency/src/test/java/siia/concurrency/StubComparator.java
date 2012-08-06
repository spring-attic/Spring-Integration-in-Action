package siia.concurrency;

import java.util.Comparator;

/**
 * @author Marius Bogoevici
 */
public class StubComparator implements Comparator<Object> {

    @Override
    public int compare(Object o, Object o1) {
        return 0;
    }
}
