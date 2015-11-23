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

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * SampleApplication
 * @author Kem
 */
public class SampleApplication extends Application<WicketConfiguration> {

    public static void main(String[] args) throws Exception {
        new SampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "SampleApplication";
    }

    @Override
    public void initialize(Bootstrap<WicketConfiguration> bootstrap) {
        bootstrap.addBundle(new WicketBundle());
    }

    @Override
    public void run(WicketConfiguration t, Environment e) throws Exception {
        // Do Nothing...
    }
}
