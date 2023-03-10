package com.juaracoding.mf.repo;

import com.juaracoding.mf.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long>{

}
