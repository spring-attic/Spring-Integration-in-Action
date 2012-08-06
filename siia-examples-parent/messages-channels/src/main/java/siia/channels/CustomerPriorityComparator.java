package siia.channels;

import java.util.Comparator;

/**
 * @author Marius Bogoevici
 */
public class CustomerPriorityComparator implements Comparator<Booking> {

    @Override
    public int compare(Booking left, Booking right) {
        return left.getCustomerEmail().compareTo(right.getCustomerEmail());
    }
}
