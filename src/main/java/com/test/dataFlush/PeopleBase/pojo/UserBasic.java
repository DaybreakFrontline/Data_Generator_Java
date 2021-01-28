package com.test.dataFlush.PeopleBase.pojo;

// K_USER_BASIC

import java.io.Serializable;
import java.util.Objects;

public class UserBasic implements Serializable {

    private String id;

    private String name;

    private String addres;

    private String job;

    private String phone;

    private String time;

    private String identity;

    @Override
    public String toString() {
        return "UserBasic{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", addres='" + addres + '\'' +
                ", job='" + job + '\'' +
                ", phone='" + phone + '\'' +
                ", time='" + time + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBasic userBasic = (UserBasic) o;
        return Objects.equals(id, userBasic.id) &&
                Objects.equals(name, userBasic.name) &&
                Objects.equals(addres, userBasic.addres) &&
                Objects.equals(job, userBasic.job) &&
                Objects.equals(phone, userBasic.phone) &&
                Objects.equals(time, userBasic.time) &&
                Objects.equals(identity, userBasic.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, addres, job, phone, time, identity);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
