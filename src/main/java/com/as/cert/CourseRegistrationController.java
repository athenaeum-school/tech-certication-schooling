package com.as.cert;
import com.as.cert.domain.CourseRegistration;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = CourseRegistration.class)
@Controller
@RequestMapping("/courseregistrations")
public class CourseRegistrationController {
}
