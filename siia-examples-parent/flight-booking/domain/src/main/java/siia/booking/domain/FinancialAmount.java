/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package siia.booking.domain;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Jonas Partner
 */
public class FinancialAmount {

    private final Currency currency;

    private final BigDecimal amount;

    public FinancialAmount(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public FinancialAmount add(FinancialAmount financialAmount) {
        checkCurrenciesMatch(financialAmount);
        return new FinancialAmount(getCurrency(), financialAmount.getAmount());
    }

    protected void checkCurrenciesMatch(FinancialAmount financialAmount) {
        if (!financialAmount.getCurrency().equals(getCurrency())) {
            throw new IllegalArgumentException("Can not add financial amounts with different currencies " +
                    getCurrency() + " to " + financialAmount.getCurrency());
        }

    }

    @Override
    public String toString() {
        return getCurrency().getSymbol() + getAmount().setScale(2);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof FinancialAmount)){
            return false;
        }
        return super.equals(obj);
    }
}
