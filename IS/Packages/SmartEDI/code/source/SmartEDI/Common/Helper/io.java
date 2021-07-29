package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-02-09 11:30:08 EST
// -----( ON-HOST: 10.185.180.92

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.*;
import java.text.*;
// --- <<IS-END-IMPORTS>> ---

public final class io

{
	// ---( internal utility methods )---

	final static io _instance = new io();

	static io _newInstance() { return new io(); }

	static io _cast(Object o) { return (io)o; }

	// ---( server methods )---




	public static final void checkFileExistence (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(checkFileExistence)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [o] field:0:required flag
		IDataCursor idcPipeline = null;
		 
		try{ 
			String filename, filepath;
			int length,idx1,idx2;
		 
			//Output Variables
			String successFlag = "false";
		
			idcPipeline = pipeline.getCursor();
		
			// Check to see if the filename object is in the pipeline
			if (idcPipeline.first("filename")) {
				
				//get the filename string object out of the pipeline
				filename = (String)idcPipeline.getValue();
		
				File file = new File (filename);
		
				if (file.exists()){
					idcPipeline.insertAfter("flag","true");
					
				} else{
					idcPipeline.insertAfter("flag","false");		
					
				}
				file = null;
				return;
			}
		
		} // end of try
		catch (java.lang.Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
		finally {
			//Always destroy cursors that you created
			if(idcPipeline != null ) 	
				idcPipeline.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void checkIsFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(checkIsFile)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [i] field:0:required directory
		// [o] field:0:required successFlag
		IDataCursor idcPipeline = null; 
		String successFlag = null;
		String filename = null,directory;
		
		try {
			//Output Variables
			successFlag= "false";
			
			idcPipeline = pipeline.getCursor();
			// Check to see if the filename object is in the pipeline
			if (idcPipeline.first("filename")) {
		
				//get the filename string object out of the pipeline
				filename = (String)idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else {
				successFlag = "false";
				return;
			}
			
			// Check to see if the directory object is in the pipeline
			if (idcPipeline.first("directory")) {
				//get the directory string object out of the pipeline
				directory = (String)idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else {
				successFlag = "false";
				return;
			}
		
			filename = (directory + File.separator + filename);
		
		
			File file = new File(filename);
			if(file.isFile()) {
				successFlag = "true";
		
				//insert the successFlag into the pipeline
				idcPipeline.insertAfter("successFlag", successFlag);
			}
			else {
				successFlag = "false";
				idcPipeline.insertAfter("successFlag", successFlag);
			}
		
		}catch (Exception e) {
			successFlag = "false";
			idcPipeline.insertAfter("successFlag", successFlag);
			throw new ServiceException(e.getMessage());
		} finally {
			//Always destroy cursors that you created
			if(idcPipeline != null ) 
				idcPipeline.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void closeFileWriter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(closeFileWriter)>> ---
		// @sigtype java 3.5
		// [i] object:0:required fileWriter
		// [o] field:0:required successFlag
		//define input variables 
		
		IDataCursor idcPipeline = null;
		String successFlag = "false";
		
		try
		{	
			BufferedWriter fileWriter = null;
		
			//Output Variables 
			idcPipeline = pipeline.getCursor();
			
			// Check to see if the fileWriter object is in the pipeline
			if (idcPipeline.first("fileWriter"))
			{	
				//get the BufferedWriter stream out of the pipeline					
				fileWriter = (BufferedWriter) idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else
			{
				successFlag = "false";	
		
				//insert the successFlag into the pipeline
				idcPipeline.insertAfter("successFlag", successFlag);
				return;
			}
		
			// Try to flush the fileWriter object.  Handle the exception if it fails.
			
			if(fileWriter != null) 
				fileWriter.close();
			successFlag = "true";						
			
		
		}//end of try
		
		
		catch (java.lang.Exception e)
		{
			//Set the success flag because the service failed
			successFlag = "false";
			
			throw new ServiceException(e.getMessage());
		} finally {
		
			//remove any other successFlags from pipline
			if(idcPipeline.first("successFlag"))
			{
				idcPipeline.delete();
			}
		
			//insert the successFlag into the pipeline
			idcPipeline.insertAfter("successFlag", successFlag);
			
			//Always destroy cursors that you created
			if(idcPipeline != null ) 	
			    idcPipeline.destroy();
		}	
		// --- <<IS-END>> ---

                
	}



	public static final void deleteFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(deleteFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [i] field:0:required directory
		// [o] field:0:required successFlag
		//define input variables
		IDataCursor idcPipeline = null; 
		String longFilename, filename, directory;
		String successFlag = null;
		try {
			//Output Variables
			successFlag= "false";
		
			idcPipeline = pipeline.getCursor();
		
			// Check to see if the filename object is in the pipeline
			if (idcPipeline.first("filename")) {
		
				//get the filename string object out of the pipeline
				filename = (String)idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else {
				successFlag = "false";
				return;
			}
		
		
			// Check to see if the directory object is in the pipeline
			if (idcPipeline.first("directory")) {
				//get the directory string object out of the pipeline
				directory = (String)idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else {
				successFlag = "false";
				return;
			}
		
			//Check if a directory was entered
			if (directory==null) {
				longFilename = (filename);
			} else {
				longFilename = (directory + File.separator + filename);
			}
		
			//Assign full path name to a file object
			File localFile = new File(longFilename);
		
			//Try to delete the file
			//Check is a directory was entered
			
			if (localFile.isDirectory()) {
				successFlag = "false";
			} else if (!localFile.exists()) { 	//Check if the file doesn't exist
				successFlag = "false";
			} else if (!localFile.canWrite()) {    	//check if you can write to file
				successFlag = "false";
			} else { 				//File can be deleted
				localFile.delete();
				successFlag = "true";
			}
		 //insert the successFlag into the pipeline
			idcPipeline.insertAfter("successFlag", successFlag);
		}
		//Catch any other error's
		catch (Exception e) {
			successFlag = "false";
			idcPipeline.insertAfter("successFlag", successFlag);
			throw new ServiceException(e.getMessage());
		} finally {
		
			//Always destroy cursors that you created
			if(idcPipeline != null ) 
				idcPipeline.destroy();
		
		}
		// --- <<IS-END>> ---

                
	}



	public static final void deleteFileWithAbsolutePath (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(deleteFileWithAbsolutePath)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [o] field:0:required successFlag
		//define input variables
		IDataCursor idcPipeline = null; 
		String filepathandname;
		String successFlag = null;
		try {
			//Output Variables
			successFlag= "false";
		
			idcPipeline = pipeline.getCursor();
		
			// Check to see if the filename object is in the pipeline
			if (idcPipeline.first("filepathandname")) {
		
				//get the filename string object out of the pipeline
				filepathandname = (String)idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else {
				successFlag = "false";
				return;
			}
		
			
			//Assign full path name to a file object
			File localFile = new File(filepathandname);
		
			//Try to delete the file
			//Check is a directory was entered
			
			if (localFile.isDirectory()) {
				successFlag = "false";
			} else if (!localFile.exists()) { 	//Check if the file doesn't exist
				successFlag = "false";
			} else if (!localFile.canWrite()) {    	//check if you can write to file
				successFlag = "false";
			} else { 				//File can be deleted
				localFile.delete();
				successFlag = "true";
			}
		 //insert the successFlag into the pipeline
			idcPipeline.insertAfter("successFlag", successFlag);
		}
		//Catch any other error's
		catch (Exception e) {
			successFlag = "false";
			idcPipeline.insertAfter("successFlag", successFlag);
			throw new ServiceException(e.getMessage());
		} finally {
		
			//Always destroy cursors that you created
			if(idcPipeline != null ) 
				idcPipeline.destroy();
		
		}
		
		// --- <<IS-END>> ---

                
	}



	public static final void fileLastModified (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(fileLastModified)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [i] field:0:required directory
		// [o] field:0:required successFlag
		// [o] field:0:required lastModifiedDate
		IDataCursor idcPipeline = null; 
		String filename, directory, longFilename;
		String successFlag = null;
		String lastModifiedDate = null;
		long longDate;
		
		try {
			//Output Variables
			successFlag= "false";
		
			idcPipeline = pipeline.getCursor();
		
			// Check to see if the filename object is in the pipeline
			if (idcPipeline.first("filename")) {
		
				//get the filename string object out of the pipeline
				filename = (String)idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else {
				successFlag = "false";
				return;
			}
		
		
			// Check to see if the directory object is in the pipeline
			if (idcPipeline.first("directory")) {
				//get the directory string object out of the pipeline
				directory = (String)idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else {
				successFlag = "false";
				return;
			}
		
			longFilename = (directory + File.separator + filename);
		
			//Assign full path name to a file object
			File localFile = new File(longFilename);
		
			//Try to delete the file
			//Check is a directory was entered
			
			if (localFile.isDirectory()) {
				successFlag = "false";
			} else if (!localFile.exists()) { 	//Check if the file doesn't exist
				successFlag = "false";
			} else if (!localFile.canWrite()) {    	//check if you can write to file
				successFlag = "false";
			} else { 				//Get the last modified date
				longDate = localFile.lastModified();
				//lastModifiedDate = String.valueOf(longDate);
				java.util.Date date2 = new java.util.Date(longDate); 
				java.text.SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
				lastModifiedDate = new String( dateformatYYYYMMDD.format( date2 ) );	
				successFlag = "true";
			}
		 	//insert the successFlag into the pipeline
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("lastModifiedDate", lastModifiedDate );
		}//Catch any other error's
		catch (Exception e) {
			successFlag = "false";
			idcPipeline.insertAfter("successFlag", successFlag);
			throw new ServiceException(e.getMessage());
		} finally {
			//Always destroy cursors that you created
			if(idcPipeline != null ) 
				idcPipeline.destroy();
		}
		
		
		// --- <<IS-END>> ---

                
	}



	public static final void flushFileWriter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(flushFileWriter)>> ---
		// @sigtype java 3.5
		// [i] object:0:required fileWriter
		// [o] field:0:required successFlag
		//define input variables 
		BufferedWriter fileWriter = null;
		IDataCursor idcPipeline = null;
		//Output Variables 
		String successFlag = "false";
		try
		{			
			idcPipeline = pipeline.getCursor();
		
			// Check to see if the fileWriter object is in the pipeline
			if (idcPipeline.first("fileWriter"))
			{	
				//get the BufferedWriter stream out of the pipeline					
				fileWriter = (BufferedWriter) idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else
			{
				successFlag = "false";	
				return;
			}
		
			fileWriter.flush();
			successFlag = "true";						
			//insert the successFlag into the pipeline
			idcPipeline.insertAfter("successFlag", successFlag);
		}//end of try
		catch (java.lang.Exception e)
		{
		
			//Set the success flag because the service failed
			successFlag = "false";
			idcPipeline.insertAfter("successFlag", successFlag);
			throw new ServiceException(e.getMessage());
		} finally {
		
			//remove any other successFlags from pipline
			if(idcPipeline.first("successFlag"))
			{
				idcPipeline.delete();
			}
		
			//Always destroy cursors that you created
			if(idcPipeline != null ) 
				idcPipeline.destroy();
		}	
			
		// --- <<IS-END>> ---

                
	}



	public static final void listFilesInDirectory (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(listFilesInDirectory)>> ---
		// @sigtype java 3.5
		// [i] field:0:required targetDirectory
		// [o] field:1:optional filenameList
		// [o] field:0:required successFlag
		//define input variables
		IDataCursor idcPipeline = null;
		try
		{
			String successFlag = "false";
			String targetDirectory = null;
		
			idcPipeline  = pipeline.getCursor();
		
			// Check to see if the targetDirectory object is in the pipeline
			if (idcPipeline.first("targetDirectory")) {
				//get the targetDirectory out of the pipeline
				targetDirectory = (String)idcPipeline.getValue();
			} else {  			//if it is not in the pipeline, then handle the error
				successFlag = "false";
				
				//insert the successFlag into the pipeline
				idcPipeline.insertAfter("successFlag", successFlag);
				
				return;
			}
		
			String filenamePattern = null;
		
			// Check to see if the filenamePattern object is in the pipeline
			if (idcPipeline.first("filenamePattern"))  {
				//get the filenamePattern out of the pipeline
				filenamePattern = (String)idcPipeline.getValue();
			}
			File targetDirectoryPath = new File(targetDirectory);
			
			if(!targetDirectoryPath.isDirectory()
				||!targetDirectoryPath.exists()) {
		
				successFlag = "false";
		
				//insert the successFlag into the pipeline
				idcPipeline.insertAfter("successFlag", successFlag);
				return;
			}
		
			idcPipeline.last();
		
			String[] filenameList;
		
			//Load the direcotory listing
			filenameList = targetDirectoryPath.list();
			successFlag = "true";
		
			//Check to see if directory empty
			if ((filenameList==null) || (filenameList.length<=0)) {
				filenameList = null;
				successFlag = "true";
			}
		
			//insert the filenameList into the pipeline
			idcPipeline.insertAfter("filenameList", filenameList);
		
			//insert the successFlag into the pipeline
			idcPipeline.insertAfter("successFlag", successFlag);
		
		}//end try
		catch (java.lang.Exception e)
		{
			throw new ServiceException(e.getMessage());
		} finally {
			//Always destroy cursors that you created
			if(idcPipeline != null ) 	
				idcPipeline.destroy();
		}	
			
		// --- <<IS-END>> ---

                
	}



	public static final void moveFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(moveFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required source
		// [i] field:0:required target
		// [o] field:0:required status
		IDataHashCursor idc = null;
		try {
			idc = pipeline.getHashCursor();
			
			// Get input values
		   	idc.first( "source" );
			String source = (String) idc.getValue();
		   	idc.first( "target" );
			String target = (String) idc.getValue();
			
			File source_file = new File(source);
			source_file.renameTo(new File(target));
			source_file = null;
			
			idc.first();
			idc.insertAfter("status","SUCCESS");
			
		
		} catch (java.lang.Exception e)
		{
			
		   	idc.first();
			idc.insertAfter("status","ERROR");		
		
			throw new ServiceException(e.getMessage());
		} finally {
			//Always destroy cursors that you created
			if(idc != null ) 
				idc.destroy();
		}	
		// --- <<IS-END>> ---

                
	}



	public static final void openFileWriter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(openFileWriter)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [i] field:0:required overwriteFlag {"overwrite","append"}
		// [o] object:0:required fileWriter
		// [o] field:0:required successFlag
		// [o] field:0:required error
			
		IDataCursor idcPipeline = null;
		//Output Variables 
		String successFlag = "false";
		String errorMsg = null;
		BufferedWriter fileWriter = null;
		try
		{
			String strFilename = null;
			String strOverwriteFlag = null;
		
			idcPipeline = pipeline.getCursor();
		
			// Check to see if the filename object is in the pipeline
			if (idcPipeline.first("filename"))
			{
				//get the filename out of the pipeline						
				strFilename = (String)idcPipeline.getValue();	
		
			}
			//if it is not in the pipeline, then handle the error
			else
			{
				successFlag="false";
				errorMsg = "fileName cannot be null";
		
				//insert the successFlag into the pipeline
				//idcPipeline.insertAfter("successFlag", successFlag);	
				//idcPipeline.insertAfter("error", errorMsg);
		
				return;
			}
		
		
			// Check to see if the overwriteFlag object is in the pipeline
			if (idcPipeline.first("overwriteFlag"))	
			{
				//get the overwriteFlag out of the pipeline						
				strOverwriteFlag = (String)idcPipeline.getValue();	 
			}
			//if it is not in the pipeline, then handle the error
			else
			{
		
				successFlag="false";
				errorMsg = "overwrite Flag not found";
		
				//insert the successFlag into the pipeline
				//idcPipeline.insertAfter("successFlag", successFlag);	
				//idcPipeline.insertAfter("error", errorMsg);
			
				return;
			}
		
		
			// Check to see if the overwriteFlag was set to overwrite
			if (strOverwriteFlag.equals("overwrite")) 			
			{
				//Create a new BufferedWriter object that will overwrite the old file
				fileWriter = new BufferedWriter(new FileWriter(strFilename, false));
			}
			// Check to see if the overwriteFlag was set to append
			else
			{
				//Create a new BufferedWriter object that will append to the old file
				fileWriter = new BufferedWriter(new FileWriter(strFilename, true));
			}
		
			//Set the success flag because the service was successful
			successFlag = "true";
		
			
		
		}//end try
		
		catch (java.lang.Exception e)
		{
			//Set the success flag because the service failed
			successFlag = "false";
			errorMsg = e.getMessage();
		
			throw new ServiceException(e.getMessage());
		}
		
		
		finally {
		
			//insert the fileWriter into the pipeline
			idcPipeline.insertAfter("fileWriter", fileWriter);	
		
			//insert the successFlag into the pipeline
			idcPipeline.insertAfter("successFlag", successFlag);	
			idcPipeline.insertAfter("error", errorMsg);
		
		
			//Always destroy cursors that you created
			idcPipeline.destroy();
		}	
			
		// --- <<IS-END>> ---

                
	}



	public static final void renameFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(renameFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required source
		// [i] field:0:required target
		// [o] field:0:required status
		// [o] field:0:required error
		String status = null;
		String error = null;
		
		IDataCursor idc = null;
		try
		{ 
		
			idc  = pipeline.getHashCursor();
		
			// Get input values 
		   	idc.first( "source" );
			String source = (String) idc.getValue();
		
		   	idc.first( "target" );
			String target = (String) idc.getValue();
			
			File source_file = new File(source);
			source_file.renameTo(new File(target));
		
			File target_file = new File(target);
			if (!target_file.exists()) {
				status = "ERROR";
				error = target +" Directory doesn't exists";
			}else {
				status = "SUCCESS";
			}
					
			source_file = null;
			
			idc.first();
			idc.insertAfter("status",status);
			idc.insertAfter("error",error);
			
		
		}//end try
		
		catch (java.lang.Exception e)
		{
		   	idc.first();
			idc.insertAfter("status","ERROR");
			idc.insertAfter("error",e.getMessage());		
			
			throw new ServiceException(e.getMessage());
		}
		
		finally {
			
			//Always destroy cursors that you created
			if(idc != null ) 
				idc.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void synchronizedFileWriter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(synchronizedFileWriter)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [i] field:0:required fileContent
		// [i] field:0:required overwriteFlag {"Append","overwrite"}
		// [o] field:0:required successFlag
		// [o] field:0:required error
		//define input variables 
		IDataCursor idcPipeline = null;
		String successFlag = "false";
		String errorMsg = null;
		BufferedWriter fileWriter = null;
		try
		{
			String strFilename = null;
			String 	strFileContent = null;
			String strOverwriteFlag = null;
		
			idcPipeline = pipeline.getCursor();
			Object lock = new Object();
		
			//Output Variables
		
			// Check to see if the filename object is in the pipeline
			if (idcPipeline.first("filename")) {
				//get the filename out of the pipeline						
				strFilename = (String)idcPipeline.getValue();	
		
			} else {			//if it is not in the pipeline, then handle the error
				successFlag="false";
				errorMsg = "fileName cannot be null";
				return;
			}
		
			// Check to see if the fileContent object is in the pipeline
			if (idcPipeline.first("fileContent"))	{
				//get the fileContent out of the pipeline						
				strFileContent = (String) idcPipeline.getValue();
		
			} else {	//if it is not in the pipeline, then handle the error
				return;
			}
		
			// Check to see if the overwriteFlag object is in the pipeline
			if (idcPipeline.first("overwriteFlag"))	{
				//get the overwriteFlag out of the pipeline						
				strOverwriteFlag = (String)idcPipeline.getValue();	 
		
			} else {	//if it is not in the pipeline, then handle the error
				successFlag="false";
				errorMsg = "overwrite Flag not found";
				return;
			}
		
			// Try to create a BufferedWriter object.  Handle the exception if it fails.
			
		        synchronized (lock) {
				// Check to see if the overwriteFlag was set to overwrite
			       	if (strOverwriteFlag.equals("overwrite")) {
					//Create a new BufferedWriter object that will overwrite the old file
					fileWriter = new BufferedWriter(new FileWriter(strFilename, false));
		
		   		} else {   // Check to see if the overwriteFlag was set to append
					//Create a new BufferedWriter object that will append to the old file
					fileWriter = new BufferedWriter(new FileWriter(strFilename, true));
				}
		
				// Try to write to the BufferedWriter object.  Handle the exception if it fails
				fileWriter.write(strFileContent);
				
				// Try to flush the fileWriter object.  Handle the exception if it fails.
		        	fileWriter.flush();
		
				// Set the success flag because the service was successful
				successFlag = "true";
		        }
		
		} catch (Exception e) {
			//Set the success flag because the service failed
			successFlag = "false";
			errorMsg = e.getMessage();
			throw new ServiceException(errorMsg);
		
		} finally {
			
		
			try {
				if(fileWriter != null) 
					fileWriter.close();
		
			} catch(Exception ex) {
				
				//Set the success flag because the service failed
				successFlag = "false";
				errorMsg = ex.getMessage();
				throw new ServiceException(errorMsg);
		
			} finally {
				
				//insert the successFlag into the pipeline
				idcPipeline.insertAfter("successFlag", successFlag);	
				idcPipeline.insertAfter("error", errorMsg);
			
				//Always destroy cursors that you created
				if(idcPipeline != null)
					idcPipeline.destroy();	
			}
		}
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void writeBytesToFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(writeBytesToFile)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:required bytes
		// [i] field:0:required fileName
		// [i] field:0:required overwriteFlag {"append","overwrite"}
		IDataCursor cur = null;
		byte[] bytes=null;
		String fileName="";
		String overwriteFlag="";
		FileOutputStream fos = null;
		try 
		{ 
			cur  = pipeline.getCursor();
			
			if (cur.first("bytes")) {	
				bytes = (byte[])cur.getValue(); 
			}
		
			if (cur.first("fileName")) {
				fileName=(String)cur.getValue();
			}
		
			if (cur.first("overwriteFlag")){
		        	overwriteFlag=(String)cur.getValue();
		
				if(overwriteFlag.equalsIgnoreCase("append")) {
					fos = new FileOutputStream(fileName,true); 
					fos.write(bytes); 
				} else {
					fos = new FileOutputStream(fileName); 
					fos.write(bytes); 
		        	}
			}
		 
		} 
		catch (Exception e) 
		{ 
			throw new ServiceException(e); 
		}  finally {
			
			try {
				if(fos != null)
					fos.close(); 
			} catch(IOException eIO) { 
				throw new ServiceException(eIO); 
			} 
		
			//Always destroy cursors that you created
			if(cur != null ) 
				cur.destroy();
		}	
		// --- <<IS-END>> ---

                
	}



	public static final void writeFileWriter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(writeFileWriter)>> ---
		// @sigtype java 3.5
		// [i] object:0:required fileWriter
		// [i] field:0:required fileContent
		// [o] object:0:required fileWriter
		// [o] field:0:required successFlag
		//define input variables 
		IDataCursor idcPipeline = null;
		String successFlag = null;
		
		try
		{
			String 	strFileContent = null;
		
			//Output Variables 
			BufferedWriter fileWriter = null;
			
			idcPipeline  = pipeline.getCursor();
			successFlag = "false";
		
			// Check to see if the fileWriter object is in the pipeline
			if (idcPipeline.first("fileWriter"))
			{	
				//get the BufferedWriter stream out of the pipeline	
				fileWriter = (BufferedWriter) idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else
			{
				return;
			}
		
		
			// Check to see if the fileContent object is in the pipeline
			if (idcPipeline.first("fileContent"))	
			{
			
				//get the fileContent out of the pipeline	
				strFileContent = (String) idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
			else
			{
				return;
			}
			
			fileWriter.write(strFileContent);
		
			//Set the success flag because the service was successful
			successFlag = "true";
		
		}
		catch (Exception e) {
			//Set the success flag because the service failed
			successFlag = "false";
		
			throw new ServiceException(e.getMessage());
		
		} finally {
		
			//remove any other successFlags from pipline
			if(idcPipeline.first("successFlag"))
			{
				idcPipeline.delete();
			}
		
			//insert the successFlag into the pipeline
			idcPipeline.insertAfter("successFlag", successFlag);	
		
			//Always destroy cursurs that you created
			if(idcPipeline != null ) 
				idcPipeline.destroy();	
		}
		
		// --- <<IS-END>> ---

                
	}



	public static final void writeToFilePS (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(writeToFilePS)>> ---
		// @sigtype java 3.5
		// [i] field:0:required userData
		// [i] field:0:required filename
		// [i] field:0:required appendOverwriteFlag
		// {"append","overwrite","failIfFileExists"}
		// Based on Ryan's writeFile service
		
		IDataCursor idcPipeline = pipeline.getCursor();
		String strUserData = null;
		String strFullFilename = null;
		if (idcPipeline.first("userData"))
		{
		  strUserData = (String) idcPipeline.getValue();
		}
		if (idcPipeline.first("filename"))
		{
		  strFullFilename = (String) idcPipeline.getValue();
		}
		else
		{
		  throw new ServiceException("filename is null!");
		}
		idcPipeline.first("appendOverwriteFlag");
		String appendOverwriteFlag = (String) idcPipeline.getValue();
		
		// *** Check if path is on the allowed list ***
		/*try
		{
		  if (!checkPathValidity(strFullFilename, "write"))
		  {
		    throw new ServiceException("Specified path is not on the write allowed list in the PSUtilities configuration file!");
		  }
		}
		catch (Exception e)
		{
		  throw new ServiceException(e.getMessage());
		}*/
		// *** End check ***
		
		// Separate filename into path and filename
		// This is done so that the directory can be written (if necessary)
		String separator = System.getProperty("file.separator");
		int indexSeparator = strFullFilename.lastIndexOf(separator);
		if (indexSeparator == -1)
		{
		  // Account for fact that you can use either '\' or '/' in Windows
		  indexSeparator = strFullFilename.lastIndexOf('/');
		}
		String strPathName = strFullFilename.substring(0, indexSeparator + 1);
		String strFileName = strFullFilename.substring(indexSeparator + 1);
		
		FileWriter fw = null;
		try
		{
		  File pathToBeWritten = new File(strPathName);
		  // System.out.println("canonical path = " +
		  // pathToBeWritten.getCanonicalPath());
		
		  // Write the directory...
		  if (pathToBeWritten.exists() == false)
		  {
		    throw new ServiceException("Path does not exist!");
		  }
		
		  // Check if file exists
		  File fileToBeWritten = new File(strFullFilename);
		  if (fileToBeWritten.exists() == true && appendOverwriteFlag != null && appendOverwriteFlag.equals("failIfFileExists"))
		  {
		    throw new ServiceException("File " + strFullFilename + " already exists!");
		  }
		
		  // Write the file...
		  if (appendOverwriteFlag != null && appendOverwriteFlag.equals("overwrite"))
		  {
		    // overwrite
		    fw = new FileWriter(strFullFilename, false);
		  }
		  else
		  {
		    // append
		    fw = new FileWriter(strFullFilename, true);
		  }
		  fw.write(strUserData);
		}
		catch (Exception e)
		{
		  throw new ServiceException(e.getMessage());
		}
		finally
		{
		  // Close the output stream....
		  try
		  {
		    fw.close();
		  }
		  catch (Exception e)
		  {
		  }
		
		  idcPipeline.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}
}

