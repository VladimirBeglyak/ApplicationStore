package by.beglyakdehterenok.store.config;

import by.beglyakdehterenok.store.config.DatabaseConfig;
import by.beglyakdehterenok.store.config.WebConfig;
import by.beglyakdehterenok.store.config.WebSecurityConfig;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

public class WebAppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

//    private String TMP_FOLDER="/tmp";
//    private int MAX_UPLOAD_SIZE=5*1024*1024;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {DatabaseConfig.class, WebSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return new Filter[]{filter};
    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        ServletRegistration.Dynamic appServlet = servletContext.
//                addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
//
//        appServlet.setLoadOnStartup(1);
//
//        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
//
//        appServlet.setMultipartConfig(multipartConfigElement);
//    }
}
