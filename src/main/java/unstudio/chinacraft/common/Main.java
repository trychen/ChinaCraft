package unstudio.chinacraft.common;

import javax.swing.*;

/**
 * Created by trychen on 16/10/21.
 */
public class Main {
    public static void main(String[] args) {
        // 当直接打开Mod时提示信息
        JOptionPane.showMessageDialog(null, new String[]{"This is a Minecraft Forge Mod , you can't run it!","这是一个Forge Mod，你不能直接运行！","これはForge Mod、あなたは直接実行！","c'est un Minecraft Forge Mod, tu ne peux pas faire ça!"},
                "Chinacraft : mccraft.cn", JOptionPane.OK_OPTION);
        System.exit(0);
    }
}
