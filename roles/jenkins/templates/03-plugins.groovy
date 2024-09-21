def plugins = [
    "workflow-multibranch"
]

def instance = Jenkins.getInstance()
def pm = instance.getPluginManager()
def uc = instance.getUpdateCenter()

plugins.each { pluginName ->
  if (!pm.getPlugin(pluginName)) {
    def plugin = uc.getPlugin(pluginName)
    if (plugin) {
      println("Installing " + pluginName)
      plugin.deploy()
    } else {
      println("Plugin not found: " + pluginName)
    }
  } else {
    println("Plugin already installed: " + pluginName)
  }
}

instance.save()
instance.doSafeRestart()
