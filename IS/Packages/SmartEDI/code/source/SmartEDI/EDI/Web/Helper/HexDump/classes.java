package SmartEDI.EDI.Web.Helper.HexDump;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-08-18 09:00:58 EDT
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.*;
// --- <<IS-END-IMPORTS>> ---

public final class classes

{
	// ---( internal utility methods )---

	final static classes _instance = new classes();

	static classes _newInstance() { return new classes(); }

	static classes _cast(Object o) { return (classes)o; }

	// ---( server methods )---




	public static final void Hex (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(Hex)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required content
		// [o] field:0:required result
		//define input variables
		IDataCursor idcPipeline = null;
		String content = null;
		
		idcPipeline = pipeline.getCursor();
		if (idcPipeline.first("content")) {
		  content = (String)idcPipeline.getValue();
		
		  byte[] bytes = content.getBytes();
		        /* print to the IS Console
		        PrintWriter writer = new PrintWriter( System.out );
		        dump( writer, bytes, 0L, "", false );
		        */
		       idcPipeline.insertAfter("result",  dump( bytes, 0L, "" ));
		}
		if ( idcPipeline != null ) {
		   idcPipeline.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static String dump( byte[] data, long startAddress, String indent )
	    {
	        StringWriter writer = new StringWriter();
	        PrintWriter printer = new PrintWriter( writer, true );
	        
	        dump( printer, data, startAddress, indent, false );
	        
	        return writer.toString();
	    } 
	public static String dumpWithBinary( byte[] data, long startAddress, String indent )
	    {
	        StringWriter writer = new StringWriter();
	        PrintWriter printer = new PrintWriter( writer, true );
	        
	        dump( printer, data, startAddress, indent, true );
	        
	        return writer.toString();
	    }    
	    
	public static void dump( PrintWriter out, 
	                             byte[] data, 
	                             long startAddress, 
	                             String indent, 
	                             boolean includeBinary )
	    {
	        dump( out, data, 0, data.length, startAddress, indent, includeBinary );
	    }    
	    
	public static void dump( PrintWriter out, 
	                             byte[] data,
	                             int  startIndex,
	                             int  length,
	                             long startAddress, 
	                             String indent, 
	                             boolean includeBinary )
	    {
	        if( data == null ) return;  //nothing to dump
	        
	        int i = startIndex; 
	        int endIndex = startIndex + length - 1;
	        if( endIndex >= data.length ) endIndex = data.length - 1;
	        
	        while( i <= endIndex )
	        {
	            String hex   = "";
	            String chars = " ";
	            String binary = "";
	            
	            for( int j = 0; j < 16; j++ )
	            {
	                if( i + j <= endIndex )
	                {
	                    hex += " " + leadingZeros( Integer.toHexString( getByte(data[i+j])), 2 );
	
	                    if( includeBinary )
	                    {
	                        binary += " " + leadingZeros( Integer.toBinaryString( getByte(data[i+j])), 8 );
	                    }                    
	                    
	                    if( data[i+j] < 32 )
	                    {
	                        chars += ".";
	                    }
	                    else
	                    {
	                        chars += new String( new byte[] { data[i+j] } );
	                    }
	                }
	                else
	                {
	                    hex   += " --";
	                    chars += " ";
	
	                    if( includeBinary )
	                    {
	                        binary += " --------";
	                    }
	                }
	            }
	            
	            out.println( ((indent != null) ? indent : "") 
	                         + leadingZeros( Long.toHexString( startAddress ), 8 ) 
	                         + hex 
	                         + chars
	                         + binary );
	            
	            i += 16;
	            startAddress += 16;
	        }        
	    }
	    
	
	    /**
	     * Pad the string with leading zeros until it it reached the given size
	     */
	public static String leadingZeros( String string, int size )
	    {
	        String s = string;
	        
	        while( s.length() < size )
	        {
	            s = "0" + s;
	        }
	        
	        return s;
	    }
	
	    /**
	     * Get unsigned byte
	     */
	protected static int getByte( byte b )
	    {
	        if( b >= 0 ) return (int)b;
	        
	        //else byte is negative and needs conversion to an unsigned int
	        return b + 256;
	    }
	    
		
	// --- <<IS-END-SHARED>> ---
}

