package siia.monitoring.wiretap;

public class DebitService {

	public void process(Debit debit) {
		System.out.println("processing debit [" +
				debit.getAmount() + "] from account: " +
				debit.getAccountId());
		}

}
