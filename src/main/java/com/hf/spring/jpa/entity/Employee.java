package com.hf.spring.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table(name="JPA_EMPLOYEES")	//雇员
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1701478904395689630L;
	private Integer id;
	@NotEmpty
	private String lastName;
	@Email
	private String email;
	//直接在date类型上加入注解，同时指定格式样式(或者使用自定义类型转换器)
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;
	//float类型用@NumberFormat注解  @NumberFormat(pattern="#,###,###.#")
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;
	private Department department;//部门
	
	//在get方法的前面注解。不注解的，默认注解为@Basic
	
	@GeneratedValue(strategy=GenerationType.AUTO)//默认是auto 也可以不写，自动递增
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Temporal用于指定Date精度（DATE：单独日期。TIME：时间。TIMESTAMP：日期时间）
	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	@JoinColumn(name="DEPARTMENT_ID")
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE})
	@JsonIgnore
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Transient
	//工具方法，不需要映射为数据表
	public String getInfo(){
		return "lastName"+lastName+",email"+email;
	}
	
	public Employee(Integer id, String lastName, String email, Date birth,
			Date createTime, Department department) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.birth = birth;
		this.createTime = createTime;
		this.department = department;
	}
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email="
				+ email + ", birth=" + birth + ", createTime=" + createTime
				+ "]";
	}
}
