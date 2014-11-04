package com.as.cert;
import com.as.cert.domain.CourseCatalog;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = CourseCatalog.class)
@Controller
@RequestMapping("/coursecatalogs")
public class CourseCatalogController {
}
