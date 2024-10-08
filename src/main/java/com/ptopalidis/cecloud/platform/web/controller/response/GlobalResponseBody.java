package com.ptopalidis.cecloud.platform.web.controller.response;

public class GlobalResponseBody {

    private Object data;

    public GlobalResponseBody(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
