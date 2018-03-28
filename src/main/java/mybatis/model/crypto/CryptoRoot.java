package mybatis.model.crypto;

public class CryptoRoot {

    String response;
    Data [] data;
    int type;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Data [] getData() {
        return data;
    }

    public void setData(Data [] data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
