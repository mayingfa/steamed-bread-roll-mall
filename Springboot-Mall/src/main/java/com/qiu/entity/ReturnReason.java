package com.qiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/24 20:54
 * @description 退货原因表
 */
@Data
@Entity
@Table(name = "return_reason")
@NoArgsConstructor
@AllArgsConstructor
public class ReturnReason {
    @Id
    private Integer reasonId;
    private String reasonName;
    private Boolean status;
}
