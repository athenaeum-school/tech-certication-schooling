package com.as.cert;

import com.as.cert.domain.CourseRegistration;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

@RooWebJson(jsonObject = CourseRegistration.class)
@Controller
@RequestMapping("/courseregistrations")
public class CourseRegistrationController {

	@RequestMapping(value = "/{id_}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id_") Long id_) {
        CourseRegistration courseRegistration = CourseRegistration.findCourseRegistration(id_);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (courseRegistration == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(courseRegistration.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<CourseRegistration> result = CourseRegistration.findAllCourseRegistrations();
        return new ResponseEntity<String>(CourseRegistration.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        CourseRegistration courseRegistration = CourseRegistration.fromJsonToCourseRegistration(json);
        courseRegistration.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+courseRegistration.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (CourseRegistration courseRegistration: CourseRegistration.fromJsonArrayToCourseRegistrations(json)) {
            courseRegistration.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id_}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id_") Long id_) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        CourseRegistration courseRegistration = CourseRegistration.fromJsonToCourseRegistration(json);
        courseRegistration.setId_(id_);
        if (courseRegistration.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id_}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id_") Long id_) {
        CourseRegistration courseRegistration = CourseRegistration.findCourseRegistration(id_);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (courseRegistration == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        courseRegistration.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
