package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/20 16:29
 * @description vip信息
 */
@Entity
@Table(name = "vip")
public class Vip {
    private Integer vipId;
    private String accountNumber;
    private Date creatTime;
    private Date overdueTime;

    public Vip() {
    }
    public Vip(Integer vipId, String accountNumber, Date creatTime, Date overdueTime) {
        this.vipId = vipId;
        this.accountNumber = accountNumber;
        this.creatTime = creatTime;
        this.overdueTime = overdueTime;
    }

    @Id
    public Integer getVipId() {
        return vipId;
    }
    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getCreatTime() {
        return creatTime;
    }
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getOverdueTime() {
        return overdueTime;
    }
    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "vipId=" + vipId +
                ", accountNumber='" + accountNumber + '\'' +
                ", creatTime=" + creatTime +
                ", overdueTime=" + overdueTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vip vip = (Vip) o;
        return Objects.equals(vipId, vip.vipId) &&
                Objects.equals(accountNumber, vip.accountNumber) &&
                Objects.equals(creatTime, vip.creatTime) &&
                Objects.equals(overdueTime, vip.overdueTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vipId, accountNumber, creatTime, overdueTime);
    }
}
