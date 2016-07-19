package unstudio.chinacraft.common;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trychen on 16/7/10.
 */
public class Command implements ICommand {
	@Override
	public String getCommandName() {
		return "chinacraft";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return "chinacraft <input>";
	}

	@Override
	public List getCommandAliases() {
		List list = new ArrayList();
		return list;
	}

	@Override
	public void processCommand(ICommandSender iCommandSender, String[] args) {
		if (args.length == 2) {

		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
