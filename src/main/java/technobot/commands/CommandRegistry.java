package technobot.commands;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import technobot.TechnoBot;
import technobot.commands.utility.PingCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Registers, listens, and executes commands.
 *
 * @author TechnoVision
 */
public class CommandRegistry extends ListenerAdapter {

    public static final ArrayList<Command> commands = new ArrayList<>();

    /**
     * Adds commands to a global list and registers them as event listener.
     *
     * @param bot An instance of CivBot.
     */
    public CommandRegistry(TechnoBot bot) {
        //Economy commands
        commands.add(new PingCommand(bot));

        //Register commands as listeners
        for (Command command : commands) {
            bot.shardManager.addEventListener(command);
        }
    }

    /**
     * Registers slash commands as guild commands.
     * TEMPORARY! CHANGE TO GLOBAL COMMANDS ON RELEASE!
     *
     * @param event executes when a guild is ready.
     */
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        for (Command command : commands) {
            commandData.add(Commands.slash(command.name, command.description).addOptions(command.args));
        }
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}