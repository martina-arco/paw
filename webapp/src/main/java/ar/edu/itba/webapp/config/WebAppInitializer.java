package ar.edu.itba.webapp.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(WebConfig.class, WebAuthConfig.class);

        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        filterRegistration.addMappingForUrlPatterns(null, true, "/api/v1/*");

        servletContext.setInitParameter("contextConfigLocation", "<NONE>");
        servletContext.addListener(new ContextLoaderListener(appContext));

        ServletContainer jerseyServlet = new ServletContainer();
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("jersey-servlet", jerseyServlet);

        servletRegistration.setInitParameter("jersey.config.server.provider.packages", "ar.edu.itba.webapp.controllers");
        servletRegistration.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/api/v1/*");
    }

}