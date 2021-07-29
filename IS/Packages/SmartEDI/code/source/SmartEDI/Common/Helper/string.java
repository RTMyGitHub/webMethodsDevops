package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-01-14 16:52:58 EST
// -----( ON-HOST: 10.170.16.230

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
// --- <<IS-END-IMPORTS>> ---

public final class string

{
	// ---( internal utility methods )---

	final static string _instance = new string();

	static string _newInstance() { return new string(); }

	static string _cast(Object o) { return (string)o; }

	// ---( server methods )---




	public static final void convertNumberToString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(convertNumberToString)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:required inNum
		// [o] field:0:required outStr
		IDataCursor idcPipeline = null;
		String outOutput = null;
		try{
			idcPipeline = pipeline.getCursor();
			MInteger intInput;
			if (idcPipeline.first("inNum"))
			{
				intInput = (MInteger)idcPipeline.getValue();
			}
			else
			{
				throw new ServiceException("Input must be supplied!");
			}
			outOutput = String.valueOf(intInput);
			
			idcPipeline.insertAfter("outStr", outOutput);
		}
		catch (java.lang.Exception e) 
		{
			throw new ServiceException(e.getMessage());
		}
		finally
		{
			if(idcPipeline != null)
			{
				idcPipeline.destroy();
			}
		}
		// --- <<IS-END>> ---

                
	}


    public static final Values multiConcat (Values in)
    {
        Values out = in;
		// --- <<IS-START(multiConcat)>> ---
		// @sigtype java 3.0
		// [i] field:0:required inStr1
		// [i] field:0:required inStr2
		// [i] field:0:required inStr3
		// [i] field:0:required inStr4
		// [i] field:0:required inStr5
		// [i] field:0:required inStr6
		// [i] field:0:required inStr7
		// [i] field:0:required inStr8
		// [i] field:0:required inStr9
		// [i] field:0:required inStr10
		// [o] field:0:required outStr
		/** Service takes in up to ten strings, checks them for null (see Shared tab method checkNull), and
		  * concatenates all of them together. checkNull returns a "" if the String is null, effectively
		  * cancelling out its effect on the concatenation. Returns the concatenated String as "outStr".
		  *
		  * @author Tom Tan, Professional Services, webMethods, Inc.
		  * @version 1.0
		  */
		
		
		String str1 = checkNull(in.getString("inStr1"));
		String str2 = checkNull(in.getString("inStr2"));
		String str3 = checkNull(in.getString("inStr3"));
		String str4 = checkNull(in.getString("inStr4"));
		String str5 = checkNull(in.getString("inStr5"));
		String str6 = checkNull(in.getString("inStr6"));
		String str7 = checkNull(in.getString("inStr7"));
		String str8 = checkNull(in.getString("inStr8"));
		String str9 = checkNull(in.getString("inStr9"));
		String str10 = checkNull(in.getString("inStr10"));
		
		String outStr = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9 + str10;
		
		out.put( "outStr", outStr );
			
		// --- <<IS-END>> ---
        return out;
                
	}

	// --- <<IS-START-SHARED>> ---
	/** Used by "multiConcat"
	  * 
	  * @author Tom Tan, Professional Services, webMethods, Inc.
	  * @version 1.0
	  */
	private static String checkNull(String inputString)
	{
		if( inputString == null )
			return "";
		else
			return inputString;
	}
	// --- <<IS-END-SHARED>> ---
}

