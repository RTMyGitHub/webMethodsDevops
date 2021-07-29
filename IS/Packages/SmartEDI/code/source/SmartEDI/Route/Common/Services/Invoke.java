package SmartEDI.Route.Common.Services;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 14:29:31 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSName;
// --- <<IS-END-IMPORTS>> ---

public final class Invoke

{
	// ---( internal utility methods )---

	final static Invoke _instance = new Invoke();

	static Invoke _newInstance() { return new Invoke(); }

	static Invoke _cast(Object o) { return (Invoke)o; }

	// ---( server methods )---




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
		        // throw service Exception, and catch in try/catch in the flow service
			throw new ServiceException(ex.getMessage()); 
		}finally { 
			if(pipelineCursor != null) {
				pipelineCursor.destroy();
			}
		}
		// --- <<IS-END>> ---

                
	}
}

