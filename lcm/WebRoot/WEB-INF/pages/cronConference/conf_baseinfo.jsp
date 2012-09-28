<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sd" uri="/struts-dojo-tags" %>
 <table border="0" cellspacing="0" cellpadding="0" class="dialogue_table tab_panel_table">
    <colgroup>
    	<col style="width: 20%;"  />
    	<col style="width: 30%;" />
    	<col style="width: 20%;"  />
    	<col style="width: 30%;" />
    </colgroup>
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.confname" />
    	</td>
    	<td class="dialogue_td_input" colspan="3">
    		<s:textfield name="conf.confSubject" theme="simple" />
    	</td>
    	<!--  <td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.text.callno" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="conf.customer.nos" listKey="id" listValue="noCode" name="conf.conferenceNo.id" />
    	</td>-->
    </tr>
   <tr>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.cronconf.pertype" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="cronTypeList" name="conf.cronType" cssClass="type_select" />
    	</td>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.cronconf.pertime" />
    	</td>
    	<td class="dialogue_td_input" id="cron_time_select">
    		<s:if test="conf.id==null">
    			<s:select list="dateList" name="conf.cronTime" theme="simple" cssClass="date_select_date" cssStyle="display:none" />
    			<s:select list="weekList" name="conf.cronTime" theme="simple" cssClass="date_select_week" cssStyle="display:none" />
    		</s:if>
    		<s:else>
    			<s:if test="conf.cronType==1">
    				<s:select list="dateList" name="conf.cronTime" theme="simple" cssClass="date_select_date" />
    				<s:select list="weekList" theme="simple" cssClass="date_select_week" cssStyle="display:none" />
    			</s:if>
    			<s:else>
    				<s:select list="dateList" theme="simple" cssClass="date_select_date" cssStyle="display:none" />
    				<s:select list="weekList" name="conf.cronTime" theme="simple" cssClass="date_select_week" />
    			</s:else>
    		</s:else>
    	</td>
    </tr>
    <tr>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.cronconf.hours" />
    	</td>
    	<td class="dialogue_td_input">
    		<%--<s:textfield name="conf.length" />--%>
    		<s:textfield name="datetimePair.startTime" cssClass="timepicker"></s:textfield>
    	</td>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.cronconf.length" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:textfield name="conf.length" />
    	</td>
    </tr>
    <tr>
    	<!-- <td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.text.host" />
    	</td>
    	<td class="dialogue_td_input">
			<s:select list="userList" name="conf.confHost.id" listKey="id" listValue="username" theme="simple" />
    	</td> -->
    	<td class="dialogue_td_text">
    		<s:text name="page.text.public" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select id="ispublic" list="yesNoList" name="conf.confPublic" theme="simple" onchange="if(this.value==1){$('#userpasswd')[0].disabled='true';}if(this.value==2){$('#userpasswd')[0].disabled='';}"/>
    	</td>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.confpass" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:password name="conf.realPassword" theme="simple" id="userpasswd" disabled="true"/>
    	</td>
    </tr>
    <!--<tr>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.text.confmode" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="confModeList" name="conf.confMode" theme="simple" />
    	</td>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.text.memberlimit" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:textfield name="conf.confMemberCount" theme="simple" />	
    	</td>
    </tr>-->
    <!-- <tr>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.conf.mixerkey" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="conf.customer.mcuServer.mixers" name="conf.mcuMixerKey" listKey="mixerKey"
    			listValue="mixerKey" theme="simple"  />
    	</td>

    </tr>-->
    <!--<tr>

    	
    	  <td class="dialogue_td_text">
    		<s:text name="page.text.requiredlogin" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="yesNoList" name="conf.requiredLogin" theme="simple"  />
    	</td>
    	<td class="dialogue_td_text">
    		<s:text name="page.conf.allowbefore" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="allowBeforeList" name="conf.confAllowBefore" theme="simple" />
    	</td>
    </tr>-->
    
    <!--<tr>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.text.videolimit" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:textfield name="conf.confVideoCount" theme="simple" />
    	</td>
    </tr>-->
    <tr>
    	<!--<td class="dialogue_td_text">
    		<s:text name="page.conf.mediaquality" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="mediaQuality" name="conf.mcuMediaQuality" theme="simple" />
		</td>-->
    	<td class="dialogue_td_text">
    		<s:text name="page.conf.medialayout" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="mcuMediaLayout" name="conf.mcuMediaLayOut"></s:select>
    	</td>
		<td class="dialogue_td_text">
			<s:text name="page.text.mediaplay" />
		</td>
		<td class="dialogue_td_input">
			<s:select list="confMedias" name="conf.mediaId" theme="simple" headerKey="" headerValue=""/>
		</td>
    </tr>
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.conf.mediaquality" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select list="mediaQuality" name="conf.mcuMediaQuality" theme="simple" />
		</td>
			<td class="dialogue_td_text">
			<span class="required">*</span>
   			<s:text name="page.conf.meetingtype" />
   		</td>
   		<td class="dialogue_td_input">
   		<s:if test="conf.id==null">
   		    <s:select list="mixOrTransList" name="conf.ismix" theme="simple" headerKey="" headerValue="请选择"></s:select>
   		</s:if>
   		<s:else>
   		    <s:select list="mixOrTransList" name="conf.ismix" theme="simple" disabled="true" headerKey="" headerValue="请选择"></s:select>
   		</s:else>
   		</td>
    </tr>
	<tr>

    	<td class="dialogue_td_text">
    		<s:text name="page.text.desc" />
    	</td>
    	<td class="dialogue_td_input" colspan="3">
    		<s:textarea name="conf.confDesc" cssClass="dialogue_textarea" theme="simple" />
    	</td>
	</tr>
</table>
<script type="text/javascript">
  	$(document).ready(function(){
  		 var ispublic = $("#ispublic")[0].value;
  		 if(ispublic==1){
  		 	$("#userpasswd")[0].disabled='true';
  		 }
  		 if(ispublic==2){
  		 	$("#userpasswd")[0].disabled='';
  		 }
  	});
</script>