/*
 * Copyright 2014 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.aeron;

import uk.co.real_logic.aeron.driver.MediaDriver;

import java.util.function.BooleanSupplier;
import java.util.function.IntConsumer;

public class SystemTestHelper
{
    public static void executeUntil(final BooleanSupplier condition, final IntConsumer body,
                                    final int maxIterations, final long timeout)
    {
        final long start = System.nanoTime();
        long end;
        int iteration = 0;

        do
        {
            body.accept(iteration);
            end = System.nanoTime();
        }
        while (!condition.getAsBoolean() && ((end - start) < timeout) && iteration++ < maxIterations);
    }

    public static void shutdownAndClose(final Aeron aeron) throws Exception
    {
        if (null != aeron)
        {
            aeron.shutdown();
            aeron.close();
        }
    }

    public static void shutdownAndClose(final MediaDriver driver) throws Exception
    {
        if (null != driver)
        {
            driver.shutdown();
            driver.close();
        }
    }
}