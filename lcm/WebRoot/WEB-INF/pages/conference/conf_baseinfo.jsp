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
    		<s:if test="conf.confStatus==null or conf.confStatus==0">
    			<s:textfield name="conf.confSubject" theme="simple" />
    		</s:if>
    		<s:else>
    			<s:textfield name="conf.confSubject" disabled="true" />
    		</s:else>
    	</td>
    	<!--  <td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.text.callno" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:if test="conf.confStatus==null or conf.confStatus==0">
    			<s:select list="conf.customer.nos" listKey="id" listValue="noCode" name="conf.conferenceNo.id" />
    		</s:if>
    		<s:else>
				<s:textfield name="conf.conferenceNo.noCode" disabled="true" />    			
    		</s:else>
    	</td>-->
    </tr>

    
   	<s:if test="conf.confType==2">
   		<tr>
   			<td class="dialogue_td_text">
   				<span class="required">*</span>
   				<s:text name="page.text.starttime" />
   			</td>
   			<td class="dialogue_td_input date_input" colspan="3">
   				<s:if test="conf.confStatus!=1">
   						&nbsp;&nbsp;<s:text name="page.text.date" />
   	    				<sd:datetimepicker name="datetimePair.startDate" displayFormat="yyyy-MM-dd" />
   	    				&nbsp;&nbsp;<s:text name="page.text.time" />
   	    				<s:textfield name="datetimePair.startTime" theme="simple" cssClass="timepicker"></s:textfield>
				</s:if>
   				<s:else>
   					<input type="text" disabled="true" value="<s:date name='conf.startTime' format='yyyy-MM-dd HH:mm' />" />
   				</s:else>
   			</td>
   		</tr>
   	</s:if>
   	<tr>
   		<td class="dialogue_td_text date_input">
   			<span class="required">*</span>
   			<s:text name="page.text.endtime" />
   		</td>
   		<td class="dialogue_td_input" colspan="3">
   			<s:if test="conf.confStatus==null or conf.confStatus==0 or conf.confStatus==1">
   				&nbsp;&nbsp;<s:text name="page.text.date" />
   	    		<sd:datetimepicker name="datetimePair.endDate" id="endDate" displayFormat="yyyy-MM-dd" />
   	    		&nbsp;&nbsp;<s:text name="page.text.time" />
   	    		<s:textfield name="datetimePair.endTime" theme="simple" cssClass="timepicker"></s:textfield>
   			</s:if>
   			<s:else>
   				<input type="text" disabled="true" value="<s:date name='conf.endTime' format='yyyy-MM-dd HH:mm' />" />
   			</s:else>
   		</td>
   	</tr>

    <!--  <tr>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.text.host" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:if test="conf.confStatus==null or conf.confStatus==0">
				<s:select list="userList" name="conf.confHost.id" listKey="id" listValue="username" theme="simple" />
    		</s:if>
    		<s:else>
    			<s:textfield name="conf.confHost.username" disabled="true" />
    		</s:else>
    	</td>
    	

    </tr>-->
    <!--  <tr>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.text.confmode" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:if test="conf.confStatus==null or conf.confStatus==0">
    			<s:select list="confModeList" name="conf.confMode" theme="simple" />
    		</s:if>
    		<s:else>
    			<s:select list="confModeList" name="conf.confMode" disabled="true" theme="simple" />
    		</s:else>
    	</td>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.text.memberlimit" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:if test="conf.confStatus==null or conf.confStatus==0">
    			<s:textfield name="conf.confMemberCount" theme="simple" />	
    		</s:if>
    		<s:else>
    			<s:textfield name="conf.confMemberCount" disabled="true" />
    		</s:else>
    	</td>
    </tr>-->
    
    <!--<tr>
    	  <td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.conf.mixerkey" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:if test="conf.confStatus==null or conf.confStatus==0">
    			<s:select list="conf.customer.mcuServer.mixers" name="conf.mcuMixerKey" listKey="mixerKey"
    			 listValue="mixerKey" theme="simple"  />
    		</s:if>
    		<s:else>
    			<s:textfield name="conf.mcuMixerKey" disabled="true" />
    		</s:else>
    	</td>

    </tr>-->
    
    <tr>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.public" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:select id="ispublic" list="yesNoList" name="conf.confPublic" theme="simple" />
    	</td>
    	<td class="dialogue_td_text">
    		<s:text name="page.text.confpass" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:password name="conf.realPassword" theme="simple" id="userpasswd" disabled="true"/>
    	</td>
    	
    	<!-- <td class="dialogue_td_text">
    		<s:text name="page.text.requiredlogin" />
    	</td>
    	<td class="dialogue_td_input">
    		<s:if test="conf.confStatus==null or conf.confStatus==0">
    			<s:select list="yesNoList" name="conf.requiredLogin" theme="simple"  />
    		</s:if>
    		<s:else>
    			<s:select list="yesNoList" name="conf.requiredLogin" theme="simple" disabled="true" />
    		</s:else>
    	</td> -->
    	<!--<s:if test="conf.confType==2">
    		<td class="dialogue_td_text">
    			<s:text name="page.conf.allowbefore" />
    		</td>
    		<td class="dialogue_td_input">
    			<s:if test="conf.confStatus==null or conf.confStatus==0">
    				<s:select list="allowBeforeList" name="conf.confAllowBefore" theme="simple" />
    			</s:if>
    			<s:else>
    				<s:select list="allowBeforeList" name="conf.confAllowBefore" theme="simple" disabled="true" />
    			</s:else>
    		</td>
    	</s:if>-->
    	<!--<s:else>
    		<td class="dialogue_td_text">
    			<s:text name="page.conf.mediaquality" />
    		</td>
    		<td class="dialogue_td_input">
    			<s:if test="conf.confStatus==null or conf.confStatus==0">
    				<s:select list="mediaQuality" name="conf.mcuMediaQuality" theme="simple" />
    			</s:if>
    			<s:else>
    				<s:select list="mediaQuality" name="conf.mcuMediaQuality" theme="simple" disabled="true" />
    			</s:else>
    		</td>
    	</s:else>-->
    </tr>
    
     <!--<tr>
    	<td class="dialogue_td_text">
    		<span class="required">*</span>
    		<s:text name="page.text.videolimit" />
    	</td>
    	 <td class="dialogue_td_input">
    		<s:if test="conf.confStatus==null or conf.confStatus==0">
    			<s:textfield name="conf.confVideoCount" theme="simple" />
    		</s:if>
    		<s:else>
    			<s:textfield name="conf.confVideoCount" disabled="true" />
    		</s:else>
    	</td> 
	
    </tr>-->
    <!--<s:if test="conf.confType==2"> 
    	<tr>
    		<td class="dialogue_td_text">
    			<s:text name="page.conf.mediaquality" />
    		</td>
    		<td class="dialogue_td_input">
    			<s:if test="conf.confStatus==null or conf.confStatus==0">
    				<s:select list="mediaQuality" name="conf.mcuMediaQuality" theme="simple" />
    			</s:if>
    			<s:else>
    				<s:select list="mediaQuality" name="conf.mcuMediaQuality" theme="simple" disabled="true" />
    			</s:else>
    		</td>
    		<td colspan="2"></td>
    	</tr>
    </s:if>-->
	<tr>
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
			<s:select list="confMedias" name="conf.mediaId" theme="simple" headerKey="" headerValue="请选择"/>
		</td>
	</tr>   
	<tr>
   		<td class="dialogue_td_text">
   			<s:text name="page.conf.mediaquality" />
   		</td>
   		<td class="dialogue_td_input">
   			<s:if test="conf.confStatus==null or conf.confStatus==0">
   				<s:select list="mediaQuality" name="conf.mcuMediaQuality" theme="simple" />
   			</s:if>
   			<s:else>
   				<s:select list="mediaQuality" name="conf.mcuMediaQuality" theme="simple" disabled="true" />
   			</s:else>
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
   		    <s:select list="mixOrTransList" name="conf.ismix" theme="simple" disabled="true"  headerKey="" headerValue="请选择"></s:select>
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
  		 $("#ispublic").change( 
  		 	function(){
  		 		if(this.value==1){
  		 			$('#userpasswd')[0].disabled='true';
  		 		}else if(this.value==2){
  		 			$('#userpasswd')[0].disabled='';
  		 		}
  		 	} 
  		 );
  	});

</script>