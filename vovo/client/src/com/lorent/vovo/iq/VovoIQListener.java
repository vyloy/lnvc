package com.lorent.vovo.iq;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.MyOpenfireUtil;

public class VovoIQListener implements PacketListener{

	@Override
	public void processPacket(Packet packet) {
		if(!(packet instanceof VovoIQ)){
			return;
		}
		IQXmlParser parser = new IQXmlParser(packet.toXML());
		if(MyOpenfireUtil.GROUPCHAT_SPACE_NAME.equals(parser.getSpaceName())){
			if(parser.getOperateName().equals(Constants.GET_GROUPCHAT)){//获取已加入的群组信息
				GroupChatJidBean bean = GetGroupChatXmlParser.parse(parser.getDocument());
				Vovo.sendMessage("initGroupChat", new Object[]{bean});
			}else if(parser.getOperateName().equals(Constants.APPLYIN_GROUPCHAT)){//群主接收到申请加入信息
				ApplyInGroupChatBean bean = ApplyInGroupChatXmlParser.parse(parser.getDocument());
				Vovo.sendMessage("recieveGroupChatApplyIn", new Object[]{bean});
			}else if(parser.getOperateName().equals(Constants.FETCH_GROUPCHAT_AUTHORITY)){//获取群组权限
//				FetchGroupChatAuthorityBean bean = FetchGroupChatAuthorityXmlParser.parse(parser.getDocument());
//				Vovo.sendMessage("fetchGroupChatAuthority", new Object[]{bean});
			}else if(parser.getOperateName().equals(Constants.UPDATE_GROUPCHAT_AUTHORITY)){//通知权限修改
				UpdateGroupChatAuthorityBean bean = UpdateGroupChatAuthorityXmlParser.parse(parser.getDocument());
				Vovo.sendMessage("updateGroupChatAuthority", new Object[]{bean});
			}else if(parser.getOperateName().equals(Constants.QUIT_GROUPCHAT)){
				QuitGroupChatBean bean = QuitGroupChatXmlParser.parse(parser.getDocument());
				Vovo.sendMessage("quitGroupChat", new Object[]{bean});
			}else if(parser.getOperateName().equals(Constants.SEARCH_GROUPCHAT)){//查询群组
				SearchGroupChatBean bean = SearchGroupChatBeanXmlParser.parse(parser.getDocument());
				Vovo.sendMessage("searchGroupChatCallBack", new Object[]{bean});
			}
		}
	}

}
