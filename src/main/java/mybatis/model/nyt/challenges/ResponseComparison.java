package mybatis.model.nyt.challenges;

/**
 * Make 2 API calls which will return search result of 2 terms. with
 */

public class ResponseComparison {

    String searchTermOne;
    String searchTermTwo;
    int searchTermOneCount;
    int searchTermTwoCount;

    public String getSearchTermOne() {
        return searchTermOne;
    }

    public void setSearchTermOne(String searchTermOne) {
        this.searchTermOne = searchTermOne;
    }

    public String getSearchTermTwo() {
        return searchTermTwo;
    }

    public void setSearchTermTwo(String searchTermTwo) {
        this.searchTermTwo = searchTermTwo;
    }

    public int getSearchTermOneCount() {
        return searchTermOneCount;
    }

    public void setSearchTermOneCount(int searchTermOneCount) {
        this.searchTermOneCount = searchTermOneCount;
    }

    public int getSearchTermTwoCount() {
        return searchTermTwoCount;
    }

    public void setSearchTermTwoCount(int searchTermTwoCount) {
        this.searchTermTwoCount = searchTermTwoCount;
    }
}
