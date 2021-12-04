package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/24 20:54
 * @description 退货原因表 实体类
 */
@Entity
@Table(name = "return_reason")
public class ReturnReason {
    private Integer reasonId;
    private String reasonName;
    private Boolean status;

    public ReturnReason() {
    }
    public ReturnReason(Integer reasonId, String reasonName, Boolean status) {
        this.reasonId = reasonId;
        this.reasonName = reasonName;
        this.status = status;
    }
    @Id
    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReturnReason{" +
                "reasonId=" + reasonId +
                ", reasonName='" + reasonName + '\'' +
                ", status=" + status +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnReason that = (ReturnReason) o;
        return Objects.equals(reasonId, that.reasonId) &&
                Objects.equals(reasonName, that.reasonName) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reasonId, reasonName, status);
    }
}
