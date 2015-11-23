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

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.ArrayList;
import java.util.List;

/**
 * InjectorHolder
 * @author Kem
 */
public class InjectorHolder {

    private static Injector injector;

    public synchronized static Injector getInjector() {
        if (injector == null) {
            injector = newInjector();
        }
        return injector;
    }

    private static Injector newInjector() {
        List<AbstractModule> modules = new ArrayList<>();
        modules.add(new AppServletModule());
        return Guice.createInjector(modules);
    }

    public static <T> T new_(Class<T> clazz) {
        return getInjector().getInstance(clazz);
    }

}
