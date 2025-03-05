package org.example.adventurexp.repository;

import org.example.adventurexp.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}
