// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.as.cert.domain;

import com.as.cert.domain.CourseDetail;
import com.as.cert.domain.CourseDetailDataOnDemand;
import com.as.cert.domain.CourseDetailIntegrationTest;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect CourseDetailIntegrationTest_Roo_IntegrationTest {
    
    declare @type: CourseDetailIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: CourseDetailIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: CourseDetailIntegrationTest: @Transactional;
    
    @Autowired
    CourseDetailDataOnDemand CourseDetailIntegrationTest.dod;
    
    @Test
    public void CourseDetailIntegrationTest.testCountCourseDetails() {
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to initialize correctly", dod.getRandomCourseDetail());
        long count = CourseDetail.countCourseDetails();
        Assert.assertTrue("Counter for 'CourseDetail' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void CourseDetailIntegrationTest.testFindCourseDetail() {
        CourseDetail obj = dod.getRandomCourseDetail();
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to provide an identifier", id);
        obj = CourseDetail.findCourseDetail(id);
        Assert.assertNotNull("Find method for 'CourseDetail' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'CourseDetail' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void CourseDetailIntegrationTest.testFindAllCourseDetails() {
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to initialize correctly", dod.getRandomCourseDetail());
        long count = CourseDetail.countCourseDetails();
        Assert.assertTrue("Too expensive to perform a find all test for 'CourseDetail', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<CourseDetail> result = CourseDetail.findAllCourseDetails();
        Assert.assertNotNull("Find all method for 'CourseDetail' illegally returned null", result);
        Assert.assertTrue("Find all method for 'CourseDetail' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void CourseDetailIntegrationTest.testFindCourseDetailEntries() {
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to initialize correctly", dod.getRandomCourseDetail());
        long count = CourseDetail.countCourseDetails();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<CourseDetail> result = CourseDetail.findCourseDetailEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'CourseDetail' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'CourseDetail' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void CourseDetailIntegrationTest.testFlush() {
        CourseDetail obj = dod.getRandomCourseDetail();
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to provide an identifier", id);
        obj = CourseDetail.findCourseDetail(id);
        Assert.assertNotNull("Find method for 'CourseDetail' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyCourseDetail(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'CourseDetail' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void CourseDetailIntegrationTest.testMergeUpdate() {
        CourseDetail obj = dod.getRandomCourseDetail();
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to provide an identifier", id);
        obj = CourseDetail.findCourseDetail(id);
        boolean modified =  dod.modifyCourseDetail(obj);
        Integer currentVersion = obj.getVersion();
        CourseDetail merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'CourseDetail' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void CourseDetailIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to initialize correctly", dod.getRandomCourseDetail());
        CourseDetail obj = dod.getNewTransientCourseDetail(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'CourseDetail' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'CourseDetail' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void CourseDetailIntegrationTest.testRemove() {
        CourseDetail obj = dod.getRandomCourseDetail();
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CourseDetail' failed to provide an identifier", id);
        obj = CourseDetail.findCourseDetail(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'CourseDetail' with identifier '" + id + "'", CourseDetail.findCourseDetail(id));
    }
    
}
