package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-12-24 06:51:28 EST
// -----( ON-HOST: 10.168.51.4

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.io.*;
import java.text.*;
// --- <<IS-END-IMPORTS>> ---

public final class date

{
	// ---( internal utility methods )---

	final static date _instance = new date();

	static date _newInstance() { return new date(); }

	static date _cast(Object o) { return (date)o; }

	// ---( server methods )---




	public static final void calculateDateDifference (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(calculateDateDifference)>> ---
		// @sigtype java 3.5
		// [i] field:0:required startDateTime
		// [i] field:0:required endDateTime
		// [i] field:0:required startDateFormat
		// [i] field:0:required endDateFormat
		// [o] field:0:required dateDifferenceSec
		// [o] field:0:required dateDifferenceMin
		// [o] field:0:required dateDifferenceHr
		// [o] field:0:required dateDifferenceDay
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	startDateTime = "";
		String	endDateTime = "";
		String startDateFormat = "";
		String endDateFormat = "";
		if (pipelineCursor.first("startDateTime"))
		{
			startDateTime = (String) pipelineCursor.getValue();
		}
		if (pipelineCursor.first("endDateTime"))
		{
			endDateTime = (String) pipelineCursor.getValue();
		}
		if (pipelineCursor.first("startDateFormat"))
		{
			startDateFormat = (String) pipelineCursor.getValue();
		}
		if (pipelineCursor.first("endDateFormat"))
		{
			endDateFormat = (String) pipelineCursor.getValue();
		}
		
		if (startDateTime.equals("") || endDateTime.equals(""))
			throw new ServiceException("Dates cannot be null");
		if (startDateFormat.equals("") || endDateFormat.equals(""))
			throw new ServiceException("Date formats cannot be null");
		
		pipelineCursor.destroy();
		
		try {
				SimpleDateFormat sdf = new SimpleDateFormat(startDateFormat);
				Date sdt = sdf.parse(startDateTime);
				SimpleDateFormat edf = new SimpleDateFormat(endDateFormat);
				Date edt = edf.parse(endDateTime);
				long  timediff = edt.getTime() - sdt.getTime();
		
		//		SimpleDateFormat ssdf = new SimpleDateFormat("HH:mm:ss");
		//		Calendar cal = Calendar.getInstance();
		//		cal.clear();
		//		cal.set(Calendar.SECOND, (int) timediff /1000);
		
		//		Date newDateTime = cal.getTime();
		//		String displayTime=null;
		
		//		if (cal.get(Calendar.DAY_OF_YEAR) > 1 )
		//		    displayTime = ssdf.format(newDateTime) + " and " + (cal.get(Calendar.DAY_OF_YEAR)-1) + " Day(s)" ;
		//		else 
		//		    displayTime = ssdf.format(newDateTime);
		
				String displayTimeSec = Long.toString(timediff/1000);
				String displayTimeMin = Long.toString(timediff/60000);
				String displayTimeHr = Long.toString(timediff/3600000);
				String displayTimeDay = Long.toString(timediff/86400000);
		
				// pipeline
				IDataCursor pipelineCursor_1 = pipeline.getCursor();
				pipelineCursor_1.last();
				pipelineCursor_1.insertAfter( "dateDifferenceSec", displayTimeSec);
				pipelineCursor_1.insertAfter( "dateDifferenceMin", displayTimeMin);
				pipelineCursor_1.insertAfter( "dateDifferenceHr", displayTimeHr);
				pipelineCursor_1.insertAfter( "dateDifferenceDay", displayTimeDay);
				pipelineCursor_1.destroy();
			} catch (ParseException e) {
				throw new ServiceException("Error parsing the date time: " + e);
			}
		// --- <<IS-END>> ---

                
	}
}

