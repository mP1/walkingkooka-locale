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
import walkingkooka.ContextTesting;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface LocaleContextTesting2<C extends LocaleContext> extends LocaleContextTesting, ContextTesting<C> {

    @Test
    default void testDateTimeSymbolsForLocaleWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .dateTimeSymbolsForLocale(null)
        );
    }

    @Test
    default void testDecimalNumberSymbolsForLocaleWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .decimalNumberSymbolsForLocale(null)
        );
    }

    @Test
    default void testFindByLocaleTextWithNegativeOffsetFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createContext()
                .findByLocaleText(
                    "text",
                    -1,
                    1,
                    Locale.ENGLISH
                )
        );
    }

    @Test
    default void testFindByLocaleTextWithInvalidCountFails() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.createContext()
                .findByLocaleText(
                    "text",
                    0,
                    -1,
                    Locale.ENGLISH
                )
        );
    }

    @Test
    default void testFindByLocaleTextWithNullTextFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .findByLocaleText(
                    null,
                    0,
                    1,
                    Locale.ENGLISH
                )
        );
    }

    @Test
    default void testFindByLocaleTextWithNullLocaleFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .findByLocaleText(
                    "",
                    0,
                    1,
                    null
                )
        );
    }

    @Test
    default void testLocaleTextWithNullLocaleFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .localeText(
                    null,
                    Locale.ENGLISH
                )
        );
    }

    @Test
    default void testLocaleTextWithNullRequestedLocaleFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .localeText(
                    Locale.ENGLISH,
                    null
                )
        );
    }

    @Test
    default void testSetLocaleWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createContext()
                .setLocale(null)
        );
    }

    @Override
    default String typeNameSuffix() {
        return LocaleContext.class.getSimpleName();
    }
}
