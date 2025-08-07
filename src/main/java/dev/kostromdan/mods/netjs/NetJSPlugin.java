package dev.kostromdan.mods.netjs;

import dev.kostromdan.mods.netjs.bindings.NetJSWrapper;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;

public class NetJSPlugin implements KubeJSPlugin {
        @Override
        public void registerBindings(BindingRegistry registry) {
                registry.add("NetJS", NetJSWrapper.class);
        }
}
