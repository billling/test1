package org.stt.module.remote;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.stt.module.constants.ExceptionConstants;
import org.stt.module.exception.ExceptionEnum;
import org.stt.module.exception.SystemException;

/**
 * use RestUtil
 * @author wangchao
 *
 */
public class RestExecutor {

    /* URL of server */
    private String serverUrl;

    /* http method : get/post/put/delete */
    private String httpMethod;

    /* the date sent to server */
    private String clientData;

    /* timeout : 5 seconds */
    private final int DEFAULT_TIMEOUT = 5000;

    /* connection timeout */
    private int connectTimeout;

    /* the time to wait for data from server */
    private int socketTimeout;

    private String charset = "UTF-8";

    /**
     * Constructs client with URL, method, and data
     * 
     * @param url the url of server of REST
     * @param method method of HTTP(get/post/delete/put)
     * @param data data in JSON format
     */
    public RestExecutor(String url, String method, String data) {

        this.serverUrl = url;
        this.httpMethod = method;
        this.clientData = data;
        this.connectTimeout = DEFAULT_TIMEOUT;
        this.socketTimeout = DEFAULT_TIMEOUT;
    }

    /**
     * Constructs client with the specified URL
     * 
     * @param url the url of server of REST
     */
    public RestExecutor(String url) {

        this.serverUrl = url;
        this.httpMethod = "GET";
        this.clientData = null;
        this.connectTimeout = DEFAULT_TIMEOUT;
        this.socketTimeout = DEFAULT_TIMEOUT;
    }

    /**
     * Constructs client with the specified URL and data
     * 
     * @param url the url of server of REST
     * @param data data in JSON format
     */
    public RestExecutor(String url, String data) {

        this.serverUrl = url;
        this.httpMethod = "GET";
        this.clientData = data;
        this.connectTimeout = DEFAULT_TIMEOUT;
        this.socketTimeout = DEFAULT_TIMEOUT;
    }

    /**
     * Sets the url of server of REST
     * 
     * @param url the url of server of REST
     */
    public void setURL(String url) {

        this.serverUrl = url;
    }

    /**
     * Sets the HTTP method
     * 
     * @param method method of HTTP(get/post/delete/put)
     */
    public void setMethod(String method) {

        this.httpMethod = method;
    }

    /**
     * Sets the data be sent to server of REST
     * 
     * @param data data will be sent to server of REST
     */
    public void setData(String data) {

        this.clientData = data;
    }

    /**
     * Sets the time to connect to server of REST,unit(second)
     * 
     * @param connectTimeout the time to connect to server of REST
     */
    public void setConnectTimeout(int connectTimeout) {

        if (0 < connectTimeout) {
            this.connectTimeout = connectTimeout;
        } else {

        }
    }

    /**
     * Sets the time to wait server of REST to deal with data,unit(second)
     * 
     * @param socketTimeout the time to wait server of REST to deal with data
     */
    public void setSocketTimeout(int socketTimeout) {

        if (0 < socketTimeout) {
            this.socketTimeout = socketTimeout;
        } else {

        }
    }

    /**
     * Sends the request to server and gets the data from server
     * 
     * @return data from server
     * @throws IOException
     * @throws RestException Thrown when exception in encode, decode or
     *             connection
     */
    public String execute() throws SystemException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        CloseableHttpResponse httpResponse = null;
        try {
            if (HttpGet.METHOD_NAME.equals(httpMethod)) {
                // set the get data with the url
                HttpGet httpGet = new HttpGet(getUrlForMethod(serverUrl));
                httpGet.setConfig(requestConfig);
                httpResponse = httpClient.execute(httpGet);
            } else if (HttpPost.METHOD_NAME.equals(httpMethod)) {
                HttpPost httpPost = new HttpPost(serverUrl);
                httpPost.setConfig(requestConfig);
                if (null != clientData) {
                    // set the post data in the body
                    httpPost.setEntity(new StringEntity(clientData));
                } else {
                    // TODO 入参为空是否需要做处理
                }
                httpResponse = httpClient.execute(httpPost);
            } else if (HttpPut.METHOD_NAME.equals(httpMethod)) {
                HttpPut httpPut = new HttpPut();
                httpPut.setConfig(requestConfig);
                if (null != clientData) {
                    // set the put data in the body
                    httpPut.setEntity(new StringEntity(clientData));
                } else {
                    // TODO 入参为空是否需要做处理
                }
                httpResponse = httpClient.execute(httpPut);
            } else if (HttpDelete.METHOD_NAME.equals(httpMethod)) {
                HttpDelete httpDelete = new HttpDelete(getUrlForMethod(serverUrl));
                httpDelete.setConfig(requestConfig);
                httpResponse = httpClient.execute(httpDelete);
            }
            // handle response
            StatusLine statusLine = httpResponse.getStatusLine();
            HttpEntity entity = httpResponse.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                throw new SystemException(ExceptionEnum.EXECUTE_REMOTE_EXCP.setFormatMsg(
                        ExceptionConstants.REMOTE_CALL_FAIL, new Object[] { serverUrl, statusLine.getStatusCode() }));
            }
            return entity == null ? null : EntityUtils.toString(entity, charset);

        } catch (SystemException e) {
            throw e;
        } catch (Exception e) {
            throw new SystemException(ExceptionEnum.EXECUTE_REMOTE_EXCP.setFormatMsg(
                    ExceptionConstants.REMOTE_CALL_FAIL, new Object[] { serverUrl, "-1" }));
        } finally {
            httpClient.close();
            httpResponse.close();
        }

    }

    /**
     * method "GET" "DELETE"<br>
     * 
     * @param serverUrl
     * @return
     */
    private String getUrlForMethod(String serverUrl) {

        if (clientData != null) {
            if (serverUrl.endsWith("?")) {
                return serverUrl + clientData;
            } else {
                return serverUrl + "?" + clientData;
            }
        }
        return serverUrl;
    }

}
