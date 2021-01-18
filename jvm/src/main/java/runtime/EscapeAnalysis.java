package runtime;

/**
 * @author :覃玉锦
 * @create :2021-01-13 23:34:00
 * 逃逸分析，判断方法中new的对象是否会被方法外引用
 */
public class EscapeAnalysis {
    public EscapeAnalysis obj;

    /**
     * 发生逃逸，可能有new的对象返回了
     * @return
     */
    public EscapeAnalysis getInstance(){
        return obj==null?new EscapeAnalysis():obj;
    }

    /**
     * 发生逃逸
     */
    public void setObj(){
        this.obj = new EscapeAnalysis();
    }

    /**
     * 没有发生逃逸
     */
    public void useEscapeAnalysis(){
        EscapeAnalysis e = new EscapeAnalysis();
    }

    /**
     * 调用了getInstance方法，发生逃逸
     */
    public void useEscapeAnalysis1(){
        EscapeAnalysis e = getInstance();
    }
}
