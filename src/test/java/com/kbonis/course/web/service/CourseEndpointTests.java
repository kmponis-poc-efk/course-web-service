package com.kbonis.course.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kbonis.course.web.service.repository.CourseRepository;
import com.kbonis.course.web.service.services.ManageDatabase;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbonis.course.web.service.models.Course;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CourseEndpointTests {
    
    final String BASE_PATH = "http://localhost:8093/course";
    final String SESSION_ID = "?sessionId=qwesdad";

	@Autowired
	private CourseRepository courseRepository;
    
    @Autowired
    private ManageDatabase manageDatabase;

    private RestTemplate restTemplate;
    
    private ObjectMapper MAPPER = new ObjectMapper();
    
    @Before
    public void setUp() throws Exception {
        manageDatabase.deleteCourses();
        manageDatabase.addCourses();
        restTemplate = new RestTemplate();
    }
    
    @Test
    public void testCreateCourse() throws JsonProcessingException {
        Course course = new Course("Course", "Description");
        Course response = restTemplate.postForObject(BASE_PATH + SESSION_ID, course, Course.class);
        assertEquals("Course - Description", response.getName() + " - " + response.getDescription());
        courseRepository.delete(response);
    }
    
    @Test
    public void testUpdateStudent() throws IOException { 
        List<Course> courses = courseRepository.findAll();

        Course course1 = courseRepository.findByCourseId(courses.get(0).getCourseId());
        course1.setDescription("Music fundamentals and malakies");
        restTemplate.put(BASE_PATH + SESSION_ID, course1);

        Course course2 = courseRepository.findByCourseId(courses.get(0).getCourseId());
        assertNotNull(course2);
    	assertEquals("Music fundamentals and malakies", course2.getDescription());
    }

    // TODO Remove @ignore
    @Ignore
    @Test
    public void testDeleteStudent() throws IOException {
        List<Course> courses1 = courseRepository.findAll();
        Course course1 = courses1.get(0);
        
        // TODO fix delete call, throws 404
        restTemplate.delete(BASE_PATH + "/delete/"+ course1.getCourseId() + SESSION_ID);

        List<Course> courses2 = courseRepository.findAll();
        Course course2 = courses2.get(0);
        assertNotEquals(course1.getCourseId(), course2.getCourseId());
    }

    @Test
    public void testFindByCourseId() throws IOException {
        List<Course> courses = courseRepository.findAll();
        Course course = restTemplate.getForObject(BASE_PATH + "/findByCourseId/" + courses.get(1).getCourseId() + SESSION_ID, Course.class);
        assertNotNull(course);
    	assertEquals("Malakes", course.getName());
    	assertEquals("Learning the basics of how to be a malaka", course.getDescription());
    }

    @Test
    public void testFindByName() throws IOException {
        List<Course> courses = courseRepository.findAll();
        List<Course> courseByName = restTemplate.getForObject(BASE_PATH + "/findByName/" + courses.get(1).getName() + SESSION_ID, ArrayList.class);
        assertNotNull(courseByName);
        assertThat(courseByName).hasSize(1).extracting("name").contains("Malakes");
        assertThat(courseByName).hasSize(1).extracting("description").contains("Learning the basics of how to be a malaka");
    }
    
    @Test
    public void testFindCourseIdsName() {
        List<Course> courses = courseRepository.findAll();
        String courseIdsParam = "";
        for (Course course: courses) {
            if ("".equals(courseIdsParam)) {
                courseIdsParam = "?courseIds=" + course.getCourseId();
            } else {
                courseIdsParam = courseIdsParam + "&courseIds=" + course.getCourseId();
            }
        }
        String courseIdsName = restTemplate.getForObject(BASE_PATH + "/findCourseIdsName" + courseIdsParam + "&sessionId=asdasd", String.class);
        assertEquals("Music <br> Malakes <br> Angular <br> Delete", courseIdsName);
    }

    @Test
    public void testFindAll() throws IOException {
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll" + SESSION_ID, String.class);
        List<Course> courses = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Course.class));
    	assertEquals("Music", courses.get(0).getName());
    	assertEquals("Malakes", courses.get(1).getName());
    }

    @Test
    public void testInitialiseCourses() {
        courseRepository.save(new Course("Course", "Description"));
        List<Course> coursesBefore = courseRepository.findAll();
        assertEquals(5, coursesBefore.size());
        
        restTemplate.getForObject(BASE_PATH + "/initialiseCourses" + SESSION_ID, String.class);

        List<Course> coursesAfter = courseRepository.findAll();
        assertEquals(4, coursesAfter.size());
        assertThat(coursesAfter).hasSize(4).extracting("courseId").contains("5c0fac73ba7ba50d161e671a", "5c0fac73ba7ba50d161e671b", "5c0fac73ba7ba50d161e671c", "5c0fac73ba7ba50d161e671d");
        assertThat(coursesAfter).hasSize(4).extracting("name").contains("Music", "Malakes", "Angular", "Delete");
        assertThat(coursesAfter).hasSize(4).extracting("description").contains("Initial course for music", "Learning the basics of how to be a malaka", "Learning the basics of angular", "After deleting this course check Anderson Silva");
    }
}