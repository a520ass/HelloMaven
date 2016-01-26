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


@Table(name="JPA_EMPLOYEES")	//��Ա
@Entity
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
	//ֱ����date�����ϼ���ע�⣬ͬʱָ����ʽ��ʽ(����ʹ���Զ�������ת����)
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;
	//float������@NumberFormatע��  @NumberFormat(pattern="#,###,###.#")
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;
	private Department department;//����
	
	//��get������ǰ��ע�⡣��ע��ģ�Ĭ��ע��Ϊ@Basic
	
	@GeneratedValue(strategy=GenerationType.AUTO)//Ĭ����auto Ҳ���Բ�д���Զ�����
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
	
	//Temporal����ָ��Date���ȣ�DATE���������ڡ�TIME��ʱ�䡣TIMESTAMP������ʱ�䣩
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
	
	/**
	 * ������LAZY Department
	 * @return
	 */
	@JoinColumn(name="DEPARTMENT_ID")
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE})
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Transient
	//���߷���������Ҫӳ��Ϊ���ݱ�
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
