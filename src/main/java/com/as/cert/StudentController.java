package com.as.cert;
import com.as.cert.domain.Student;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = Student.class)
@Controller
@RequestMapping("/students")
public class StudentController {
}
