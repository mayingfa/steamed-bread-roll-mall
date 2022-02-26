package com.qiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/20 16:29
 * @description vip信息
 */
@Data
@Entity
@Table(name = "vip")
@NoArgsConstructor
@AllArgsConstructor
public class Vip {
    @Id
    private Integer vipId;
    private String accountNumber;
    private Date creatTime;
    private Date overdueTime;
}
