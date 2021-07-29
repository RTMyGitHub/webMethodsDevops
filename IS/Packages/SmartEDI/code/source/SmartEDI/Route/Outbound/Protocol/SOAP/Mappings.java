package SmartEDI.Route.Outbound.Protocol.SOAP;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 14:33:38 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class Mappings

{
	// ---( internal utility methods )---

	final static Mappings _instance = new Mappings();

	static Mappings _newInstance() { return new Mappings(); }

	static Mappings _cast(Object o) { return (Mappings)o; }

	// ---( server methods )---




	public static final void insertInputToPipeline (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(insertInputToPipeline)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:required InputField
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			String[] inputFieldList = IDataUtil.getStringArray(pipelineCursor, "InputField");
			int inputLength = inputFieldList.length ;
			String inputFieldValue[][] = new String[inputLength+1][2];
			for (int i = 0; i < inputLength; i++ ) {
				String tmp[] = inputFieldList[i].split(":");
				inputFieldValue[i][0] = tmp[1] ;
				inputFieldValue[i][1] = IDataUtil.getString(pipelineCursor, tmp[0] );
			}
		
			for (int i = 0; i < inputLength; i++ ) {
				IDataUtil.put(pipelineCursor,
				inputFieldValue[i][0],
				inputFieldValue[i][1] );
			}
			
		} catch (Exception ex) {
			throw new ServiceException (ex.toString());
		} finally {
			pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void processResponse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(processResponse)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required ResponseField
		// [i] field:0:required responseDocumentName
		// [o] record:0:required Response
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			String ResponseField = IDataUtil.getString(pipelineCursor,"ResponseField");
			String SoapResponse =  IDataUtil.getString(pipelineCursor,ResponseField);
			String responseDocumentName = IDataUtil.getString(pipelineCursor,"responseDocumentName");
			
		
			IDataUtil.put(pipelineCursor, "responseDocumentName", responseDocumentName );
		
			// input
			IData input = IDataFactory.create();
			IDataCursor inputCursor = input.getCursor();
			IDataUtil.put( inputCursor, "xmldata", SoapResponse );
			IDataUtil.put( inputCursor, "isXML", "true" );
			inputCursor.destroy();
		
			// output
			IData 	output = IDataFactory.create();
			try{
				output = Service.doInvoke( "pub.xml", "xmlStringToXMLNode", input );
			}catch( Exception ex){
				throw new ServiceException (ex.toString());
			}
			IDataCursor outputCursor = output.getCursor();
			Object	node = IDataUtil.get(outputCursor, "node" );
			outputCursor.destroy();
		
			//IDataUtil.put(pipelineCursor, "node", node );
			
		
			// input
			IData inputNode = IDataFactory.create();
			IDataCursor inputCursorNode = inputNode.getCursor();
			IDataUtil.put( inputCursorNode, "node", node );
			IDataUtil.put( inputCursorNode, "makeArrays", "false" );
		
			IDataUtil.put( inputCursorNode, "documentTypeName", responseDocumentName );
			inputCursorNode.destroy();
		
			// output
			IData 	outputNode = IDataFactory.create();
			try{
				outputNode = Service.doInvoke( "pub.xml", "xmlNodeToDocument", inputNode );
			}catch( Exception ex){
				throw new ServiceException (ex.toString());
			}
				IDataCursor outputNodeCursor = outputNode.getCursor();
		
			// document
			IData	document = IDataUtil.getIData( outputNodeCursor , "document" );
			outputNodeCursor.destroy();
		
			IDataUtil.put(pipelineCursor, "Response", document );
		 
		} catch (Exception ex) {
			throw new ServiceException (ex.toString());
		} finally {
			pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

