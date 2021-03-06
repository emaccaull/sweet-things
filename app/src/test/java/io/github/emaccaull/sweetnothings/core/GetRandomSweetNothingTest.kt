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
package io.github.emaccaull.sweetnothings.core

import io.github.emaccaull.sweetnothings.core.SweetNothing.Companion.builder
import io.github.emaccaull.sweetnothings.core.data.MessageDataSource
import io.github.emaccaull.sweetnothings.core.data.MessageFilter
import io.github.emaccaull.sweetnothings.core.usecase.GetRandomSweetNothing
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Maybe
import org.junit.Test

class GetRandomSweetNothingTest {

    private val messageDataSource = mockk<MessageDataSource>()
    private var getRandomSweetNothing = GetRandomSweetNothing(messageDataSource)

    @Test
    fun apply() {
        // Given that the data source will return a SweetNothing for our filter
        val filter = MessageFilter(includeUsed = false)
        val message = builder("id123").message("howdy").build()
        every { messageDataSource.fetchRandomMessage(filter) } returns Maybe.just(message)

        // When applying the use case
        val observer = getRandomSweetNothing.apply().test()

        // Then the observer should have received the given sweet nothing
        observer.assertValue(message)
    }
}
