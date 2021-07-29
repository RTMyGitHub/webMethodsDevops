package SmartEDI.EDI.Web.Helper;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-08-18 15:21:14 EDT
// -----( ON-HOST: windows-vultr

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.tn.util.qm.TNQuery;
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
import com.wm.app.tn.profile.*;
// --- <<IS-END-IMPORTS>> ---

public final class TN

{
	// ---( internal utility methods )---

	final static TN _instance = new TN();

	static TN _newInstance() { return new TN(); }

	static TN _cast(Object o) { return (TN)o; }

	// ---( server methods )---




	public static final void getIdTypesInternal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getIdTypesInternal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required idType
		// [i] object:0:required idTypes
		// [o] object:0:required idTypeInternal
		String idType = ValuesEmulator.getString(pipeline, "idType");
		Hashtable idTypes = (Hashtable) ValuesEmulator.get(pipeline, "idTypes");
		int i = Integer.parseInt(idTypes.get(idType).toString());
		ValuesEmulator.put(pipeline, "idTypeInternal", i);
		// --- <<IS-END>> ---

                
	}



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
		            	throw new ServiceException("TRNSERV.000018.000063");
		        	//  KK: Just a quick fix - no doc on EXMLException
	            	//	throw EXMLException.fromResource("TRNSERV.000018.000063", null, null);
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
				    if (pgs != null ){
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
		        	throw new ServiceException("TRNSERV.000018.000062");
		        	// 	KK: Just a quick fix - no doc on EXMLException	        	
		        	// 	throw EXMLException.fromResource("TRNSERV.000018.000062", null, t);
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



	public static final void setTNProfile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(setTNProfile)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:required idTypes
		// [o] object:0:required TNProfile
		IData theProfile = (IData) ValuesEmulator.get(pipeline, "profileInfo");
		Hashtable idTypes = (Hashtable) ValuesEmulator.get(pipeline, "idTypes");
		
		IData ids = (IData) ValuesEmulator.get(theProfile, "IDs");
		String DUNSExternalID = ValuesEmulator.getString(ids, "DUNSExternalID");
		String EDIFRAMEWORKExternalID = ValuesEmulator.getString(ids, "EDIFRAMEWORKExternalID");
		
		IData ediid = (IData) ValuesEmulator.get(theProfile, "ediID");
		String idType = ValuesEmulator.getString(ediid, "idType");
		String ID = ValuesEmulator.getString(ediid, "ID");
		
		
		String corporationName = ValuesEmulator.getString(theProfile, "corporationName");
		String unitName = ValuesEmulator.getString(theProfile, "unitName");
		String profileGroupMemberShip =  ValuesEmulator.getString(theProfile,"profileGroupMemberShip");
		String[] pgms= {LookupStore.getProfileGroupId(profileGroupMemberShip)};
		
		
		Profile profile = new Profile();
		
		//base info
		Corporation corp = new Corporation();
		corp.setCorporationName(corporationName);
		corp.setOrgUnitName (unitName);
		corp.setPartnerType(Constants.PARTNER_TYPE_SERVER);
		corp.setStatus(Constants.PARTNER_STATUS_ACTIVE);
		corp.setSelf(false);
		profile.setCorporation(corp);
		profile.setProfileGroups(pgms);
		//external ID
		ID extID = new ID();
		int i = Integer.parseInt(idTypes.get("EDIFRAMEWORK").toString());
		extID.setIDType(i); 
		extID.setExternalID(EDIFRAMEWORKExternalID);
		profile.addID(extID);
		
		//DUNS
		extID = new ID();
		i = Integer.parseInt(idTypes.get("DUNS").toString());
		extID.setIDType(i); // DUNS
		extID.setExternalID(DUNSExternalID);
		profile.addID(extID);
		
		
		//EDI Ids
		if (idType!=null && idType.trim().length()>0) {
		extID = new ID();
		int iii = Integer.parseInt(idTypes.get(idType).toString());
		extID.setIDType(iii); // edi qualifier to TNid
		extID.setExternalID(ID);
		profile.addID(extID);
		}
		
		
		ValuesEmulator.put(pipeline, "TNProfile", profile);
		
			
		// --- <<IS-END>> ---

                
	}
}

