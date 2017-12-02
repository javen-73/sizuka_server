package com.javen.sizuka.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author javen
 */
@Data
public class Users implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 别名
     */
    private String aliasName;

    /**
     * 0女 1男 3未知
     */
    private Integer sex;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 头像url
     */
    private String profilePicture;

    private String token;

    private static final long serialVersionUID = 1L;


}