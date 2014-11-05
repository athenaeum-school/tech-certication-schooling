package com.as.cert.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("id", "name", "studentId", "courseId");

	public static final EntityManager entityManager() {
        EntityManager em = new CourseRegistration().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCourseRegistrations() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CourseRegistration o", Long.class).getSingleResult();
    }

	public static List<CourseRegistration> findAllCourseRegistrations() {
        return entityManager().createQuery("SELECT o FROM CourseRegistration o", CourseRegistration.class).getResultList();
    }

	public static List<CourseRegistration> findAllCourseRegistrations(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseRegistration o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseRegistration.class).getResultList();
    }

	public static CourseRegistration findCourseRegistration(Long id_) {
        if (id_ == null) return null;
        return entityManager().find(CourseRegistration.class, id_);
    }

	public static List<CourseRegistration> findCourseRegistrationEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CourseRegistration o", CourseRegistration.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<CourseRegistration> findCourseRegistrationEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseRegistration o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseRegistration.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            CourseRegistration attached = CourseRegistration.findCourseRegistration(this.id_);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public CourseRegistration merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CourseRegistration merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Integer getId() {
        return this.id;
    }

	public void setId(Integer id) {
        this.id = id;
    }

	public String getName() {
        return this.name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public Long getStudentId() {
        return this.studentId;
    }

	public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

	public Long getCourseId() {
        return this.courseId;
    }

	public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_")
    private Long id_;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId_() {
        return this.id_;
    }

	public void setId_(Long id) {
        this.id_ = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }

	public static CourseRegistration fromJsonToCourseRegistration(String json) {
        return new JSONDeserializer<CourseRegistration>()
        .use(null, CourseRegistration.class).deserialize(json);
    }

	public static String toJsonArray(Collection<CourseRegistration> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<CourseRegistration> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<CourseRegistration> fromJsonArrayToCourseRegistrations(String json) {
        return new JSONDeserializer<List<CourseRegistration>>()
        .use("values", CourseRegistration.class).deserialize(json);
    }
}
