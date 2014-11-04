// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.as.cert.domain;

import com.as.cert.domain.Student;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Student_Roo_Json {
    
    public String Student.toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }
    
    public String Student.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }
    
    public static Student Student.fromJsonToStudent(String json) {
        return new JSONDeserializer<Student>()
        .use(null, Student.class).deserialize(json);
    }
    
    public static String Student.toJsonArray(Collection<Student> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }
    
    public static String Student.toJsonArray(Collection<Student> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }
    
    public static Collection<Student> Student.fromJsonArrayToStudents(String json) {
        return new JSONDeserializer<List<Student>>()
        .use("values", Student.class).deserialize(json);
    }
    
}