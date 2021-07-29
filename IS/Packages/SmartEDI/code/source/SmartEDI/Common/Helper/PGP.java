package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-01-14 23:39:08 EST
// -----( ON-HOST: 10.170.16.230

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.*;
// --- <<IS-END-IMPORTS>> ---

public final class PGP

{
	// ---( internal utility methods )---

	final static PGP _instance = new PGP();

	static PGP _newInstance() { return new PGP(); }

	static PGP _cast(Object o) { return (PGP)o; }

	// ---( server methods )---




	public static final void fireCommandExec (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(fireCommandExec)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required command
		// [o] field:0:required output
		// [o] field:0:required successFlag
	//define input variables 
	IDataCursor idcPipeline = pipeline.getCursor();
	String 	strFileContent = null; 

	//Output Variables 
	String successFlag = "false"; 
	String fullCommand ;   
 
	// Check to see if the fullCommand object is in the pipeline
	if (idcPipeline.first("command"))
	{	
		//get the fullCommand stream out of the pipeline					
		fullCommand = (String) idcPipeline.getValue();
	}
	//if it is not in the pipeline, then handle the error
	else
	{
			throw new ServiceException("Required parameter 'command' missing");
			
	}
	//Execute the command. Handle the exception if it fails.
	try 
	{
 	
 		
		Process child =   Runtime.getRuntime().exec(fullCommand);
		successFlag="true";
		
		int ct=0;
		String output="";		
		String error="";		
		BufferedReader input =  new BufferedReader(new InputStreamReader(child.getErrorStream()));
		String line = "";
		input =  new BufferedReader(new InputStreamReader(child.getInputStream()));
		line = input.readLine();
     	while (((line = input.readLine()) != null)) {
       												output=output+line;
													}

		idcPipeline.insertAfter("output", output);
     	input.close();
 	} 

	catch (Exception ex) 
	{
		successFlag="False";
		throw new ServiceException(ex);
		
 	}

	//insert the successFlag into the pipeline
	idcPipeline.insertAfter("successFlag", successFlag);	


	//Always destroy cursors that you created
	idcPipeline.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void fireCommandExecWithErr (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(fireCommandExecWithErr)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required command
		// [o] field:0:required output
		// [o] field:0:required error
		// [o] field:0:required successFlag
	//define input variables 
	IDataCursor idcPipeline = pipeline.getCursor();
	String 	strFileContent = null;

	//Output Variables 
	String successFlag = "false"; 
	String fullCommand ;    
    
	// Check to see if the fullCommand object is in the pipeline
	if (idcPipeline.first("command"))
	{	  
		//get the fullCommand stream out of the pipeline					
		fullCommand = (String) idcPipeline.getValue();
	}
	//if it is not in the pipeline, then handle the error
	else
	{  
		throw new ServiceException("Required parameter 'command' missing");		 	
	}
	try {
 		//Execute the command. Handle the exception if it fails.		
		
		Process child =   Runtime.getRuntime().exec(fullCommand);
		
		successFlag="true";		
		int ct=0;
		String output="";		
		String error="";		
		
                BufferedReader input =  new BufferedReader(new InputStreamReader(child.getErrorStream()));
		String line = input.readLine();
		
		while (((line = input.readLine()) != null)) {
                      System.out.println(line);
                      error=error+line;													
	        }
		
        	input =  new BufferedReader(new InputStreamReader(child.getInputStream()));
		line = null;
		line = input.readLine();
       	        
	        while (((line = input.readLine()) != null)) {
       		      output=output+line;
		}

		  
        	idcPipeline.insertAfter("error", error);
		idcPipeline.insertAfter("output", output);
		
        	input.close();
	}	 
	catch (java.lang.Exception ex) 
	{
  	      successFlag="False";
	      throw new ServiceException(ex);		
 	}

	//insert the successFlag into the pipeline
	idcPipeline.insertAfter("successFlag", successFlag);	

	//Always destroy cursors that you created
	idcPipeline.destroy();
		// --- <<IS-END>> ---

                
	}
}

