package dev.kostromdan.mods.netjs.results;

import dev.kostromdan.mods.netjs.utils.NetJSUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class NetJSResultMap<K, V> extends LinkedHashMap<K, V> {

        public NetJSResultMap() {
        }

        public boolean isSuccess() {
                return (boolean) this.get("success");
        }

        public Exception getException() {
                return (Exception) this.get("exception");
        }

        public String getRaw() {
                Object raw = this.get("raw_text");
                return raw != null ? raw.toString() : null;
        }

        public Map<?, ?> parseRawToJson() {
                return NetJSUtils.parseRawJsonToMap(getRaw());
        }

}
