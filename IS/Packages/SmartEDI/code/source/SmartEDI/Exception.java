package SmartEDI;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-11-17 09:01:15 EST
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.data.IData;
import com.wm.data.ValuesEmulator;
import com.wm.lang.ns.NSService;
// --- <<IS-END-IMPORTS>> ---

public final class Exception

{
	// ---( internal utility methods )---

	final static Exception _instance = new Exception();

	static Exception _newInstance() { return new Exception(); }

	static Exception _cast(Object o) { return (Exception)o; }

	// ---( server methods )---




	public static final void getServiceName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getServiceName)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required currentServiceName
		NSService callingService = Service.getCallingService();
		
		if (callingService == null) {
		ValuesEmulator.put(pipeline, "currentServiceName" ,"");
		} else {
		ValuesEmulator.put(pipeline, "currentServiceName" ,callingService.toString());
		}
			
		// --- <<IS-END>> ---

                
	}
}

