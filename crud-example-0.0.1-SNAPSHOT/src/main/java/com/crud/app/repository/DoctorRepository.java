package com.crud.app.repository;

import com.crud.app.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
	public Doctor findByEmailAndPassword(String email, String password);
}
