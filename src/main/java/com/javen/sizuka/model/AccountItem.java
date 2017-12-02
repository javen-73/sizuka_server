package com.javen.sizuka.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author javen
 */
@Data
public class AccountItem implements Serializable {
    /**
     * id
     */
    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Integer priceType;

    private Integer price;

    /**
     * 0 软删除 ,1正常
     */
    private Integer status;
    private Integer itemStatus;

    private String remark;

    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;


}