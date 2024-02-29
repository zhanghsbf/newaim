package com.zyk.newaimdataapi.entity.dto;


/**
 * @description: TODO
 * @date: 2024/2/29 10:33
 * @author: zyk
 */
public class Contactor {
    private String id;
    private String username;
    private String name;
    private String gender;
    private String address;
    private String mail;
    private String birthdate;


    public Contactor(String id, String username, String name, String gender, String address, String mail, String birthdate) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.mail = mail;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
