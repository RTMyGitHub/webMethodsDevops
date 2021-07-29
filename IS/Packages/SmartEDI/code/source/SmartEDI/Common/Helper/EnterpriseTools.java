package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-01-14 16:19:48 EST
// -----( ON-HOST: 10.170.16.230

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.*;
import com.wm.app.b2b.server.*;
// --- <<IS-END-IMPORTS>> ---

public final class EnterpriseTools

{
	// ---( internal utility methods )---

	final static EnterpriseTools _instance = new EnterpriseTools();

	static EnterpriseTools _newInstance() { return new EnterpriseTools(); }

	static EnterpriseTools _cast(Object o) { return (EnterpriseTools)o; }

	// ---( server methods )---




	public static final void getClientIP (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getClientIP)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required IPAddress
		InvokeState is = new InvokeState();
		String ip = null;
		
		ip = is.getCurrentSocket().getInetAddress().toString();
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "IPAddress", ip );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}
}

