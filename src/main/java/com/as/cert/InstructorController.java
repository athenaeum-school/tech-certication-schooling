package com.as.cert;
import com.as.cert.domain.Instructor;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = Instructor.class)
@Controller
@RequestMapping("/instructors")
public class InstructorController {
}
