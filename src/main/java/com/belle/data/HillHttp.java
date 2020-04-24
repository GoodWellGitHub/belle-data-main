package com.belle.data;

import com.belle.util.BeanUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum HillHttp {
    INSTANCE;

    CloseableHttpClient client = null;

    HillHttp() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        clientConnectionManager.setMaxTotal(50);
        clientConnectionManager.setDefaultMaxPerRoute(25);
        RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(1000).setSocketTimeout(2000).setConnectionRequestTimeout(2000).build();
        client = HttpClients.custom().setConnectionManager(clientConnectionManager).setDefaultRequestConfig(defaultRequestConfig).build();
    }

    public String postJson(String request, Map<String, String> headers, String url, int connectTime, int socketTimeout) throws IOException {
        System.out.println("Post json data to: " + url);
        HttpPost httpPost = new HttpPost(url);
        if (headers != null) {
            headers.forEach((k, v) -> {
                httpPost.setHeader(k, v);
            });
        }
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTime).setSocketTimeout(socketTimeout).build();
        httpPost.setConfig(requestConfig);
        StringEntity entity = new StringEntity(request, ContentType.create("application/json", "utf-8"));
        httpPost.setEntity(entity);
        return execute(httpPost);
    }

    public String postJson(String request, Map<String, String> headers, String url) throws IOException {
        return postJson(request, headers, url, 2000, 2000);
    }

    public String postString(String request, Map<String, String> headers, String url, int connectTime, int socketTimeout) throws IOException {
        System.out.println("Post json data to:{}" + url);
        HttpPost httpPost = new HttpPost(url);
        if (headers != null) {
            headers.forEach((k, v) -> {
                httpPost.setHeader(k, v);
            });
        }
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTime).setSocketTimeout(socketTimeout).build();
        httpPost.setConfig(requestConfig);
        StringEntity entity = new StringEntity(request, ContentType.create("text/plain", "utf-8"));
        httpPost.setEntity(entity);
        return execute(httpPost);
    }

    public String postString(String request, Map<String, String> headers, String url) throws IOException {
        return this.postString(request, headers, url, 2000, 2000);
    }


    public String postForm(Object object, Map<String, String> headers, String url, int connectTime, int socketTimeout) throws Exception {
        List<NameValuePair> list = new ArrayList<>();
        Map<String, Object> map = BeanUtil.getBeanMap(object);
        map.forEach((k, v) -> {
            list.add(new BasicNameValuePair(k, v.toString()));
        });
        System.out.println("Post form data to:{}" + url);
        HttpPost httpPost = new HttpPost(url);
        if (headers != null) {
            headers.forEach((k, v) -> {
                httpPost.setHeader(k, v);
            });
        }
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTime).setSocketTimeout(socketTimeout).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
        httpPost.setEntity(entity);
        return execute(httpPost);
    }


    public String postForm(Object object, Map<String, String> headers, String url) throws Exception {
        return postForm(object, headers, url, 2000, 2000);
    }


    public String get(Map<String, String> headers, String url, int connectTime, int socketTimeout) throws IOException {
        System.out.println("Get data from:{}" + url);
        HttpGet httpGet = new HttpGet(url);
        if (headers != null) {
            headers.forEach((k, v) -> {
                httpGet.addHeader(k, v);
            });
        }
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTime).setSocketTimeout(socketTimeout).build();
        httpGet.setConfig(requestConfig);
        return execute(httpGet);
    }

    public String get(Map<String, String> headers, String url) throws IOException {
        return get(headers, url, 5000, 5000);
    }

    public String post(Map<String, String> headers, String url, byte[] body, int connectTime, int socketTimeout) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        if (headers != null) {
            headers.forEach((k, v) -> {
                httpPut.addHeader(k, v);
            });
        }
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTime).setSocketTimeout(socketTimeout).build();
        httpPut.setConfig(requestConfig);

        HttpEntity httpEntity = new ByteArrayEntity(body);

        httpPut.setEntity(httpEntity);
        return execute(httpPut);
    }

    private String execute(HttpRequestBase request) throws IOException {
        return client.execute(request, new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                InputStream is = null;
                ByteArrayOutputStream baos = null;
                if (response.getStatusLine().getStatusCode() == 500) {
                    throw new RuntimeException("Failed execute http,request:" + request.toString());
                }
                try {
                    is = response.getEntity().getContent();
                    baos = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int count = 0;
                    while ((count = is.read(buff)) != -1) {
                        baos.write(buff, 0, count);
                    }
                    byte[] result = baos.toByteArray();
                    return new String(result, "utf-8");
                } finally {
                    is.close();
                    baos.close();
                }
            }
        });
    }
}
