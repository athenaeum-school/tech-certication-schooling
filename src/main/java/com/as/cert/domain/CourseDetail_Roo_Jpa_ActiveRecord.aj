// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.as.cert.domain;

import com.as.cert.domain.CourseDetail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect CourseDetail_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager CourseDetail.entityManager;
    
    public static final List<String> CourseDetail.fieldNames4OrderClauseFilter = java.util.Arrays.asList("name");
    
    public static final EntityManager CourseDetail.entityManager() {
        EntityManager em = new CourseDetail().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long CourseDetail.countCourseDetails() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CourseDetail o", Long.class).getSingleResult();
    }
    
    public static List<CourseDetail> CourseDetail.findAllCourseDetails() {
        return entityManager().createQuery("SELECT o FROM CourseDetail o", CourseDetail.class).getResultList();
    }
    
    public static List<CourseDetail> CourseDetail.findAllCourseDetails(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseDetail o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseDetail.class).getResultList();
    }
    
    public static CourseDetail CourseDetail.findCourseDetail(Long id) {
        if (id == null) return null;
        return entityManager().find(CourseDetail.class, id);
    }
    
    public static List<CourseDetail> CourseDetail.findCourseDetailEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CourseDetail o", CourseDetail.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<CourseDetail> CourseDetail.findCourseDetailEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseDetail o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseDetail.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void CourseDetail.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void CourseDetail.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            CourseDetail attached = CourseDetail.findCourseDetail(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void CourseDetail.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void CourseDetail.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public CourseDetail CourseDetail.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CourseDetail merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}