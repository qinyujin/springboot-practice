package com.meiju;

/**
 * @author :覃玉锦
 * @create :2020-10-09 12:03:00
 */
public class EnumTestApplication {
    public static void main(String[] args) {
        /*System.out.println("请选择你喜欢的季节查看他们的介绍：");
        String season;
        System.out.println("A:" + com.meiju.season.SPRING.getSeasonName() +
                "\nB:" + com.meiju.season.SUMMER.getSeasonName() +
                "\nC:" + com.meiju.season.AUTUMN.getSeasonName() +
                "\nD:" + com.meiju.season.WINTER.getSeasonName());
        Scanner choose = new Scanner(System.in);
        String next = choose.next();
        System.out.println("您的输入:"+next);
        switch (next) {
            case "A":
                System.out.println(com.meiju.season.SPRING.getSeasonDesc());
                break;
            case "B":
                System.out.println(com.meiju.season.SUMMER.getSeasonDesc());
                break;
            case "C":
                System.out.println(com.meiju.season.AUTUMN.getSeasonDesc());
                break;
            case "D":
                System.out.println(com.meiju.season.WINTER.getSeasonDesc());
                break;
            default:
                System.out.println("wrong input!");
                break;
        }*/

        season[] values = season.values();
        for (season value : values) {
            System.out.println(value);
            value.show();
        }
    }
}

interface info{
    void show();
}

enum season implements info{

    SPRING("春天", "春暖花开的季节"){
        @Override
        public void show() {
            System.out.println("一年之计在于春");
        }
    },
    SUMMER("夏天", "贵阳是凉爽之都"),
    AUTUMN("秋天", "秋风瑟瑟的时候"),
    WINTER("冬天", "冰雪纷飞的日子");

    private final String seasonName;

    private final String seasonDesc;

    season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public void show() {
        System.out.println("这个季节是："+this.seasonName);
    }
}