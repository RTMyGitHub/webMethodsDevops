package SmartEDI.EDI.Web;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-11-17 08:56:23 EST
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.data.IData;
import com.wm.data.ValuesEmulator;
// --- <<IS-END-IMPORTS>> ---

public final class init

{
	// ---( internal utility methods )---

	final static init _instance = new init();

	static init _newInstance() { return new init(); }

	static init _cast(Object o) { return (init)o; }

	// ---( server methods )---




	public static final void initParams (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(initParams)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ValuesEmulator.put(pipeline, "name", "SciQuest WM Framework");
		ValuesEmulator.put(pipeline, "url", "../SQFramework/");
		ValuesEmulator.put(pipeline, "target", "SciQuest WM Framework");
		ValuesEmulator.put(pipeline, "text", "SciQuest WM Framework"); 
			
			
		// --- <<IS-END>> ---

                
	}
}

