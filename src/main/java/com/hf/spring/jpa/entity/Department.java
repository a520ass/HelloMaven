package com.hf.spring.jpa.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Table(name="JPA_DEPARTMENTS")
@Entity
public class Department implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3496147272059842649L;
	private Integer id;
	private String departmentName;
	//private Set<Employee> employees=new HashSet<>();
	
	//allocationSize默认为50  生成主键为PK_VALUE乘于allocationSize（PK_VALUE值是按1递增的，先乘allocationSize后，
	//数据表再递增，递增后显示是PK_VALUE+1）
	@TableGenerator(name="ID_GENERATOR",
			table="jpa_id_generators",
			pkColumnName="PK_NAME",
			pkColumnValue="DEPARTMENT_ID",
			valueColumnName="PK_VALUE",
			allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="ID_GENERATOR")
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	/*@JoinColumn(name="DEPARTMENT_ID")
	@OneToMany(cascade={CascadeType.PERSIST})
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}*/
	public Department(Integer id, String departmentName) {
		super();
		this.id = id;
		this.departmentName = departmentName;
	}
	public Department() {
		super();
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName
				+ "]";
	}
}
