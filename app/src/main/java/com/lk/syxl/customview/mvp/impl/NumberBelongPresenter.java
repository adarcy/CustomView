package com.lk.syxl.customview.mvp.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lk.syxl.customview.model.Phone;
import com.lk.syxl.customview.mvp.NumberBelongView;
import com.lk.syxl.customview.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by likun on 2017/10/11.
 */

public class NumberBelongPresenter extends BasePresenter {

    String api = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
    NumberBelongView belongView;
    Phone mPhone;

    public NumberBelongPresenter(NumberBelongView belongView){
        this.belongView = belongView;
    }

    public Phone getPhoneInfo(){
        return mPhone;
    }

    public void searchPhoneInfo(String phone){
        if (phone.length() != 11){
            belongView.showToast("手机号码格式不对");
            return;
        }
        belongView.showLoading();
        check(phone);
    }

    private void check(String phone) {
        Map<String ,String> praram = new HashMap<String, String>();
        praram.put("tel",phone);

        HttpUtils utils = new HttpUtils(new HttpUtils.HttpResponse() {
            @Override
            public void onSuccess(Object obj) {
                String json = obj.toString();
                int indexOf = json.indexOf("{");
                json = json.substring(indexOf);
                mPhone = parseModelFromFastjson(json);

                belongView.closeLoading();
                belongView.updateView();
            }

            @Override
            public void onError(String error) {
                belongView.showToast(error);
                belongView.closeLoading();
            }
        });
        utils.sendGetRequest(api,praram);
    }

    private Phone parseModelFromGson(String json){
        Gson gson = new Gson();
        Phone phone = gson.fromJson(json, Phone.class);
        return phone;
    }

    private Phone parseModelFromFastjson(String json){
        Phone phone = JSONObject.parseObject(json,Phone.class);
        return phone;
    }
}
