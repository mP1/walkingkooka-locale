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
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.reflect.ThrowableTesting2;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MissingLocaleExceptionTest implements ThrowableTesting2<MissingLocaleException> {

    // with.............................................................................................................

    @Test
    public void testWithNullLocaleCodeFails() {
        assertThrows(
            NullPointerException.class,
            () -> new MissingLocaleException(null)
        );
    }

    @Test
    public void testWith() {
        final String languageTag = "AUD";

        this.checkEquals(
            languageTag,
            new MissingLocaleException(languageTag)
                .languageTag()
        );
    }

    // with.............................................................................................................

    @Test
    public void testGetMessage() {
        this.checkMessage(
            new MissingLocaleException("AUD"),
            "Missing Locale with language tag \"AUD\""
        );
    }

    // class............................................................................................................

    @Override
    public void testIfClassIsFinalIfAllConstructorsArePrivate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<MissingLocaleException> type() {
        return MissingLocaleException.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
