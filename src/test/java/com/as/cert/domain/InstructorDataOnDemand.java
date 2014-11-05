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
@RooDataOnDemand(entity = Instructor.class)
public class InstructorDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Instructor> data;

	public Instructor getNewTransientInstructor(int index) {
        Instructor obj = new Instructor();
        setName(obj, index);
        return obj;
    }

	public void setName(Instructor obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

	public Instructor getSpecificInstructor(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Instructor obj = data.get(index);
        Long id = obj.getId();
        return Instructor.findInstructor(id);
    }

	public Instructor getRandomInstructor() {
        init();
        Instructor obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Instructor.findInstructor(id);
    }

	public boolean modifyInstructor(Instructor obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Instructor.findInstructorEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Instructor' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Instructor>();
        for (int i = 0; i < 10; i++) {
            Instructor obj = getNewTransientInstructor(i);
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
