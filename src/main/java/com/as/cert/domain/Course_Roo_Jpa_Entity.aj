// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.as.cert.domain;

import com.as.cert.domain.Course;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Course_Roo_Jpa_Entity {
    
    declare @type: Course: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Course.id;
    
    @Version
    @Column(name = "version")
    private Integer Course.version;
    
    public Long Course.getId() {
        return this.id;
    }
    
    public void Course.setId(Long id) {
        this.id = id;
    }
    
    public Integer Course.getVersion() {
        return this.version;
    }
    
    public void Course.setVersion(Integer version) {
        this.version = version;
    }
    
}
