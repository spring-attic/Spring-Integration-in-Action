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

package siia.booking.domain.car;

import siia.booking.domain.FinancialAmount;

/**
 * @author Jonas Partner
 */
public class CarRentalBooking {

    private final String confirmationId;

    private final CarRental rental;

    private final FinancialAmount rentalCost;

    private final boolean paid;


    public CarRentalBooking(String confirmationId, CarRental rental, FinancialAmount rentalCost, boolean paid) {
        this.confirmationId = confirmationId;
        this.rental = rental;
        this.rentalCost = rentalCost;
        this.paid = paid;
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public CarRental getRental() {
        return rental;
    }

    public FinancialAmount getRentalCost() {
        return rentalCost;
    }

    public boolean isPaid() {
        return paid;
    }
}
