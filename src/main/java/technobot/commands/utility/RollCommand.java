package technobot.commands.utility;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import technobot.TechnoBot;
import technobot.commands.Category;
import technobot.commands.Command;

import java.util.Random;

/**
 * Command to roll a die for random number generation.
 *
 * @author TechnoVision
 */
public class RollCommand extends Command {

    private final Random random;

    public RollCommand(TechnoBot bot) {
        super(bot);
        this.name = "roll";
        this.description = "Roll a dice.";
        this.category = Category.UTILITY;
        this.args.add(new OptionData(OptionType.INTEGER, "dice", "The number of sides on the dice"));
        this.random = new Random();
    }

    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        OptionMapping option = event.getOption("dice");
        int bound = option != null ? option.getAsInt() : 6;
        if (bound == 0) bound = 1;
        int result = random.nextInt(bound) + 1;
        event.getHook().sendMessage(String.valueOf(result)).queue();
    }
}