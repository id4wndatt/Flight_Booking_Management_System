package com.dnd.fbs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "airlineCompany-photos";
        Path photosDir = Paths.get(dirName);

        String photosPath = photosDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/admin/" + dirName + "/**")
                .addResourceLocations("file:/" + photosPath + "/");
    }
}
