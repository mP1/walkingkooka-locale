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
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.reflect.PublicClassTesting;
import walkingkooka.reflect.ThrowableTesting;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CanDecimalNumberSymbolsForLocaleTest implements PublicClassTesting<CanDecimalNumberSymbolsForLocale>,
    LocaleContextTesting,
    ThrowableTesting {

    @Test
    public void testDecimalNumberSymbolsForLocaleOrFail() {
        this.getMessageAndCheck(
            assertThrows(
                IllegalArgumentException.class,
                () -> new CanDecimalNumberSymbolsForLocale() {

                    @Override
                    public Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale) {
                        return Optional.empty();
                    }
                }.decimalNumberSymbolsForLocaleOrFail(LOCALE)
            ),
            "Missing DecimalNumberSymbols for \"en-AU\""
        );
    }

    // class............................................................................................................

    @Override
    public Class<CanDecimalNumberSymbolsForLocale> type() {
        return CanDecimalNumberSymbolsForLocale.class;
    }
}
