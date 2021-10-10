package com.saraya.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class EmployeeDto {

    @Size(max = 50)
    @NotBlank
    private String name;
    @Size(max = 50)
    @NotBlank
    private String email;
    @Size(max = 15)
    @NotBlank
    private String phone;
    @Size(max = 25)
    @NotBlank
    private String jobTitle;
    private String employeeCode;
    @NotBlank
    private Date dob;
    @NotBlank
    private String address;
    @NotBlank
    private Character gender;
    @NotBlank
    private String imageUrl;
    private Boolean isActive;

    public EmployeeDto() {}

    public EmployeeDto(String name, String email, String phone, String jobTitle, String employeeCode, Date dob,
                                        String address, Character gender, String imageUrl, Boolean isActive) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.employeeCode = employeeCode;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
