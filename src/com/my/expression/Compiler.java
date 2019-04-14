package com.my.expression;

import com.my.expression.lexer.Lexer;
import com.my.expression.lexer.LexerException;
import com.my.expression.lexer.Token;
import com.my.expression.parser.Parser;
import com.my.expression.parser.ParserException;

import java.util.List;

public class Compiler {

    private Context context; // 编译上下文
    private Lexer lexer; // 词法分析器
    private TokenFilter tokenFilter; // 标记过滤器
    private Parser parser; // 语法分析器

    public Compiler() {
        context = new Context();
        lexer = new Lexer(context);
        parser = new Parser(context);
    }

    /**
     * 将输入文本编译为可运行的表达式
     * @param input 要编译成表达式的的文本
     * @return 编译生成的表达式
     */
    public Expression compile(String input) throws LexerException, ParserException {
        // 将输入文本识别为标记流
        List<Token> tokens = lexer.parse(input);
        // 使用设置的标记过滤器调整生成的标记流
        tokens = tokenFilter != null ? tokenFilter.filter(tokens) : tokens;
        // 将标记流编译为可运行表达式并返回
        return parser.parse(tokens.listIterator());
    }

    /**
     * 获取编译上下文
     */
    public Context getContext() {
        return context;
    }

    /**
     * 设置标记过滤器
     */
    public void setTokenFilter(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    /**
     * 标记过滤器, 用于调整生成的标记列表
     */
    public interface TokenFilter {
        List<Token> filter(List<Token> tokens);
    }
}
