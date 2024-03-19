package me.ghostdevelopment.ghostbot.main.Commands;

import lombok.Getter;
import lombok.Setter;
import me.ghostdevelopment.ghostbot.main.Utils.Logs;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.ArrayList;

@Setter @Getter
public abstract class iCommand extends Logs, ListenerAdapter {

    private String name;
    private String description;
    private ArrayList<Permission> permissions = new ArrayList<>();
    private OptionType optionType1;
    private OptionType optionType2;
    private OptionType optionType3;
    private OptionType optionType4;

    @Override
    public void onSlashCommandInteraction( event) {



    }

}
