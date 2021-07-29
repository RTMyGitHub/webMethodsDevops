package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-08-19 09:23:28 EDT
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import com.wm.app.b2b.server.*;
// --- <<IS-END-IMPORTS>> ---

public final class info

{
	// ---( internal utility methods )---

	final static info _instance = new info();

	static info _newInstance() { return new info(); }

	static info _cast(Object o) { return (info)o; }

	// ---( server methods )---




	public static final void getServerInfo (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getServerInfo)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required serverName
		// [o] field:0:required port
		// [o] field:0:required ipAddress
		IDataHashCursor idc = pipeline.getHashCursor();
		
		
		
			Properties config = System.getProperties();
		
			String portStr = config.getProperty("watt.server.port");
		
			String hostname = ServerAPI.getServerName();
		        String ip = null;
		
		
			// pipeline
			idc.insertAfter( "serverName", hostname );
			idc.insertAfter( "port", portStr );
		
			idc.destroy(); 
		
		
		
		
			
		// --- <<IS-END>> ---

                
	}
}

