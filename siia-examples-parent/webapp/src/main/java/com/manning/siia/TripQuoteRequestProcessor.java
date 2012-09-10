package com.manning.siia;

import siia.booking.domain.trip.LegQuoteCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;


public class TripQuoteRequestProcessor {

    private Log logger = LogFactory.getLog(getClass());

    public Source processTripRequest(Source requestSource) throws Exception{
        logger.info("Trip request recevied");
        StringResult res = new StringResult();
        res.getWriter().append("<ok/>");
        return new StringSource(res.toString());
    }

    public OkResponse processTripRequest(String requestSource) throws Exception{
        logger.info("Trip request recevied");
        return new OkResponse();
    }

     public OkResponse processTripRequest(LegQuoteCommand legQuoteCommand) throws Exception{
        logger.info("Trip request received:" + legQuoteCommand);
        return new OkResponse();
    }

}
