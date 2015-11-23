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

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

/**
 * AppServletModule
 * @author kensakurai
 */
public class AppServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        // servlet config
        Map<String, String> wicketFilterParams = new HashMap<>();
        wicketFilterParams.put(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        filter("/*").through(AppWicketFilter.class, wicketFilterParams);
        // bind WicketApplication
        bind(WebApplication.class).to(WicketApplication.class);

    }

    @Singleton
    private static class AppWicketFilter extends WicketFilter {
        
        @Inject
        private WebApplication webApplication;
        
        @Override
        protected IWebApplicationFactory getApplicationFactory() {

            return new IWebApplicationFactory() {
                @Override
                public WebApplication createApplication(WicketFilter filter) {
                    webApplication.setWicketFilter(filter);
                    return webApplication;
                }

                @Override
                public void destroy(WicketFilter filter) {

                }
            };
        }
    }
}
