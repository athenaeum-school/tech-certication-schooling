package com.as.cert.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
public class CourseRegistration {

    /**
     */
    private Integer id;

    /**
     */
    @NotNull
    @Size(min = 2)
    private String name;

    /**
     */
    private Long studentId;

    /**
     */
    private Long courseId;
}
