package com.kbonis.course.web.service.services;

import com.kbonis.course.web.service.models.Course;

import java.util.List;

public interface CourseService {

    /**
     * Create Course
     * @param course Course
     * @return Course
     */
    Course create(Course course);

    /**
     * Update Course
     * @param course Course
     * @return Course
     */
    Course update(Course course);

    /**
     * Delete Course
     * @param id String
     */
    void delete(String id);

    /**
     * Get Course by course id
     * @param courseId String
     * @return Course
     */
    Course findByCourseId(String courseId);

    /**
     * Get Course by name
     * @param name String
     * @return List<Course>
     */
    List<Course> findByName(String name);

    /**
     * Get a string with all names of courseIds separated by comma.
     * @param courseIds
     * @return String
     */
    String findCourseIdsName(List<String> courseIds);

    /**
     * Get all Courses
     * @return List<Course>
     */
    List<Course> findAll();

    /**
     * Initialise Database courses
     */
    void initialiseCourses();
}
