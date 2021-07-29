package SmartEDI.Common.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-12-24 06:52:12 EST
// -----( ON-HOST: 10.168.51.4

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.tn.db.BizDocStore;
import com.wm.app.tn.db.DatastoreException;
// --- <<IS-END-IMPORTS>> ---

public final class Tnutils

{
	// ---( internal utility methods )---

	final static Tnutils _instance = new Tnutils();

	static Tnutils _newInstance() { return new Tnutils(); }

	static Tnutils _cast(Object o) { return (Tnutils)o; }

	// ---( server methods )---




	public static final void uniqueness (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(uniqueness)>> ---
		// @sigtype java 3.5
		// [i] field:0:required docID
		// [i] field:0:required documentType
		// [i] field:0:required senderID
		// [o] field:0:required result
		String docId = null;
		String documentType = null;
		String senderID = null;
		int val = 0;
		String result = "false";
		IDataCursor idcPipeline = null ;
			
		try{
			idcPipeline = pipeline.getCursor();
		
			if(idcPipeline.first("docID")) {
				docId = (String) idcPipeline.getValue();
			}
			if(idcPipeline.first("senderID")) {
				senderID = (String) idcPipeline.getValue();
			}
			if(idcPipeline.first("documentType")) {
				documentType = (String) idcPipeline.getValue(); 
			}
		
			BizDocStore bs = new BizDocStore();
		
			try {
		
				val = bs.checkUnique(1,docId,senderID,documentType,null);
		
		
				if (val > 1) {
					result = "false";
				}
				else {
					result = "true";
				}
		
			} catch (com.wm.app.tn.db.DatastoreException de) {
				throw new ServiceException(de.getMessage());
			}
		catch (java.lang.Exception ex) {
				throw new ServiceException(ex.getMessage());	
			}
		
			idcPipeline.insertAfter("result", result);
		}//end of try
		
		catch (java.lang.Exception e)
		{
		throw new ServiceException(e.getMessage());
		}
		
		
		finally {
			//Always destroy cursors that you created
			if(idcPipeline != null ) {
				idcPipeline.destroy();
			}
		}	
			
		// --- <<IS-END>> ---

                
	}
}

