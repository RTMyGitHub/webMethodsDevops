package SmartEDI.Common.ArchivalServices;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 14:25:16 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.lang.*;
import java.io.*;
// --- <<IS-END-IMPORTS>> ---

public final class javaservices

{
	// ---( internal utility methods )---

	final static javaservices _instance = new javaservices();

	static javaservices _newInstance() { return new javaservices(); }

	static javaservices _cast(Object o) { return (javaservices)o; }

	// ---( server methods )---




	public static final void callCommanndLine (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(callCommanndLine)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required command
		// [o] field:0:required bufferLine
		// [o] field:0:required successFlag
		//define input variables
		IDataCursor idc = pipeline.getCursor();
		String command = IDataUtil.getString(idc , "command");
		
		//Output Variables
		String successFlag = "";
		String line = null;
		String bufferLine = " ";
		//Execute the command. Handle the exception if it fails.
		try	{
			Runtime systemShell = Runtime.getRuntime();
			//Process output = systemShell.exec("zip -1 C:\\nas\\Java\\Archive.zip C:\\nas\\Java\\*.*");
			Process output = systemShell.exec(command);
		
			BufferedReader br = new BufferedReader (new InputStreamReader(output.getInputStream()));
			while((line = br.readLine()) != null ) { 	
				bufferLine = bufferLine + line + '\n' ;
			}        // display process output
			
			int exitVal = output.waitFor(); // get process exit value
			bufferLine = bufferLine +  "Exit Value is : " + exitVal;	
			successFlag = "True" ;
			idc.insertAfter("bufferLine", bufferLine);
			
			//idc.close();
		}
		catch (Exception ex)	{
			successFlag="False";
			bufferLine = ex.getMessage();
		}
		finally {
			idc.insertAfter("bufferLine",bufferLine);	
			idc.insertAfter("successFlag", successFlag);
			
			//Always destroy cursors that you created
			idc.destroy();
		}
		//insert the successFlag into the pipeline
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	class StreamGobbler extends Thread
	{
	
	}
	// --- <<IS-END-SHARED>> ---
}

