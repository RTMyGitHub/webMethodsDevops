package SmartEDI.Route.Common.Services;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 14:31:18 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.util.regex.*;
// --- <<IS-END-IMPORTS>> ---

public final class Utils

{
	// ---( internal utility methods )---

	final static Utils _instance = new Utils();

	static Utils _newInstance() { return new Utils(); }

	static Utils _cast(Object o) { return (Utils)o; }

	// ---( server methods )---




	public static final void characterAtIndex (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(characterAtIndex)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required index
		// [i] field:0:required inString
		// [i] field:0:required baseIndex {"0","1"}
		// [o] field:0:required character
		IDataCursor pipelineCursor = pipeline.getCursor();
		String strInput = IDataUtil.getString(pipelineCursor, "inString" );
		String indexString = IDataUtil.getString(pipelineCursor, "index" );
		String stringArray[] = null;
		String baseIndexString = IDataUtil.getString(pipelineCursor, "baseIndex");
		int index = 1;
		int baseIndex = 0;
		
		try { 
			index=Integer.parseInt(indexString); 
			baseIndex=Integer.parseInt(baseIndexString);	
		} 
			catch(NumberFormatException e){
		} 
		
		
		if(strInput == null) {
			throw new IllegalArgumentException("strInput field can not be null");
		}
		
		if (baseIndex == 1) {
			index--;
		}
		
		int strLength = strInput.length();
		
		if(strLength < index) {
			throw new IllegalArgumentException("requested index is outside the range of the input string");
		}
		
		
		String charAtIndex = strInput.substring(index, ++index);
		
		// Don't need this now, but could come in handy for a real StringToArray function
		/*
		//set array size
		int arraysize=1;
		arraysize = strLength;
		
		stringArray = new String[arraysize];
		
		for (int i = 0; i < arraysize; i++) {
			int j = i+1;
			stringArray[i] = strInput.substring(i, j);
			stringConcat = stringConcat + strInput.substring(i, j);
		}
		*/
		//String myChar = strInput.substring(0,1);
		//stringArray[0] = myChar;
		
		IDataUtil.put(pipelineCursor, "character", charAtIndex);
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void getLastIndexOf (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLastIndexOf)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required inString
		// [i] field:0:required subString
		// [o] field:0:required value
		IDataCursor pipelineCursor = pipeline.getCursor();
		try{ 
			String inString = IDataUtil.getString( pipelineCursor, "inString" );
			String subString = IDataUtil.getString( pipelineCursor, "subString" );
		
		        int value = inString.lastIndexOf(subString);
			IDataUtil.put(pipelineCursor,"value", Integer.toString(value) );
		
		   }catch (Exception ex) {
				throw new ServiceException(ex.getMessage());
		   }finally {
		pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void getTNParams (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getTNParams)>> ---
		// @subtype unknown
		// @sigtype java 3.5
	
		// --- <<IS-END>> ---

                
	}



	public static final void parseConnectionString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parseConnectionString)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required string
		// [o] field:0:required url
		// [o] field:0:required port
		// [o] field:0:required password
		// [o] field:0:required username
		// [o] field:0:required timeout
		// [o] field:0:required encoding
		// [o] field:0:required error
		// [o] field:0:required successFlag
		String connString;
		int bindex;
		int eindex;
		
		String successFlag = "false";
		
		IDataCursor idcPipeline = null;
		
		try{
		
		idcPipeline = pipeline.getCursor();
		
		if (idcPipeline.first("string")) {
		
			connString = (String)idcPipeline.getValue();
		
		}
		
		else {
		
		
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "No input string for connection url.");
		
			return;
		
		}
		
		
		connString = connString.trim();
		
		bindex = connString.indexOf("<url>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <url> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = connString.indexOf("</url>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </url> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("url", connString.substring(bindex+5,eindex));
		
		bindex = connString.indexOf("<port>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <port> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = connString.indexOf("</port>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </port> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("port", connString.substring(bindex+6,eindex));
		
		bindex = connString.indexOf("<encoding>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <encoding> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = connString.indexOf("</encoding>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </encoding> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		
		String encoding = connString.substring(bindex+10,eindex);
		
		idcPipeline.insertAfter("encoding", encoding);
		
		bindex = connString.indexOf("<timeout>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <timeout> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = connString.indexOf("</timeout>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </timeout> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("timeout", connString.substring(bindex+9,eindex));
		
		
		bindex = connString.indexOf("<username>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <username> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = connString.indexOf("</username>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </username> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("username", connString.substring(bindex+10,eindex));
		
		
		bindex = connString.indexOf("<password>", 0);
		
		if(bindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The <password> tag is missing from the http profile.");
		
			return;
		
		}
		
		eindex = connString.indexOf("</password>", 0);
		
		if(eindex < 0) {
		
			idcPipeline.insertAfter("successFlag", successFlag);
			idcPipeline.insertAfter("error", "The </password> tag is missing from begining of the http profile.");
		
			return;
		
		}
		
		idcPipeline.insertAfter("password", connString.substring(bindex+10,eindex));
		
		
		successFlag = "true";
		
		idcPipeline.insertAfter("successFlag", successFlag);
		
		}//end of try block
		
		catch (Exception e) 
		{
			throw new ServiceException(e.toString());
		}
		
		finally
		{
		if(idcPipeline != null ) 
		idcPipeline.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void stringTostringList (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stringTostringList)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required inputString
		// [i] field:0:optional delimiter
		// [o] field:1:required outStringList
		IDataCursor pipelineCursor = pipeline.getCursor();
		try{ 
			String inputString = IDataUtil.getString( pipelineCursor, "inputString" );
			String delimiter = IDataUtil.getString( pipelineCursor, "delimiter" );
			StringTokenizer tokenize = null ;
			if(delimiter == null ) {
		    tokenize = new StringTokenizer(inputString);
			} else {
			tokenize = new StringTokenizer(inputString, delimiter );
			}
			
			int numberOfToken = tokenize.countTokens(); 
			String outArray[] = new String[numberOfToken];
		
			for(int i =0 ; i < numberOfToken ; i++) {
				outArray[i] = tokenize.nextToken();
			}
			IDataUtil.put(pipelineCursor,"outStringList", outArray );
			IDataUtil.put(pipelineCursor,"delimiter", delimiter );
		
		   }catch (Exception ex) {
				throw new ServiceException(ex.getMessage());
		   }finally {
		pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static String removeSpaces(String inputStr) {
		inputStr = inputStr.trim() ;
		int i = 0 ;
		int j = inputStr.indexOf(' ');
		while (j != -1) {
			inputStr = inputStr.substring(i,j) + inputStr.substring(j+1,inputStr.length()) ;
			j = inputStr.indexOf(' ');
		}// end while
	
		//check comments 
		j = inputStr.indexOf("//");
		if(j >= 0 ) {
			inputStr = inputStr.substring(i,j);
		}
	
		return inputStr ;
	}
	 
	private static String removeEndComments(String inputStr) {
		inputStr = inputStr.trim() ;
		int i = 0 ;
		int j = inputStr.indexOf("//");
		if(j >= 0 ) {
			inputStr = inputStr.substring(i,j);
		}
	
		return inputStr ;
	}
	
	private static String isCommentedLine(String line) {
		String flag = "False" ;
		line = line.trim();
		if( line.startsWith("//") ){
			flag = "Slash" ;
		} else if( line.startsWith("/*") && line.endsWith("*/")  ) { 
			flag = "SlashStarEnd" ;
		} else if( line.startsWith("/*") ) {
			flag = "SlashStar" ;
		} else if ( line.endsWith("*/") ) {
			flag = "StarSlash" ;
		}
		return flag ;
	} 
	
	
	private static String removeExtraSpaces(String inputStr) {
		inputStr = inputStr.trim() ;
		int i = 0 ;
		int j = inputStr.indexOf("  ");
		while (j != -1) {
			inputStr = inputStr.substring(i,j) + inputStr.substring(j+1,inputStr.length()) ;
			j = inputStr.indexOf("  ");
		}// end while
	
		return inputStr ;
	}
	// --- <<IS-END-SHARED>> ---
}

