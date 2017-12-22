package com.javen.sizuka.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 
 */
@Data
public class Version implements Serializable {
    private String version;

    private LocalDateTime createTime;

    private String update;

}