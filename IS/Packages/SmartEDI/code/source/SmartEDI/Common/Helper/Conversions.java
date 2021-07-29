package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-01-14 16:37:57 EST
// -----( ON-HOST: 10.170.16.230

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.*;
import java.util.*;
import java.util.zip.*;
// --- <<IS-END-IMPORTS>> ---

public final class Conversions

{
	// ---( internal utility methods )---

	final static Conversions _instance = new Conversions();

	static Conversions _newInstance() { return new Conversions(); }

	static Conversions _cast(Object o) { return (Conversions)o; }

	// ---( server methods )---




	public static final void buildEmailExtField (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(buildEmailExtField)>> ---
		// @sigtype java 3.5
		// [i] field:0:required to
		// [i] field:0:required cc
		// [i] field:0:required bcc
		// [i] field:0:required subject
		// [i] field:0:required subjectCharset
		// [i] field:0:required charset
		// [i] field:0:required from
		// [i] field:0:required mailhost
		// [i] field:0:required body
		// [i] field:0:required attachcontenttype
		// [i] field:0:required attachencoding
		// [i] field:0:required attachcharset
		// [o] field:0:required string
		//define input variables
		IDataCursor idcPipeline = null;
		
		
		try{
			idcPipeline = pipeline.getCursor();
			String emailextfield = new String();
			
		String tmp = new String();
		
			if (idcPipeline.first("to")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<to>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</to>\n";
			tmp = "";
		
			if (idcPipeline.first("cc"))
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<cc>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</cc>\n";
			tmp = "";
		
			if (idcPipeline.first("bcc")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<bcc>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</bcc>\n";
			tmp = "";
		
			if (idcPipeline.first("subject")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<subject>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</subject>\n";
			tmp = "";
		
			if (idcPipeline.first("subjectCharset")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<subjectCharset>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</subjectCharset>\n";
			tmp = "";
		
			if (idcPipeline.first("charset")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<charset>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</charset>\n";
			tmp = "";
		
			if (idcPipeline.first("from")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<from>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</from>\n";
			tmp = "";
		
			if (idcPipeline.first("mailhost")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<mailhost>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</mailhost>\n";
			tmp = "";
		
			if (idcPipeline.first("body")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<body>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</body>\n";
			tmp = "";
		
			if (idcPipeline.first("attachcontenttype")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<attachcontenttype>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</attachcontenttype>\n";
			tmp = "";
		
			if (idcPipeline.first("attachencoding")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<attachencoding>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</attachencoding>\n";
			tmp = "";
		
			if (idcPipeline.first("attachcharset")) 
			{
				tmp = (String)idcPipeline.getValue();
			}
		
			tmp = tmp.trim();
			emailextfield = emailextfield + "<attachcharset>";
			emailextfield = emailextfield + tmp;
			emailextfield = emailextfield + "</attachcharset>";
			idcPipeline.insertAfter("string", emailextfield);
		
		}// end of try block
		
		catch (Exception e)
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



	public static final void byteArrayInputStreamToString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(byteArrayInputStreamToString)>> ---
		// @sigtype java 3.5
		// [i] object:0:required content
		// [o] field:0:required string
		//define input variables
		IDataCursor idcPipeline = null;
		ByteArrayInputStream inputStream = null; 
		StringBuffer contentBuffer = null;
		Object object = null;
		
		try{
		 
		idcPipeline = pipeline.getCursor();
		
		contentBuffer = new StringBuffer();
		
		//Output Variables
		String successFlag = "false";
		
		// Check to see if the integer object is in the pipeline
		if (idcPipeline.first("content")) {
			//get the integer value object out of the pipeline
			inputStream = (ByteArrayInputStream)idcPipeline.getValue();
			while( inputStream.available() != 0 )
				contentBuffer.append((char)inputStream.read());
		}
		
		//insert the string into the pipeline
		idcPipeline.insertAfter("string", contentBuffer.toString());
		
		}//end of try block
		
		catch (Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
		
		finally{
			//Always destroy cursors that you created
			if ( idcPipeline != null )
				idcPipeline.destroy();
			if (inputStream != null)
			try {
				inputStream.close();
			} catch (IOException ex) {
				throw new ServiceException(ex.getMessage()); 
			}
		}
		// --- <<IS-END>> ---

                
	}



	public static final void extractZipFiles (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(extractZipFiles)>> ---
		// @sigtype java 3.5
		// [i] object:0:required inputbytes
		// [o] field:1:required filescontent
		IDataCursor idcPipeline = null;
		String[] filesContent;
		ZipInputStream zipinputstream;
		byte[] inputBytes;
		int j = 0;
		int numberEntries = 0;
		
		try {
			idcPipeline = pipeline.getCursor();
		if (idcPipeline.first("inputbytes")) {
			//get the inputBytes value object out of the pipeline
			inputBytes = (byte[])idcPipeline.getValue();
		}
		//if it is not in the pipeline, then handle the error
		else {
		
		
			//Always destroy cursors that you created
			idcPipeline.destroy();
		
			return;
		}
		
		zipinputstream = new ZipInputStream(new ByteArrayInputStream(inputBytes));
		
		try{
		
			ZipEntry zipEntry = zipinputstream.getNextEntry();
		
			while (zipEntry != null) 
			{
				if (zipEntry.isDirectory()) {
					continue;
				}
				numberEntries++;
				zipinputstream.closeEntry();
				zipEntry = zipinputstream.getNextEntry();
			}
		
			zipinputstream.close();
			
		} catch (java.lang.Exception e) {
			throw new ServiceException(e.getMessage());
		}
		 
		filesContent = new String[numberEntries];
		for (int t = 0; t < numberEntries; t++) {
			filesContent[t] = "";
		}
		
		zipinputstream = new ZipInputStream(new ByteArrayInputStream(inputBytes));
		
		try {
		
			ZipEntry zipentry = 
		zipinputstream.getNextEntry();
			
			while(zipentry != null) {
		
				if (zipentry.isDirectory()) {
					continue;
				}
					
				int value;
		
				while ((value = zipinputstream.read()) != -1) {
					filesContent[j] += (char)value;
				}
			
				j++;
				zipinputstream.closeEntry();
				zipentry = 
		zipinputstream.getNextEntry();
			}
		
			zipinputstream.close();
		
		} catch (java.lang.Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		if (filesContent != null) {
			idcPipeline.insertAfter("filescontent",filesContent);	
		}
		
		}catch (java.lang.Exception ex) {
			throw new ServiceException(ex.getMessage());
		} finally {
			if(idcPipeline != null ) {
				idcPipeline.destroy();
			}
		}
		// --- <<IS-END>> ---

                
	}



	public static final void integerToString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(integerToString)>> ---
		// @sigtype java 3.5
		// [i] object:0:required integer
		// [o] field:0:required string
		//define input variables
		IDataCursor idcPipeline = null ;
		
		try{
			idcPipeline = pipeline.getCursor();
			Object object = null; 
			Integer integer = null;
		
			//Output Variables
			String successFlag = "false";
		
			// Check to see if the integer object is in the pipeline
			if (idcPipeline.first("integer")) {
				//get the integer value object out of the pipeline
				object = idcPipeline.getValue();
				integer = (Integer)object;
			}
		
			//insert the string into the pipeline
			idcPipeline.insertAfter("string", object.toString());
		
		
		}//end of try block
		
		catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		finally {
			//Always destroy cursors that you created
			if ( idcPipeline != null )
				idcPipeline.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void isZipFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(isZipFile)>> ---
		// @sigtype java 3.5
		// [i] object:0:required inputbytes
		// [o] field:0:required iszipfile
		IDataCursor idcPipeline = null;
		
		ZipInputStream zipinputstream = null;
		
		try{						
			idcPipeline = pipeline.getCursor();
			byte[] inputBytes = null;
			String result = "false";
		
			if (idcPipeline.first("inputbytes")) {
				//get the inputBytes value object out of the pipeline
				inputBytes = (byte[])idcPipeline.getValue();
			}
			//if it is not in the pipeline, then handle the error
		
		
			zipinputstream = new ZipInputStream(new ByteArrayInputStream(inputBytes));
		
			ZipEntry zipentry = 
		zipinputstream.getNextEntry();
		
			if(zipentry != null) {
				zipinputstream.closeEntry();
				result = "true";
			}
		
		
			idcPipeline.insertAfter("iszipfile", result);
		
		}//end of try block
		
		catch (Exception e) 
		{
			throw new ServiceException(e.getMessage());
		}
		
		finally
		{
			if (zipinputstream != null)
				try {
					zipinputstream.close();
				} catch (IOException ex) {
					throw new ServiceException(ex.getMessage()); 
				}
			//Always destroy cursors that you created
			idcPipeline.destroy();
			
		}
		// --- <<IS-END>> ---

                
	}



	public static final void parseEmailExtField (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parseEmailExtField)>> ---
		// @sigtype java 3.5
		// [i] field:0:required string
		// [o] field:0:required to
		// [o] field:0:required cc
		// [o] field:0:required bcc
		// [o] field:0:required subject
		// [o] field:0:required subjectCharset
		// [o] field:0:required charset
		// [o] field:0:required from
		// [o] field:0:required mailhost
		// [o] field:0:required body
		// [o] field:0:required attachcontenttype
		// [o] field:0:required attachencoding
		// [o] field:0:required attachcharset
		//define input variables
		IDataCursor idcPipeline = null;
		
		try{
		
		String emailstring ;
		int bindex;
		int eindex;
		
		idcPipeline = pipeline.getCursor();
		
		//Output Variables
		
		String successFlag = "false";
		
		
		
		// Check to see if the integer object is in the pipeline
		
		if (idcPipeline.first("string")) {
		
			//get the integer value object out of the pipeline
		
			emailstring = (String)idcPipeline.getValue();
		
		}
		
		//if it is not in the pipeline, then handle the error
		
		else {
		
		
		
		
			//Always destroy cursors that you created
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "No input string in email extended fiel profile.");
		
		
		
			return;
		
		}
		
		
		emailstring = emailstring.trim();
		
		bindex = emailstring.indexOf("<to>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <to> tag is missing from email extended field.");
		
			return;
		
		}
		
		eindex = emailstring.indexOf("</to>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </to> tag is missing from email extended field.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("to", emailstring.substring(bindex+4,eindex));
		
		bindex = emailstring.indexOf("<cc>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <cc> tag is missing from email extended field.");
		
			return;
		
		}
		
		eindex = emailstring.indexOf("</cc>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </cc> tag is missing from email extended field.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("cc", emailstring.substring(bindex+4,eindex));
		
		bindex = emailstring.indexOf("<bcc>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <bcc> tag is missing from email extended field.");
		
			return;
		
		}
		
		eindex = emailstring.indexOf("</bcc>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </bcc> tag is missing from email extended field.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("bcc", emailstring.substring(bindex+5,eindex));
		
		bindex = emailstring.indexOf("<subject>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <subject> tag is missing from email extended field.");
		
			return;
		
		}
		
		eindex = emailstring.indexOf("</subject>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </subject> tag is missing from email extended field.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("subject", emailstring.substring(bindex+9,eindex));
		
		bindex = emailstring.indexOf("<subjectCharset>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <subjectCharset> tag is missing from email extended field.");
		
			return;
		
		}
		
		eindex = emailstring.indexOf("</subjectCharset>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </subjectCharset> tag is missing from email extended field.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("subjectCharset", emailstring.substring(bindex+16,eindex));
		
		bindex = emailstring.indexOf("<charset>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <charset> tag is missing from email extended field.");
		
			return;
		
		}
		
		eindex = emailstring.indexOf("</charset>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </charset> tag is missing from email extended field.");
		
		
			return;
		
		}
		
		idcPipeline.insertAfter("charset", emailstring.substring(bindex+9,eindex));
		
		bindex = emailstring.indexOf("<from>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <from> tag is missing from email extended field.");
		
			return;
		
		}
		
		eindex = emailstring.indexOf("</from>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </from> tag is missing from email extended field.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("from", emailstring.substring(bindex+7,eindex));
		
		bindex = emailstring.indexOf("<mailhost>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <mailhost> tag is missing from email extended field.");
		
			return;
		
		}
		
		eindex = emailstring.indexOf("</mailhost>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </mailhost> tag is missing from email extended field.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("mailhost", emailstring.substring(bindex+10,eindex));
		
		bindex = emailstring.indexOf("<body>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <body> tag is missing from email extended field.");
		
			return;
		
		}
		
		eindex = emailstring.indexOf("</body>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </body> tag is missing from email extended field.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("body", emailstring.substring(bindex+6,eindex));
		
		bindex = emailstring.indexOf("<attachcontenttype>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <attachcontenttype> tag is missing from email extended field.");
		
			return;
		
		}
		
		eindex = emailstring.indexOf("</attachcontenttype>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </attachcontenttype> tag is missing from email extended field.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("attachcontenttype", emailstring.substring(bindex+19,eindex));
		
		bindex = emailstring.indexOf("<attachencoding>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <attachencoding> tag is missing from email extended field.");
			return;
		}
		
		eindex = emailstring.indexOf("</attachencoding>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </attachencoding> tag is missing from email extended field.");
			return;
		}
		String encoding = emailstring.substring(bindex+16,eindex);
		
		idcPipeline.insertAfter("attachencoding", encoding);
		
		bindex = emailstring.indexOf("<attachcharset>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <attachcharset> tag is missing from email extended field.");
			return;
		}
		
		eindex = emailstring.indexOf("</attachcharset>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </attachcharset> tag is missing from email extended field.");
			return;
		}
		
		idcPipeline.insertAfter("attachcharset", emailstring.substring(bindex+15,eindex));
		
		successFlag = "true";
		
		idcPipeline.insertAfter("successFlag", successFlag);
		
		}//end of try block
		
		catch (Exception e) 
		{
			throw new ServiceException(e.getMessage());
		}
		
		finally
		{
			//Always destroy cursors that you created
			if(idcPipeline != null ) 
				idcPipeline.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void parseFTPURL (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parseFTPURL)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required string
		// [o] field:0:required serverhost
		// [o] field:0:required serverport
		// [o] field:0:required dataport
		// [o] field:0:required encoding
		// [o] field:0:required timeout
		// [o] field:0:required username
		// [o] field:0:required password
		// [o] field:0:required path
		// [o] field:0:required filename
		// [o] field:0:required error
		// [o] field:0:required successFlag
		String ftpstring;
		int bindex;
		int eindex;
		
		
		String successFlag = "false";
		
		
		IDataCursor idcPipeline = null;
		
		try{
		
		idcPipeline = pipeline.getCursor();
		
		if (idcPipeline.first("string")) {
		
			ftpstring = (String)idcPipeline.getValue();
		
		}
		
		else {
		
		
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "No input string for ftp url.");
		
			return;
		
		}
		
		
		ftpstring = ftpstring.trim();
		
		bindex = ftpstring.indexOf("<serverhost>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <serverhost> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = ftpstring.indexOf("</serverhost>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </serverhost> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("serverhost", ftpstring.substring(bindex+12,eindex));
		
		bindex = ftpstring.indexOf("<serverport>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <serverport> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = ftpstring.indexOf("</serverport>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </serverport> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("serverport", ftpstring.substring(bindex+12,eindex));
		
		bindex = ftpstring.indexOf("<dataport>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <dataport> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = ftpstring.indexOf("</dataport>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </dataport> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("dataport", ftpstring.substring(bindex+10,eindex));
		
		bindex = ftpstring.indexOf("<encoding>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <encoding> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = ftpstring.indexOf("</encoding>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </encoding> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		
		String encoding = ftpstring.substring(bindex+10,eindex);
		
		idcPipeline.insertAfter("encoding", encoding);
		
		bindex = ftpstring.indexOf("<timeout>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <timeout> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = ftpstring.indexOf("</timeout>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </timeout> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("timeout", ftpstring.substring(bindex+9,eindex));
		
		
		bindex = ftpstring.indexOf("<username>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <username> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = ftpstring.indexOf("</username>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </username> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("username", ftpstring.substring(bindex+10,eindex));
		
		
		bindex = ftpstring.indexOf("<password>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <password> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = ftpstring.indexOf("</password>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </password> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("password", ftpstring.substring(bindex+10,eindex));
		
		bindex = ftpstring.indexOf("<path>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <path> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = ftpstring.indexOf("</path>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </path> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("path", ftpstring.substring(bindex+6,eindex));
		
		bindex = ftpstring.indexOf("<filename>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <filename> tag is missing from the http profile.");
			return;
		
		}
		
		eindex = ftpstring.indexOf("</filename>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </filename> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("filename", ftpstring.substring(bindex+10,eindex));
		
		successFlag = "true";
		
		idcPipeline.insertAfter("successFlag", successFlag);
		
		}//end of try block
		
		catch (Exception e) 
		{
			throw new ServiceException(e.getMessage());
		}
		
		finally
		{
		if(idcPipeline != null ) 
		idcPipeline.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void parseHTTPURL (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parseHTTPURL)>> ---
		// @sigtype java 3.5
		// [i] field:0:required string
		// [o] field:0:required authtype
		// [o] field:0:required authusername
		// [o] field:0:required authpassword
		// [o] field:0:required encodingtype
		// [o] field:0:required httpurl
		// [o] field:0:required error
		// [o] field:0:required successFlag
		IDataCursor idcPipeline = null
		;
		
		try{
		String httpstring;
		int bindex;
		int eindex;
		
		idcPipeline = pipeline.getCursor();
		
		String successFlag = "false";
		
		if (idcPipeline.first("string")) {
			httpstring = (String)idcPipeline.getValue();
		}
		else {
		
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "No input string for http url.");
		
			return;
		}
		
		httpstring = httpstring.trim();
		
		bindex = httpstring.indexOf("<authtype>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <authtype> tag is missing from the http profile.");
			return;
		
		}
		
		eindex = httpstring.indexOf("</authtype>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </authtype> tag is missing from begining of the http profile.");
			return;
		
		}
		
		idcPipeline.insertAfter("authtype", httpstring.substring(bindex+10,eindex));
		
		bindex = httpstring.indexOf("<authusername>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <authusername> tag is missing from the http profile.");
			return;
		
		}
		
		eindex = httpstring.indexOf("</authusername>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </authusername> tag is missing from the http profile.");
			return;
		
		}
		
		idcPipeline.insertAfter("authusername", httpstring.substring(bindex+14,eindex));
		
		bindex = httpstring.indexOf("<authpassword>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <authpassword> tag is missing from the http profile.");
			return;
		
		}
		
		eindex = httpstring.indexOf("</authpassword>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </authpassword> tag is missing from the http profile.");
			return;
		
		}
		
		idcPipeline.insertAfter("authpassword", httpstring.substring(bindex+14,eindex));
		
		
		bindex = httpstring.indexOf("<encodingtype>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <encodingtype> tag is missing from the http profile.");
			return;
		
		}
		
		eindex = httpstring.indexOf("</encodingtype>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </encodingtype> tag is missing from the http profile.");
			return;
		
		}
		
		String encoding = httpstring.substring(bindex+14,eindex);
		
		idcPipeline.insertAfter("encodingtype",encoding);
		
		bindex = httpstring.indexOf("<httpurl>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <httpurl> tag is missing from the http profile.");
			return;
		
		}
		
		eindex = httpstring.indexOf("</httpurl>", bindex);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </httpurl> tag is missing from the http profile.");
			return;
		
		}
		
		idcPipeline.insertAfter("httpurl", httpstring.substring(bindex+9,eindex));
		
		successFlag = "true";
		
		idcPipeline.insertAfter("successFlag", successFlag);
		
		}//end of try block
		
		catch (Exception e) 
		{
			throw new ServiceException(e.getMessage());
		}
		
		finally
		{
			if(idcPipeline != null ) 
				idcPipeline.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void stripEmailAlias (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stripEmailAlias)>> ---
		// @sigtype java 3.5
		// [i] field:0:required string
		// [o] field:0:required emailsalias
		// [o] field:0:required error
		IDataCursor idcPipeline = null;
		
		String emailsalias;
		int bindex;
		int eindex;
		 
		try
		{
		
		idcPipeline = pipeline.getCursor();
		
		if (idcPipeline.first("string")) {
			emailsalias = (String)idcPipeline.getValue();
		}
		
		else {
		
		
			idcPipeline.insertAfter("error", "No input string in email string");
			return;
		}
		
		emailsalias = emailsalias.trim();
		
		bindex = emailsalias.indexOf("<", 0);
		
		eindex = emailsalias.indexOf(">", 0);
		
		if(eindex < 0 || bindex < 0) {
			idcPipeline.insertAfter("emailsalias", emailsalias);
		} else {
			idcPipeline.insertAfter("emailsalias", emailsalias.substring(bindex+1,eindex));
		}
		
		}//end of try block
		
		catch (Exception e) 
		{
			throw new ServiceException(e.getMessage());
		}
		
		finally
		{
			//Always destroy cursors that you created
			if (idcPipeline != null)
				idcPipeline.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

