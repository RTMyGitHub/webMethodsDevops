package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-12-24 06:51:22 EST
// -----( ON-HOST: 10.168.51.4

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.util.coder.*;
import com.wm.data.*;
// --- <<IS-END-IMPORTS>> ---

public final class broker

{
	// ---( internal utility methods )---

	final static broker _instance = new broker();

	static broker _newInstance() { return new broker(); }

	static broker _cast(Object o) { return (broker)o; }

	// ---( server methods )---




	public static final void DecodeFromESDoc (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(DecodeFromESDoc)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required decodedDocument
		IDataCursor idcPipeline = pipeline.getCursor();
		String keyValue = "";
		IDataBinCoder idatabincoder = null;
		IData idata = null;
		byte abyte0[] = null;
		boolean gotfirst = false;
		String lastkey = "";
		
		try{
		idcPipeline = pipeline.getCursor();
		idcPipeline.last();
		lastkey = idcPipeline.getKey();
		idatabincoder = new IDataBinCoder();
		
		if (idcPipeline.first()){
			while (!gotfirst && !keyValue.equalsIgnoreCase(lastkey)) {
				keyValue = 	idcPipeline.getKey();
				if ((idcPipeline.getKey()).indexOf("Publishable")>0) {
					idata = (IData)idcPipeline.getValue();
					IDataCursor idCursor = idata.getCursor();
					if (idCursor.first("IDATA")){
						abyte0 = (byte []) idCursor.getValue();
						idata = idatabincoder.decodeFromBytes(abyte0);
						idcPipeline.insertAfter("decodedDocument",idata);
						gotfirst = true;
					}
					else {
						gotfirst = true;
						throw new ServiceException("Invalid ES document Type must contain an IDATA element");
					}
				}
			idcPipeline.next();
			}
			if (!gotfirst)
				throw new ServiceException("Pipeline does not contain required publishable document");
			}
		} catch (java.lang.Exception e) {
			throw new ServiceException(e);
		} finally {
			if(idcPipeline != null ) { 
				idcPipeline.destroy();
			}
		}
		// --- <<IS-END>> ---

                
	}



	public static final void EncodeToESDoc (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(EncodeToESDoc)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:required recordIn
		// [o] object:0:required binaryIDATA
		IDataCursor idcPipeline = null;
		
		try {
				idcPipeline = pipeline.getCursor();
				IData idata = (new IDataUtil()).getIData(idcPipeline, "recordIn");
		
				byte abyte0[] = (new IDataBinCoder()).encodeToBytes(idata);
				if (abyte0 != null) 
					idcPipeline.insertAfter("binaryIDATA",abyte0);
				else {
					throw new ServiceException("Value of Input cannot be null");
				}
		} catch (java.lang.Exception ex) {
			throw new ServiceException(ex);
		} finally {
			if(idcPipeline != null ) {
				idcPipeline.destroy();
			}
		}
		// --- <<IS-END>> ---

                
	}



	public static final void extractFieldsFromEvent (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(extractFieldsFromEvent)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required documentOut
		// [o] - field:0:required Data1
		// [o] - field:0:required Data2
		// [o] - field:0:required Data3
		// [o] - field:0:required Data4
		// [o] - field:0:required Data5
		// [o] - field:0:required DocumentID
		// [o] - field:0:required BODNumber
		IDataCursor idcPipeline = null ;
		
		String keyValue = "";
		IDataBinCoder idatabincoder = null ;
		IData idata = null;
		String Data = null;
		boolean gotfirst = false;
		String lastkey = "" ;
		
		try{ 
		idcPipeline = pipeline.getCursor();
		idatabincoder = new IDataBinCoder();
		IData documentOut = IDataFactory.create();
		IDataCursor idcDocumentOut = documentOut.getCursor();
		
		idcPipeline.last();
		lastkey = idcPipeline.getKey();
		
		if (idcPipeline.first()){
			while (!gotfirst && !keyValue.equalsIgnoreCase(lastkey)) {
				keyValue = 	idcPipeline.getKey();
				if ((idcPipeline.getKey()).indexOf("Publishable")>0) {
					idata = (IData)idcPipeline.getValue();
					IDataCursor idCursor = idata.getCursor();
						if (idCursor.first("Data1"))
						{
							Data = (String)idCursor.getValue();
							String key = idCursor.getKey();
							IDataUtil.put( idcDocumentOut, key, Data );
							gotfirst = true;
						}
		
						if (idCursor.first("Data2"))
						{
							Data = (String)idCursor.getValue();
							String key = idCursor.getKey();
							IDataUtil.put( idcDocumentOut, key, Data );
							gotfirst = true;
						}
		
						if (idCursor.first("Data3"))
						{
							Data = (String)idCursor.getValue();
							String key = idCursor.getKey();
							IDataUtil.put( idcDocumentOut, key, Data );
							gotfirst = true;
						}
		
						if (idCursor.first("Data4"))
						{
							Data = (String)idCursor.getValue();
							String key = idCursor.getKey();
							IDataUtil.put( idcDocumentOut, key, Data );
							gotfirst = true;
						}
		
						if (idCursor.first("Data5"))
						{
							Data = (String)idCursor.getValue();
							String key = idCursor.getKey();
							IDataUtil.put( idcDocumentOut, key, Data );
							gotfirst = true;
						}
		
						if (idCursor.first("BODNumber"))
						{
							Data = (String)idCursor.getValue();
							String key = idCursor.getKey();
							IDataUtil.put( idcDocumentOut, key, Data );
							gotfirst = true;
						}
		
						if (idCursor.first("DocumentID"))
						{
							Data = (String)idCursor.getValue();
							String key = idCursor.getKey();
							IDataUtil.put( idcDocumentOut, key, Data );
							gotfirst = true;
						}
		
				}
		
			idcPipeline.next();
			}
			if (!gotfirst)
				throw new ServiceException("Pipeline does not contain required publishable document");
		}
		IDataUtil.put( idcPipeline, "documentOut", documentOut );
		
		} catch (java.lang.Exception ex) {
			throw new ServiceException(ex);
		} finally {
			if(idcPipeline != null ) {
				idcPipeline.destroy();
			}
		}
		// --- <<IS-END>> ---

                
	}
}

