package motivation.command;

/**
 * @author :覃玉锦
 * @create :2021-01-19 16:26:00
 * 命令模式主要用于调用者和实现者解耦。例如gui的每一个按钮都是一条命令。调用者只需要专注于命令即可。
 */
public class CommandTest {
    public static void main(String[] args) {
        //命令可以选择具体实现
        Command command = new ConcreteCommand(new Receiver());
        //调用者只需要专注于命令。
        Invoke invoke = new Invoke(command);
        invoke.invoke();
    }
}

//执行者
class Receiver{
    public void action(){
        System.out.println("命令执行");
    }
}

interface Command{
    void execute();
}
//具体命令专注于实现。
class ConcreteCommand implements Command{
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(){
        receiver.action();
    }
}

//调用者只专注命令，并不专注于实现
class Invoke{
    private Command command;

    public Invoke(Command command) {
        this.command = command;
    }

    public void invoke(){
        command.execute();
    }
}
