package com.dempe.lamp.proto.json;

import com.alibaba.fastjson.JSONObject;
import com.dempe.lamp.codec.pack.Pack;
import com.dempe.lamp.codec.pack.Unpack;
import com.dempe.lamp.proto.Response;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/1/28
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
public class JSONResponse extends JSONObject implements Response {

    public JSONResponse() {
    }

    public String getUri() {
        return getString("uri");
    }

    public void setUri(String uri) {
        put("uri", uri);
    }


    public JSONObject getData() {
        return getJSONObject("data");
    }

    public void setData(JSONObject data) {
        put("data", data);
    }


    @Override
    public void marshal(Pack pack) {
        String str = toJSONString();
        pack.putVarstr(str);
    }

    @Override
    public void unmarshal(Unpack unpack) {
        String jsonStr = unpack.popVarstr();
        if (StringUtils.isNotBlank(jsonStr)) {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            if (jsonObject != null) {
                setUri(jsonObject.getString("uri"));
                setData(jsonObject.getJSONObject("data"));
            }

        }
    }
}