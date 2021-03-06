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
package xyz.monotalk.dropwizard.wicket.pages;

import javax.inject.Inject;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import xyz.monotalk.dropwizard.wicket.service.IService;

/**
 * SamplePage
 * @author kensakurai
 */
public class SamplePage extends WebPage {

    private static final long serialVersionUID = 8605508706668725437L;
    
    @Inject
    IService service;
    
    @Override
    public void onInitialize() {
    	super.onInitialize();
        add(new Label("message", service.execute()));
    }
}
