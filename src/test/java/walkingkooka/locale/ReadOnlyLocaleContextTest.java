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
import walkingkooka.HashCodeEqualsDefinedTesting2;
import walkingkooka.ToStringTesting;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ReadOnlyLocaleContextTest implements LocaleContextTesting2<ReadOnlyLocaleContext>,
    HashCodeEqualsDefinedTesting2<ReadOnlyLocaleContext>,
    ToStringTesting<ReadOnlyLocaleContext> {

    private final static Locale LOCALE = Locale.forLanguageTag("en-AU");

    @Test
    public void testWithNullLocaleFails() {
        assertThrows(
            NullPointerException.class,
            () -> ReadOnlyLocaleContext.with(null)
        );
    }

    @Test
    public void testWithReadOnlyLocale() {
        final ReadOnlyLocaleContext context = this.createContext();
        assertSame(
            context,
            ReadOnlyLocaleContext.with(context)
        );
    }

    // setLocale........................................................................................................

    @Test
    public void testSetLocaleWithDifferentFails() {
        assertThrows(
            UnsupportedOperationException.class,
            () -> this.createContext()
                .setLocale(
                    Locale.FRANCE
                )
        );
    }

    @Test
    public void testSetLocaleWithSame() {
        this.setLocaleAndCheck(
            this.createContext(),
            LOCALE
        );
    }

    @Override
    public ReadOnlyLocaleContext createContext() {
        return ReadOnlyLocaleContext.with(LocaleContexts.jre(LOCALE));
    }

    // hashCode/equals..................................................................................................

    @Test
    public void testEqualsDifferentLocale() {
        final Locale locale = Locale.FRANCE;

        this.checkNotEquals(
            LOCALE,
            locale
        );

        this.checkNotEquals(
            ReadOnlyLocaleContext.with(
                JreLocaleContext.with(locale)
            )
        );
    }

    @Override
    public ReadOnlyLocaleContext createObject() {
        return this.createContext();
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createContext(),
            "ReadOnly JRE en-AU"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ReadOnlyLocaleContext> type() {
        return ReadOnlyLocaleContext.class;
    }
}
