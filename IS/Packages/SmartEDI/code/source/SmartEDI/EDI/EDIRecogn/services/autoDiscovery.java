package SmartEDI.EDI.EDIRecogn.services;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-11-17 07:42:32 EST
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ns.*;
import com.wm.lang.ns.*;
// --- <<IS-END-IMPORTS>> ---

public final class autoDiscovery

{
	// ---( internal utility methods )---

	final static autoDiscovery _instance = new autoDiscovery();

	static autoDiscovery _newInstance() { return new autoDiscovery(); }

	static autoDiscovery _cast(Object o) { return (autoDiscovery)o; }

	// ---( server methods )---




	public static final void nsTemplateMgr (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(nsTemplateMgr)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required sender
		// [i] field:0:required receiver
		// [i] field:0:required version
		// [i] field:0:required transaction
		// [o] field:0:required service
			String sender = ValuesEmulator.getString(pipeline, "sender");
			String receiver = ValuesEmulator.getString(pipeline, "receiver");
			String version = ValuesEmulator.getString(pipeline, "version");
			String transaction  = ValuesEmulator.getString(pipeline, "transaction");
			
			if (sender==null || sender.trim().length()==0) return;
			if (receiver==null || receiver.trim().length()==0) return;
			if (version==null || version.trim().length()==0) return;
			if (transaction==null || transaction.trim().length()==0) return;
		
			String dot = ".";
		
		
		
		
		
		
				String ifc = sender +dot+ receiver+dot+ version+dot+ transaction;
				String nsName = "transformEDI";
		
		
				if ((version.equalsIgnoreCase("anyversion")) && (transaction.equalsIgnoreCase("anytrans"))){
				nsName = "passThroughEDI";
				}
		
		
		
		
		
		
		
		ValuesEmulator.put(pipeline, "service", ifc+":"+nsName);
		
			
		// --- <<IS-END>> ---

                
	}
}

