package SmartEDI.Route.Inbound.Protocol.FTP.Get;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 14:34:32 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class Polling

{
	// ---( internal utility methods )---

	final static Polling _instance = new Polling();

	static Polling _newInstance() { return new Polling(); }

	static Polling _cast(Object o) { return (Polling)o; }

	// ---( server methods )---




	public static final void checkPattern (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(checkPattern)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required actualFilePattern
		// [i] field:0:required testFilePattern
		// [o] field:0:required pattern
		// Get IDataCursor to manipulate pipeline
		IDataCursor idcPipeline = pipeline.getCursor();
		
		String actualFilePattern = IDataUtil.getString(idcPipeline, "actualFilePattern");
		String testFilePattern = IDataUtil.getString(idcPipeline, "testFilePattern");
		String actualFilePath = new String();
		String actualFileName = new String();
		String testFileName = new String();
		String testFilePath = new String();
		int pattern = -1;
		
		//if ftp criteria includes a specific path
		if(testFilePattern.contains("/")){
		
		actualFilePath = actualFilePattern.substring(0, actualFilePattern.lastIndexOf("/"));
		actualFileName = actualFilePattern.substring(actualFilePattern.lastIndexOf("/"),actualFilePattern.length());
		testFilePath = testFilePattern.substring(0, testFilePattern.lastIndexOf("/"));
		testFileName = testFilePattern.substring(testFilePattern.lastIndexOf("/"),testFilePattern.length());
		testFileName = testFileName.replace("*", "");
		testFileName = testFileName.replace("/", "");
		
		if(actualFilePath.equals(testFilePath) && actualFileName.contains(testFileName)) pattern = 1;
		else if(actualFilePath.equals(testFilePath) && actualFilePattern.contains(testFilePattern)) pattern = 1;
		else if(actualFilePath.equals(testFilePath) && testFileName.equals(".")) pattern = 1;
		
		}
		
		IDataUtil.put( idcPipeline, "actualFilePath", actualFilePath);
		IDataUtil.put( idcPipeline, "actualFileName", actualFileName);
		IDataUtil.put( idcPipeline, "testFilePath", testFilePath);
		IDataUtil.put( idcPipeline, "testFileName", testFileName);
		IDataUtil.put( idcPipeline, "pattern", pattern);
		// --- <<IS-END>> ---

                
	}
}

