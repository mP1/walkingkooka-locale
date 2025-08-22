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
import walkingkooka.convert.ConverterContext;
import walkingkooka.convert.ConverterContexts;
import walkingkooka.convert.Converters;
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.math.DecimalNumberContexts;

import java.math.MathContext;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class BasicLocaleConverterContextTest implements LocaleConverterContextTesting<BasicLocaleConverterContext> {

    private final static Locale LOCALE = Locale.ENGLISH;

    private final static ConverterContext CONTEXT = ConverterContexts.basic(
        Converters.EXCEL_1904_DATE_SYSTEM_OFFSET,
        Converters.simple(),
        DateTimeContexts.basic(
            DateTimeSymbols.fromDateFormatSymbols(
                new DateFormatSymbols(LOCALE)
            ),
            LOCALE,
            1950,
            50,
            LocalDateTime::now
        ),
        DecimalNumberContexts.american(MathContext.DECIMAL32)
    );

    @Test
    public void testWithNullContextFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicLocaleConverterContext.with(null)
        );
    }

    @Override
    public BasicLocaleConverterContext createContext() {
        return BasicLocaleConverterContext.with(CONTEXT);
    }

    @Override
    public MathContext mathContext() {
        return CONTEXT.mathContext();
    }

    @Override
    public String currencySymbol() {
        return CONTEXT.currencySymbol();
    }

    @Override
    public char decimalSeparator() {
        return CONTEXT.decimalSeparator();
    }

    @Override
    public String exponentSymbol() {
        return CONTEXT.exponentSymbol();
    }

    @Override
    public char groupSeparator() {
        return CONTEXT.groupSeparator();
    }

    @Override
    public String infinitySymbol() {
        return CONTEXT.infinitySymbol();
    }

    @Override
    public char monetaryDecimalSeparator() {
        return CONTEXT.monetaryDecimalSeparator();
    }

    @Override
    public String nanSymbol() {
        return CONTEXT.nanSymbol();
    }

    @Override
    public char percentSymbol() {
        return CONTEXT.percentSymbol();
    }

    @Override
    public char permillSymbol() {
        return CONTEXT.permillSymbol();
    }

    @Override
    public char negativeSign() {
        return CONTEXT.negativeSign();
    }

    @Override
    public char positiveSign() {
        return CONTEXT.positiveSign();
    }

    @Override
    public char zeroDigit() {
        return CONTEXT.zeroDigit();
    }

    // class............................................................................................................

    @Override
    public Class<BasicLocaleConverterContext> type() {
        return BasicLocaleConverterContext.class;
    }
}
