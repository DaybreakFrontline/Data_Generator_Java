package com.test.dataFlush.redis;

import org.springframework.util.StringUtils;

public enum CtimsModelEnum {
    CTIMS_COMM_CAP("comm", "公共");

    private String code;
    private String desc;

    public static CtimsModelEnum getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        } else {
            CtimsModelEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CtimsModelEnum ctimsModelEnum = var1[var3];
                if (ctimsModelEnum.getCode().equals(code)) {
                    return ctimsModelEnum;
                }
            }

            return null;
        }
    }

    private CtimsModelEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}