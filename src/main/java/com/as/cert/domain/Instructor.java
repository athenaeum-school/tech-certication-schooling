package com.as.cert.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
public class Instructor {

	/**
     */
	@NotNull
	@Size(min = 2)
	private String name;

	/**
     */
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Course> courses = new HashSet<Course>();

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }

	public static Instructor fromJsonToInstructor(String json) {
        return new JSONDeserializer<Instructor>()
        .use(null, Instructor.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Instructor> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<Instructor> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<Instructor> fromJsonArrayToInstructors(String json) {
        return new JSONDeserializer<List<Instructor>>()
        .use("values", Instructor.class).deserialize(json);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("name", "courses");

	public static final EntityManager entityManager() {
        EntityManager em = new Instructor().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countInstructors() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Instructor o", Long.class).getSingleResult();
    }

	public static List<Instructor> findAllInstructors() {
        return entityManager().createQuery("SELECT o FROM Instructor o", Instructor.class).getResultList();
    }

	public static List<Instructor> findAllInstructors(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Instructor o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Instructor.class).getResultList();
    }

	public static Instructor findInstructor(Long id) {
        if (id == null) return null;
        return entityManager().find(Instructor.class, id);
    }

	public static List<Instructor> findInstructorEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Instructor o", Instructor.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Instructor> findInstructorEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Instructor o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Instructor.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Instructor attached = Instructor.findInstructor(this.id);
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
    public Instructor merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Instructor merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String getName() {
        return this.name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public Set<Course> getCourses() {
        return this.courses;
    }

	public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
