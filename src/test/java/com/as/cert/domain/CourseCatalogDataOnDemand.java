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

@Configurable
@Component
@RooDataOnDemand(entity = CourseCatalog.class)
public class CourseCatalogDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<CourseCatalog> data;

	public CourseCatalog getNewTransientCourseCatalog(int index) {
        CourseCatalog obj = new CourseCatalog();
        setName(obj, index);
        return obj;
    }

	public void setName(CourseCatalog obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

	public CourseCatalog getSpecificCourseCatalog(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        CourseCatalog obj = data.get(index);
        Long id = obj.getId();
        return CourseCatalog.findCourseCatalog(id);
    }

	public CourseCatalog getRandomCourseCatalog() {
        init();
        CourseCatalog obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return CourseCatalog.findCourseCatalog(id);
    }

	public boolean modifyCourseCatalog(CourseCatalog obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = CourseCatalog.findCourseCatalogEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'CourseCatalog' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<CourseCatalog>();
        for (int i = 0; i < 10; i++) {
            CourseCatalog obj = getNewTransientCourseCatalog(i);
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
