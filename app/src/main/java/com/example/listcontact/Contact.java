package com.example.listcontact;

public class Contact {

    int id;
    String name,number,email,organaization,relationship,dateOfBirth;


    public Contact(){}

    public Contact(int id,String name,String number,String email,String organaization,String relationship){
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.organaization = organaization;
        this.relationship = relationship;
    }

    public Contact(String name,String number,String email,String organaization,String relationship){
        this.name = name;
        this.number = number;
        this.email = email;
        this.organaization = organaization;
        this.relationship = relationship;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganaization() {
        return organaization;
    }

    public void setOrganaization(String organaization) {
        this.organaization = organaization;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
