package com.itheima.restkeeper.enums;

import com.itheima.restkeeper.basic.IBasicEnum;

/**
 * @ClassName RotaryTableEnum.java
 * @Description 转台枚举
 */
public enum  RotaryTableEnum implements IBasicEnum {
    SUCCEED("200","操作成功"),
    ROTARY_TABLE_FAIL("180001","转台失败，请检查订单及桌台状态")
            ;

    private String code;
    private String msg;

    RotaryTableEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
