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
@RooDataOnDemand(entity = CourseDetail.class)
public class CourseDetailDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<CourseDetail> data;

	public CourseDetail getNewTransientCourseDetail(int index) {
        CourseDetail obj = new CourseDetail();
        setName(obj, index);
        return obj;
    }

	public void setName(CourseDetail obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

	public CourseDetail getSpecificCourseDetail(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        CourseDetail obj = data.get(index);
        Long id = obj.getId();
        return CourseDetail.findCourseDetail(id);
    }

	public CourseDetail getRandomCourseDetail() {
        init();
        CourseDetail obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return CourseDetail.findCourseDetail(id);
    }

	public boolean modifyCourseDetail(CourseDetail obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = CourseDetail.findCourseDetailEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'CourseDetail' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<CourseDetail>();
        for (int i = 0; i < 10; i++) {
            CourseDetail obj = getNewTransientCourseDetail(i);
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
