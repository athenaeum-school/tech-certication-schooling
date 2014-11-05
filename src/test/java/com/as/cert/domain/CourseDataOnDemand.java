package com.as.cert.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
@RooDataOnDemand(entity = Course.class)
public class CourseDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Course> data;

	public Course getNewTransientCourse(int index) {
        Course obj = new Course();
        setEndDate(obj, index);
        setFee(obj, index);
        setName(obj, index);
        setStartDate(obj, index);
        return obj;
    }

	public void setEndDate(Course obj, int index) {
        Date endDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setEndDate(endDate);
    }

	public void setFee(Course obj, int index) {
        Float fee = new Integer(index).floatValue();
        obj.setFee(fee);
    }

	public void setName(Course obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

	public void setStartDate(Course obj, int index) {
        Date startDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setStartDate(startDate);
    }

	public Course getSpecificCourse(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Course obj = data.get(index);
        Long id = obj.getId();
        return Course.findCourse(id);
    }

	public Course getRandomCourse() {
        init();
        Course obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Course.findCourse(id);
    }

	public boolean modifyCourse(Course obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Course.findCourseEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Course' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Course>();
        for (int i = 0; i < 10; i++) {
            Course obj = getNewTransientCourse(i);
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
