package com.as.cert;
import com.as.cert.domain.CourseDetail;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = CourseDetail.class)
@Controller
@RequestMapping("/coursedetails")
public class CourseDetailController {
}
