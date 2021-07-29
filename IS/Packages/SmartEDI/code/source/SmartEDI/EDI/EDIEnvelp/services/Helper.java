package SmartEDI.EDI.EDIEnvelp.services;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-11-24 14:03:41 EST
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class Helper

{
	// ---( internal utility methods )---

	final static Helper _instance = new Helper();

	static Helper _newInstance() { return new Helper(); }

	static Helper _cast(Object o) { return (Helper)o; }

	// ---( server methods )---




	public static final void appendMultStrings (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(appendMultStrings)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:required inStringList
		// [o] field:0:required outString
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String[] inStringList = IDataUtil.getStringArray( pipelineCursor, "inStringList" );
		pipelineCursor.destroy();
		
		String outString ="";
		if (inStringList !=null){
			for (int i =0; i<inStringList.length; i++)
			{
			outString = outString+inStringList[i];
			}
		}
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "outString", outString );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void recordListToSearchableIData (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(recordListToSearchableIData)>> ---
		// @sigtype java 3.5
		// [i] record:1:required recordList
		// [i] field:0:required keyName
		// [o] record:0:required searchableIData
		//get cursor on pipeline
		IDataCursor idc = pipeline.getCursor();
		
		
		//define working variables
		IData[] recordList = null;
		String keyName = null;
		IData searchableIData = IDataFactory.create();
		IDataCursor idcSID = searchableIData.getCursor();
		
		
		try {
		
			//attempt to get recordList parameter from pipeline, throw exception if not found
			if (idc.first("recordList")) {
				recordList = IDataUtil.getIDataArray(idc, "recordList");
			} else {
				throw new ServiceException("ERROR: recordListToSearchableIData - Parameter 'recordList' must contain a valid record list");
			}
		
			//attempt to get keyName parameter from pipeline, throw exception if not found
			if (idc.first("keyName")) {
				keyName = (String)idc.getValue();
			} else {
				throw new ServiceException("ERROR: recordListToSearchableIData - Parameter 'keyName' must be provided");
			}
			
		
			for (int i=0;i<recordList.length;i++) {
				IData temprec = recordList[i];
				IDataCursor idcTemprec = temprec.getCursor();
				if (idcTemprec.first(keyName)) {
					String keyValue = (String)idcTemprec.getValue();
					IDataUtil.put(idcSID, keyValue, temprec);
				}
				idcTemprec.destroy();
			}
		
			idc.insertAfter("searchableIData", searchableIData);
		
			idcSID.destroy();
		
		} catch (Exception e) {
			throw new ServiceException ("ERROR: " + e.toString());
		} finally {
			idc.destroy();
		}
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void searchableIDataLookup (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(searchableIDataLookup)>> ---
		// @sigtype java 3.5
		// [i] field:0:required searchValue
		// [i] record:0:required searchableIData
		// [o] record:0:required boundNode
		// [o] - field:0:required CustomSchamaNS
		// [o] field:0:required searchValueFound
		// get a cursor on the pipeline
		IDataCursor idc = pipeline.getCursor();
		
		//define working variables
		boolean searchValueFound = false;
		IData resultrec = null;
		String searchValue = null;
		IData searchableIData = IDataFactory.create();
		IDataCursor idcSID = null;
		
		try {
		
			//get the searchableIData from the pipeline and return an error if it is not found
			if (idc.first("searchableIData")) {
				searchableIData = IDataUtil.getIData(idc, "searchableIData");
				idcSID = searchableIData.getCursor();
			} else {
				throw new ServiceException ("ERROR: searchableIData must be provided");
			}
		
			//get the searchValue from the pipeline and return an error if it is not found
			if (idc.first("searchValue")) {
				searchValue = (String)IDataUtil.get(idc, "searchValue");
			} else {
				throw new ServiceException("ERROR: searchValue must be provided");
			}
		
			//attempt to locate the key that has the value of searchValue in the searchableIData and put the value of that key on the output pipelne as boundNode
			if (idcSID.first(searchValue)) {
				searchValueFound = true;
				resultrec = (IData)IDataUtil.get(idcSID, searchValue);
				idc.insertAfter("boundNode", resultrec);
			} else {
				idc.insertAfter("boundNode", resultrec);
				searchValueFound = false;
			}
		
			//finished with the idcSID cursor, so destroy it
			idcSID.destroy();
		
			//put the searchValueFound on the output pipeline
			idc.insertAfter("searchValueFound", String.valueOf(searchValueFound));
		
		//handle exceptions
		} catch (Exception e) {
			throw new ServiceException ("Error:  " + e.toString());
		} finally {
			idc.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}
}

