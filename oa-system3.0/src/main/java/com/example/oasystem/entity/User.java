package com.example.oasystem.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String nickName = "无";

    private String loginName;

    private Integer age = 18;

    private String location = "中国";

    private String password;

    private String role = "user";

    private static final long serialVersionUID = 1L;
}