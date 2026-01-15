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
import walkingkooka.text.CharSequences;
import walkingkooka.text.printer.TreePrintableTesting;
import walkingkooka.util.HasLocaleTesting;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public interface LocaleContextTesting extends HasLocaleTesting,
    TreePrintableTesting {

    default void availableLocalesAndCheck(final LocaleContext context,
                                          final Locale... expected) {
        this.availableLocalesAndCheck(
            context,
            Sets.of(expected)
        );
    }

    default void availableLocalesAndCheck(final LocaleContext context,
                                          final Set<Locale> expected) {
        this.checkEquals(
            expected,
            context.availableLocales(),
            context::toString
        );
    }

    default void dateTimeSymbolsForLocale(final LocaleContext context,
                                          final Locale locale) {
        this.dateTimeSymbolsForLocale(
            context,
            locale,
            Optional.empty()
        );
    }

    default void dateTimeSymbolsForLocale(final LocaleContext context,
                                          final Locale locale,
                                          final DateTimeSymbols expected) {
        this.dateTimeSymbolsForLocale(
            context,
            locale,
            Optional.of(expected)
        );
    }

    default void dateTimeSymbolsForLocale(final LocaleContext context,
                                          final Locale locale,
                                          final Optional<DateTimeSymbols> expected) {
        this.checkEquals(
            expected,
            context.decimalNumberSymbolsForLocale(locale),
            () -> "dateTimeSymbolsForLocale " + locale
        );
    }

    default void decimalNumberSymbolsForLocale(final LocaleContext context,
                                               final Locale locale) {
        this.decimalNumberSymbolsForLocale(
            context,
            locale,
            Optional.empty()
        );
    }

    default void decimalNumberSymbolsForLocale(final LocaleContext context,
                                               final Locale locale,
                                               final DecimalNumberSymbols expected) {
        this.decimalNumberSymbolsForLocale(
            context,
            locale,
            Optional.of(expected)
        );
    }

    default void decimalNumberSymbolsForLocale(final LocaleContext context,
                                               final Locale locale,
                                               final Optional<DecimalNumberSymbols> expected) {
        this.checkEquals(
            expected,
            context.decimalNumberSymbolsForLocale(locale),
            () -> "decimalNumberSymbolsForLocale " + locale
        );
    }

    // findByLocaleTextAndCheck.........................................................................................

    default void findByLocaleTextAndCheck(final LocaleContext context,
                                          final String text,
                                          final int offset,
                                          final int count,
                                          final Locale... expected) {
        this.findByLocaleTextAndCheck(
            context,
            text,
            offset,
            count,
            Sets.of(expected)
        );
    }

    default void findByLocaleTextAndCheck(final LocaleContext context,
                                          final String text,
                                          final int offset,
                                          final int count,
                                          final Set<Locale> expected) {
        this.checkEquals(
            expected,
            context.findByLocaleText(
                text,
                offset,
                count
            ),
            () -> "findByLocaleText " + CharSequences.quoteAndEscape(text) + " offset=" + offset + " count=" + count
        );
    }

    // setLocale..........................................................................................................

    default void setLocaleAndCheck(final LocaleContext context,
                                   final Locale locale) {
        context.setLocale(locale);

        this.localeAndCheck(
            context,
            locale
        );
    }

    // localeText.......................................................................................................

    default void localeTextAndCheck(final LocaleContext context,
                                    final Locale locale) {
        this.localeTextAndCheck(
            context,
            locale,
            Optional.empty()
        );
    }

    default void localeTextAndCheck(final LocaleContext context,
                                    final Locale locale,
                                    final String expected) {
        this.localeTextAndCheck(
            context,
            locale,
            Optional.of(expected)
        );
    }

    default void localeTextAndCheck(final LocaleContext context,
                                    final Locale locale,
                                    final Optional<String> expected) {
        this.checkEquals(
            expected,
            context.localeText(locale),
            () -> "localeText " + locale
        );
    }
}
