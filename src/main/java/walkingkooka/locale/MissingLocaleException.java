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

import walkingkooka.text.CharSequences;

import java.util.Objects;

/**
 * The exception that should be thrown when a {@link String} language tag is not found by all {@link CanLocaleForLanguageTag}.
 */
public final class MissingLocaleException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public MissingLocaleException(final String languageTag) {
        super(
            "Missing Locale with language tag " +
                CharSequences.quoteAndEscape(
                    Objects.requireNonNull(languageTag, "languageTag")
                )
        );
        this.languageTag = languageTag;
    }

    public String languageTag() {
        return this.languageTag;
    }

    private final String languageTag;

    // hashCode/equals..................................................................................................

    @Override
    public int hashCode() {
        return this.languageTag.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return this == other ||
            other instanceof MissingLocaleException && this.equals0((MissingLocaleException) other);
    }

    private boolean equals0(final MissingLocaleException other) {
        return this.languageTag.equals(other.languageTag);
    }
}
