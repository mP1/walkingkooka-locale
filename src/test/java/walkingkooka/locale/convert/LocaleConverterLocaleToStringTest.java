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

package walkingkooka.locale.convert;

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;

import java.util.Locale;

public final class LocaleConverterLocaleToStringTest extends LocaleConverterTestCase<LocaleConverterLocaleToString<LocaleConverterContext>> {

    @Test
    public void testConvertLocaleToLocaleFails() {
        this.convertFails(
            Locale.FRANCE,
            Locale.class
        );
    }

    @Test
    public void testConvertLocaleToString() {
        final Locale locale = Locale.FRANCE;

        this.convertAndCheck(
            locale,
            locale.toLanguageTag()
        );
    }

    @Override
    public void testToString() {
        this.toStringAndCheck(
            LocaleConverterLocaleToString.instance(),
            "Locale to String"
        );
    }

    @Override
    public LocaleConverterLocaleToString<LocaleConverterContext> createConverter() {
        return LocaleConverterLocaleToString.instance();
    }

    @Override
    public LocaleConverterContext createContext() {
        return LocaleConverterContexts.fake();
    }

    @Override
    public final String typeNamePrefix() {
        return LocaleConverter.class.getSimpleName();
    }

    @Override
    public Class<LocaleConverterLocaleToString<LocaleConverterContext>> type() {
        return Cast.to(LocaleConverterLocaleToString.class);
    }
}
