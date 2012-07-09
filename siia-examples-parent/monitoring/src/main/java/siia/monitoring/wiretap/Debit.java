package siia.monitoring.wiretap;

import java.math.BigDecimal;

public class Debit {

	private final BigDecimal amount;
	private final String accountId;

	public Debit(BigDecimal amount, String accountId) {
		this.amount = amount;
		this.accountId = accountId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public String toString() {
		return "[amount=" + this.amount + ", accountId=" + accountId + "]";
	}
}