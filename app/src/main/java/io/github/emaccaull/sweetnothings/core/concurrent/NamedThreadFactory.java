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

package io.github.emaccaull.sweetnothings.core.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A nameable thread factory. Each new thread is named "$name #n" where n the the nth created
 * thread.
 */
public final class NamedThreadFactory implements ThreadFactory {
    private final String name;
    private final AtomicInteger count = new AtomicInteger(1);

    public NamedThreadFactory(String name) {
        this.name = checkNotNull(name, "name is null");
    }

    @Override
    public Thread newThread(@NonNull Runnable r) {
        return new Thread(r, name + " #" + count.getAndIncrement());
    }
}
