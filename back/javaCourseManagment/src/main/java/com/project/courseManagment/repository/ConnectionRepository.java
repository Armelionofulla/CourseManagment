package com.project.courseManagement.repository;

import com.project.courseManagement.dto.ConnectionsDTO;
import com.project.courseManagement.entity.Connection;
import com.project.courseManagement.entity.Course;
import com.project.courseManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection,Long> {

    @Query(value = "Select s2.name as studentName, s2.lastName,  s2.username, s2.email, s2.address, s2.picture, s2.id from connection as c join " +
            " student as s on c.receiver_id =s.id or c.sender_id = s.id" +
            " join student as s2 on c.receiver_id = s2.id or c.sender_id = s2.id" +
            " where s.id = :id and c.status = :status and s2.id != 4" , nativeQuery = true)
    List<ConnectionsDTO> getConnections(Long id, String status);


    @Override
    Optional<Connection> findById(Long aLong);
}
//Select s.name as studentName, s.lastName,  s.username, s.email, s.address, s.picture, s.id