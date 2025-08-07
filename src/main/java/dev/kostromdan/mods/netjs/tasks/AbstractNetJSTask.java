package dev.kostromdan.mods.netjs.tasks;

import dev.kostromdan.mods.netjs.callbacks.NetJSICallback;
import dev.kostromdan.mods.netjs.results.NetJSResultMap;
import dev.kostromdan.mods.netjs.utils.TrustAllCertificates;
import dev.latvian.mods.kubejs.script.ConsoleJS;

public abstract class AbstractNetJSTask extends TrustAllCertificates implements Runnable {
        public String id = null;
        public NetJSResultMap<String, Object> result;
        protected NetJSICallback callback = null;

        public AbstractNetJSTask(String id) {
                result = new NetJSResultMap<String, Object>();
                this.id = id;
        }

        public void callback() {
                try {
                        callback.onCallback(result);
                } catch (Throwable ex) {
                        ConsoleJS.SERVER.error("Error occurred while handling NetJS callback: " + ex.getMessage());
                }
        }

        public void success() {
                result.put("success", true);
                callback();
        }

		public void exception(Exception err) {
			result.put("success", false);
			result.put("exception", err);
			callback();
			err.printStackTrace();
		}

        public boolean isSuccess() {
                return result.isSuccess();
        }

        public Exception getException() {
                return result.getException();
        }
}
