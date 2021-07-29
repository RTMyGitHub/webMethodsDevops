package SmartEDI.EDIINT.Payload;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-06 16:30:39 EDT
// -----( ON-HOST: testBox-PC

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Enumeration;
import java.util.Vector;
import com.wm.app.tn.profile.ProfileStore;
import com.wm.app.tn.profile.ProfileSummary;
import com.wm.app.tn.profile.LookupStore;
import com.wm.app.tn.err.EXMLException;
import com.wm.app.tn.util.I18NUtil;
// --- <<IS-END-IMPORTS>> ---

public final class Helper

{
	// ---( internal utility methods )---

	final static Helper _instance = new Helper();

	static Helper _newInstance() { return new Helper(); }

	static Helper _cast(Object o) { return (Helper)o; }

	// ---( server methods )---




	public static final void getPartners (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getPartners)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:1:required ProfileSummary
		// [o] - field:0:required DisplayName
		// [o] - field:0:required CorporationName
		// [o] - field:0:required OrgUnitName
		// [o] - field:0:required ProfileInternalID
		// [o] - field:0:required ProfileGroup
		// [o] - field:0:required ProfileGroupAS2
		        IDataCursor cur = null;
		        IDataCursor pCur = null;
		        try
		        {
		            cur = pipeline.getCursor();
		            Vector v = ProfileStore.getProfileSummaryList(false, true);
		            IData partners[] = null;
		            if(v != null && v.size() != 0)
		                partners = new IData[v.size() - 1];
		            else
		//		                throw EXMLException.fromResource("TRNSERV.000018.000063", null, null);
		                throw new EXMLException("TRNSERV.000018.000063", null);
		            Enumeration en = v.elements();
		            for(int i = 0; en.hasMoreElements(); i++)
		            {
		                ProfileSummary ps = (ProfileSummary)en.nextElement();
		                String name = ps.getDisplayName();
		                String id = ps.getProfileID();
		                if(I18NUtil.isEqual(name, "Unknown") && I18NUtil.isEqual(id, "000000000000000000000000"))
		                {
		                    i--;
		                } else
		                {
		                    partners[i] = IDataFactory.create();
		                    pCur = partners[i].getCursor();
		                    pCur.insertAfter("DisplayName", ps.getDisplayName());
		                    pCur.insertAfter("CorporationName", ps.getCorporationName());
				    pCur.insertAfter("OrgUnitName", ps.getOrgUnitName());
		                    pCur.insertAfter("ProfileInternalID", ps.getProfileID());
			   
				   String[] pgs = ps.getProfileGroups();
		                      if (pgs != null){
		             	    pCur.insertAfter("ProfileGroup",LookupStore.getProfileGroupName(pgs[0]) );
				     }else {
				     pCur.insertAfter("ProfileGroup",null );
		
				     }
		                                                             
		                }
		            }
		
		            cur.insertAfter("ProfileSummary", partners);
		        }
		        catch(Throwable t)
		        {
		//		            throw EXMLException.fromResource("TRNSERV.000018.000062", null, t);
		            throw new EXMLException("TRNSERV.000018.000062", t);
		        }
		        finally
		        {
		            if(cur != null)
		                cur.destroy();
		            if(pCur != null)
		                pCur.destroy();
		        }
		// --- <<IS-END>> ---

                
	}
}

