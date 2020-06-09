/*
 * Copyright (C) 2020 Emmanuel MacCaull
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

package io.github.emaccaull.sweetnothings.init;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import io.github.emaccaull.sweetnothings.data.init.DataSourceInitializationTask;

import javax.inject.Singleton;

/**
 * Makes initialization tasks available to the task runner.
 * TODO(emmanuel): determine if this module should be split into several modules (one for each task)
 *
 * @see InitializationTasksPluginImpl
 */
@Module
public abstract class InitializationTasksModule {

    @Binds
    @IntoSet
    abstract InitializationTask bindDataSourceInitializationTask(DataSourceInitializationTask task);

    @Singleton
    @Binds
    abstract InitializationTasksPlugin provideInitializationTasksPlugin(
            InitializationTasksPluginImpl impl);
}
