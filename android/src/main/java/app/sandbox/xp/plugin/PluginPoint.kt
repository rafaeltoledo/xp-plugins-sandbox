package app.sandbox.xp.plugin

import app.sandbox.xp.Experiments

abstract class PluginPoint<D, T> {

    fun getPlugins(dynamicDependency: D): List<T> {
        return getInternalFactories()
            .filter { Experiments.isTreated(it.killSwitch) && it.isApplicable(dynamicDependency) }
            .map { it.createPlugin(dynamicDependency) }
    }

    fun getPlugin(dynamicDependency: D): T {
        return getPlugins(dynamicDependency).first()
    }

    protected abstract fun getInternalFactories(): List<PluginFactory<D, T>>
}