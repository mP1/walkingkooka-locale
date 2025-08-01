/*
 * Copyright 2025 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.locale;

import javaemul.internal.annotations.GwtIncompatible;
import walkingkooka.text.CharSequences;

import java.util.Locale;
import java.util.Optional;

class JreLocaleContextLocaleText extends JreLocaleContextLocaleTextGwt {

    @GwtIncompatible
    static Optional<String> localeText(final Locale locale) {
        final String displayName = locale.getDisplayName();

        return Optional.ofNullable(
                CharSequences.isNullOrEmpty(displayName) ?
                        null :
                        displayName
        );
    }
}
