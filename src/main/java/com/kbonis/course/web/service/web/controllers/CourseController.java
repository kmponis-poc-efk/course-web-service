package com.kbonis.course.web.service.web.controllers;

import com.kbonis.course.web.service.models.Course;
import com.kbonis.course.web.service.services.CourseService;
import com.sopra.steria.LogTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO Make this endpoints accecible by swagger.
 */
@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Course create(@RequestBody Course course, @RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("CourseController().create(sessionId=" + sessionId + ")");
		Course created = courseService.create(course);
		LogTimes.endTiming("CourseController().create(sessionId=" + sessionId + ")", "info");
		return created;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Course update(@RequestBody Course course, @RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("CourseController().update(sessionId=" + sessionId + ")");
		Course updated = courseService.update(course);
		LogTimes.endTiming("CourseController().update(sessionId=" + sessionId + ")", "info");
		return updated;
	}

	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{courseId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "courseId") String courseId, @RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("CourseController().delete(sessionId=" + sessionId + ")");
		courseService.delete(courseId);
		LogTimes.endTiming("CourseController().delete(sessionId=" + sessionId + ")", "info");
    }

	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/findByCourseId/{courseId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Course findByCourseId(@PathVariable(value = "courseId") String courseId, @RequestParam("sessionId") String sessionId) {
		LogTimes.startTiming("CourseController().findByCourseId(sessionId=" + sessionId + ")");
		Course findByCourseId = courseService.findByCourseId(courseId);
		LogTimes.endTiming("CourseController().findByCourseId(sessionId=" + sessionId + ")", "info");
		return findByCourseId;
    }

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Course> findByName(@PathVariable(value = "name") String name, @RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("CourseController().findByName(sessionId=" + sessionId + ")");
		List<Course> findByLastName = courseService.findByName(name);
		LogTimes.endTiming("CourseController().findByName(sessionId=" + sessionId + ")", "info");
		return findByLastName;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/findCourseIdsName", method = RequestMethod.GET)
	public String findCourseIdsName(@RequestParam("courseIds") List<String> courseIds, @RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("CourseController().findCourseIdsName(sessionId=" + sessionId + ")");
		String courseIdsName = courseService.findCourseIdsName(courseIds);
		LogTimes.endTiming("CourseController().findCourseIdsName(sessionId=" + sessionId + ")", "info");
		return courseIdsName;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Course> findAll(@RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("CourseController().findAll(sessionId=" + sessionId + ")");
		List<Course> findAll = courseService.findAll();
		LogTimes.endTiming("CourseController().findAll(sessionId=" + sessionId + ")", "info");
		return findAll;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/initialiseCourses", method = RequestMethod.GET)
	public void initialiseCourses(@RequestParam("sessionId") String sessionId){
		LogTimes.startTiming("CourseController().initialiseCourses(sessionId=" + sessionId + ")");
		courseService.initialiseCourses();
		LogTimes.endTiming("CourseController().initialiseCourses(sessionId=" + sessionId + ")", "info");
	}
}
