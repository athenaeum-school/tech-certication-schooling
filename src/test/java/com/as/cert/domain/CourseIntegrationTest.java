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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
@RooIntegrationTest(entity = Course.class)
public class CourseIntegrationTest {

	@Test
	public void testMarkerMethod() {
	}

	@Autowired
    CourseDataOnDemand dod;

	@Test
    public void testCountCourses() {
        Assert.assertNotNull("Data on demand for 'Course' failed to initialize correctly", dod.getRandomCourse());
        long count = Course.countCourses();
        Assert.assertTrue("Counter for 'Course' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindCourse() {
        Course obj = dod.getRandomCourse();
        Assert.assertNotNull("Data on demand for 'Course' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Course' failed to provide an identifier", id);
        obj = Course.findCourse(id);
        Assert.assertNotNull("Find method for 'Course' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Course' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllCourses() {
        Assert.assertNotNull("Data on demand for 'Course' failed to initialize correctly", dod.getRandomCourse());
        long count = Course.countCourses();
        Assert.assertTrue("Too expensive to perform a find all test for 'Course', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Course> result = Course.findAllCourses();
        Assert.assertNotNull("Find all method for 'Course' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Course' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindCourseEntries() {
        Assert.assertNotNull("Data on demand for 'Course' failed to initialize correctly", dod.getRandomCourse());
        long count = Course.countCourses();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Course> result = Course.findCourseEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Course' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Course' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Course obj = dod.getRandomCourse();
        Assert.assertNotNull("Data on demand for 'Course' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Course' failed to provide an identifier", id);
        obj = Course.findCourse(id);
        Assert.assertNotNull("Find method for 'Course' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyCourse(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Course' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Course obj = dod.getRandomCourse();
        Assert.assertNotNull("Data on demand for 'Course' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Course' failed to provide an identifier", id);
        obj = Course.findCourse(id);
        boolean modified =  dod.modifyCourse(obj);
        Integer currentVersion = obj.getVersion();
        Course merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Course' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Course' failed to initialize correctly", dod.getRandomCourse());
        Course obj = dod.getNewTransientCourse(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Course' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Course' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Course' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Course obj = dod.getRandomCourse();
        Assert.assertNotNull("Data on demand for 'Course' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Course' failed to provide an identifier", id);
        obj = Course.findCourse(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Course' with identifier '" + id + "'", Course.findCourse(id));
    }
}
