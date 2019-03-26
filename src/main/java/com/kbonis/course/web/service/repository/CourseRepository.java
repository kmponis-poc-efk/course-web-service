package com.kbonis.course.web.service.repository;

import com.kbonis.course.web.service.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository <Course, String> {
   
    Course findByCourseId(String courseId);
    List<Course> findByName(String name);
}
