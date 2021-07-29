package SmartEDI.Common.ArchivalServices;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 14:26:13 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.zip.*;
// --- <<IS-END-IMPORTS>> ---

public final class MonthlyArchive

{
	// ---( internal utility methods )---

	final static MonthlyArchive _instance = new MonthlyArchive();

	static MonthlyArchive _newInstance() { return new MonthlyArchive(); }

	static MonthlyArchive _cast(Object o) { return (MonthlyArchive)o; }

	// ---( server methods )---




	public static final void getLastModifiedDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLastModifiedDate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required targetDirectory
		// [i] field:0:required checkFile
		// [o] field:0:required successFlag
		// [o] field:0:required output
		// [o] field:0:required stringLastModified
		IDataCursor idc = pipeline.getCursor();
		String output = "";
		String targetDirectory = IDataUtil.getString(idc , "targetDirectory");
		String checkFileName = IDataUtil.getString(idc , "checkFile");
		String successFlag = "false";
		String stringLastModified="";
		
		
		File targetDirectoryPath = new File (targetDirectory);
		
		
		try {
			if(!targetDirectoryPath.isDirectory() ||!targetDirectoryPath.exists() || targetDirectoryPath ==null ) {
				successFlag = "false";
				output = "Not a valid directory / Invalid Path";
				throw new java.lang.Exception();
			}
			
			String wholeName = targetDirectory + "\\" + checkFileName;
			File checkLastModify = new File (wholeName);
			long lastmodified = checkLastModify.lastModified();
			
			java.util.Date dateLastModified = new Date(lastmodified);
			stringLastModified = "" + dateLastModified;
			successFlag="true";
			checkLastModify = null ;
		}catch (Exception ex)	{
			successFlag="false";
			output = ex.getMessage();
		}finally	{
			//insert the successFlag into the pipeline
			idc.insertAfter("successFlag", successFlag);
			idc.insertAfter("stringLastModified",stringLastModified);
			idc.insertAfter("output",output);
			//Always destroy cursors that you created
			targetDirectoryPath = null;
			idc.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void zipFiles (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(zipFiles)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required targetDirectory
		// [i] field:0:required zipFileName
		// [i] field:1:required archiveList
		// [o] field:0:required successFlag
		// [o] field:0:required output
		//define input variables
		IDataCursor idc = pipeline.getCursor();
		
		//final String fileType = IDataUtil.getString(idc , "fileType");
		String targetDirectory = IDataUtil.getString(idc , "targetDirectory");
		String zipFileName = IDataUtil.getString(idc , "zipFileName");
		String[] children = IDataUtil.getStringArray(idc , "archiveList");
		String output = "";
		String successFlag = "";
		
		zipFileName = targetDirectory + '\\' + zipFileName ;
		
		try	{
		
			byte[] buffer = new byte[18024];
		
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		
			// Set the compression ratio
			out.setLevel(Deflater.DEFAULT_COMPRESSION);
		
			// iterate through the array of files, adding each to the zip file
			for (int i = 0; i < children.length; i++) {
		
				//if (children[i].length >0) {
				// Associate a file input stream for the current file
				String wholeName = targetDirectory + "\\" + children[i];
		
				FileInputStream in = new FileInputStream(wholeName);
		
				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(children[i]));
		
				// Transfer bytes from the current file to the ZIP file
				out.write(buffer, 0, in.read(buffer));
		
				int len;
				while ((len = in.read(buffer)) > 0) 
				{
					out.write(buffer, 0, len);
				}
		
				// Close the current entry
				out.closeEntry();
		
				// Close the current file input stream
				in.close();
				//} //if close for length check
		
			}//for closure
			// Close the ZipOutPutStream
			out.close();
		
			successFlag="true";
			output = " Zip file is created ";
				
		}catch (IllegalArgumentException iae) {
			successFlag="false";
			output = iae.getMessage();
		}
		catch (FileNotFoundException fnfe) {
			successFlag="false";
			output = fnfe.getMessage();
		}
		catch (IOException ioe)
		{
			successFlag="false";
			output =ioe.getMessage();
		}catch (Exception ex)	
		{
			successFlag="false";
			if (ex.getMessage()==null)
				output = output ;
			else
				output = output + ex.getMessage();
		}finally	{
			//insert the successFlag into the pipeline
			idc.insertAfter("successFlag", successFlag);
			idc.insertAfter("output", output);
			
			//Always destroy cursors that you created
			idc.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

