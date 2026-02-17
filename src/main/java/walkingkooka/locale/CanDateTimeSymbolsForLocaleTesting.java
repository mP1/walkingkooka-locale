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
import walkingkooka.test.Testing;

import java.util.Locale;
import java.util.Optional;

public interface CanDateTimeSymbolsForLocaleTesting extends Testing {

    default void dateTimeSymbolsForLocaleAndCheck(final CanDateTimeSymbolsForLocale can,
                                                  final Locale locale) {
        this.dateTimeSymbolsForLocaleAndCheck(
            can,
            locale,
            Optional.empty()
        );
    }

    default void dateTimeSymbolsForLocaleAndCheck(final CanDateTimeSymbolsForLocale can,
                                                  final Locale locale,
                                                  final DateTimeSymbols expected) {
        this.dateTimeSymbolsForLocaleAndCheck(
            can,
            locale,
            Optional.of(expected)
        );
    }

    default void dateTimeSymbolsForLocaleAndCheck(final CanDateTimeSymbolsForLocale can,
                                                  final Locale locale,
                                                  final Optional<DateTimeSymbols> expected) {
        this.checkEquals(
            expected,
            can.dateTimeSymbolsForLocale(locale),
            () -> "dateTimeSymbolsForLocale " + locale
        );
    }
}
