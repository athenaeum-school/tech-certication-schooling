package com.as.cert;
import com.as.cert.domain.Course;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = Course.class)
@Controller
@RequestMapping("/courses")
public class CourseController {
}
