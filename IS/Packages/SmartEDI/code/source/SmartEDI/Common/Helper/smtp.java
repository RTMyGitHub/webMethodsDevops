package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-08-19 09:24:10 EDT
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.io.*;
import com.wm.app.b2b.server.Session;
import com.wm.app.b2b.server.ServerAPI;
import java.net.InetAddress;
// --- <<IS-END-IMPORTS>> ---

public final class smtp

{
	// ---( internal utility methods )---

	final static smtp _instance = new smtp();

	static smtp _newInstance() { return new smtp(); }

	static smtp _cast(Object o) { return (smtp)o; }

	// ---( server methods )---




	public static final void getSmtpServer (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getSmtpServer)>> ---
		// @sigtype java 3.5
		// [o] field:0:required smtpServer
		// [o] field:0:required smtpServerPort
		// [o] field:0:required mailAlias
		// [o] field:0:required emailfrom
		// [o] field:0:required charset
		IDataCursor pipelineCursor = null;
		
		
		try
		{ 
			pipelineCursor = pipeline.getCursor();
		
			Properties config = System.getProperties();
			String str = config.getProperty("watt.server.smtpServer");
		        String strPort = config.getProperty("watt.server.smtpServerPort");
			String email = config.getProperty("watt.server.MailAlias");
			String charset = config.getProperty("watt.server.email.charset");
			String emailfrom = config.getProperty("watt.server.emailfrom");
		
			
			IDataUtil.put( pipelineCursor, "smtpServer", str );
		        IDataUtil.put( pipelineCursor, "smtpServerPort", strPort );
			IDataUtil.put( pipelineCursor, "mailAlias", email );
			IDataUtil.put( pipelineCursor,"emailfrom", emailfrom );
			IDataUtil.put( pipelineCursor,"charset", charset );
		}
		
		catch(java.lang.Exception ex)
			{ 
				throw new ServiceException(ex.getMessage()); 
			}
			finally 
			{ 
				if(pipelineCursor != null ) 
					pipelineCursor.destroy();
			
			}
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void setSmtpServerProp (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(setSmtpServerProp)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required smtpServer
		// [i] field:0:required smtpServerPort
		// [i] field:0:required MailAlias
		// [i] field:0:required emailfrom
		// [i] field:0:required charset
	IDataCursor pipelineCursor = pipeline.getCursor();

	String smtpServer = IDataUtil.getString(pipelineCursor, "smtpServer");
    String smtpServerPort = IDataUtil.getString(pipelineCursor, "smtpServerPort");
	String MailAlias = IDataUtil.getString(pipelineCursor, "MailAlias");
	String charset = IDataUtil.getString(pipelineCursor, "charset");
	String emailfrom = IDataUtil.getString(pipelineCursor, "emailfrom");

	if(smtpServer != null){ 
		 smtpServer =  System.setProperty("watt.server.smtpServer", smtpServer );
	}
	if(smtpServerPort != null){ 
		 smtpServer =  System.setProperty("watt.server.smtpServerPort", smtpServerPort );
	}
	if(MailAlias!=null){
	 MailAlias =  System.setProperty("watt.server.MailAlias" , MailAlias);
	}
	if(charset!=null){
	 charset =  System.setProperty("watt.server.email.charset", charset);
	}
	if(emailfrom!=null){
	 emailfrom =  System.setProperty("watt.server.emailfrom", emailfrom);
	}
		// --- <<IS-END>> ---

                
	}
}

