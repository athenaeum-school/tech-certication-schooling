// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.as.cert.domain.security;

import com.as.cert.domain.security.Principal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect Principal_Roo_Jpa_Entity {
    
    declare @type: Principal: @Entity;
    
    declare @type: Principal: @Table(name = "security_principals");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Principal.id;
    
    @Version
    @Column(name = "version")
    private Integer Principal.version;
    
    public Long Principal.getId() {
        return this.id;
    }
    
    public void Principal.setId(Long id) {
        this.id = id;
    }
    
    public Integer Principal.getVersion() {
        return this.version;
    }
    
    public void Principal.setVersion(Integer version) {
        this.version = version;
    }
    
}
