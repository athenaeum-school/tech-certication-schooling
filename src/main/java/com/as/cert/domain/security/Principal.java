package com.as.cert.domain.security;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "security_principals")
public class Principal {

    /**
     */
    @NotNull
    @Size(min = 3, max = 50)
    private String password;

    /**
     */
    private Boolean enabled;

    /**
     */
    @ManyToOne
    private Principal username;

    /**
     */
    @ManyToOne
    private Authority roleId;
}
