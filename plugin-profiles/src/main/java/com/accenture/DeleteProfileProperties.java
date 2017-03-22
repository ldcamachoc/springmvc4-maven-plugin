package com.accenture;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

@Mojo(name = "delete-profile-files-goal")
public class DeleteProfileProperties extends AbstractMojo {

    private static final String PROFILE_DEV = "dev";
    private static final String PROFILE_TEST = "test";

    private static final String PROJECT_BUILD_OUTPUT_DIRECTORY = "project.build.outputDirectory";

    @Parameter(defaultValue = "${"+PROJECT_BUILD_OUTPUT_DIRECTORY+"}")
    private File projectBuildOutputDirectory;
    @Parameter(defaultValue = "true", required = true)
    private boolean deleteProperties;
    @Parameter
    private List<String> profiles;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        if (deleteProperties) {
            String[] listEntries = projectBuildOutputDirectory.list();

            for (String entry : listEntries) {
                File currentFile = new File(projectBuildOutputDirectory.getPath(), entry);

                if (currentFile.isDirectory() && getProfiles().contains(currentFile.getName())) {
                    Path directory = currentFile.toPath();

                    try {
                        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                            @Override
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                Files.delete(file);
                                return FileVisitResult.CONTINUE;
                            }

                            @Override
                            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                                Files.delete(dir);
                                return FileVisitResult.CONTINUE;
                            }

                        });
                    } catch (IOException exception) {
                        System.out.println("Error deleting file" + directory);
                    }
                }
            }
        }
    }

    public List<String> getProfiles() {
        if (profiles == null) {
            profiles = new ArrayList<String>();

            profiles.add(PROFILE_DEV);
            profiles.add(PROFILE_TEST);
        }
        return profiles;
    }
}
