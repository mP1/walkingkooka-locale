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

import org.junit.jupiter.api.Test;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.reflect.PublicClassTesting;
import walkingkooka.reflect.ThrowableTesting;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CanDateTimeSymbolsForLocaleTest implements PublicClassTesting<CanDateTimeSymbolsForLocale>,
    LocaleContextTesting,
    ThrowableTesting {

    @Test
    public void testDateTimeSymbolsForLocaleOrFail() {
        this.getMessageAndCheck(
            assertThrows(
                IllegalArgumentException.class,
                () -> new CanDateTimeSymbolsForLocale() {
                    @Override
                    public Optional<DateTimeSymbols> dateTimeSymbolsForLocale(Locale locale) {
                        return Optional.empty();
                    }
                }.dateTimeSymbolsForLocaleOrFail(LOCALE)
            ),
            "Missing DateTimeSymbols for \"en-AU\""
        );
    }

    // class............................................................................................................

    @Override
    public Class<CanDateTimeSymbolsForLocale> type() {
        return CanDateTimeSymbolsForLocale.class;
    }
}
