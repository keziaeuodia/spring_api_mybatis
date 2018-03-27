package mybatis.services;

import mybatis.mappers.NYTMapper;
import mybatis.model.nyt.external.NYTDocs;
import mybatis.model.nyt.external.NYTRoot;
import mybatis.model.nyt.challenges.ResponseComparison;
import mybatis.model.nyt.internal.NYTOverview;
import mybatis.threads.NYTOverviewThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NYTService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    NYTMapper nytMapper;

    public NYTRoot searchNYT(String query, boolean persist) {

        String fQuery = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q="+query+"&api-key=";

        NYTRoot response = restTemplate.getForObject(
                fQuery, NYTRoot.class);

        response.setCopyright("hey this is my copyright");

        System.out.println("*************** got the results");

        if (persist){
            System.out.println("*************** starting persistence thread");
            NYTOverviewThread thread = new NYTOverviewThread(response);
        }

        System.out.println("returning response");

        return  response;
    }

    public ResponseComparison compareNYTResults(String t1, String t2) {
        NYTRoot responseOne = searchNYT(t1, false);
        NYTRoot responseTwo = searchNYT(t2, false);

        ResponseComparison obj = new ResponseComparison();
        obj.setSearchTermOne(t1);   //
        obj.setSearchTermTwo(t2);
        obj.setSearchTermOneCount(responseOne.getResponse().getMeta().getHits());
        obj.setSearchTermTwoCount(responseTwo.getResponse().getMeta().getHits());

        return obj;
    }

    public void insertNYTSummary(NYTOverview result) {
        nytMapper.insertSummary(result);
    }
}
