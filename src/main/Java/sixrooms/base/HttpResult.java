package sixrooms.base;

/**
 * Author: zh
 * Date: 2021/6/16 14:53
 */

public class HttpResult {

    private Integer code;

    private String body;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HttpResult(Integer code, String body) {
        this.code = code;
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", body='" + body + '\'' +
                '}';
    }
}
