package unstudio.chinacraft.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlocksChecker {

	private BlockRule[][][] data;

	private int widthX;
	private int widthZ;
	private int height;

	private int offsetX;
	private int offsetZ;
	private int offsetY;
	
	private BlocksChecker() {}

	/**
	 * 构造一个方块结构检查者
	 * 例：
	 * new BlocksChecker(new String[][]{{"AAA",
	 *                                                          "AAA",
	 *                                                          "AAA"},
	 *                                                          
	 * 															{"AAA",
	 * 															 "ABA",
	 *                                                          "AAA"},
	 *                                                          
	 *                                                          {"AAA",
	 *                                                          "AAA",
	 *                                                          "AAA"}},
	 *                                                          "A",new BlockRule(Blocks.stone,0},"B",new BlockRule(Blocks.air,0});
	 * 构造一个中空3X3X3石头结构
	 * @param data 输入一个二维String数组，分别为Y，Z，X
	 * @param args 输入每个字符所代表的BlockRule
	 */
	public BlocksChecker(String[][] data, Object... args) {
		this.setRule(data, args);
	}

	public void setRule(String[][] blocks, Object... args) {
		this.height = blocks.length;
		this.widthX = blocks[0].length;
		this.widthZ = blocks[0][0].length();

		Map<Character, BlockRule> target = new HashMap<Character, BlockRule>();
		Character key = null;
		for (Object arg : args) {
			if (key == null)
				key = (Character) arg;
			else {
				target.put(key, (BlockRule) arg);
				key = null;
			}
		}

		this.data = new BlockRule[this.height][this.widthX][this.widthZ];
		for (int y = 0; y < this.height; y++) {
			String[] data = blocks[y];
			for (int z = 0; z < this.widthZ; z++) {
				char[] array = data[z].toCharArray();
				for (int x = 0; x < this.widthX; x++) {
					char character = array[x];
					if (character == ' ')
						continue;

					this.data[y][z][x] = target.get(character);
				}
			}
		}
	}

	/**
	 * 设置方块结构检查者偏移量
	 * @param x X坐标
	 * @param y Y坐标
	 * @param z Z坐标
	 * @return 方块结构检查者
	 */
	public BlocksChecker setOffset(int x, int y, int z) {
		this.offsetX = x;
		this.offsetY = y;
		this.offsetZ = z;

		return this;
	}

	public int getOffsetX() {
		return this.offsetX;
	}

	public int getOffsetY() {
		return this.offsetY;
	}

	public int getOffsetZ() {
		return this.offsetZ;
	}

	/**
	 * 检查该方块
	 * @param world 世界
	 * @param X X坐标
	 * @param Y Y坐标
	 * @param Z Z坐标
	 * @return 如果为真，则该结构是指定结构
	 */
	public boolean check(World world,int X,int Y,int Z) {
		X+=this.offsetX;
		Y+=this.offsetY;
		Z+=this.offsetZ;

		for (int y = 0; y < this.height; y++)
			for (int z = 0; z < this.widthZ; z++)
				for (int x = 0; x < this.widthX; x++) {
					BlockRule rule = this.data[y][z][x];

					if (rule != null && !rule.check(world,X+x, Y-y, Z+z))
						return false;
				}

		return true;
	}
	
	public BlocksChecker copy() {
		BlocksChecker object = new BlocksChecker();
		object.data = this.data;
		return object;
	}

	public class BlockRule {

		private Block block;
		private int data;
		
		public BlockRule(Block block) {
			this.setRule(block, 0);
		}

		public BlockRule(Block block, int data) {
			this.setRule(block, data);
		}

		public Block getBlock() {
			return this.block;
		}

		public int getData() {
			return this.data;
		}

		public void setRule(Block block, int data) {
			this.block = block;
			this.data = data;
		}

		public boolean check(World world,int X,int Y,int Z) {
			return this.check(world.getBlock(X, Y, Z),world.getBlockMetadata(X, Y, Z));
		}

		public boolean check(Block block,int data) {
			if (block == null)
				return false;
			if (block != this.block)
				return false;
			if (this.data != -1 && data != this.data)
				return false;

			return true;
		}

		public BlockRule copy() {
			return new BlockRule(this.block, this.data);
		}
	}
}
