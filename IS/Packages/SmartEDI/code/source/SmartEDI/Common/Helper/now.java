package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-12-24 06:51:48 EST
// -----( ON-HOST: 10.168.51.4

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.tn.db.BizDocStore;
import com.wm.app.tn.doc.BizDocEnvelope;
import com.wm.app.tn.doc.BizDocErrorSet;
import com.wm.app.tn.err.ActivityLogEntry;
import com.wm.lang.ns.*;
import com.wm.lang.ns.NSService;
import com.wm.app.b2b.server.*;
// --- <<IS-END-IMPORTS>> ---

public final class now

{
	// ---( internal utility methods )---

	final static now _instance = new now();

	static now _newInstance() { return new now(); }

	static now _cast(Object o) { return (now)o; }

	// ---( server methods )---




	public static final void now (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(now)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional now
		// [o] field:0:required now
		// [o] field:0:required delta
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	now = IDataUtil.getString( pipelineCursor, "now" );
		pipelineCursor.destroy();
		
		long newNow = System.currentTimeMillis();
		
		if(now != null)
		{
			long then = (new Long(now)).longValue();
			IDataCursor pipelineCursor_1 = pipeline.getCursor();
			IDataUtil.put( pipelineCursor_1, "delta", (new Long(newNow-then)).toString() );
			pipelineCursor_1.destroy();
		}
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "now", (new Long(newNow)).toString() );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}
}

