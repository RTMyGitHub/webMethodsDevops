package SmartEDI.Common.Helper.tn;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-08-19 07:41:04 EDT
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.tn.util.qm.TNQuery;
import com.wm.app.b2b.server.*;
import com.wm.app.tn.db.*;
import com.wm.app.tn.doc.*;
import com.wm.app.tn.err.EXMLException;
import com.wm.app.tn.profile.ProfileStore;
import com.wm.app.tn.profile.ProfileSummary;
import com.wm.app.tn.security.SecurityUtil;
import com.wm.app.tn.util.*;
import com.wm.app.tn.util.qm.TNQueryset;
import com.wm.util.coder.IDataCodable;
import java.text.*;
import java.util.*;
import java.io.UnsupportedEncodingException;
// --- <<IS-END-IMPORTS>> ---

public final class getPartnerNameAndID

{
	// ---( internal utility methods )---

	final static getPartnerNameAndID _instance = new getPartnerNameAndID();

	static getPartnerNameAndID _newInstance() { return new getPartnerNameAndID(); }

	static getPartnerNameAndID _cast(Object o) { return (getPartnerNameAndID)o; }

	// ---( server methods )---




	public static final void getPartners (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getPartners)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:1:required partners
		// [o] - field:0:required name
		// [o] - field:0:required partnerId
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
		        throw EXMLException.fromResource(0,"TRNSERV.000018.000063", null, null,null,null);
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
		            pCur.insertAfter("name", ps.getDisplayName());
		            pCur.insertAfter("partnerId", ps.getProfileID());
		        }
		    }
		
		    cur.insertAfter("partners", partners);
		}
		catch(Throwable t)
		{
		    throw EXMLException.fromResource(0,"TRNSERV.000018.000062",null, null,null, t);
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

