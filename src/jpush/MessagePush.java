package jpush;

import java.util.Set;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class MessagePush {

	private static final String appKey = "0f39b65fd3b5acc46c008eda";
	private static final String masterSecret = "3d2f65a446ef13434ddb9c99";
	private JPushClient jpushClient;
	private String title;
	private String content;
	private String message;

	public MessagePush(String message) {
		this.message = message;
		jpushClient = new JPushClient(masterSecret, appKey, 3);
	}

	public MessagePush(String message, String title) {
		this(message);
		this.title = title;
	}

	/**
	 * �������˷�����Ϣ
	 * 
	 * @return ��Ϣid
	 */
	public long sendPushAll() {
		PushPayload payload = buildPushObject_all_all_alert();
		long msgId = 0;
		try {
			PushResult result = jpushClient.sendPush(payload);
			msgId = result.msg_id;
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			// LOG.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			// LOG.info("HTTP Status: " + e.getStatus());
			msgId = e.getMsgId();
		}
		return msgId;
	}

	/**
	 * ��ָ�������Ŀͻ��˷�����Ϣ
	 * 
	 * @param alias
	 *            ���б�����Ϣ���ϣ������ʾ��������ѧ�����
	 * @return ��Ϣid
	 */
	public long sendPushAlias(Set<String> alias) {
		PushPayload payloadAlias = buildPushObject_android_alias_alertWithTitle(alias);
		long msgId = 0;
		try {
			PushResult result = jpushClient.sendPush(payloadAlias);
			msgId = result.msg_id;

		} catch (APIConnectionException e) {
			// LOG.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			// LOG.info("HTTP Status: " + e.getStatus());
			// LOG.info("Error Code: " + e.getErrorCode());
			// LOG.info("Error Message: " + e.getErrorMessage());
			// LOG.info("Msg ID: " + e.getMsgId());
			msgId = e.getMsgId();
		}
		return msgId;
	}

	/**
	 * ��ָ���鷢����Ϣ
	 * 
	 * @param tag
	 *            ������
	 * @return ��Ϣid
	 */
	public long sendPushTag(String tag) {
		PushPayload payloadtag = buildPushObject_android_tag_alertWithTitle(tag);
		long msgId = 0;
		try {
			PushResult result = jpushClient.sendPush(payloadtag);
			msgId = result.msg_id;
			// LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			// LOG.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			// LOG.info("HTTP Status: " + e.getStatus());
			// LOG.info("Error Code: " + e.getErrorCode());
			// LOG.info("Error Message: " + e.getErrorMessage());
			// LOG.info("Msg ID: " + e.getMsgId());
			// msgId=e.getMsgId();
		}
		return msgId;
	}

	/**
	 * ���з�װ�����ֻ����Ϣ���Ͷ���PushPayload���ķ���
	 * buildPushObject_android_alias_alertWithTitle��
	 * buildPushObject_android_tag_alertWithTitle�� buildPushObject_all_all_alert
	 */
	public PushPayload buildPushObject_android_alias_alertWithTitle(
			Set<String> alias) {
		return PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.alias(alias))
				.setNotification(Notification.android(message, title, null))
				.build();
	}

	public PushPayload buildPushObject_android_tag_alertWithTitle(String tag) {
		return PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.tag(tag))
				.setNotification(Notification.android(message, title, null))
				.build();
	}

	public PushPayload buildPushObject_all_all_alert() {
		return PushPayload.alertAll(message);
	}
}