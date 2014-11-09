package com.as.cert.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")

public abstract class Person {
	
	@Size(min = 1, max = 30)
    private String firstName;
	@Size(min = 1, max = 30)
    private String middleNameOrInitial;
    @NotNull
    @Size(min = 1, max = 30)
    private String lastName;
    @NotNull
    @Size(min = 1, max = 60)
    private String addressLine1;
    @Size(min = 0, max = 60)
    private String addressLine2;
    @NotNull
    @Size(min = 1, max = 40)
    private String city;
    @NotNull
    @Size(min = 2, max = 2)
    private String stateCode;
    @NotNull
    @Size(min = 1, max = 10)
    private String postalCode;
    @NotNull
    @Size(max = 80)
    private java.lang.String emailAddress;
	

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }
}
