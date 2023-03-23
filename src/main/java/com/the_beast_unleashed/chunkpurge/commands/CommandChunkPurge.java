package com.the_beast_unleashed.chunkpurge.commands;

import java.util.Arrays;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

import com.the_beast_unleashed.chunkpurge.ModChunkPurge;

// I attempted to heavily comment this code more than necessary in case anybody
// in the future wants to edit the code and wonders what the heck I was doing

public class CommandChunkPurge extends CommandBase {

    // Use msg to format text to send to the player
    IChatComponent msg;

    // Set the mod's command name, used as /commandName [Other cool secondary commands]
    @Override
    public String getCommandName() {
        return "chunkpurge";
    }

    // Tell the player what the command does in the /help menu
    @Override
    public String getCommandUsage(ICommandSender commandUseage) {
        return "/chunkpurge | Get usage help for ChunkPurge";
    }

    // #####################################################
    // If you trigger these methods, you clearly did something wrong.
    private void invalid(ICommandSender commandInvalid) {
        msg = new ChatComponentText(EnumChatFormatting.RED + "Invalid Argument. Type /chunkpurge for help");
        commandInvalid.addChatMessage(msg);
        return;
    }

    private void dimIntInvalid(ICommandSender commandInvalid) {
        msg = new ChatComponentText(
                EnumChatFormatting.RED + "Dimensions MUST be an Integer. If more than one, they MUST");
        commandInvalid.addChatMessage(msg);
        msg = new ChatComponentText(EnumChatFormatting.RED + "be comma seperated with no spaces");
        commandInvalid.addChatMessage(msg);
        return;
    }

    // #####################################################
    // If a command is entered, what should I do?
    @Override
    public void processCommand(ICommandSender sender, String[] arg) {
        List<String> ARList;
        List<String> configIDs;
        int iAR;
        int length;
        String AR;
        String sAR;
        switch (arg.length) {
            // Case 0 (no secondary commands) Lets tell the player how to use the command
            case 0:
                msg = new ChatComponentText(EnumChatFormatting.GREEN + "--- Showing Help for ChunkPurge ---");
                sender.addChatMessage(msg);
                msg = new ChatComponentText("/chunkpurge delay [ticks]");
                sender.addChatMessage(msg);
                msg = new ChatComponentText("/chunkpurge debug [true|false]");
                sender.addChatMessage(msg);
                msg = new ChatComponentText("/chunkpurge enable [true|false]");
                sender.addChatMessage(msg);
                msg = new ChatComponentText("/chunkpurge pradius [# of chunks]");
                sender.addChatMessage(msg);
                msg = new ChatComponentText("/chunkpurge tradius [# of chunks]");
                sender.addChatMessage(msg);
                msg = new ChatComponentText("/chunkpurge sradius [# of chunks]");
                sender.addChatMessage(msg);
                msg = new ChatComponentText("/chunkpurge dimlist | Lists dimensions that get purged");
                sender.addChatMessage(msg);
                msg = new ChatComponentText("/chunkpurge dimlist add [dimID,dimID,ect.]");
                sender.addChatMessage(msg);
                msg = new ChatComponentText("/chunkpurge dimlist remove [dimID,dimID,ect.]");
                sender.addChatMessage(msg);
                return;
            // Case 1 (only one secondary command) The player must only want information about a command, not to change
            // it
            case 1:
                // If they typed delay, lets give them the current ChunkPurge Unload Delay
                if (arg[0].equalsIgnoreCase("delay")) {

                    msg = new ChatComponentText(
                            EnumChatFormatting.GREEN + "Unload Delay: "
                                    + EnumChatFormatting.WHITE
                                    + String.valueOf(ModChunkPurge.config.chunkUnloadDelay));
                    sender.addChatMessage(msg);
                    return;
                }
                // If they typed debug, tell them if debug is on or not
                if (arg[0].equalsIgnoreCase("debug")) {

                    msg = new ChatComponentText(
                            EnumChatFormatting.GREEN + "Debug Mode Enabled: "
                                    + EnumChatFormatting.WHITE
                                    + String.valueOf(ModChunkPurge.config.debug));
                    sender.addChatMessage(msg);
                    return;
                }
                // If they typed enable, tell them if the mod is enabled or not
                if (arg[0].equalsIgnoreCase("enable")) {

                    msg = new ChatComponentText(
                            EnumChatFormatting.GREEN + "Chunk Purge Enabled: "
                                    + EnumChatFormatting.WHITE
                                    + String.valueOf(ModChunkPurge.config.enabled));
                    sender.addChatMessage(msg);
                    return;
                }
                // If they typed pradius, give them the added radius to the players view range that will be ignored
                // while unloading chunks
                if (arg[0].equalsIgnoreCase("pradius")) {

                    msg = new ChatComponentText(
                            EnumChatFormatting.GREEN + "Player Ignore Radius = View Distance("
                                    + MinecraftServer.getServer().getConfigurationManager().getViewDistance()
                                    + ") + "
                                    + EnumChatFormatting.WHITE
                                    + String.valueOf(ModChunkPurge.config.pradius)
                                    + EnumChatFormatting.GREEN
                                    + " Chunks");
                    sender.addChatMessage(msg);
                    return;
                }
                // If they typed tradius, give them the radius around chunk loaders (tickets) that will be ignored while
                // unloading chunks
                if (arg[0].equalsIgnoreCase("tradius")) {

                    msg = new ChatComponentText(
                            EnumChatFormatting.GREEN + "Chunk Loader Ticket Ignore Radius = "
                                    + EnumChatFormatting.WHITE
                                    + String.valueOf(ModChunkPurge.config.tradius)
                                    + EnumChatFormatting.GREEN
                                    + " Chunks");
                    sender.addChatMessage(msg);
                    return;
                }
                // If they typed sradius, give them the added radius to the Spawn Chunks that will be ignored while
                // unloading chunks
                if (arg[0].equalsIgnoreCase("sradius")) {

                    msg = new ChatComponentText(
                            EnumChatFormatting.GREEN + "Spawn Chunk Ignore Radius = Spawn Radius(8) + "
                                    + EnumChatFormatting.WHITE
                                    + String.valueOf(ModChunkPurge.config.sradius)
                                    + EnumChatFormatting.GREEN
                                    + " Chunks");
                    sender.addChatMessage(msg);
                    return;
                }
                // If they typed dimlist, give them the list of dimensions being processed by ChunkPurge
                if (arg[0].equalsIgnoreCase("dimlist")) {
                    msg = new ChatComponentText(
                            EnumChatFormatting.GREEN + "ID's of dimensions being ChunkPurged | "
                                    + EnumChatFormatting.WHITE
                                    + ModChunkPurge.config.dimlist.replace(",", ", "));
                    sender.addChatMessage(msg);
                    return;
                }
                // If none of the above, you did something wrong.
                else {
                    invalid(sender);
                    return;
                }
                // Case 2 (The player typed in 2 separate commands after the main command) They must want to change a
                // config value.
            case 2:
                // If they typed delay, either change the delay to what they typed, or catch an error and tell them what
                // they entered is wrong.
                if (arg[0].equalsIgnoreCase("delay")) {
                    try {

                        if (Integer.valueOf(arg[1]) <= 0) {

                            msg = new ChatComponentText(
                                    EnumChatFormatting.RED + "[Ticks] must be an integer greater than 0");
                            sender.addChatMessage(msg);
                            return;
                        }

                        else {

                            ModChunkPurge.config.chunkUnloadDelay = Integer.valueOf(arg[1]);

                            msg = new ChatComponentText(
                                    EnumChatFormatting.GREEN + "Chunk Unload Delay Set to: "
                                            + EnumChatFormatting.WHITE
                                            + arg[1]);
                            sender.addChatMessage(msg);

                            ModChunkPurge.config.saveConfig();
                            return;
                        }

                    }

                    catch (NumberFormatException ex) {

                        msg = new ChatComponentText(
                                EnumChatFormatting.RED + "Chunk Unload Delay MUST be an integer. Your input was: "
                                        + EnumChatFormatting.WHITE
                                        + arg[1]);
                        sender.addChatMessage(msg);

                    }
                    return;
                }
                // If they typed debug, either change debug to true/false, or catch an error and tell them what they
                // entered is wrong.
                if (arg[0].equalsIgnoreCase("debug")) {

                    if (arg[1].equalsIgnoreCase("true") || arg[1].equalsIgnoreCase("false")) {

                        ModChunkPurge.config.debug = Boolean.valueOf(arg[1]);

                        msg = new ChatComponentText(
                                EnumChatFormatting.GREEN + "Debug Mode Enabled Set to: "
                                        + EnumChatFormatting.WHITE
                                        + String.valueOf(ModChunkPurge.config.debug));
                        sender.addChatMessage(msg);

                        ModChunkPurge.config.saveConfig();
                        return;
                    }

                    else {

                        msg = new ChatComponentText(
                                EnumChatFormatting.RED + "Debug MUST be True or False. Your input was: "
                                        + EnumChatFormatting.WHITE
                                        + arg[1]);
                        sender.addChatMessage(msg);
                        return;
                    }
                }
                // If they typed enable, either change enable to true/false, or catch an error and tell them what they
                // entered is wrong.
                if (arg[0].equalsIgnoreCase("enable")) {

                    if (arg[1].equalsIgnoreCase("true") || arg[1].equalsIgnoreCase("false")) {

                        ModChunkPurge.config.enabled = Boolean.valueOf(arg[1]);

                        msg = new ChatComponentText(
                                EnumChatFormatting.GREEN + "Chunk Purge Enabled Set to: "
                                        + EnumChatFormatting.WHITE
                                        + String.valueOf(ModChunkPurge.config.enabled));
                        sender.addChatMessage(msg);

                        ModChunkPurge.config.saveConfig();
                        return;
                    }

                    else {

                        msg = new ChatComponentText(
                                EnumChatFormatting.RED + "Enabled MUST be True or False. Your input was: "
                                        + EnumChatFormatting.WHITE
                                        + arg[1]);
                        sender.addChatMessage(msg);
                        return;
                    }
                }
                // If they typed pradius, either change the player ignore radius adder to what they typed, or catch an
                // error and tell them what they entered is wrong.
                if (arg[0].equalsIgnoreCase("pradius")) {
                    try {

                        if (Integer.valueOf(arg[1]) < 0) {

                            msg = new ChatComponentText(
                                    EnumChatFormatting.RED
                                            + "[# of chunks] MUST be an integer greater than or equal to 0");
                            sender.addChatMessage(msg);
                            return;
                        }

                        else {

                            ModChunkPurge.config.pradius = Integer.valueOf(arg[1]);

                            msg = new ChatComponentText(
                                    EnumChatFormatting.GREEN + "Player Ignore Radius Set to: View Distance("
                                            + MinecraftServer.getServer().getConfigurationManager().getViewDistance()
                                            + ") + "
                                            + EnumChatFormatting.WHITE
                                            + arg[1]
                                            + EnumChatFormatting.GREEN
                                            + " Chunks");
                            sender.addChatMessage(msg);

                            ModChunkPurge.config.saveConfig();
                            return;
                        }

                    }

                    catch (NumberFormatException ex) {

                        msg = new ChatComponentText(
                                EnumChatFormatting.RED + "[# of chunks] MUST be an integer greater than or equal to 0. "
                                        + EnumChatFormatting.RED
                                        + "Your input was: "
                                        + EnumChatFormatting.WHITE
                                        + arg[1]);
                        sender.addChatMessage(msg);

                    }
                    return;
                }
                // If they typed tradius, either change the ticket ignore radius adder to what they typed, or catch an
                // error and tell them what they entered is wrong.
                if (arg[0].equalsIgnoreCase("tradius")) {
                    try {

                        if (Integer.valueOf(arg[1]) < 0) {

                            msg = new ChatComponentText(
                                    EnumChatFormatting.RED
                                            + "[# of chunks] MUST be an integer greater than or equal to 0");
                            sender.addChatMessage(msg);
                            return;
                        }

                        else {

                            ModChunkPurge.config.tradius = Integer.valueOf(arg[1]);

                            msg = new ChatComponentText(
                                    EnumChatFormatting.GREEN + "Chunk Loader Ticket Ignore Radius Set to: "
                                            + EnumChatFormatting.WHITE
                                            + arg[1]
                                            + EnumChatFormatting.GREEN
                                            + " Chunks");
                            sender.addChatMessage(msg);

                            ModChunkPurge.config.saveConfig();
                            return;
                        }

                    }

                    catch (NumberFormatException ex) {

                        msg = new ChatComponentText(
                                EnumChatFormatting.RED + "[# of chunks] MUST be an integer greater than or equal to 0. "
                                        + EnumChatFormatting.RED
                                        + "Your input was: "
                                        + EnumChatFormatting.WHITE
                                        + arg[1]);
                        sender.addChatMessage(msg);

                    }
                    return;
                }
                // If they typed sradius, either change the spawn chunk ignore radius adder to what they typed, or catch
                // an error and tell them what they entered is wrong.
                if (arg[0].equalsIgnoreCase("sradius")) {
                    try {

                        if (Integer.valueOf(arg[1]) < 0) {

                            msg = new ChatComponentText(
                                    EnumChatFormatting.RED
                                            + "[# of chunks] MUST be an integer greater than or equal to 0");
                            sender.addChatMessage(msg);
                            return;
                        }

                        else {

                            ModChunkPurge.config.sradius = Integer.valueOf(arg[1]);

                            msg = new ChatComponentText(
                                    EnumChatFormatting.GREEN + "Spawn Chunk Ignore Radius Set to: Spawn Radius(8) + "
                                            + EnumChatFormatting.WHITE
                                            + arg[1]
                                            + EnumChatFormatting.GREEN
                                            + " Chunks");
                            sender.addChatMessage(msg);

                            ModChunkPurge.config.saveConfig();
                            return;
                        }

                    }

                    catch (NumberFormatException ex) {

                        msg = new ChatComponentText(
                                EnumChatFormatting.RED + "[# of chunks] MUST be an integer greater than or equal to 0. "
                                        + EnumChatFormatting.RED
                                        + "Your input was: "
                                        + EnumChatFormatting.WHITE
                                        + arg[1]);
                        sender.addChatMessage(msg);

                    }
                    return;
                }
                // If none of the above, you did something wrong
                else {
                    invalid(sender);
                    return;
                }
                // Case 3 (3 separate commands were typed after the main command) They must want to add to or remove
                // from the dimlist
            case 3:
                // Their first command had better be dimlist
                if (arg[0].equalsIgnoreCase("dimlist")) {
                    // If they type add as the second command, lets add something to the dimlist or catch an error if
                    // they enter that something wrong
                    if (arg[1].equalsIgnoreCase("add")) {
                        try {
                            // Turn the player input into an array
                            ARList = Arrays.asList(arg[2].split(","));
                            // Check the size of the array
                            length = ARList.size();
                            // Process the following for each object in the array
                            for (int i = 0; i < length; i++) {
                                // Set the object to String first. We could just use iAR =
                                // Integer.parseInt(String.valueOf(ARList.get(i)));
                                // but we use the string later, so this just makes everything cleaner.
                                sAR = ARList.get(i);
                                // Attempt to turn the string into an integer (this is where exception is caught)
                                iAR = Integer.parseInt(sAR);
                                // If you get down here, it's a valid number, so lets call the current dimlist to do
                                // things with it
                                configIDs = Arrays.asList(ModChunkPurge.config.dimlist.split(","));
                                if (configIDs.contains(sAR)) {
                                    // Why are you adding things to the dimlist that are already there... why do I have
                                    // to write code to catch this?
                                    msg = new ChatComponentText(
                                            EnumChatFormatting.RED + "Config already contains dimension "
                                                    + EnumChatFormatting.WHITE
                                                    + sAR);
                                    sender.addChatMessage(msg);
                                } else {
                                    // Add the new input to the dimlist, reset the dimlist value, and tell the player
                                    ModChunkPurge.config.dimlist = ModChunkPurge.config.dimlist + "," + iAR;
                                    msg = new ChatComponentText(
                                            EnumChatFormatting.GREEN + "Dimension "
                                                    + EnumChatFormatting.WHITE
                                                    + sAR
                                                    + EnumChatFormatting.GREEN
                                                    + " added to config");
                                    sender.addChatMessage(msg);
                                }

                            }
                            msg = new ChatComponentText(
                                    EnumChatFormatting.GREEN + "ID's of dimensions now being ChunkPurged | "
                                            + EnumChatFormatting.WHITE
                                            + ModChunkPurge.config.dimlist.replace(",", ", "));
                            sender.addChatMessage(msg);
                            // SAVE your config AFTER the loop, not inside it.
                            ModChunkPurge.config.saveConfig();
                            return;
                        } catch (NumberFormatException ex) {
                            dimIntInvalid(sender);
                            return;
                        }
                    }
                    // If they type remove, lets remove things or catch an error and tell them what they typed is wrong
                    if (arg[1].equalsIgnoreCase("remove")) {
                        try {
                            // Turn the player input into an array
                            ARList = Arrays.asList(arg[2].split(","));
                            // Check the size of the array
                            length = ARList.size();
                            // Process the following for each object in the array
                            for (int i = 0; i < length; i++) {
                                // Set the object to String first. We could just use iAR =
                                // Integer.parseInt(String.valueOf(ARList.get(i)));
                                // but we use the string later, so this just makes everything cleaner.
                                sAR = ARList.get(i);
                                // Attempt to turn the string into an integer (this is where exception is caught)
                                iAR = Integer.parseInt(sAR);
                                // If you get down here, it's a valid number, so lets call the current dimlist to do
                                // things with it
                                configIDs = Arrays.asList(ModChunkPurge.config.dimlist.split(","));
                                if (configIDs.contains(sAR)) {
                                    // Grab the dimlist
                                    AR = ModChunkPurge.config.dimlist;
                                    // Replace the removed dim with nothing
                                    AR = AR.replace(sAR, "");
                                    // Clean up the comma mess you made
                                    AR = AR.replace(",,", ",");
                                    AR = AR.replaceFirst("^,", "");
                                    AR = AR.replaceAll(",$", "");
                                    // Set the new dimlist and tell the player they did a good job
                                    ModChunkPurge.config.dimlist = AR;
                                    msg = new ChatComponentText(
                                            EnumChatFormatting.GREEN + "Removed Dimension "
                                                    + EnumChatFormatting.WHITE
                                                    + sAR
                                                    + EnumChatFormatting.GREEN
                                                    + " from Dimensions being ChunkPurged");
                                    sender.addChatMessage(msg);
                                } else {
                                    // Why are you trying to remove things that aren't there? Stop doing that...
                                    msg = new ChatComponentText(
                                            EnumChatFormatting.RED + "Dimension "
                                                    + EnumChatFormatting.WHITE
                                                    + sAR
                                                    + EnumChatFormatting.RED
                                                    + " was not in the config");
                                    sender.addChatMessage(msg);
                                }

                            }
                            msg = new ChatComponentText(
                                    EnumChatFormatting.GREEN + "ID's of dimensions now being ChunkPurged | "
                                            + EnumChatFormatting.WHITE
                                            + ModChunkPurge.config.dimlist.replace(",", ", "));
                            sender.addChatMessage(msg);
                            // SAVE your config AFTER the loop, not inside it.
                            ModChunkPurge.config.saveConfig();
                            return;
                        } catch (NumberFormatException ex) {
                            dimIntInvalid(sender);
                            return;
                        }
                    }
                    // Look, 3 more instances where the player typed something wrong
                    else {
                        invalid(sender);
                        return;
                    }
                } else {
                    invalid(sender);
                    return;
                }
            default:
                invalid(sender);
                return;
        }
    }

    // Set the permission for the command higher than the normal user. Normal user is 1? Default OP is 4.
    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}
