package tr.com.webapp.main;

import javax.faces.webapp.FacesServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.sun.faces.config.ConfigureListener;

@SpringBootApplication
@Configuration
@ComponentScan("tr.com.webapp")
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	// JSF Configration Baslangic
	@Bean
	public ServletRegistrationBean<FacesServlet> facesServletRegistraiton() {
		ServletRegistrationBean<FacesServlet> registration = new ServletRegistrationBean<FacesServlet>(
				new FacesServlet(), new String[] { "*.xhtml" });
		registration.setName("Faces Servlet");
		registration.setLoadOnStartup(1);

		return registration;
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
			
			servletContext.setInitParameter("primefaces.THEME", "bootstrap");
		
			servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
			servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
			servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
		};
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}
}
