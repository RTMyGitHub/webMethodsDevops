package SmartEDI.EDI;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-11-17 09:54:49 EST
// -----( ON-HOST: windows-vultr

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

public final class Helper

{
	// ---( internal utility methods )---

	final static Helper _instance = new Helper();

	static Helper _newInstance() { return new Helper(); }

	static Helper _cast(Object o) { return (Helper)o; }

	// ---( server methods )---




	public static final void changeBizDocStatusByID (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(changeBizDocStatusByID)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required bizdocID
		// [i] field:0:required systemStatus
		// [i] field:0:required userStatus
		// [o] field:0:required errorMessage
		String errorMessage = "";
		String bizdocID = null;
		String systemStatus = null;
		String userStatus  = null;
		
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		if ( pipelineCursor.first( "bizdocID" ) )
			bizdocID = (String) pipelineCursor.getValue();
		
		if ( pipelineCursor.first( "systemStatus" ) )
			systemStatus = (String) pipelineCursor.getValue();
		
		if ( pipelineCursor.first( "userStatus" ) )
			userStatus = (String) pipelineCursor.getValue();
		
		BizDocStore bizdocStore = new BizDocStore();
		
		try 
		  {
		       if ( !bizdocStore.changeStatus(bizdocID, systemStatus, userStatus)  )
					errorMessage = "Unable to update bizDocID " + bizdocID + " with System Status " + systemStatus + " and User Status " + userStatus;
		  }
		catch(Exception e)  {
		  errorMessage = e.getMessage();
		}
		
		
		pipelineCursor.insertAfter("errorMessage", errorMessage);
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getErrorLogs (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getErrorLogs)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] recref:0:required bizdoc wm.tn.rec:BizDocEnvelope
		// [i] field:0:required errorClass
		// [o] field:1:required errorLogs
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		pipelineCursor.first( "bizdoc" );
		BizDocEnvelope bizDocEnvelope = (BizDocEnvelope) pipelineCursor.getValue();
		pipelineCursor.first( "errorClass" );
		String errorClass = (String) pipelineCursor.getValue();
		
		//BizDocStore bizDocStore = new BizDocStore();
		//BizDocEnvelope bizDocEnvelope = bizDocStore.getDocument(bizDocID, true);
		BizDocErrorSet bizDocErrorSet = bizDocEnvelope.getErrorSet();
		ActivityLogEntry[] errorLogs = bizDocErrorSet.getErrors(errorClass);
		
		if (errorLogs.length <= 0) {
			pipelineCursor.destroy();
			return;
		}
		
		String[] errEntries = new String[errorLogs.length];
		
		for (int i=0; i < errorLogs.length; i++) {
			errEntries[i] = errorLogs[i].getFullMessage();
		}
		
		pipelineCursor.insertAfter("errorLogs", errEntries);
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getInvokedNSN (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getInvokedNSN)>> ---
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



	public static final void getSvc (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getSvc)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required serviceName
		// [o] field:0:required ifc
		// [o] field:0:required svc
			String serviceName = ValuesEmulator.getString(pipeline, "serviceName");
		        if (serviceName == null || serviceName.trim().length() == 0 ||
				serviceName.indexOf(":") == -1) {
				throw new ServiceException("Missing serviceName or missing colon ") ;
			} else {
			
		ValuesEmulator.put(pipeline, "ifc", serviceName.substring(0, serviceName.indexOf(":")));
		ValuesEmulator.put(pipeline, "svc", serviceName.substring(serviceName.indexOf(":") + 1, serviceName.length()));	
		
		}
		// --- <<IS-END>> ---

                
	}



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
	ValuesEmulator.put(pipeline, "error","An Error Occured Invoking Service " + ifc+":"+svc+ " , Invoked from SQFramework.SQEDI.Helper:invoke  " + e.toString());
           throw new ServiceException("An Error Occured in Invoking Service " + ifc+":"+svc+ " , Invoked from SQFramework.SQEDI.Helper:invoke  "  + e);
		
	}
		// --- <<IS-END>> ---

                
	}



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

