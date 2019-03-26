package com.kbonis.course.web.service.services.implementations;

import com.kbonis.course.web.service.models.Course;
import com.kbonis.course.web.service.repository.CourseRepository;
import com.kbonis.course.web.service.services.CourseService;
import com.kbonis.course.web.service.services.ManageDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ManageDatabase manageDatabase;

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void delete(String courseId) {
        courseRepository.delete(courseId);
    }

    @Override
    public Course findByCourseId(String courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    @Override
    public List<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public String findCourseIdsName(List<String> courseIds) {
        String courseIdsName = "";
        for (int i=0; i<courseIds.size(); i++){
            String courseId = courseIds.get(i);
            if (null != courseId && !"null".equals(courseId) && !"".equals(courseId)){
                Course course = courseRepository.findByCourseId(courseId);
                if ("".equals(courseIdsName)) {
                    courseIdsName = course.getName();
                } else {
                    courseIdsName = courseIdsName + " <br> " + course.getName();
                }
            }
        }
        return courseIdsName;
    }
    
    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void initialiseCourses() {
        manageDatabase.deleteCourses();
        manageDatabase.addCourses();
    }
}
