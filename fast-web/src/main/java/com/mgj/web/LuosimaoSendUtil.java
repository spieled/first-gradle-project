package com.mgj.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;

/**
 * 
 * @ClassName: LuosimaoSendUtil
 * @Description: TODO(螺丝帽消息发送工具类)
 * @author wb
 * @date 2016年3月11日 上午11:08:51
 *
 */
public class LuosimaoSendUtil {
	private static final String SEND_URL = "http://sms-api.luosimao.com/v1/send.json";// 单发url
	private static final String BATCH_SEND_URL = "http://sms-api.luosimao.com/v1/send_batch.json";// 群发url
	private static final String USERNAME = "";// 用户名
	private static final String PASSWORD = "";// 密码
	private static final boolean isSend = true;

	/*public static void main(String[] args) {
		boolean send = send("18224421156", "验证码125632", "快支付");
		System.err.println(send);
	}*/

	/**
	 * 给单个用户发送消息
	 * 
	 * @param phone
	 *            接收信息的电话号码
	 * @param message
	 *            发送的消息
	 * @return 是否发送成功 true:成功 false:失败
	 */
	public static boolean send(String phone, String message, String sign) {
		if (!isSend) {return false;}
		try {
			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter(USERNAME, PASSWORD));
			WebResource webResource = client.resource(SEND_URL);
			MultivaluedMapImpl formData = new MultivaluedMapImpl();
			formData.add("mobile", phone);
			formData.add("message", message + "【" + sign + "】");
			ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
					formData);
			String responseData = response.getEntity(String.class);
			JSONObject jsonObject = JSON.parseObject(responseData);
//			JsonNode readTree = AccountLogAspect.objectMapper.readTree(responseData);
			int error = jsonObject.getIntValue("error"); //readTree.get("error").getIntValue();
			return error == 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 给多个用户发送消息
	 * 
	 * @param phones
	 *            电话号码 多个电话号码以逗号隔开
	 * @param message
	 *            消息内容
	 * @return
	 */
	public static boolean batchSend(String phones, String message, String sign) {
		if (!isSend) {return false;}
		try {
			Client client = Client.create();
			client.addFilter(new HTTPBasicAuthFilter(USERNAME, PASSWORD));
			WebResource webResource = client.resource(BATCH_SEND_URL);
			MultivaluedMapImpl formData = new MultivaluedMapImpl();
			formData.add("mobile_list", phones);
			formData.add("message", message + "【" + sign + "】");
			ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,
					formData);
			String responseData = response.getEntity(String.class);
			JSONObject jsonObject = JSON.parseObject(responseData);
			// JsonNode readTree = AccountLogAspect.objectMapper.readTree(responseData);
			int error = jsonObject.getIntValue("error"); // readTree.get("error").getIntValue();
			return error == 0;
		} catch (Exception e) {
			return false;
		}
	}
}
