package com.manning.siia.domain.binding;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Jonas Partner
 */
public class JodaDateTimeAdapter extends XmlAdapter<Calendar, DateTime> {
    @Override
    public DateTime unmarshal(Calendar cal) throws Exception {
        return new DateTime(cal.getTime(), ISOChronology.getInstanceUTC());
    }

    @Override
    public Calendar marshal(DateTime dt) throws Exception {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(dt.getMillis());
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        return cal;
    }
}
