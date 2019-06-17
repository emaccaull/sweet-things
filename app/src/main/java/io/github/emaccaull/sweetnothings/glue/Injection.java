/*
 * Copyright (C) 2019 Emmanuel MacCaull
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

package io.github.emaccaull.sweetnothings.glue;

import io.github.emaccaull.sweetnothings.core.SchedulerProvider;
import io.github.emaccaull.sweetnothings.core.usecase.GetRandomSweetNothing;
import io.github.emaccaull.sweetnothings.core.usecase.GetSweetNothing;
import io.github.emaccaull.sweetnothings.core.usecase.MarkUsed;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Manages component dependencies to allow easy object creation.
 */
public final class Injection {

    private static volatile Injection INSTANCE = new Injection();

    private Configuration config;

    public static Injection getInstance() {
        return INSTANCE;
    }

    public static GetRandomSweetNothing provideGetRandomSweetNothing() {
        Configuration config = INSTANCE.getConfiguration();
        return new GetRandomSweetNothing(config.messageDataSource());
    }

    public static GetSweetNothing provideGetSweetNothing() {
        Configuration config = INSTANCE.getConfiguration();
        return new GetSweetNothing(config.messageDataSource());
    }

    public static MarkUsed provideMarkUsed() {
        Configuration config = INSTANCE.getConfiguration();
        return new MarkUsed(config.messageDataSource());
    }

    public static SchedulerProvider provideSchedulerProvider() {
        Configuration config = INSTANCE.getConfiguration();
        return config.schedulerProvider();
    }

    public void setConfiguration(Configuration config) {
        this.config = new InstanceCachingConfiguration(config);
    }

    Configuration getConfiguration() {
        return checkNotNull(config, "config is null");
    }

    private Injection() {
    }
}
