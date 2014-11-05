package com.as.cert.domain;

import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@RooIntegrationTest(entity = CourseRegistration.class)
public class CourseRegistrationIntegrationTest {

	@Test
	public void testMarkerMethod() {
	}

	@Autowired
    CourseRegistrationDataOnDemand dod;

	@Test
    public void testCountCourseRegistrations() {
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to initialize correctly", dod.getRandomCourseRegistration());
        long count = CourseRegistration.countCourseRegistrations();
        Assert.assertTrue("Counter for 'CourseRegistration' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindCourseRegistration() {
        CourseRegistration obj = dod.getRandomCourseRegistration();
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to provide an identifier", id);
        obj = CourseRegistration.findCourseRegistration(id);
        Assert.assertNotNull("Find method for 'CourseRegistration' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'CourseRegistration' returned the incorrect identifier", id, obj.getId_());
    }

	@Test
    public void testFindAllCourseRegistrations() {
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to initialize correctly", dod.getRandomCourseRegistration());
        long count = CourseRegistration.countCourseRegistrations();
        Assert.assertTrue("Too expensive to perform a find all test for 'CourseRegistration', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<CourseRegistration> result = CourseRegistration.findAllCourseRegistrations();
        Assert.assertNotNull("Find all method for 'CourseRegistration' illegally returned null", result);
        Assert.assertTrue("Find all method for 'CourseRegistration' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindCourseRegistrationEntries() {
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to initialize correctly", dod.getRandomCourseRegistration());
        long count = CourseRegistration.countCourseRegistrations();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<CourseRegistration> result = CourseRegistration.findCourseRegistrationEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'CourseRegistration' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'CourseRegistration' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        CourseRegistration obj = dod.getRandomCourseRegistration();
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to provide an identifier", id);
        obj = CourseRegistration.findCourseRegistration(id);
        Assert.assertNotNull("Find method for 'CourseRegistration' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyCourseRegistration(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'CourseRegistration' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        CourseRegistration obj = dod.getRandomCourseRegistration();
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to provide an identifier", id);
        obj = CourseRegistration.findCourseRegistration(id);
        boolean modified =  dod.modifyCourseRegistration(obj);
        Integer currentVersion = obj.getVersion();
        CourseRegistration merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId_(), id);
        Assert.assertTrue("Version for 'CourseRegistration' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to initialize correctly", dod.getRandomCourseRegistration());
        CourseRegistration obj = dod.getNewTransientCourseRegistration(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'CourseRegistration' identifier to be null", obj.getId_());
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
        Assert.assertNotNull("Expected 'CourseRegistration' identifier to no longer be null", obj.getId_());
    }

	@Test
    public void testRemove() {
        CourseRegistration obj = dod.getRandomCourseRegistration();
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to initialize correctly", obj);
        Long id = obj.getId_();
        Assert.assertNotNull("Data on demand for 'CourseRegistration' failed to provide an identifier", id);
        obj = CourseRegistration.findCourseRegistration(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'CourseRegistration' with identifier '" + id + "'", CourseRegistration.findCourseRegistration(id));
    }
}
