package sapufferfish.constructionfg.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Subcommand {
    private static final HashMap<String, Subcommand> subcommands = new HashMap<>();
    String name;
    String perm = ".";

    ArrayList<String> args = new ArrayList<>();

    protected Subcommand(String name, String perm) {
        subcommands.put(name, this);
        this.name = name;
        this.perm += perm;
    }

    public String getName() {
        return name;
    }

    public String getPerm() {
        return perm;
    }

    public ArrayList<String> getArgs() {
        return args;
    }

    public abstract void action(CommandSender sender, String[] args);

    public static HashMap<String, Subcommand> getSubcommands() {
        return subcommands;
    }

    public static void reject(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Invalid usage: Please do /help to check correct usage.");
    }
}
