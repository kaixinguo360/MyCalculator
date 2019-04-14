MyCalculator
=======

Java写的一个计算器小程序

GUI
--------
入口类: FrameCalculator

API
-------
#### StringCalculator
文本界面的计算器
```java
import com.my.calculator.StringCalculator;

public class Test {
    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();
        String in = "sin(PI/2)";
        String out = calculator.calculate(in);
        System.out.println(out);
    }
}
```

输出:
```
1.000000
```

#### Expression & Compiler
可运行表达式 & 编译器
```java
import com.my.expression.Compiler;
import com.my.expression.*;
import com.my.expression.lexer.LexerException;
import com.my.expression.parser.ParserException;

public class Test {
    public static void main(String[] args) {

        // 新建编译器
        Compiler compiler = new Compiler();
        // 获取编译上下文
        Context context = compiler.getContext();

        // 添加常量 (不区分大小写)
        context.addConstant("PI", Math.PI);
        context.addConstant("E", Math.E);
        // 添加自定义运算符 (仅支持二元运算符)
        context.addOperator(new CustomOperator("+", ins -> ins[0] + ins[1]));
        context.addOperator(new CustomOperator("mod", ins -> ins[0] % ins[1]));
        // 添加自定义函数 (不区分大小写)
        context.addFunction(new CustomFunction("sin", ins -> Math.sin(ins[0]))); // 一元函数
        context.addFunction(new CustomFunction("max", 2, ins -> Math.max(ins[0], ins[1]))); // 二元函数

        try {
            // 将字符串编译成可运行的表达式
            Expression expr = compiler.compile("sin(PI/2)");
            // 打印表达式计算结果
            System.out.println(String.format("%f", expr.getValue()));
        } catch (LexerException | ParserException | CalculateException e) {
            // 打印错误信息
            System.out.println(e.getMessage());
        }
    }
}
```

输出:
```
1.000000
```