package com.lzh.mybatis.entity;

import java.util.Date;

public class MscPerm {
    private Long id;

    private String permName;

    private String permDesc;

    private String permValue;

    private Integer permLevel;

    private Integer permOrder;

    private Date createDate;

    private Date modifyDate;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName == null ? null : permName.trim();
    }

    public String getPermDesc() {
        return permDesc;
    }

    public void setPermDesc(String permDesc) {
        this.permDesc = permDesc == null ? null : permDesc.trim();
    }

    public String getPermValue() {
        return permValue;
    }

    public void setPermValue(String permValue) {
        this.permValue = permValue == null ? null : permValue.trim();
    }

    public Integer getPermLevel() {
        return permLevel;
    }

    public void setPermLevel(Integer permLevel) {
        this.permLevel = permLevel;
    }

    public Integer getPermOrder() {
        return permOrder;
    }

    public void setPermOrder(Integer permOrder) {
        this.permOrder = permOrder;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}