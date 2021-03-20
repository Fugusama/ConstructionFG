package sapufferfish.constructionfg;

import org.bukkit.plugin.java.JavaPlugin;
import sapufferfish.constructionfg.Command.CommandCore;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main extends JavaPlugin {

    static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        CommandCore.setupCommands();
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

    public static void loadClasses(String pack) {
        try {
            JarFile jar = new JarFile(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            Enumeration<JarEntry> entries = jar.entries();
            String prefix = "sapufferfish/template/" + pack;

            while (entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();

                if (name.startsWith(prefix) && name.endsWith(".class")) {
                    Class<?> clazz = Class.forName(name.replaceAll(".class", "").replaceAll("/", "."));
                    clazz.newInstance();
                }
            }

            jar.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NullPointerException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
