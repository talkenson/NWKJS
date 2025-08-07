package dev.kostromdan.mods.netjs.tasks;

import dev.kostromdan.mods.netjs.callbacks.NetJSICallback;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.HttpStatusException;

import java.io.IOException;

public class HttpRequestTask extends AbstractNetJSTask {
        private final Connection.Method method;
        private final String body;

        public HttpRequestTask(String url, String method, String body, NetJSICallback c) {
                super(url);
                this.callback = c;
                Connection.Method parsed;
                if (method == null || method.isEmpty()) {
                        parsed = Connection.Method.GET;
                } else {
                        try {
                                parsed = Connection.Method.valueOf(method.toUpperCase());
                        } catch (IllegalArgumentException e) {
                                parsed = Connection.Method.GET;
                        }
                }
                this.method = parsed;
                this.body = body;
        }

        @Override
        public void run() {
                Connection connection = Jsoup.connect(id).ignoreContentType(true).method(method);
                if (body != null && !body.isEmpty()) {
                        connection.requestBody(body);
                }
                Connection.Response response;
                String raw_text;

				try {
					response = connection.execute();
					raw_text = response.body();
				} catch (HttpStatusException httpEx) {
					result.put("error", "HTTP Error: " + httpEx.getStatusCode());
					result.put("error_message", httpEx.getMessage());
					result.put("error_code", httpEx.getStatusCode());
					exception(httpEx);
					return;
				} catch (IOException ioEx) {
					exception(ioEx);
					return;
				}

                int response_code = response.statusCode();
                result.put("response_code", response_code);
                result.put("raw_text", raw_text);
                success();
        }
}
