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

import walkingkooka.collect.set.Sets;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.math.DecimalNumberSymbols;

import java.text.DateFormatSymbols;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * A {@link LocaleContext} that sources its data from the running JRE querying various {@link Locale} methods.
 */
final class JreLocaleContext implements LocaleContext {

    /**
     * Singleton
     */
    final static JreLocaleContext INSTANCE = new JreLocaleContext();

    private JreLocaleContext() {
        super();
    }

    @Override
    public Set<Locale> availableLocales() {
        return Sets.of(
                Locale.getAvailableLocales()
        );
    }

    @Override
    public Optional<DateTimeSymbols> dateTimeSymbolsForLocale(Locale locale) {
        return Optional.of(
                DateTimeSymbols.fromDateFormatSymbols(
                        new DateFormatSymbols(locale)
                )
        );
    }

    @Override
    public Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale) {
        return Optional.of(
                DecimalNumberSymbols.fromDecimalFormatSymbols(
                        '+',
                        new DecimalFormatSymbols(locale)
                )
        );
    }

    @Override
    public Locale locale() {
        return Locale.getDefault();
    }

    @Override
    public String toString() {
        return "JRE";
    }
}
