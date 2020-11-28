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
package io.github.emaccaull.sweetnothings.ui.util

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

/**
 * Utilities for managing Fragments.
 */
object FragmentUtils {
    /**
     * Removes any fragments with the given `tag`, then shows `dialog` with the given
     * `tag`.
     */
    @JvmStatic
    fun showDialog(fragmentManager: FragmentManager, dialog: DialogFragment, tag: String?) {
        val fragment = fragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            fragmentManager.commit { remove(fragment) }
        }
        dialog.show(fragmentManager, tag)
    }
}