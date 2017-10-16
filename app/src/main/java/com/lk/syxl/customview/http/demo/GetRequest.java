package com.lk.syxl.customview.http.demo;


import com.lk.syxl.customview.http.HttpRequest;
import com.lk.syxl.customview.http.HttpResponseHandler;

import java.util.HashMap;

public class GetRequest extends HttpRequest {

    private  static final String LOG_TAG = "GetRequest";

    private HashMap<String, String> params;

	private HttpResponseHandler<String> mResponseHandlerHandler;

	public GetRequest(String path, HashMap<String, String> params, HttpResponseHandler<String> handler) {
        super();
        setPath(path);
        this.params = params;
        this.mResponseHandlerHandler = handler;
    }

	@Override
	public String getUrl() {
        String url = getBaseUrl();
        return appendParams(url,params);
	}

    @Override
    protected void onRequestSuccess(int statusCode, String response) {
//        response = "{\n" +
//                "    \"scode\": 0,\n" +
//                "    \"body\": {\n" +
//                "        \"members\": [{\n" +
//                "            \"id\": \"33001\",\n" +
//                "            \"name\": \"170919-00001\",\n" +
//                "            \"icon\": \"/embeded/im/chat/source/img/default_customer.png\"\n" +
//                "        }, {\n" +
//                "            \"id\": \"645505\",\n" +
//                "            \"name\": \"我想去拉萨在海拔四千米的山上大吼一声\",\n" +
//                "            \"icon\": \"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/16/e311daa6-c982-4647-ba30-007cb764e819.jpg\"\n" +
//                "        }, {\n" +
//                "            \"id\": \"645506\",\n" +
//                "            \"name\": \"how are you?\",\n" +
//                "            \"icon\": \"/embeded/im/chat/source/img/default_server.png\"\n" +
//                "        }, {\n" +
//                "            \"id\": \"645701\",\n" +
//                "            \"name\": \"客服机器人\",\n" +
//                "            \"icon\": \"https://crm-testrs.ingageapp.com/static/img/default_robot.png\"\n" +
//                "        }],\n" +
//                "        \"isMore\": \"1\",\n" +
//                "        \"list\": [\"{\\\"bt\\\":0,\\\"chl\\\":\\\"1\\\",\\\"data\\\":{\\\"content\\\":\\\"用户结束了会话\\\"},\\\"msgId\\\":359688408148213760,\\\"mt\\\":7,\\\"nt\\\":12,\\\"rId\\\":\\\"chat/agent/254902/645506\\\",\\\"sId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sTime\\\":1505797999190,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"1\\\",\\\"content\\\":\\\"由于很久没有收到您的消息，系统自动结束了本次会话\\\",\\\"msgId\\\":359688408072716288,\\\"mt\\\":0,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/robot/254902/645506\\\",\\\"sTime\\\":1505797999172,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"msgId\\\":359687143737524224,\\\"rId\\\":\\\"chat/agent/254902/645506\\\",\\\"sId\\\":\\\"chat/visitor/254902/33001\\\",\\\"kId\\\":\\\"\\\",\\\"mt\\\":0,\\\"bt\\\":8,\\\"st\\\":2,\\\"nt\\\":0,\\\"sTime\\\":1505797697731,\\\"sync\\\":0,\\\"ver\\\":\\\"1.1\\\",\\\"tenant\\\":\\\"254902\\\",\\\"chl\\\":\\\"web\\\",\\\"content\\\":\\\"44\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"1\\\",\\\"content\\\":\\\"您好，很高兴为您服务，请问有什么可以帮您的?\\\",\\\"msgId\\\":359687101320527872,\\\"mt\\\":0,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/agent/254902/645506\\\",\\\"sTime\\\":1505797687618,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"bt\\\":0,\\\"chl\\\":\\\"1\\\",\\\"data\\\":{\\\"content\\\":\\\"会话已开始\\\",\\\"id\\\":30803,\\\"visitorName\\\":\\\"访客170919-00001\\\",\\\"visitorId\\\":\\\"33001\\\",\\\"chatId\\\":\\\"chat/254902/33001\\\"},\\\"msgId\\\":359687101291167744,\\\"mt\\\":7,\\\"nt\\\":1,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/agent/254902/645506\\\",\\\"sTime\\\":1505797687611,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"3\\\",\\\"content\\\":\\\"[图片]\\\",\\\"height\\\":300,\\\"isDeleted\\\":false,\\\"msgId\\\":359663252407320576,\\\"mt\\\":2,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/agent/254902/645506\\\",\\\"sTime\\\":1505792001594,\\\"sendLocalTime\\\":1505792003533,\\\"sendState\\\":1,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"totalSize\\\":17849,\\\"tumbL\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/l_0b88984e-e65e-462f-b07a-a59b81ccfa2d.jpg\\\",\\\"tumbM\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/0b88984e-e65e-462f-b07a-a59b81ccfa2d.jpg\\\",\\\"tumbS\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/s_0b88984e-e65e-462f-b07a-a59b81ccfa2d.jpg\\\",\\\"uploadingPercent\\\":99,\\\"ver\\\":\\\"1.1\\\",\\\"width\\\":225,\\\"appId\\\":0,\\\"canDelete\\\":false,\\\"commonMember\\\":[],\\\"deleted\\\":false,\\\"deleting\\\":false,\\\"first\\\":true,\\\"has\\\":false,\\\"id\\\":0,\\\"isNew\\\":false,\\\"isSelect\\\":false,\\\"recentVisitTime\\\":0,\\\"schedule_deleted\\\":false,\\\"showActions\\\":false,\\\"showDelete\\\":false,\\\"showMore\\\":false,\\\"errorType\\\":0,\\\"isTimeOut\\\":false,\\\"toString\\\":\\\"\\\"}\", \"{\\\"bt\\\":0,\\\"chl\\\":\\\"1\\\",\\\"data\\\":{\\\"content\\\":\\\"用户结束了会话\\\"},\\\"msgId\\\":359652378833584128,\\\"mt\\\":7,\\\"nt\\\":12,\\\"rId\\\":\\\"chat/agent/254902/645506\\\",\\\"sId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sTime\\\":1505789409132,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"1\\\",\\\"content\\\":\\\"由于很久没有收到您的消息，系统自动结束了本次会话\\\",\\\"msgId\\\":359652378749698048,\\\"mt\\\":0,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/robot/254902/645506\\\",\\\"sTime\\\":1505789409112,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"3\\\",\\\"content\\\":\\\"[图片]\\\",\\\"height\\\":300,\\\"isDeleted\\\":false,\\\"msgId\\\":359651222145204224,\\\"mt\\\":2,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/agent/254902/645506\\\",\\\"sTime\\\":1505789133356,\\\"sendLocalTime\\\":1505789135666,\\\"sendState\\\":1,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"totalSize\\\":17955,\\\"tumbL\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/l_42b70330-d714-4dd2-b568-7f9c597ea47e.jpg\\\",\\\"tumbM\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/42b70330-d714-4dd2-b568-7f9c597ea47e.jpg\\\",\\\"tumbS\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/s_42b70330-d714-4dd2-b568-7f9c597ea47e.jpg\\\",\\\"uploadingPercent\\\":99,\\\"ver\\\":\\\"1.1\\\",\\\"width\\\":225,\\\"appId\\\":0,\\\"canDelete\\\":false,\\\"commonMember\\\":[],\\\"deleted\\\":false,\\\"deleting\\\":false,\\\"first\\\":true,\\\"has\\\":false,\\\"id\\\":0,\\\"isNew\\\":false,\\\"isSelect\\\":false,\\\"recentVisitTime\\\":0,\\\"schedule_deleted\\\":false,\\\"showActions\\\":false,\\\"showDelete\\\":false,\\\"showMore\\\":false,\\\"errorType\\\":0,\\\"isTimeOut\\\":false,\\\"toString\\\":\\\"\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"1\\\",\\\"content\\\":\\\"您好，很高兴为您服务，请问有什么可以帮您的?\\\",\\\"msgId\\\":359651113378512896,\\\"mt\\\":0,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/agent/254902/645506\\\",\\\"sTime\\\":1505789107424,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\"]\n" +
//                "    }\n" +
//                "}";

        if (mResponseHandlerHandler != null) {
            mResponseHandlerHandler.onResponse(response);
        }
    }

    @Override
    protected void onRequestFailure(int statusCode, String errorResponse) {
//        errorResponse = "{\n" +
//                "    \"scode\": 0,\n" +
//                "    \"body\": {\n" +
//                "        \"members\": [{\n" +
//                "            \"id\": \"33001\",\n" +
//                "            \"name\": \"170919-00001\",\n" +
//                "            \"icon\": \"/embeded/im/chat/source/img/default_customer.png\"\n" +
//                "        }, {\n" +
//                "            \"id\": \"645505\",\n" +
//                "            \"name\": \"我想去拉萨在海拔四千米的山上大吼一声\",\n" +
//                "            \"icon\": \"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/16/e311daa6-c982-4647-ba30-007cb764e819.jpg\"\n" +
//                "        }, {\n" +
//                "            \"id\": \"645506\",\n" +
//                "            \"name\": \"how are you?\",\n" +
//                "            \"icon\": \"/embeded/im/chat/source/img/default_server.png\"\n" +
//                "        }, {\n" +
//                "            \"id\": \"645701\",\n" +
//                "            \"name\": \"客服机器人\",\n" +
//                "            \"icon\": \"https://crm-testrs.ingageapp.com/static/img/default_robot.png\"\n" +
//                "        }],\n" +
//                "        \"isMore\": \"1\",\n" +
//                "        \"list\": [\"{\\\"bt\\\":0,\\\"chl\\\":\\\"1\\\",\\\"data\\\":{\\\"content\\\":\\\"用户结束了会话\\\"},\\\"msgId\\\":359688408148213760,\\\"mt\\\":7,\\\"nt\\\":12,\\\"rId\\\":\\\"chat/agent/254902/645506\\\",\\\"sId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sTime\\\":1505797999190,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"1\\\",\\\"content\\\":\\\"由于很久没有收到您的消息，系统自动结束了本次会话\\\",\\\"msgId\\\":359688408072716288,\\\"mt\\\":0,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/robot/254902/645506\\\",\\\"sTime\\\":1505797999172,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"msgId\\\":359687143737524224,\\\"rId\\\":\\\"chat/agent/254902/645506\\\",\\\"sId\\\":\\\"chat/visitor/254902/33001\\\",\\\"kId\\\":\\\"\\\",\\\"mt\\\":0,\\\"bt\\\":8,\\\"st\\\":2,\\\"nt\\\":0,\\\"sTime\\\":1505797697731,\\\"sync\\\":0,\\\"ver\\\":\\\"1.1\\\",\\\"tenant\\\":\\\"254902\\\",\\\"chl\\\":\\\"web\\\",\\\"content\\\":\\\"44\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"1\\\",\\\"content\\\":\\\"您好，很高兴为您服务，请问有什么可以帮您的?\\\",\\\"msgId\\\":359687101320527872,\\\"mt\\\":0,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/agent/254902/645506\\\",\\\"sTime\\\":1505797687618,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"bt\\\":0,\\\"chl\\\":\\\"1\\\",\\\"data\\\":{\\\"content\\\":\\\"会话已开始\\\",\\\"id\\\":30803,\\\"visitorName\\\":\\\"访客170919-00001\\\",\\\"visitorId\\\":\\\"33001\\\",\\\"chatId\\\":\\\"chat/254902/33001\\\"},\\\"msgId\\\":359687101291167744,\\\"mt\\\":7,\\\"nt\\\":1,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/agent/254902/645506\\\",\\\"sTime\\\":1505797687611,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"3\\\",\\\"content\\\":\\\"[图片]\\\",\\\"height\\\":300,\\\"isDeleted\\\":false,\\\"msgId\\\":359663252407320576,\\\"mt\\\":2,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/agent/254902/645506\\\",\\\"sTime\\\":1505792001594,\\\"sendLocalTime\\\":1505792003533,\\\"sendState\\\":1,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"totalSize\\\":17849,\\\"tumbL\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/l_0b88984e-e65e-462f-b07a-a59b81ccfa2d.jpg\\\",\\\"tumbM\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/0b88984e-e65e-462f-b07a-a59b81ccfa2d.jpg\\\",\\\"tumbS\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/s_0b88984e-e65e-462f-b07a-a59b81ccfa2d.jpg\\\",\\\"uploadingPercent\\\":99,\\\"ver\\\":\\\"1.1\\\",\\\"width\\\":225,\\\"appId\\\":0,\\\"canDelete\\\":false,\\\"commonMember\\\":[],\\\"deleted\\\":false,\\\"deleting\\\":false,\\\"first\\\":true,\\\"has\\\":false,\\\"id\\\":0,\\\"isNew\\\":false,\\\"isSelect\\\":false,\\\"recentVisitTime\\\":0,\\\"schedule_deleted\\\":false,\\\"showActions\\\":false,\\\"showDelete\\\":false,\\\"showMore\\\":false,\\\"errorType\\\":0,\\\"isTimeOut\\\":false,\\\"toString\\\":\\\"\\\"}\", \"{\\\"bt\\\":0,\\\"chl\\\":\\\"1\\\",\\\"data\\\":{\\\"content\\\":\\\"用户结束了会话\\\"},\\\"msgId\\\":359652378833584128,\\\"mt\\\":7,\\\"nt\\\":12,\\\"rId\\\":\\\"chat/agent/254902/645506\\\",\\\"sId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sTime\\\":1505789409132,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"1\\\",\\\"content\\\":\\\"由于很久没有收到您的消息，系统自动结束了本次会话\\\",\\\"msgId\\\":359652378749698048,\\\"mt\\\":0,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/robot/254902/645506\\\",\\\"sTime\\\":1505789409112,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"3\\\",\\\"content\\\":\\\"[图片]\\\",\\\"height\\\":300,\\\"isDeleted\\\":false,\\\"msgId\\\":359651222145204224,\\\"mt\\\":2,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/agent/254902/645506\\\",\\\"sTime\\\":1505789133356,\\\"sendLocalTime\\\":1505789135666,\\\"sendState\\\":1,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"totalSize\\\":17955,\\\"tumbL\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/l_42b70330-d714-4dd2-b568-7f9c597ea47e.jpg\\\",\\\"tumbM\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/42b70330-d714-4dd2-b568-7f9c597ea47e.jpg\\\",\\\"tumbS\\\":\\\"https://devrs.s3.cn-north-1.amazonaws.com.cn/254902/2017/09/19/s_42b70330-d714-4dd2-b568-7f9c597ea47e.jpg\\\",\\\"uploadingPercent\\\":99,\\\"ver\\\":\\\"1.1\\\",\\\"width\\\":225,\\\"appId\\\":0,\\\"canDelete\\\":false,\\\"commonMember\\\":[],\\\"deleted\\\":false,\\\"deleting\\\":false,\\\"first\\\":true,\\\"has\\\":false,\\\"id\\\":0,\\\"isNew\\\":false,\\\"isSelect\\\":false,\\\"recentVisitTime\\\":0,\\\"schedule_deleted\\\":false,\\\"showActions\\\":false,\\\"showDelete\\\":false,\\\"showMore\\\":false,\\\"errorType\\\":0,\\\"isTimeOut\\\":false,\\\"toString\\\":\\\"\\\"}\", \"{\\\"bt\\\":8,\\\"chl\\\":\\\"1\\\",\\\"content\\\":\\\"您好，很高兴为您服务，请问有什么可以帮您的?\\\",\\\"msgId\\\":359651113378512896,\\\"mt\\\":0,\\\"nt\\\":0,\\\"rId\\\":\\\"chat/visitor/254902/33001\\\",\\\"sId\\\":\\\"chat/agent/254902/645506\\\",\\\"sTime\\\":1505789107424,\\\"st\\\":0,\\\"sync\\\":0,\\\"tId\\\":\\\"chat/254902/33001\\\",\\\"tenant\\\":\\\"254902\\\",\\\"ver\\\":\\\"1.1\\\"}\"]\n" +
//                "    }\n" +
//                "}";
        if (mResponseHandlerHandler != null) {
            mResponseHandlerHandler.onError(errorResponse);
        }
    }
}
