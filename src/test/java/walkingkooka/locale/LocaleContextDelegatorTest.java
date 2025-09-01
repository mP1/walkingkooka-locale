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

import walkingkooka.locale.LocaleContextDelegatorTest.TestLocaleContext;

import java.util.Locale;
import java.util.Objects;

public final class LocaleContextDelegatorTest implements LocaleContextTesting2<TestLocaleContext> {

    @Override
    public TestLocaleContext createContext() {
        return new TestLocaleContext();
    }

    @Override
    public Class<TestLocaleContext> type() {
        return TestLocaleContext.class;
    }

    static class TestLocaleContext implements LocaleContextDelegator {

        @Override
        public LocaleContext setLocale(final Locale locale) {
            Objects.requireNonNull(locale, "locale");
            throw new UnsupportedOperationException();
        }

        @Override
        public LocaleContext localeContext() {
            return LocaleContexts.jre(
                Locale.forLanguageTag("EN-AU")
            );
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }
}
