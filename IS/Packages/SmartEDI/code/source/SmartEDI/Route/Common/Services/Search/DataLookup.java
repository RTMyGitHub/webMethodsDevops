package SmartEDI.Route.Common.Services.Search;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 14:30:32 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class DataLookup

{
	// ---( internal utility methods )---

	final static DataLookup _instance = new DataLookup();

	static DataLookup _newInstance() { return new DataLookup(); }

	static DataLookup _cast(Object o) { return (DataLookup)o; }

	// ---( server methods )---




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
		// [o] field:0:required searchValueFound
		//get a cursor on the pipeline
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

