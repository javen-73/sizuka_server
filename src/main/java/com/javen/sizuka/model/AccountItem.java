package com.javen.sizuka.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String itemType;

    private Object price;

    /**
     * 0 软删除 ,1正常
     */
    private Integer status;
    private Integer itemStatus;

    private String remark;
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern="yy/MM/dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getCreateTime() {
        return createTime;
    }
}