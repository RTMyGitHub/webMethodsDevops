<html>
<head>
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
  <meta http-equiv="Expires" CONTENT="-1">
  <link rel="stylesheet" TYPE="text/css" HREF="/WmRoot/webMethods.css"></link>
  <SCRIPT SRC="/WmRoot/webMethods.js.txt"></SCRIPT>
</head>
<SCRIPT language="JavaScript">
var i=0;
  function populateData(urlAliasForm)
  {

     parent.XREFAliasUpdFrame.XREFAliasForm.urlAlias.value = urlAliasForm.urlAlias.value;
     parent.XREFAliasUpdFrame.XREFAliasForm.orgID.value = urlAliasForm.orgID.value;
     parent.XREFAliasUpdFrame.XREFAliasForm.requestType.value = urlAliasForm.requestType.value;
     parent.XREFAliasUpdFrame.XREFAliasForm.identity.value = urlAliasForm.identity.value;
     parent.XREFAliasUpdFrame.XREFAliasForm.senderDUNS.value = urlAliasForm.senderDUNS.value;
     parent.XREFAliasUpdFrame.XREFAliasForm.receiverDUNS.value = urlAliasForm.receiverDUNS.value;

     return;
  }
</SCRIPT>

  %invoke SQFramework.SQRoute.Common.Services.XREF.Web.URLAlias:getAllURLAlias%
    %onerror%
      <p> <b> Service Name: </b>  %value errorService% </p>
      <p> <b> Error Message: </b> %value errorMessage% </p>
  %endinvoke%

<body>
<p> <b> </b> <font color="red">%value errorMessage% </font></p>
    <table align="center" width="100%" border="1" cellpadding="2%">
	%loop routingInfo%
	   <form name=%value urlAlias%%value orgID%%value requestType%%value identity%%value senderDUNS%%value receiverDUNS%>
	       <input type="hidden" name=urlAlias value='%value urlAlias%'>
	     <input type="hidden" name=orgID value='%value orgID%'>
	     <input type="hidden" name=requestType value='%value requestType%'>
	     <input type="hidden" name=identity value='%value identity%'>

	     <input type="hidden" name=senderDUNS value='%value senderDUNS%'>
	     <input type="hidden" name=receiverDUNS value='%value receiverDUNS%'>
	   </form>
	   <tr class="rowdata">
	     <td align="left" width="10%">
	       <A href="javascript:populateData(document.%value urlAlias%%value orgID%%value requestType%%value identity%%value senderDUNS%%value receiverDUNS%)">  %value urlAlias%-%value orgID%-%value requestType%-%value identity%-%value senderDUNS%-%value receiverDUNS% </A>
             </td>
	     <td align="left">
	       <table align="center" width="100%" border="1" cellpadding="2%">
	         <tr>
	           <td align="left" width="28%"> <b>URL Alias:</b> </td>
                   <td align="left" width="72%"> %value urlAlias% </td>
		 </tr>
               </table>
               <table align="center" width="100%" border="1" cellpadding="2%">
	         <tr>
                   <td align="left" width="28%"> <b>ORG ID:</b> </td>
                   <td align="left" width="72%"> %value orgID% </td>
		 </tr>
               </table>
	       <table align="center" width="100%" border="1" cellpadding="2%">
	         <tr>
	           <td align="left" width="28%"> <b>Request Type:</b> </td>
                   <td align="left" width="72%"> %value requestType% </td>
		 </tr>
               </table>
               <table align="center" width="100%" border="1" cellpadding="2%">
	       	         <tr>
	       	           <td align="left" width="28%"> <b>Identity:</b> </td>
	                          <td align="left" width="72%"> %value identity% </td>
	       		 </tr>
               </table>
               <table align="center" width="100%" border="1" cellpadding="2%">
	         <tr>
	           <td align="left" width="28%"> <b>Sender DUNS:</b> </td>
                   <td align="left" width="72%"> %value senderDUNS% </td>
		 </tr>

               </table>
               <table align="center" width="100%" border="1" cellpadding="2%">
	         <tr>
	           <td align="left" width="28%"> <b>Receiver DUNS:</b> </td>
                   <td align="left" width="72%"> %value receiverDUNS% </td>
		 </tr>

               </table>
               <table align="center" width="100%" border="1" cellpadding="2%">


               </table>
	     </td>
	   </tr>
	%endloop%
	</table>


</body>
</html>