package siia.monitoring.wiretap;

/**
 * @author Mark Fisher
 */
public class DebitService {

	public void process(Debit debit) {
		System.out.println("processing debit [" +
				debit.getAmount() + "] from account: " +
				debit.getAccountId());
		}

}
