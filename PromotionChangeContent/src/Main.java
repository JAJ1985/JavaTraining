<%@ page session="true" %>
<%@ page import="com.xsoft.estore.store.saf.*"%>
<%@ page import="com.xsoft.estore.admin.displayPage.saf.DisplayPageSession"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xsoft.estore.company.saf.*"%>
<%@ page import="com.xsoft.estore.util.Util"%>
<%@ page import="com.xsoft.estore.admin.promotion.*"%>
<%@ page import="com.xsoft.estore.admin.promotion.saf.*"%>
<%@ page import="com.xsoft.estore.admin.promotionType.*"%>
<%@ page import="com.xsoft.estore.admin.promotionType.saf.*"%>
<%@ page import="com.xsoft.estore.admin.campaign.*"%>
<%@ page import="com.xsoft.estore.admin.campaign.saf.*"%>
<%@ page import="com.xsoft.estore.admin.offer.*"%>
<%@ page import="com.xsoft.estore.admin.offer.saf.*"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
        String sReqURI = request.getRequestURI();
        String sServlPath = request.getServletPath();
        String appWebPath = sReqURI.substring(0,sReqURI.length() - sServlPath.length() + 1);

        DisplayPageSession displayPageSession = DisplayPageSession.acquire(request);
        Map labels = displayPageSession.getLabels(request);

        String companyCode = CompanySession.acquire(request).getCurrentCompany().getCode();
        String storeCode = StoreSession.acquire(request).getCurrentStore().getCode();
        %>
<script type="text/javascript" src="javascript/calendarDateInput.js">
/***********************************************
 * Jason's Date Input Calendar- By Jason Moon http://www.jasonmoon.net/
 * Script featured on and available at http://www.dynamicdrive.com
 * Keep this notice intact for use.
 ***********************************************/
</script>

<SCRIPT LANGUAGE="JavaScript">
        var applicationUrl = 'http://<%=request.getServerName()%><%=request.getServerPort()==80?"":":"+request.getServerPort()%>';
        applicationUrl = applicationUrl + "<%=appWebPath%>";

        function save() {
        return SAFSubmit1(document.forms[mainFormName], 'promotionAdmin.do', 'changePromotion', 'showPromotionPage');
        }

        function cancel() {
        return SAFSubmit0(document.forms[mainFormName], 'showPromotionPage.do', 'showPromotionPage');
        }

        function removal() {
        confirmStatus = window.confirm('<%=displayPageSession.getGlobalLabel("pleaseConfirmDelete")%>');
        if (!confirmStatus) {
        return false; }
        else {
        return SAFSubmit0(document.forms[mainFormName], 'promotionAdmin.do', 'removePromotion');
        }
        }

        function processForm() {
        if (validate()){
        save();
        }
        return false;
        }

        function validate(){

        if(isBlank(document.forms[mainFormName].description.value)){
        alert('\"<%=displayPageSession.getLabel(labels, "desc")%>\" should not be empty');
        document.forms[mainFormName].description.select();
        document.forms[mainFormName].description.focus();
        return false;
        }
        if(isBlank(document.forms[mainFormName].useCounter.value)){
        alert('\"<%=displayPageSession.getLabel(labels, "useCounter")%>\" should not be empty');
        document.forms[mainFormName].useCounter.select();
        document.forms[mainFormName].useCounter.focus();
        return false;
        }
        if(isBlank(document.forms[mainFormName].maxUseCounter.value)){
        alert('\"<%=displayPageSession.getLabel(labels, "maxUseCounter")%>\" should not be empty');
        document.forms[mainFormName].maxUseCounter.select();
        document.forms[mainFormName].maxUseCounter.focus();
        return false;
        }

        return true;
        }
</SCRIPT>

<%

        CampaignSession campaignSession = CampaignSession.acquire(request);
        CampaignModel campaignModel = campaignSession.getCampaignModel();
        Iterator campaignIterator = campaignModel.retrieveCampaigns().iterator();
        pageContext.setAttribute("campaignIterator", campaignIterator);

        OfferSession offerSession = OfferSession.acquire(request);
        OfferModel offerModel = offerSession.getOfferModel();

        Iterator offerIterator = offerModel.retrieveOffers(companyCode, storeCode,true);
        //pageContext.setAttribute("offerIterator", offerIterator);

        PromotionTypeSession promoTypeSession = PromotionTypeSession.acquire(request);
        PromotionTypeModel promoTypeModel = promoTypeSession.getPromotionTypeModel();
        Iterator promoIterator = promoTypeModel.retrievePromotionTypes().iterator();
        pageContext.setAttribute("promoIterator", promoIterator);

        PromotionAdminForm promotionAdminForm = null;
        Object form = request.getAttribute("PromotionAdminForm");
        String startDate = null;
        String endDate = null;
        if(form != null){
        promotionAdminForm = (PromotionAdminForm)form;promotionAdminForm = (PromotionAdminForm)form;
        Calendar cal = new GregorianCalendar();
        startDate = promotionAdminForm.getLaunchDate() != null ?
        promotionAdminForm.getLaunchDate() : Util.getDateMmmDdYyyy(cal.getTime());
        endDate = promotionAdminForm.getEndDate() != null ?
        promotionAdminForm.getEndDate() : Util.getDateMmmDdYyyy(cal.getTime());
        }

        %>
<html:hidden property="companyCode"/>
<html:hidden property="storeCode"/>


<table width="98%" align="center" cellpadding="1" cellspacing="0" border="0">
<tr><td class="text-hdrred"><%=displayPageSession.getLabel(labels, "title")%></td></tr>
<tr><td><img src="jsp/images/drkLine.gif" width="98%" height="1" alt="" border="0"></td></tr>
<tr><td height="10"></td></tr>
</table>

<table width="98%" align="center" border="0" cellpadding="1" cellspacing="0">
<tr>
<td>
<table border="0" cellspacing="0" cellpadding="1">
<tr>
<td class="text-sm"><%=displayPageSession.getLabel(labels, "promotionCode")%></td>
<td class="text-sm"><html:hidden property="promotionCode" write="true" /></td>
<td class="text-sm" colspan="2" align="right"></td>
</tr>
<tr>
<td class="text-smred"><%=displayPageSession.getLabel(labels, "desc")%></td>
<td class="text-sm">
<html:text property="description" size="40" maxlength="15" styleClass="inputText" mandatory="true" displayName='<%=displayPageSession.getLabel(labels, "desc")%>'/>
</td>
<td class="text-sm"><%=displayPageSession.getLabel(labels, "longDesc")%></td>
<td class="text-sm" colspan="3">
<html:text property="longDescription" size="100" maxlength="100" styleClass="inputText" mandatory="true" displayName='<%=displayPageSession.getLabel(labels, "longDesc")%>'/>
</td>
</tr>
<tr>
<td width="110" class="text-smred"><%=displayPageSession.getLabel(labels, "launchDate")%></td>
<td>
<script>DateInput('launchDate', true, 'MON-DD-YYYY','<%=startDate%>')</script>

</td>
<td width="110" class="text-smred"><%=displayPageSession.getLabel(labels, "expireDate")%></td>
<td>
<script>DateInput('endDate', true, 'MON-DD-YYYY','<%=endDate%>')</script>
</td>
</tr>
<tr>
<td width="110" class="text-smred"><%=displayPageSession.getLabel(labels, "campaigns")%></td>
<td class="text-sm">
<html:select property="campaignCode" styleClass="inputText">
<html:options collection="campaignIterator" property="campaignCode" labelProperty="description"/>
</html:select>
</td>
<td width="110" class="text-smred"><%=displayPageSession.getLabel(labels, "offers")%></td>
<td class="text-sm">
<html:select property="offerId" styleClass="inputText">
<%
        while(offerIterator.hasNext()){
        String offer=(String)offerIterator.next();
        String offerID=offer.substring(0,offer.indexOf(":"));
        %>
<option value="<%=offerID%>"<%=(offerID.equals(promotionAdminForm.getOfferId().trim())?" selected ":"")%>><%=offer%></option>
<%
        }


        %>
</html:select>
</td>
</tr>
<tr>
<td class="text-smred"><%=displayPageSession.getLabel(labels, "useCounter")%></td>
<td class="text-sm">
<html:text property="useCounter" size="13" maxlength="15" styleClass="inputText" mandatory="true" displayName='<%=displayPageSession.getLabel(labels, "useCounter")%>'/>
</td>
<td class="text-smred"><%=displayPageSession.getLabel(labels, "maxUseCounter")%></td>
<td class="text-sm" colspan="3">
<html:text property="maxUseCounter" size="55" maxlength="100" styleClass="inputText" mandatory="true" displayName='<%=displayPageSession.getLabel(labels, "maxUseCounter")%>'/>
</td>
</tr>
<tr>
<td width="110" class="text-smred"><%=displayPageSession.getLabel(labels, "promotionTypes")%></td>
<td class="text-sm">
<html:select property="promoTypeCode" styleClass="inputText">
<html:options collection="promoIterator" property="promotionTypeCode" labelProperty="description"/>
</html:select>
</td>
<td width="110" class="text-sm">Assist send priority</td>
<td class="text-sm">
<html:text property="assistSendPriority" size="3" maxlength="3" styleClass="inputText"  displayName="Assist send priority"/>
</td>
</tr>
<tr>
<td width="110" class="text-sm">Default Uses</td>
<td class="text-sm">
<html:text property="defaultUses" size="3" maxlength="5" styleClass="inputText"  displayName="Default Uses"/>
</td>
<td width="110" class="text-sm"></td>
<td class="text-sm">
</td>
</tr>
<tr>
<td class="text-sm"><%=displayPageSession.getGlobalLabel("modifiedDate")%> &nbsp;</td>
<td class="text-sm"><html:hidden property="modifiedDate" write="true" /></td>
<td class="text-sm"><%=displayPageSession.getGlobalLabel("modifiedUser")%> &nbsp;</td>
<td class="text-sm"><html:hidden property="modifiedUserID" write="true" /></td>
</tr>
<tr>
<td class="text-sm"><%=displayPageSession.getGlobalLabel("createdDate")%> &nbsp;</td>
<td class="text-sm"><html:hidden property="createdDate" write="true" /></td>
<td class="text-sm"><%=displayPageSession.getGlobalLabel("createdUser")%> &nbsp;</td>
<td class="text-sm"><html:hidden property="createdUserID" write="true" /></td>
</tr>
<tr>
<td colspan="4">
<input type="button" name="updateBtn" onclick="return processForm()" value="<%=displayPageSession.getGlobalLabel("updateButton")%>&nbsp;&nbsp" class="btnUpdate0" onmouseover="this.className='btnUpdate1'" onmouseout="this.className='btnUpdate0'" alt="<%=displayPageSession.getGlobalAlt("updateButton")%>">
<input type="button" name="cancelBtn" onclick="return cancel()" value="<%=displayPageSession.getGlobalLabel("cancelButton")%>&nbsp;&nbsp" class="btnCancel0" onmouseover="this.className='btnCancel1'" onmouseout="this.className='btnCancel0'" alt="<%=displayPageSession.getGlobalAlt("cancelButton")%>">
<input type="button" name="deleteBtn" onclick="return removal()" value="<%=displayPageSession.getGlobalLabel("deleteButton")%>&nbsp;&nbsp" class="btnDelete0" onmouseover="this.className='btnDelete1'" onmouseout="this.className='btnDelete0'" alt="<%=displayPageSession.getGlobalAlt("deleteButton")%>">
</td>
</tr>
</table>
</td>
</tr>
</table>