package com.meiju;

/**
 * @author :覃玉锦
 * @create :2020-10-09 17:19:00
 */
public enum Week {
    Monday("周一需要吃鱼"),
    baiTuesday("周二需要被单词"),
    Wednesday("周三冥想"),
    Thursday("周四看脑筋急转弯"),
    Friday("周五背购物清单"),
    Saturday("周六蒙眼洗澡"),
    Sunday("周天散步填数字");

    private final String plan;

    Week(String plan) {
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }
}
