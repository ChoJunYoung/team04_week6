package cnu.lecture;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.GreaterThan;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;

import org.hamcrest.core.IsAnything;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by tchi on 2016. 4. 25..
 */
public class InGameSummonerQuerierTest {
    private InGameSummonerQuerier querier;
    private InGameInfo gameInfo;
    InGameInfo.Participant[] participants = new InGameInfo.Participant[10];

    /*
    @Before
    public void setup() throws IOException {
        final String apiKey = "8242f154-342d-4b86-9642-dfa78cdb9d9c";
        GameParticipantListener dontCareListener = mock(GameParticipantListener.class);
        querier = new InGameSummonerQuerier(apiKey, dontCareListener);
    }
    */

    @Before
    public void setupMocking() throws IOException{
        String summonerName = "akane24";
        querier = mock(InGameSummonerQuerier.class);
        gameInfo = mock(InGameInfo.class);
        when(querier.getGameInfo()).thenReturn(gameInfo);
        when(gameInfo.getParticipants()).thenReturn(participants);
        when(querier.queryGameKey(summonerName)).thenReturn("4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM");
    }

    @Test
    public void shouldQuerierIdentifyGameKeyWhenSpecificSummonerNameIsGiven() throws Exception {
        final String summonerName;


        GIVEN:
        {
            summonerName = "akane24";
        }

        final String actualGameKey;
        WHEN:
        {
            actualGameKey = querier.queryGameKey(summonerName);
        }

        final String expectedGameKey = "4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM";
        THEN:
        {
            assertThat(actualGameKey, is(expectedGameKey));
        }
    }

    @Test
    public void shouldQuerierReportMoreThan5Summoners() throws Exception {
        final String summonerName;

        GIVEN:
        {
            summonerName = "akane24";
        }

        WHEN:
        {
            querier.queryGameKey(summonerName);
        }

        THEN:
        {
            int numberOfParticipants = querier.getGameInfo().getParticipants().length;
            assertThat(numberOfParticipants, greaterThan(4));

        }

    }
}