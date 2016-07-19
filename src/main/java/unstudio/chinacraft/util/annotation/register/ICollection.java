package unstudio.chinacraft.util.annotation.register;


public interface ICollection {
    /**
     * @param input 输入的是在类中将被注册的
     * @return
     */
    boolean canInvoker(Object input);
}
