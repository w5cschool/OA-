package com.example.oasystem.controller;

import lombok.Data;

/**
 * @author tianyaliaowang
 * @date 2020/11/6 - 15:21
 */

@Data
public class RespStat {
    private int code;
    private String msg;
    private String data;

    public RespStat(int code, String data, String msg) {

        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static RespStat build(int i) {
        return new RespStat(200, "ok", "meiyou");
    }
    public static RespStat build(int code, String msg) {
        // TODO Auto-generated method stub
        return new RespStat(code, msg, "meiyou");
    }
}
