package com.example.oasystem.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class UserKey implements Serializable {
    private Integer id;

    private String loginName;

    private static final long serialVersionUID = 1L;
}