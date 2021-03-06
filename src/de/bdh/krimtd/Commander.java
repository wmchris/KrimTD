package de.bdh.krimtd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Commander implements CommandExecutor {
	
	Main plugin;
	public Commander(Main plugin)
	{
		this.plugin = plugin;
	}
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if((((sender instanceof Player) && sender.hasPermission("td.admin")) || !(sender instanceof Player)) && args.length == 1 && cmd.getName().equals("td"))
		{
			this.plugin.sendall(ChatColor.RED+"A new round has started!");
			this.plugin.restart(true);
		}
		
		if(!(sender instanceof Player))
			return true;
		
		sender.sendMessage(ChatColor.AQUA+"Your Money: "+this.plugin.Money.get((Player)sender));
		sender.sendMessage(ChatColor.AQUA+"Your Income: "+this.plugin.Income.get((Player)sender));
		if(sender.hasPermission("td.admin") && args.length == 0 && cmd.getName().equals("td")) 
		{
			sender.sendMessage(ChatColor.RED+"Write /td reset to start a new round");
		}
		else if(sender.hasPermission("td.admin") && cmd.getName().equals("setmoney"))
		{
			Player plx = null;
			int amount = 0;
			if(args.length == 1)
			{
				amount = Integer.parseInt(args[0]);
				plx = (Player)sender;
			}
			else if(args.length == 2)
			{
				amount = Integer.parseInt(args[1]);
				plx = Bukkit.getServer().getPlayer(args[0]);
			}
			if(plx == null)
				sender.sendMessage(ChatColor.RED+"Player not found");
			else
			{
				this.plugin.Money.put(plx, amount);
				sender.sendMessage(ChatColor.RED+"Money of "+plx.getDisplayName()+" set to "+amount);
			}
		}
		return true;
	}
	
}
