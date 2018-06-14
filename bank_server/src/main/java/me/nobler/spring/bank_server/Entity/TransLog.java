package me.nobler.spring.bank_server.Entity;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class TransLog implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private TransType type;
    private String username;
    private String transUsername;

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    private Integer money;

    public TransLog(TransType type, String username, String transUsername, Integer money, Date date, String info) {
        this.type = type;
        this.username = username;
        this.transUsername = transUsername;
        this.money = money;
        this.date = date;
        this.info = info;
    }

    private Date date;
    private String info;

    public TransLog() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }




    public Integer getId() {
        return id;
    }

    public TransLog(TransType type, String username, String transUsername, Date date) {
        this.type = type;
        this.username = username;
        this.transUsername = transUsername;
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransType getType() {
        return type;
    }


    @Enumerated(value = EnumType.STRING)
    public void setType(TransType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTransUsername() {
        return transUsername;
    }

    public void setTransUsername(String transUsername) {
        this.transUsername = transUsername;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

