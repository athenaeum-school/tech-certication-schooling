package com.as.cert.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Component
@Configurable
@RooDataOnDemand(entity = Student.class)
public class StudentDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Student> data;

	public Student getNewTransientStudent(int index) {
        Student obj = new Student();
        setAddress(obj, index);
        setName(obj, index);
        return obj;
    }

	public void setAddress(Student obj, int index) {
        String address = "address_" + index;
        if (address.length() > 30) {
            address = address.substring(0, 30);
        }
        obj.setAddress(address);
    }

	public void setName(Student obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

	public Student getSpecificStudent(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Student obj = data.get(index);
        Long id = obj.getId();
        return Student.findStudent(id);
    }

	public Student getRandomStudent() {
        init();
        Student obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Student.findStudent(id);
    }

	public boolean modifyStudent(Student obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Student.findStudentEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Student' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student obj = getNewTransientStudent(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
}
