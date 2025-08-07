package dev.kostromdan.mods.netjs.bindings;

import dev.kostromdan.mods.netjs.callbacks.NetJSICallback;
import dev.kostromdan.mods.netjs.tasks.AbstractNetJSTask;
import dev.kostromdan.mods.netjs.tasks.HttpRequestTask;
import dev.latvian.mods.kubejs.script.ConsoleJS;

public interface NetJSWrapper {

        static void getResult(AbstractNetJSTask task, boolean is_async) {
                Thread thread = new Thread(task);
                thread.start();

                if (!is_async) {
                        try {
                                thread.join();
                        } catch (InterruptedException ie) {
                                ConsoleJS.SERVER.error("Error occurred while handling NetJS callback: " + ie.getMessage());
                                ie.printStackTrace();
                        }
                }
        }

        static void request(String url, String method, String body, boolean is_async, NetJSICallback c) {
                if (url == null || url.isEmpty()) {
                        throw new IllegalArgumentException("URL cannot be null or empty");
                }
                if (c == null) {
                        throw new IllegalArgumentException("Callback cannot be null");
                }
                String m = (method == null || method.isEmpty()) ? "GET" : method;
                HttpRequestTask task = new HttpRequestTask(url, m, body, c);
                getResult(task, is_async);
        }

        static void request(String url, String method, String body, NetJSICallback c) {
                request(url, method, body, true, c);
        }

        static void request(String url, boolean is_async, NetJSICallback c) {
                request(url, "GET", null, is_async, c);
        }

        static void request(String url, NetJSICallback c) {
                request(url, "GET", null, true, c);
        }
}
