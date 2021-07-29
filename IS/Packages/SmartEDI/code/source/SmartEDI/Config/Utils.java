package SmartEDI.Config;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-08-18 15:26:00 EDT
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.io.*;
// --- <<IS-END-IMPORTS>> ---

public final class Utils

{
	// ---( internal utility methods )---

	final static Utils _instance = new Utils();

	static Utils _newInstance() { return new Utils(); }

	static Utils _cast(Object o) { return (Utils)o; }

	// ---( server methods )---




	public static final void svc_getCurrentWorkDir_java (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(svc_getCurrentWorkDir_java)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required currentWorkingDir
		  
		
		String userdir = System.getProperty("user.dir");
		IDataCursor cursor= pipeline.getCursor();
		cursor.last();
		cursor.insertAfter("currentWorkingDir",userdir);
		cursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void svc_getFileSeperator_Java (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(svc_getFileSeperator_Java)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required fileSeperator
		IDataCursor pipelineCursor= pipeline.getCursor();
		pipelineCursor.last();
		pipelineCursor.insertAfter("fileSeperator", System.getProperty("file.separator"));
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	protected static Values Memory = new Values();
		
	// --- <<IS-END-SHARED>> ---
}

