package com.kbonis.course.web.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kbonis.course.web.service.models.Course;
import com.kbonis.course.web.service.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CourseRepositoryTests {

    @Autowired
    CourseRepository courseRepository;

    @Before
    public void setUp() { }

    @Test
    public void testFindByCourseId() {
        Course mit = new Course("MIT", "Course description test case");
        courseRepository.save(mit);
        Course repositoryResponse = courseRepository.findByCourseId(mit.getCourseId());
        assertEquals(mit.getCourseId(), repositoryResponse.getCourseId());
        courseRepository.delete(mit);
    }

    @Test
    public void testFindByName() {
        Course harvard = courseRepository.save(new Course("Harvard", "Malakes in harvard"));
        List<Course> result = courseRepository.findByName("Harvard");
        assertThat(result).hasSize(1).extracting("description").contains("Malakes in harvard");
        courseRepository.delete(harvard);
    }
}
