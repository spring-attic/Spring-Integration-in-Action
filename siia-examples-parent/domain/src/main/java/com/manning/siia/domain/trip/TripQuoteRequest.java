package com.manning.siia.domain.trip;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * @author Jonas Partner
 */

public class TripQuoteRequest {

    private List<LegQuoteRequest> legQuoteRequests;

    private TripQuoteRequest() {
    }

    public TripQuoteRequest(List<LegQuoteRequest> legQuoteRequests){
        this.legQuoteRequests = legQuoteRequests;
    }

    public List<LegQuoteRequest> getLegQuoteRequests() {
        return legQuoteRequests;
    }

    public void setLegQuoteRequests(List<LegQuoteRequest> legQuoteRequests) {
        this.legQuoteRequests = legQuoteRequests;
    }
    
}
