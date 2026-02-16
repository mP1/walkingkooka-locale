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

import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.math.DecimalNumberSymbols;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public interface LocaleContextDelegator extends LocaleContext {

    @Override
    default Set<Locale> availableLocales() {
        return this.localeContext()
            .availableLocales();
    }

    @Override
    default Optional<DateTimeSymbols> dateTimeSymbolsForLocale(final Locale locale) {
        return this.localeContext()
            .dateTimeSymbolsForLocale(locale);
    }

    @Override
    default Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale) {
        return this.localeContext()
            .decimalNumberSymbolsForLocale(locale);
    }

    @Override
    default Set<Locale> findByLocaleText(final String text,
                                         final int offset,
                                         final int count) {
        return this.localeContext()
            .findByLocaleText(
                text,
                offset,
                count
            );
    }

    @Override
    default Locale locale() {
        return this.localeContext()
            .locale();
    }

    @Override
    default Optional<String> localeText(final Locale locale,
                                        final Locale requestedLocale) {
        return this.localeContext()
            .localeText(
                locale,
                requestedLocale
            );
    }

    @Override
    default void setLocale(final Locale locale) {
        this.localeContext()
            .setLocale(locale);
    }

    LocaleContext localeContext();
}
