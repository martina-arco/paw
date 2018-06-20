package ar.edu.itba.webapp.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(WebConfig.class, WebAuthConfig.class);

        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        filterRegistration.addMappingForUrlPatterns(null, true, "/*");

        servletContext.addListener(new ContextLoaderListener(appContext));

        DispatcherServlet servlet = new DispatcherServlet(new AnnotationConfigWebApplicationContext());
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dispatcher", servlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}