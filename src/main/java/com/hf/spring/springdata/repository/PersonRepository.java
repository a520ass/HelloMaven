package com.hf.spring.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hf.spring.springdata.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>,JpaSpecificationExecutor<Person>{
	
	Person getById(Integer id);
	List<Person> getByEmailNotNull();
	List<Person> getByIdBetween(Integer idless,Integer idmore);
	
	@Query("SELECT p FROM com.hf.spring.springdata.entity.Person p WHERE p.id=(SELECT max(p2.id) FROM Person p2)")
	Person getMaxIdPerson();
	
	//使用占位符
	@Query("SELECT p FROM com.hf.spring.springdata.entity.Person p WHERE p.lastName=?1 AND p.email=?2")
	List<Person> testQueryAnnotationParams1(String lastName,String email);
	//使用命名参数
	@Query("SELECT p FROM com.hf.spring.springdata.entity.Person p WHERE p.lastName=:lastName AND p.email=:email")
	List<Person> testQueryAnnotationParams2(@Param("lastName")String lastName,@Param("email")String email);
	
	//
	@Query("SELECT p FROM com.hf.spring.springdata.entity.Person p WHERE p.lastName LIKE ?1 OR p.email LIKE ?2")
	List<Person> testQueryAnnotationLikeParams1(String lastName,String email);
	
	//
	@Query("SELECT p FROM com.hf.spring.springdata.entity.Person p WHERE p.lastName LIKE %?1% OR p.email LIKE %?2%")
	List<Person> testQueryAnnotationLikeParams2(String lastName,String email);
	
	@Query(value="select count(id) from sjpa_persons",nativeQuery=true)
	long getTotalCount();
	
	@Modifying
	@Query("update com.hf.spring.springdata.entity.Person p set p.email=:email where p.id=:id")
	int updatePersonEmail(@Param("id")Integer id,@Param("email")String email);
}
