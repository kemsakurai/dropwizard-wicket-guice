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

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import xyz.monotalk.dropwizard.wicket.pages.SamplePage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see xyz.monotalk.dropwizard.wicket.SampleApplication#main(String[])
 * @author Kem
 */
public class WicketApplication extends WebApplication {

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        // GuiceComponentInjector
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, InjectorHolder.getInjector()));
        mountPage("/sample", SamplePage.class);
    }

    @Override
    public RuntimeConfigurationType getConfigurationType() {
        //filterのinit-parameterでconfigurationに「development」と指定したときだけ、developmentモードで起動します。
        //それ以外のケースでは常にdeploymentモードで起動します。
        boolean isDevelopment = Boolean.parseBoolean(getServletContext().getInitParameter("isDevelopment"));
        if (isDevelopment) {
            return RuntimeConfigurationType.DEVELOPMENT;
        }
        return RuntimeConfigurationType.DEPLOYMENT;
    }

    /**
     * @return @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return SamplePage.class;
    }
}
