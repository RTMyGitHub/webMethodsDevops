package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-08-19 09:23:54 EDT
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.io.*;
import com.wm.app.b2b.server.*;
import com.wm.util.*;
// --- <<IS-END-IMPORTS>> ---

public final class properties

{
	// ---( internal utility methods )---

	final static properties _instance = new properties();

	static properties _newInstance() { return new properties(); }

	static properties _cast(Object o) { return (properties)o; }

	// ---( server methods )---




	public static final void getPropertyFromServerCnf (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getPropertyFromServerCnf)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required propertyName
		// [o] field:0:required propertyValue
		//define input variables 
		IDataCursor idcPipeline = pipeline.getCursor();
		
		String sPropertyName = "";
		String sPropertyValue = "";
		
		
		//Get the input value
		if (idcPipeline.first ("propertyName"))
		{
			sPropertyName = (String) idcPipeline.getValue();
		}
		
		//insert the output into the pipeline
		idcPipeline.insertAfter("propertyValue", System.getProperty(sPropertyName));	
		
		sPropertyName = null;
		sPropertyValue = null;
		
		//Always destroy cursors that you created
		idcPipeline.destroy();
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void getSpecificSystemProperties (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getSpecificSystemProperties)>> ---
		// @sigtype java 3.5
		// [o] recref:0:required SystemProperties SmartEDI.Common.Helper.properties:rec_SystemProperties
		
		IDataCursor idcPipeline = pipeline.getCursor();
		
		IData systemProperties = IDataFactory.create();
		IDataCursor idcSystemProperties = systemProperties.getCursor();
		
		idcSystemProperties.insertAfter("java.version", System.getProperty("java.version"));
		idcSystemProperties.insertAfter("java.vendor", System.getProperty("java.vendor"));
		idcSystemProperties.insertAfter("java.vendor.url", System.getProperty("java.vendor.url"));
		idcSystemProperties.insertAfter("java.home", System.getProperty("java.home"));
		idcSystemProperties.insertAfter("java.class.version", System.getProperty("java.class.version"));
		idcSystemProperties.insertAfter("java.class.path", System.getProperty("java.class.path"));
		idcSystemProperties.insertAfter("os.name", System.getProperty("os.name"));
		idcSystemProperties.insertAfter("os.arch", System.getProperty("os.arch"));
		idcSystemProperties.insertAfter("os.version", System.getProperty("os.version"));
		idcSystemProperties.insertAfter("file.separator", System.getProperty("file.separator"));
		idcSystemProperties.insertAfter("path.separator", System.getProperty("path.separator"));
		idcSystemProperties.insertAfter("line.separator", System.getProperty("line.separator"));
		idcSystemProperties.insertAfter("user.name", System.getProperty("user.name"));
		idcSystemProperties.insertAfter("user.home", System.getProperty("user.home"));
		idcSystemProperties.insertAfter("user.dir", System.getProperty("user.dir"));
		
		idcPipeline.insertAfter("SystemProperties", systemProperties);
		
		idcSystemProperties.destroy();
		idcPipeline.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getSystemProperties (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getSystemProperties)>> ---
		// @sigtype java 3.5
		// [o] field:2:required propertyList
		/** Service is designed to return a list of all available system properties.
		  *
		  * @author Ryan Johnston, Professional Services, webMethods, Inc.
		  * @version 1.1
		  * Modified slightly to replace IDataHashCursor with IDataCursor (Eric)
		  * Modified to check for existing of propertyList before delete (Ronald B)
		  */
		
		IDataCursor idc = pipeline.getCursor();
		Properties prop = System.getProperties();
		Enumeration propKeyList = prop.propertyNames();
		Vector propVec = new Vector();
		Vector valueVec = new Vector();
		
		outer:while(true)
		{
			if(propKeyList.hasMoreElements() == true)
		{
				String elementName = (String)propKeyList.nextElement();
				String elementValue = System.getProperty(elementName, "No property found");
				propVec.addElement( elementName );
				valueVec.addElement( elementValue );
			}
			else
				break outer;
		}
		
		String[] allPropsColHeaders = {"Key","Value"};
		Table allProps = new Table(allPropsColHeaders);
		for(int i=0;i<propVec.size();i++)
		{
			String[] individualRows = { (String)propVec.elementAt(i),(String)valueVec.elementAt(i) };
			allProps.addRow(individualRows);
		}
		
		if (idc.first("propertyList"))
			idc.delete();
		
		idc.insertAfter("propertyList", allProps);
		idc.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void getSystemProperty (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getSystemProperty)>> ---
		// @sigtype java 3.5
		// [i] field:0:required propertyName
		// [o] field:0:required property
		/** Service is designed to return the specified system property.
		  *
		  * @author Ryan Johnston, Professional Services, webMethods, Inc.
		  * @version 1.0
		  * Modified to check for existing of propertyList before delete (Ronald B)
		  */
		
		IDataCursor hCursor = pipeline.getCursor();
		
		hCursor.first("propertyName");
		String propertyName = (String)hCursor.getValue();
		
		String property = new String();
		
		property = System.getProperty(propertyName, "No property found");
		
		if (hCursor.first("property"))
			hCursor.delete();
		hCursor.insertAfter("property", property);
			
		// --- <<IS-END>> ---

                
	}



	public static final void readPropertiesFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(readPropertiesFile)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filename
		// [o] object:0:required property
		
		IDataCursor idcPipeline = pipeline.getCursor();
		String strFilename = null;
		if (idcPipeline.first("filename"))
		{
			strFilename = (String)idcPipeline.getValue();
		}
		else
		{
			throw new ServiceException("Could not read Properties file.  filename is null!");
		}
		
		try
		{
			File file = new File(strFilename);
			if (file.canRead())
			{
				Properties property = new Properties();
				InputStream stream = new java.io.BufferedInputStream(new java.io.FileInputStream(file));
		
				try
				{
					property.load(stream);
				}
				finally
				{
					stream.close();
				}
		
				Enumeration enumerate = property.propertyNames();
				while (enumerate.hasMoreElements())
				{
					String strPropertyName = (String)enumerate.nextElement();
					String strPropertyValue = (String)property.getProperty(strPropertyName);
					idcPipeline.insertAfter(strPropertyName, strPropertyValue);				
				}
		
				// Put entire property object into the output pipeline
				idcPipeline.insertAfter("property", property);
			}
			else
			{
				throw new ServiceException("Could not read properties file: " + strFilename);
			}
		}
		catch( Exception e )
		{
			throw new ServiceException("Error reading properties file: " + e);
		}
		finally
		{
			idcPipeline.destroy();
		}
		
		// --- <<IS-END>> ---

                
	}
}

