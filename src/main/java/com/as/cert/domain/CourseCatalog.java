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
public class CourseCatalog {

	/**
     */
	@NotNull
	@Size(min = 2)
	private String name;

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }

	public static CourseCatalog fromJsonToCourseCatalog(String json) {
        return new JSONDeserializer<CourseCatalog>()
        .use(null, CourseCatalog.class).deserialize(json);
    }

	public static String toJsonArray(Collection<CourseCatalog> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }

	public static String toJsonArray(Collection<CourseCatalog> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }

	public static Collection<CourseCatalog> fromJsonArrayToCourseCatalogs(String json) {
        return new JSONDeserializer<List<CourseCatalog>>()
        .use("values", CourseCatalog.class).deserialize(json);
    }

	public String getName() {
        return this.name;
    }

	public void setName(String name) {
        this.name = name;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("name");

	public static final EntityManager entityManager() {
        EntityManager em = new CourseCatalog().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCourseCatalogs() {
        return entityManager().createQuery("SELECT COUNT(o) FROM CourseCatalog o", Long.class).getSingleResult();
    }

	public static List<CourseCatalog> findAllCourseCatalogs() {
        return entityManager().createQuery("SELECT o FROM CourseCatalog o", CourseCatalog.class).getResultList();
    }

	public static List<CourseCatalog> findAllCourseCatalogs(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseCatalog o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseCatalog.class).getResultList();
    }

	public static CourseCatalog findCourseCatalog(Long id) {
        if (id == null) return null;
        return entityManager().find(CourseCatalog.class, id);
    }

	public static List<CourseCatalog> findCourseCatalogEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM CourseCatalog o", CourseCatalog.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<CourseCatalog> findCourseCatalogEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM CourseCatalog o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, CourseCatalog.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            CourseCatalog attached = CourseCatalog.findCourseCatalog(this.id);
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
    public CourseCatalog merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        CourseCatalog merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
