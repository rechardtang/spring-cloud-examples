package com.example.txmessageorder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TransactionLog {
    @Id
    private String id;

    @Column
    private String business;

    @Column
    private String foreignKey;

    public TransactionLog() {
    }

    public TransactionLog(String id, String business, String foreignKey) {
        this.id = id;
        this.business = business;
        this.foreignKey = foreignKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }
}
