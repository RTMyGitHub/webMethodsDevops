package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-01-14 12:39:56 EST
// -----( ON-HOST: 10.170.16.230

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

public final class invoke

{
	// ---( internal utility methods )---

	final static invoke _instance = new invoke();

	static invoke _newInstance() { return new invoke(); }

	static invoke _cast(Object o) { return (invoke)o; }

	// ---( server methods )---




	public static final void invoke (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(invoke)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required interface
		// [i] field:0:required service
		// [i] record:0:required input
		// [o] record:0:optional output
	String ifc = ValuesEmulator.getString(pipeline, "interface");
	String svc = ValuesEmulator.getString(pipeline, "service");
	IData input = (IData) ValuesEmulator.get(pipeline, "input");
        if (ifc == null || ifc.trim().length() == 0) {
		throw new ServiceException("Missing interface") ;
	} 	
	
        if (svc == null || svc.trim().length() == 0) {
		throw new ServiceException("Missing service") ;
	} 

	try {
	ValuesEmulator.put(pipeline, "output", Service.doInvoke(ifc, svc,input));
	} catch (Exception e) {
	ValuesEmulator.put(pipeline, "error","An Error Occured Invoking Service " + ifc+":"+svc+ " , Invoked from IREDI.Helper:invoke  " + e.toString());
           throw new ServiceException("An Error Occured in Invoking Service " + ifc+":"+svc+ " , Invoked from IREDI.Helper:invoke  "  + e);
		
	}
		// --- <<IS-END>> ---

                
	}



	public static final void invokeService (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(invokeService)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required serviceName
		IDataCursor  pipelineCursor = null ;
		try{  
			pipelineCursor = pipeline.getCursor();
			IData input = IDataFactory.create();
			input = pipeline ; 
			String serviceName = IDataUtil.getString( pipelineCursor, "serviceName" );
		        if (serviceName == null || serviceName.trim().length() == 0) {
				throw new ServiceException("Missing service Name ") ;
		} 
			IData outIData = Service.doInvoke( NSName.create(serviceName),input  );
			IDataUtil.append(pipeline ,outIData);
		}catch(java.lang.Exception ex) { 
			throw new ServiceException(ex.getMessage()); 
		}finally { 
			if(pipelineCursor != null) {
				pipelineCursor.destroy();
			}
		}
		// --- <<IS-END>> ---

                
	}
}

