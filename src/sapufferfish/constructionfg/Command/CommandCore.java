package sapufferfish.constructionfg.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import sapufferfish.constructionfg.Main;

import java.util.Arrays;

public class CommandCore implements CommandExecutor {
    static String name = "";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (s.equalsIgnoreCase(name)) {
            if (strings.length != 0) {
                Subcommand subcommand = Subcommand.getSubcommands().getOrDefault(strings[0], null);
                if (subcommand != null) {
                    String[] args = Arrays.copyOfRange(strings, 1, strings.length);
                    subcommand.action(commandSender, args);

                    return true;
                }
            }

            Subcommand.reject(commandSender);
        }
        return true;
    }

    public static void setupCommands() {
        PluginCommand cmd = Main.getInstance().getCommand(name);
        if (cmd == null) return;

        cmd.setExecutor(new CommandCore());
        cmd.setTabCompleter(new Tab());

        Main.loadClasses("Command/Subcommands");
    }
}
