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
import walkingkooka.collect.set.Sets;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class JreLocaleContextTest implements LocaleContextTesting2<JreLocaleContext> {

    private final static Locale LOCALE = Locale.forLanguageTag("EN-AU");

    @Test
    public void testWithNullLocaleFails() {
        assertThrows(
                NullPointerException.class,
                () -> JreLocaleContext.with(null)
        );
    }

    @Test
    public void testAvailableLocales() {
        this.checkNotEquals(
                Sets.empty(),
                this.createContext()
                        .availableLocales()
        );
    }

    @Test
    public void testLocaleText() {
        for(final Locale locale : Locale.getAvailableLocales()) {
            final JreLocaleContext context = JreLocaleContext.with(locale);
            this.checkNotEquals(
                    Optional.of(""),
                    context.localeText(locale),
                    locale::toLanguageTag
            );
        }
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                JreLocaleContext.with(LOCALE),
                "JRE en-AU"
        );
    }

    @Override
    public JreLocaleContext createContext() {
        return JreLocaleContext.with(LOCALE);
    }

    @Override
    public Class<JreLocaleContext> type() {
        return JreLocaleContext.class;
    }
}
