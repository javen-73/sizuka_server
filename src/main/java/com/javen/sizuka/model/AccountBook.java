package com.javen.sizuka.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author javen
 */
@Data
public class AccountBook implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 外键
     */
    private Integer userId;

    private String bookName;

    /**
     * 状态 0不可用, 1可用
     */
    private Integer status;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}