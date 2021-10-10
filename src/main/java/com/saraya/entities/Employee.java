package com.saraya.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = "phone")}
)
public class Employee extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long Id;
    @Size(max = 50)
	@NotBlank
    private String name;
    @Email
    @NaturalId
    @Size(max = 50)
	@NotBlank
    private String email;
    @Size(max = 15)
	@NotBlank
    private String phone;
    @Size(max = 25)
	@NotBlank
    @Column(name="job_title")
    private String jobTitle;
    @Column(name="employee_code", nullable = false, updatable = false)
    private String employeeCode;
	@NotBlank
    @Column(name="date_of_birth")
    private Date dob;
	@NotBlank
    private String address;
	@NotBlank
    private Character gender;
	@NotBlank
    @Column(name="image_url")
    private String imageUrl;
	private Boolean isActive;

    public Employee() {}

	public Employee(String name, String email, String phone, String jobTitle, String employeeCode, Date dob,
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

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	@Override
	public String toString() {
		return "Employee{" +
				"Id=" + Id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", jobTitle='" + jobTitle + '\'' +
				", employeeCode='" + employeeCode + '\'' +
				", dob=" + dob +
				", address='" + address + '\'' +
				", gender=" + gender +
				", imageUrl='" + imageUrl + '\'' +
				", isActive=" + isActive +
				'}';
	}
}
