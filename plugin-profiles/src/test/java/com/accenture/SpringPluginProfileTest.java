package com.accenture;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

/**
 * Created by l.camacho.carbajal on 3/14/2017.
 */
public class SpringPluginProfileTest extends AbstractMojoTestCase {


    public void testCopyProfileProperties()
            throws Exception {
        File pom = getTestFile("src/test/resources/pom1.xml");
        Assert.assertNotNull(pom);
        Assert.assertTrue(pom.exists());

        CopyProfileProperties myMojo = (CopyProfileProperties) lookupMojo("copy-spring-profile-properties-goal",pom);
        Assert.assertNotNull(myMojo);
        myMojo.execute();
    }


    public void testDeleteProfileProperties() throws Exception {
        File pom = getTestFile("src/test/resources/pom2.xml");
        Assert.assertNotNull(pom);
        Assert.assertTrue(pom.exists());

        DeleteProfileProperties myMojo = (DeleteProfileProperties) lookupMojo("delete-profile-files-goal",pom);
        Assert.assertNotNull(myMojo);
        myMojo.execute();
    }

}
