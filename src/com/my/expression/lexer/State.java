package com.my.expression.lexer;

/**
 * 状态接口
 */
public interface State {

    /**
     * 输入字符开始是否可以作为状态开始
     */
    boolean isStartsWith(char ch);

    /**
     * 处理下一个字符
     * @return 状态是否结束
     */
    boolean next(char ch) throws LexerException;

    /**
     * 将读取的字符识别为标记
     */
    Token build() throws LexerException;
}
