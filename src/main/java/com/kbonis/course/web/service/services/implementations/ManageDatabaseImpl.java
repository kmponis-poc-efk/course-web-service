package com.kbonis.course.web.service.services.implementations;

import java.util.List;

import com.kbonis.course.web.service.repository.CourseRepository;
import com.kbonis.course.web.service.services.ManageDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.kbonis.course.web.service.models.Course;

@Service
public class ManageDatabaseImpl implements ManageDatabase {
    
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void addCourses() {
        courseRepository.save(new Course("5c0fac73ba7ba50d161e671a", "Music", "Initial course for music"));
        courseRepository.save(new Course("5c0fac73ba7ba50d161e671b", "Malakes", "Learning the basics of how to be a malaka"));
        courseRepository.save(new Course("5c0fac73ba7ba50d161e671c", "Angular", "Learning the basics of angular"));
        courseRepository.save(new Course("5c0fac73ba7ba50d161e671d", "Delete", "After deleting this course check Anderson Silva"));
    }

    @Override
    public void deleteCourses() {
        courseRepository.delete(findAllCourses());
    }

    private List<Course> findAllCourses() {
        return courseRepository.findAll(Example.of(new Course()));
    }
}