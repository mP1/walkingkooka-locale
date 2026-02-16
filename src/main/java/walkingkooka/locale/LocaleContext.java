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

import walkingkooka.Context;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.util.HasLocale;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * A {@link Context} with a focus on {@link java.util.Locale} and related locale aware data.
 * This abstraction decouples Locale and locale related values from the JRE.
 */
public interface LocaleContext extends Context,
    HasLocale {

    /**
     * Sets or replaces the current {@link Locale}
     */
    void setLocale(final Locale locale);

    /**
     * Returns all available {@link Locale}.
     */
    Set<Locale> availableLocales();

    /**
     * Returns the {@link DateTimeSymbols} if available for the given {@link Locale}.
     */
    Optional<DateTimeSymbols> dateTimeSymbolsForLocale(final Locale locale);

    /**
     * Returns the {@link DecimalNumberSymbols} if available for the given {@link Locale}.
     */
    Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale);

    /**
     * Returns all {@link Locale} that have display or local text beginning with the given search text.
     */
    Set<Locale> findByLocaleText(final String text,
                                 final int offset,
                                 final int count);

    /**
     * Returns text to display for the given {@link Locale} if it exists.
     * This is necessary because {@link Locale#getDisplayName()} is not implemented in GWT.
     */
    Optional<String> localeText(final Locale locale);

    /**
     * Helper that throws a {@link IllegalArgumentException} if locale text was not found.
     */
    default String localeTextOrFail(final Locale locale) {
        return this.localeText(locale)
            .orElseThrow(() -> new IllegalArgumentException("Locale " + locale + ": missing locale text"));
    }
}
