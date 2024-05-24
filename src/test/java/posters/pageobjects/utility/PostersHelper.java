package posters.pageobjects.utility;

import java.time.Duration;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Step;

public class PostersHelper
{
    @Step("wait for element list to have size of {exactSize}' elements")
    public static boolean optionalWaitUntilConditionSizeIs(final ElementsCollection elements, final int exactSize,
                                                           final Duration waitingTime)
    {
        final long sleepTime = waitingTime.toMillis() / 10;
        for (int counter = 0; counter < 10; counter++)
        {
            if (elements.size() == exactSize)
            {
                return true;
            }
            Selenide.sleep(sleepTime);
        }
        return false;
    }
}
