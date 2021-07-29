package SmartEDI.Common;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 14:27:07 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.io.*;
import com.wm.app.b2b.server.*;
// --- <<IS-END-IMPORTS>> ---

public final class Exception

{
	// ---( internal utility methods )---

	final static Exception _instance = new Exception();

	static Exception _newInstance() { return new Exception(); }

	static Exception _cast(Object o) { return (Exception)o; }

	// ---( server methods )---




	public static final void getRealError (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getRealError)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] recref:0:optional lastError pub.event:exceptionInfo
		// [o] recref:0:optional realError pub.event:exceptionInfo
		// [o] field:0:required callStackString
		// [o] field:0:required topLevelService
		IDataCursor pipelineCursor = pipeline.getCursor();
		IData	lastError = IDataUtil.getIData( pipelineCursor, "lastError" );
		pipelineCursor.destroy();
		
		
		if (lastError != null)
		{
			StringBuffer callStackBuffer = new StringBuffer();
			IData realError = traverseLastError(lastError, callStackBuffer);
			if(realError == null)
				realError = lastError;
		
			com.wm.app.b2b.server.InvokeState is = com.wm.app.b2b.server.InvokeState.getCurrentState(); 
		      	java.util.Stack callStack = is.getCallStack();
		
		        String as[] = null;
		        if(callStack != null)
		        {
		            		int i = callStack.size();
		            		as = new String[i];
		            		for(int j = 0; j < i; j++)
		            		{
		                		com.wm.lang.ns.NSService nsservice = (com.wm.lang.ns.NSService)callStack.elementAt(j);
		               	 		as[j] = nsservice.getNSName().getFullName();
						//WmLogUtil.logDebug1(4,"Call Stack " + j + " - " + as[j]);
		            		}
		
		        }
		
			String topLevelService = null;
			if(as.length >= 3)
			{
				topLevelService = as[as.length-3];
			}else
			{
				//This error was caused while stepping through flow.
				IDataCursor realErrorCursor = realError.getCursor();
				topLevelService = IDataUtil.getString(realErrorCursor, "service");
				realErrorCursor.destroy();			
			}
			
			pipelineCursor = pipeline.getCursor();
			IDataUtil.put( pipelineCursor, "realError", realError);
			if(topLevelService == null)
			{
				IDataUtil.put( pipelineCursor, "callStackString", callStackBuffer.toString());
			}else
			{
				IDataUtil.put( pipelineCursor, "callStackString", callStackBuffer.toString()  + topLevelService + "\n");
				IDataUtil.put( pipelineCursor, "topLevelService", topLevelService);
			}
			pipelineCursor.destroy();	
		}
		// --- <<IS-END>> ---

                
	}



	public static final void throwError (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(throwError)>> ---
		// @sigtype java 3.5
		// [i] field:0:required errorMessage
		// To access the data in the pipeline you must create a cursor
		// on the IData object.
		IDataCursor idcPipeline = pipeline.getCursor();
		
		// Initialize variables
		String strErrorMessage = null;
		
		// Get data from pipeline
		if (idcPipeline.first("errorMessage"))
		{
			strErrorMessage = (String)idcPipeline.getValue();
		}
		
		// Always destroy your cursor
		
		idcPipeline.destroy();
		
		throw new ServiceException(strErrorMessage);
			
		// --- <<IS-END>> ---

                
	}



	public static final void throwIsRuntimeException (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(throwIsRuntimeException)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// throws IsRuntimeException
		// persists broker document when service invoked by trigger invokes this
		
		com.wm.app.b2b.server.ISRuntimeException isrte = new com.wm.app.b2b.server.ISRuntimeException();
		
		throw isrte;
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---


	public static IData traverseLastError(IData lastError,StringBuffer callStackBuffer)
	{
	  if(lastError != null)
	  {
		//StringBuffer callStackBuffer = new StringBuffer();
		IDataCursor lastErrorCursor = lastError.getCursor();
		IData childPipe = IDataUtil.getIData(lastErrorCursor, "pipeline");
		IData[]	callStack = IDataUtil.getIDataArray(lastErrorCursor, "callStack");
		String errorMsgID = IDataUtil.getString(lastErrorCursor, "errorMsgID");
		lastErrorCursor.destroy();
	
		//if(errorMsgID == null || errorMsgID.trim().length() == 0)
		//{
	        //	errorMsgID = "0000";	
		//}
	
		IData realError = null; 
		if (childPipe != null)
		{
	
			IDataCursor pc = childPipe.getCursor();
			String errorMsgID1 = IDataUtil.getString(pc, "errorMsgID");
			IData	lastError1 = IDataUtil.getIData( pc, "lastError" );
			pc.destroy();
			if(errorMsgID1 != null && errorMsgID1.trim().length() != 0)
			{
	                	errorMsgID = errorMsgID1;	
			}
	
			if(lastError1 != null)
			{
	                   realError =  traverseLastError(lastError1, callStackBuffer);		  
			}
	
		        // i.callStack
			if (callStack != null)
			{
				for (int i = 0; i < callStack.length; i++ )
				{
					IDataCursor callStackCursor = callStack[i].getCursor();
					callStackBuffer.append(IDataUtil.getString(callStackCursor, "service") + "\n");
					callStackCursor.destroy();
				}
			}
	
			if(realError != null)
			{
	                	return realError;
			}
	
	
		}
	
		if(errorMsgID == null || errorMsgID.trim().length() == 0)
		{
	               	return null;
		}else
		{
	               	return   lastError;
		}
	
		//errorHash.put(errorMsgID,lastError);
		//return callStackBuffer;
	   }
	   return null;		
	}
	// --- <<IS-END-SHARED>> ---
}

