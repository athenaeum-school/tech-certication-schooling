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
@RooIntegrationTest(entity = CourseCatalog.class)
public class CourseCatalogIntegrationTest {

	@Test
	public void testMarkerMethod() {
	}

	@Autowired
    CourseCatalogDataOnDemand dod;

	@Test
    public void testCountCourseCatalogs() {
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to initialize correctly", dod.getRandomCourseCatalog());
        long count = CourseCatalog.countCourseCatalogs();
        Assert.assertTrue("Counter for 'CourseCatalog' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindCourseCatalog() {
        CourseCatalog obj = dod.getRandomCourseCatalog();
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to provide an identifier", id);
        obj = CourseCatalog.findCourseCatalog(id);
        Assert.assertNotNull("Find method for 'CourseCatalog' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'CourseCatalog' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllCourseCatalogs() {
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to initialize correctly", dod.getRandomCourseCatalog());
        long count = CourseCatalog.countCourseCatalogs();
        Assert.assertTrue("Too expensive to perform a find all test for 'CourseCatalog', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<CourseCatalog> result = CourseCatalog.findAllCourseCatalogs();
        Assert.assertNotNull("Find all method for 'CourseCatalog' illegally returned null", result);
        Assert.assertTrue("Find all method for 'CourseCatalog' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindCourseCatalogEntries() {
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to initialize correctly", dod.getRandomCourseCatalog());
        long count = CourseCatalog.countCourseCatalogs();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<CourseCatalog> result = CourseCatalog.findCourseCatalogEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'CourseCatalog' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'CourseCatalog' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        CourseCatalog obj = dod.getRandomCourseCatalog();
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to provide an identifier", id);
        obj = CourseCatalog.findCourseCatalog(id);
        Assert.assertNotNull("Find method for 'CourseCatalog' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyCourseCatalog(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'CourseCatalog' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        CourseCatalog obj = dod.getRandomCourseCatalog();
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to provide an identifier", id);
        obj = CourseCatalog.findCourseCatalog(id);
        boolean modified =  dod.modifyCourseCatalog(obj);
        Integer currentVersion = obj.getVersion();
        CourseCatalog merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'CourseCatalog' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to initialize correctly", dod.getRandomCourseCatalog());
        CourseCatalog obj = dod.getNewTransientCourseCatalog(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'CourseCatalog' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'CourseCatalog' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        CourseCatalog obj = dod.getRandomCourseCatalog();
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'CourseCatalog' failed to provide an identifier", id);
        obj = CourseCatalog.findCourseCatalog(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'CourseCatalog' with identifier '" + id + "'", CourseCatalog.findCourseCatalog(id));
    }
}
