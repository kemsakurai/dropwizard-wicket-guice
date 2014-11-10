package net.troja.dropwizard.wicket;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.jetty.MutableServletContextHandler;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;
import java.net.URL;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WicketBundle implements ConfiguredBundle<WicketConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WicketBundle.class);
    
    private static final String WEB_INF = "WEB-INF";
    private WebApplication wicketApplication;

    public WicketBundle(Class<? extends WebApplication> wicketApplicationClass) {
	try {
	    this.wicketApplication = wicketApplicationClass.newInstance();
	} catch (Exception e) {
	    LOGGER.error("Could not create wicket application", e);
	}
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(WicketConfiguration configuration, Environment environment) throws Exception {
	FilterHolder filterHolder = getFilterHolder(configuration.isDeployment());
	
	MutableServletContextHandler context = environment.getApplicationContext();
	context.addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
	Resource baseResource = getBaseResource();
	if(baseResource != null) {
	    context.setBaseResource(baseResource);
	}
	context.addServlet(DefaultServlet.class, "/*");
	
	environment.jersey().disable();
    }

    private Resource getBaseResource() {
	URL resource = Thread.currentThread().getContextClassLoader().getResource(WEB_INF);
	try {
	    return Resource.newResource(resource.toString().replace(WEB_INF, ""));
	} catch (IOException e) {
	    LOGGER.error("Could not set base resource of servlet", e);
	    return null;
	}
    }

    private FilterHolder getFilterHolder(boolean isDeployment) {
	FilterHolder filterHolder = new FilterHolder(new WicketFilter(wicketApplication));
	filterHolder.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
	if(isDeployment) {
	    filterHolder.setInitParameter("wicket.configuration", "deployment");
	}
	return filterHolder;
    }
}
