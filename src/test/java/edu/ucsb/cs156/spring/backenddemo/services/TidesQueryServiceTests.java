package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

@RestClientTest(TidesQueryService.class)
public class TidesQueryServiceTests {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private TidesQueryService tidesQueryService;

    @Test
    public void test_getJSON() {

        String beginDate = "20120101";
        String endDate = "20120102";
        String station = "9414290";
        String expectedURL = TidesQueryService.ENDPOINT.replace("{beginDate}", beginDate)
                .replace("{endDate}", endDate).replace("{station}", station);
    
        String fakeJsonResult = "{ \"fake\" : \"result\" }";
        String fakeTidesResult = "{ \"predictions\" : [ \n{\"t\":\"2012-01-01 05:13\", \"v\":\"5.443\", \"type\":\"H\"},{\"t\":\"2012-01-01 12:06\", \"v\":\"1.568\", \"type\":\"L\"},{\"t\":\"2012-01-01 18:06\", \"v\":\"3.553\", \"type\":\"H\"},{\"t\":\"2012-01-01 23:07\", \"v\":\"2.147\", \"type\":\"L\"} \n,{\"t\":\"2012-01-02 05:56\", \"v\":\"5.542\", \"type\":\"H\"},{\"t\":\"2012-01-02 13:05\", \"v\":\"1.148\", \"type\":\"L\"},{\"t\":\"2012-01-02 19:40\", \"v\":\"3.623\", \"type\":\"H\"} \n]}";
        this.mockRestServiceServer.expect(requestTo(expectedURL))
                .andExpect(header("Accept", MediaType.APPLICATION_JSON.toString()))
                .andExpect(header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                .andRespond(withSuccess(fakeTidesResult, MediaType.APPLICATION_JSON));

        String actualResult = tidesQueryService.getJSON(beginDate, endDate, station);
        assertEquals(fakeTidesResult, actualResult);
    }
}
