package com.accenture;


import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


@Mojo(name = "copy-spring-profile-properties-goal")
public class CopyProfileProperties extends AbstractMojo {

    private static final String PROFILE_PROD = "prod";
    private static final String PROJECT_BASE_DIR = "project.basedir";
    private static final String PROJECT_RESOURCES_DIR = "/src/main/resources";

    @Parameter(defaultValue = PROFILE_PROD, required = true)
    private String springProfile;
    @Parameter(defaultValue = "application.properties", required = true)
    private String fileProperties;
    @Parameter(defaultValue = "${"+PROJECT_BASE_DIR+"}"+PROJECT_RESOURCES_DIR, required = true )
    private File projectBuildOutputDirectory;

    public void execute()
            throws MojoExecutionException {

        File file = null;

        if (!getSpringProfile().equals(PROFILE_PROD)) {
            file = new File(projectBuildOutputDirectory.getAbsolutePath()+"/"+ springProfile + "/" + fileProperties);

            if ( file.exists() ) {
                Path from = file.toPath();
                Path to   = new File(projectBuildOutputDirectory.getAbsolutePath()+"/"+fileProperties).toPath();

                try {
                    Files.copy(from,to, StandardCopyOption.REPLACE_EXISTING);

                } catch (IOException e) {
                    throw new MojoExecutionException("Error overriding file " , e);
                }
            } else {
                throw new MojoExecutionException("Error file doesn´t exist: "+file.getAbsolutePath() );
            }
        }
    }


    public String getSpringProfile() {
        if(StringUtils.isEmpty(springProfile)){
            springProfile = PROFILE_PROD;
        }

        return springProfile;
    }


}
