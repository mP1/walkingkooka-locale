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

import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.text.CharSequences;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public interface CanDecimalNumberSymbolsForLocale {

    /**
     * Returns the {@link DecimalNumberSymbols} if available for the given {@link Locale}.
     */
    Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale);

    /**
     * Attempts to fetch the {@link DecimalNumberSymbols} for the given {@link Locale}, throwing a {@link IllegalArgumentException}
     * when missing.
     */
    default DecimalNumberSymbols decimalNumberSymbolsForLocaleOrFail(final Locale locale) {
        Objects.requireNonNull(locale, "locale");

        // Missing DecimalNumberSymbols for "en-AU"
        return this.decimalNumberSymbolsForLocale(locale)
            .orElseThrow(() -> new IllegalArgumentException(
                "Missing " + DecimalNumberSymbols.class.getSimpleName() + " for " + CharSequences.quoteAndEscape(locale.toLanguageTag()))
            );
    }
}
