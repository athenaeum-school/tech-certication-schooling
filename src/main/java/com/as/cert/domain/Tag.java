package com.as.cert.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Tag {

    /**
     */
    @NotNull
    @Size(min = 1, max = 25)
    private String tag;

    /**
     */
    @NotNull
    @Size(max = 250)
    private String description;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<Course>();
}
