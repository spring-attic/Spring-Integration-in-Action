package com.manning.siia;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Result;
import javax.xml.transform.Source;


public class TripQuoteRequestProcessor {

    private Log logger = LogFactory.getLog(getClass());

    public Source processTripRequest(Source requestSource) throws Exception{
        logger.info("Trip request recevied");
        StringResult res = new StringResult();
        res.getWriter().append("<ok/>");
        return new StringSource(res.toString());
    }

}
