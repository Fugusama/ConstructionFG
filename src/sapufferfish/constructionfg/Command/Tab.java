package sapufferfish.constructionfg.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Tab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase(CommandCore.name)) {
            ArrayList<String> list = new ArrayList<>();

            for (Subcommand subcommand : Subcommand.getSubcommands().values()) {
                if (args.length == 1) {
                    if (subcommand.getName().contains(args[0]) && sender.hasPermission(subcommand.getPerm())) {
                        list.add(subcommand.getName());
                    }
                } else if (args.length <= subcommand.getArgs().size()) {
                    if (subcommand.getName().contains(args[args.length - 1]) && sender.hasPermission(subcommand.getPerm())) {
                        list.add(subcommand.getArgs().get(args.length - 2));
                    }
                }
            }

            return list;
        }
        return null;
    }
}
