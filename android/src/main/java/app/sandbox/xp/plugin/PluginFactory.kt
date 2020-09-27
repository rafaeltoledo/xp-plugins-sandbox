package app.sandbox.xp.plugin

import app.sandbox.xp.Xp

interface PluginFactory<D, T> {
  val killSwitch: Xp
  fun createPlugin(dynamicDependency: D): T
  fun isApplicable(dynamicDependency: D): Boolean
}