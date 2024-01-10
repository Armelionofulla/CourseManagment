package com.project.courseManagement.service;
import com.project.courseManagement.dto.ConnectionsDTO;
import com.project.courseManagement.entity.Connection;
import com.project.courseManagement.entity.Student;
import com.project.courseManagement.repository.ConnectionRepository;
import com.project.courseManagement.repository.CourseRepository;
import com.project.courseManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectionService {

    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public Connection addStatus(Connection connection){

        return connectionRepository.save(connection);
    }

    public ResponseEntity createConnectionRequest(Student sender, Long receiverId){
       Connection connection = new Connection();
       connection.setStatus("pending");
       connection.setSender(sender);
       Optional receiver = studentRepository.findById(receiverId);

       receiver.ifPresent(r -> {
           connection.setReceiver((Student) r);
           connectionRepository.save(connection);
       });

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    public ResponseEntity acceptConection(Long recieverId,Long senderID ){
        Connection connection = connectionRepository.findById(recieverId).get();
        connection.setStatus("friends");
         connectionRepository.save(connection);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    //public ResponseEntity deleteConnection(Long reciberId){}


    public List<ConnectionsDTO> getConnections(Long id, String status){
        return connectionRepository.getConnections(id, status);
    }

    public List<Connection> findAll(){

        return connectionRepository.findAll();
    }
}
