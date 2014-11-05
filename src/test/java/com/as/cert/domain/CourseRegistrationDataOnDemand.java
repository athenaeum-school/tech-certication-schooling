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
@RooDataOnDemand(entity = CourseRegistration.class)
public class CourseRegistrationDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<CourseRegistration> data;

	public CourseRegistration getNewTransientCourseRegistration(int index) {
        CourseRegistration obj = new CourseRegistration();
        setCourseId(obj, index);
        setId(obj, index);
        setName(obj, index);
        setStudentId(obj, index);
        return obj;
    }

	public void setCourseId(CourseRegistration obj, int index) {
        Long courseId = new Integer(index).longValue();
        obj.setCourseId(courseId);
    }

	public void setId(CourseRegistration obj, int index) {
        Integer id = new Integer(index);
        obj.setId(id);
    }

	public void setName(CourseRegistration obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

	public void setStudentId(CourseRegistration obj, int index) {
        Long studentId = new Integer(index).longValue();
        obj.setStudentId(studentId);
    }

	public CourseRegistration getSpecificCourseRegistration(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        CourseRegistration obj = data.get(index);
        Long id = obj.getId_();
        return CourseRegistration.findCourseRegistration(id);
    }

	public CourseRegistration getRandomCourseRegistration() {
        init();
        CourseRegistration obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId_();
        return CourseRegistration.findCourseRegistration(id);
    }

	public boolean modifyCourseRegistration(CourseRegistration obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = CourseRegistration.findCourseRegistrationEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'CourseRegistration' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<CourseRegistration>();
        for (int i = 0; i < 10; i++) {
            CourseRegistration obj = getNewTransientCourseRegistration(i);
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
