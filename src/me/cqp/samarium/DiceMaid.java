package me.cqp.samarium;

import com.sobte.cqp.jcq.entity.CQDebug;
import com.sobte.cqp.jcq.entity.ICQVer;
import com.sobte.cqp.jcq.entity.IMsg;
import com.sobte.cqp.jcq.entity.IRequest;
import com.sobte.cqp.jcq.event.JcqAppAbstract;

public class DiceMaid extends JcqAppAbstract implements ICQVer, IMsg, IRequest{
	
	private static Maid maid = Maid.getInstance();
	
	/**
	 * Use main function to debug
	 * @param args System arguments
	 */
	public static void main(String[] args) {
		CQ = new CQDebug();
		CQ.logInfo("JCQ DiceMaid", "Debug Starting");
		DiceMaid diceMaid = new DiceMaid();
		diceMaid.startup();
		diceMaid.enable();
		
		//diceMaid.privateMsg(11, 0, 90L, "~help", 1);
		diceMaid.groupMsg(0, 1, 1L, 0L, "", "～ra测试 80", 1);
		diceMaid.exit();
	}
	
	/**
	 * Override the method in JcqAppAbstract
	 * @return ApiVer, AppID
	 */
	@Override
	public String appInfo() {
		final String AppID = "me.cqp.samarium.dicemaid";
		return CQAPIVER + "," + AppID;
	}
	
	/**
	 * Start the CQ
	 * @return Always 0
	 */
	@Override
	public int startup() {
		@SuppressWarnings("unused")
		String appDirectory = CQ.getAppDirectory();
		return 0;
	}
	
	/**
	 * Exit the CQ
	 * @return Always 0
	 */
	@Override
	public int exit() {
		return 0;
	}
	
	/**
	 * Enable the plugin
	 * @return Always 0
	 */
	@Override
	public int enable() {
		enable = true;
		return 0;
	}
	
	/**
	 * Disable the plugin
	 * @return Always 0
	 */
	@Override
	public int disable() {
		enable = false;
		return 0;
	}
	
	/**
	 * Private Conversation
	 * @param subType 11/from friend 1/from online 2/from group 3/from discuss
	 * @param msgId Id of message
	 * @param fromQQ sender
	 * @param msg Text of message
	 * @param font Font
	 * @return *do not return the message directly*
	 */
	@Override
	public int privateMsg(int subType, int msgId, long fromQQ, String msg, int font) {
		maid.command(fromQQ, msg);
		String reply = maid.getResponse();
		if (!reply.equals("")) CQ.sendPrivateMsg(fromQQ, reply);
		return MSG_IGNORE;
	}
	
	/**
	 * Deal with the group message
	 * @param subType sub-type of the message
	 * @param msgId Id of the message
	 * @param fromGroup Id of the group
	 * @param fromQQ sender
	 * @param fromAnonymous Anonymous sender
	 * @param msg Text of message
	 * @param font Font
	 * @return *do not return the message directly*
	 */
	@Override
	public int groupMsg(int subType, int msgId, long fromGroup, long fromQQ, String fromAnonymous, String msg, int font) {
		try {
			String card = CQ.getGroupMemberInfoV2(fromGroup, fromQQ).getCard();
			String name = CQ.getGroupMemberInfoV2(fromGroup, fromQQ).getNick();
			String who = (card.equals(""))? name : card;
			maid.command(fromQQ, msg);
			String reply = maid.getResponse();
			if (!reply.equals("")) {
				if ((msg.startsWith("~h")|msg.startsWith("～h"))&!(msg.startsWith("~help")|msg.startsWith("～help"))) {
					CQ.sendGroupMsg(fromGroup, who+"进行了一次暗骰");
					CQ.sendPrivateMsg(fromQQ, reply);
				}
				else CQ.sendGroupMsg(fromGroup, who+reply);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return MSG_IGNORE;
	}
	
	/**
	 * Deal with the discuss message
	 * @param subtype sub-type of the message
	 * @param msgId Id of the message
	 * @param fromDiscuss Id of the discuss
	 * @param fromQQ sender
	 * @param msg Text of message
	 * @param font Font
	 * @return *do not return the message directly*
	 */
	@Override
	public int discussMsg(int subtype, int msgId, long fromDiscuss, long fromQQ, String msg, int font) {
		return 0;
	}
	
	@Override
	public int groupUpload(int subType, int sendTime, long fromGroup, long fromQQ, String file) {
		return 0;
	}
	
	@Override
	public int groupAdmin(int subtype, int sendTime, long fromGroup, long beingOperateQQ) {
		return 0;
	}
	
	@Override
	public int groupMemberDecrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
		return 0;
	}
	
	@Override
	public int groupMemberIncrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
		return 0;
	}
	
	@Override
	public int requestAddGroup(int subtype, int sendTime, long fromGroup, long fromQQ, String msg, String responseFlag) {
		if(subtype == 1){ // 本号为群管理，判断是否为他人申请入群
			//CQ.setGroupAddRequest(responseFlag, REQUEST_GROUP_ADD, REQUEST_ADOPT, null);// 同意入群
			return MSG_IGNORE;
		}
		if(subtype == 2){
			CQ.setGroupAddRequest(responseFlag, REQUEST_GROUP_INVITE, REQUEST_ADOPT, null);// 同意进受邀群
		}
		return MSG_IGNORE;
	}
	
	@Override
	public int friendAdd(int subtype, int sendTime, long fromQQ) {
		return 0;
	}
	
	@Override
	public int requestAddFriend(int subtype, int sendTime, long fromQQ, String msg, String responseFlag) {
		return 0;
	}
}