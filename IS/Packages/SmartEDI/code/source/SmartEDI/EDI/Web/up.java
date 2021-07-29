package SmartEDI.EDI.Web;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 14:42:55 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.*;
// --- <<IS-END-IMPORTS>> ---

public final class up

{
	// ---( internal utility methods )---

	final static up _instance = new up();

	static up _newInstance() { return new up(); }

	static up _cast(Object o) { return (up)o; }

	// ---( server methods )---




	public static final void startup (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(startup)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IData ida = IDataFactory.create();
		ValuesEmulator.put(ida, "callback", "SQFramework.SQEDI.Web.init:initParams");
		
		try {
			Service.doInvoke( "wm.server.ui", "addSolution", ida );
		} catch(Exception t ) {
			ServerAPI.logError(t);
		} 
		// --- <<IS-END>> ---

                
	}
}

