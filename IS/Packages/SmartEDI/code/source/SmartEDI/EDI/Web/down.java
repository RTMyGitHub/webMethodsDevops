package SmartEDI.EDI.Web;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 14:40:54 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class down

{
	// ---( internal utility methods )---

	final static down _instance = new down();

	static down _newInstance() { return new down(); }

	static down _cast(Object o) { return (down)o; }

	// ---( server methods )---




	public static final void shutdown (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(shutdown)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IData in = IDataFactory.create();
		ValuesEmulator.put(in, "callback", "SQFramework.SQEDI.Web.init:initParams");	
		try {
			Service.doInvoke( "wm.server.ui", "removeSolution", in ); 
		} catch(Exception t ) {}
			
		// --- <<IS-END>> ---

                
	}
}

