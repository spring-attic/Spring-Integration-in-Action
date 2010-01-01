package com.manning.siia.domain.binding;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

/**
 * @author Jonas Partner
 *
 */
public class JodaDateTimeBinder extends XmlAdapter<Date, DateTime> {
    @Override
    public DateTime unmarshal(Date v) throws Exception {
        return new DateTime(v.getTime());
    }

    @Override
    public Date marshal(DateTime v) throws Exception {
        return new Date(v.getMillis());
    }
}
