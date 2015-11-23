/*
 * Copyright 2015 Kem.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.monotalk.dropwizard.wicket;

import com.google.inject.servlet.GuiceFilter;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.jetty.MutableServletContextHandler;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;

/**
 * WicketBundle
 * @author Kem
**/
public class WicketBundle implements ConfiguredBundle<WicketConfiguration> {

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    
    }

    @Override
    public void run(WicketConfiguration configuration, Environment environment) throws Exception {
        FilterHolder filterHolder = getFilterHolder(configuration.isDeployment());
        MutableServletContextHandler context = environment.getApplicationContext();
        context.addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
        context.addEventListener(new GuiceServletConfig());
        context.addServlet(DefaultServlet.class, "/*");
        environment.servlets().setSessionHandler(new SessionHandler());
        environment.jersey().disable();
    }
    
    private FilterHolder getFilterHolder(boolean isDeployment) {
        FilterHolder filterHolder = new FilterHolder(new GuiceFilter());
        filterHolder.setName("Guice Filter");
        if (isDeployment) {
            filterHolder.setInitParameter("wicket.configuration", "deployment");
        }
        return filterHolder;
    }
}
