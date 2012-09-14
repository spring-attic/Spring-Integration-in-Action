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

package com.manning.siia;

import siia.booking.domain.trip.LegQuoteCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

/**
 * @author Jonas Partner
 */
public class TripQuoteRequestProcessor {

    private Log logger = LogFactory.getLog(getClass());

    public Source processTripRequest(Source requestSource) throws Exception{
        logger.info("Trip request received");
        StringResult res = new StringResult();
        res.getWriter().append("<ok/>");
        return new StringSource(res.toString());
    }

    public OkResponse processTripRequest(String requestSource) throws Exception{
        logger.info("Trip request received");
        return new OkResponse();
    }

    public OkResponse processTripRequest(LegQuoteCommand legQuoteCommand) throws Exception{
        logger.info("Trip request received:" + legQuoteCommand);
        return new OkResponse();
    }

}
