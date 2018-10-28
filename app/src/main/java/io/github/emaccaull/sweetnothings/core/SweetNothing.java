/*
 * Copyright (C) 2018 Emmanuel MacCaull
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

package io.github.emaccaull.sweetnothings.core;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a sweet saying to send.
 */
public final class SweetNothing {
    private final String id;
    private final String message;
    private final boolean blacklisted;
    private final boolean used;

    private SweetNothing(Builder builder) {
        this.id = builder.id;
        this.message = builder.message;
        this.blacklisted = builder.blacklisted;
        this.used = builder.used;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

    public boolean isUsed() {
        return used;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof SweetNothing))
            return false;

        SweetNothing that = (SweetNothing) o;
        return blacklisted == that.blacklisted &&
                used == that.used &&
                Objects.equal(id, that.id) &&
                Objects.equal(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, message, blacklisted, used);
    }

    @Override
    public String toString() {
        return "SweetNothing{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", blacklisted=" + blacklisted +
                ", used=" + used +
                '}';
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static Builder builder(SweetNothing seed) {
        return new Builder(seed);
    }

    public static class Builder {
        private String id;
        private String message;
        private boolean blacklisted;
        private boolean used;

        Builder(String id) {
            this.id = checkNotNull(id, "id is null");
        }

        Builder(SweetNothing seed) {
            id = seed.id;
            message = seed.message;
            blacklisted = seed.blacklisted;
            used = seed.used;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder blacklisted(boolean blacklisted) {
            this.blacklisted = blacklisted;
            return this;
        }

        public Builder used(boolean used) {
            this.used = used;
            return this;
        }

        public SweetNothing build() {
            if (Strings.isNullOrEmpty(message)) {
                throw new IllegalStateException("Must specify a message");
            }
            return new SweetNothing(this);
        }
    }
}
