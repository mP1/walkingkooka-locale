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

import walkingkooka.collect.set.ImmutableSortedSet;
import walkingkooka.collect.set.SortedSets;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.text.CharSequences;

import java.text.DateFormatSymbols;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

/**
 * A {@link LocaleContext} that sources its data from the running JRE querying various {@link Locale} methods.
 */
final class JreLocaleContext implements LocaleContext {

    /**
     * Factory that creates a {@link JreLocaleContext}.
     */
    static JreLocaleContext with(final Locale locale) {
        return new JreLocaleContext(
            Objects.requireNonNull(locale, "locale")
        );
    }

    private JreLocaleContext(final Locale locale) {
        super();
        this.locale = locale;
    }

    @Override
    public Set<Locale> availableLocales() {
        if (null == this.availableLocales) {
            final SortedSet<Locale> locales = SortedSets.tree(LocaleContexts.LANGUAGE_TAG_COMPARATOR);
            locales.addAll(
                Arrays.asList(
                    Locale.getAvailableLocales()
                )
            );
            this.availableLocales = SortedSets.immutable(locales);
        }

        return this.availableLocales;
    }

    private Set<Locale> availableLocales;

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
    public Set<Locale> findByLocaleText(final String text,
                                        final int offset,
                                        final int count) {
        Objects.requireNonNull(text, "text");
        if (offset < 0) {
            throw new IllegalArgumentException("Invalid offset < 0");
        }
        if (count < 0) {
            throw new IllegalArgumentException("Invalid count < 0");
        }

        return this.availableLocales()
            .stream()
            .filter(locale -> {
                final String localeText = this.localeText(locale)
                    .orElse(null);
                return false == CharSequences.isNullOrEmpty(localeText) &&
                    LocaleContexts.CASE_SENSITIVITY.startsWith(
                        localeText,
                        text
                    );
            })
            .skip(offset)
            .limit(count)
            .collect(
                ImmutableSortedSet.collector(LocaleContexts.LANGUAGE_TAG_COMPARATOR)
            );
    }

    @Override
    public Locale locale() {
        return this.locale;
    }

    @Override
    public void setLocale(final Locale locale) {
        this.locale = Objects.requireNonNull(locale, "locale");
    }

    private Locale locale;

    @Override
    public Optional<String> localeText(final Locale locale) {
        Objects.requireNonNull(locale, "locale");

        return JreLocaleContextLocaleText.localeText(locale);
    }

    @Override
    public String toString() {
        return "JRE " + this.locale.toLanguageTag();
    }
}
