package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-01-14 16:57:05 EST
// -----( ON-HOST: 10.170.16.230

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.Session;
import java.util.*;
// --- <<IS-END-IMPORTS>> ---

public final class session

{
	// ---( internal utility methods )---

	final static session _instance = new session();

	static session _newInstance() { return new session(); }

	static session _cast(Object o) { return (session)o; }

	// ---( server methods )---




	public static final void getSessionID (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getSessionID)>> ---
		// @sigtype java 3.5
		// [o] field:0:required sessionID
	IDataCursor idcPipeline = pipeline.getCursor();

	Session session = Service.getSession();
	String strSessionID = session.getSessionID();


	idcPipeline.insertAfter("sessionID", strSessionID);

	idcPipeline.destroy();

		// --- <<IS-END>> ---

                
	}
}

