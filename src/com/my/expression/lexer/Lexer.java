package com.my.expression.lexer;

import com.my.expression.Context;
import com.my.expression.lexer.states.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 词法分析器
 * 使用有限状态机策略, 将输入字符串识别为标记流
 */
public class Lexer {

    private final List<State> states = new ArrayList<>(); // 所有可能的状态集合
    private State state = null; // 当前状态
    private List<Token> tokens = new ArrayList<>(); // 识别出的标记

    public Lexer(Context context) {
        // 添加所有可能的状态
        states.add(new NumberState());
        states.add(new KeySymbolState());
        states.add(new OperatorState(context));
        states.add(new SpaceState());
        states.add(new LetterState(context));
    }

    /**
     * 将输入字符串识别为标记流
     */
    public List<Token> parse(String code) throws LexerException {

        for (int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);
            if (state != null) {
                if (!state.next(ch)) {
                    Token token = state.build();
                    if (token != null) {
                        tokens.add(token);
                    }
                    state = getNextState(ch);
                    state.next(ch);
                }
            } else  {
                state = getNextState(ch);
                state.next(ch);
            }
        }

        return build();
    }

    /**
     * 输出识别出的标记流
     */
    private List<Token> build() throws LexerException {
        if (state != null) {
            Token token = state.build();
            if (token != null) {
                tokens.add(token);
            }
        }
        state = null;
        List<Token> tokens = this.tokens;
        this.tokens = new ArrayList<>();
        return tokens;
    }

    /**
     * 当前状态为空时, 确定出下一个要进入的状态
     */
    private State getNextState(char ch) throws LexerException {
        for (State state : states) {
            if (state.isStartsWith(ch)) {
                return state;
            }
        }
        throw new LexerException("无法解析的字符: " + ch);
    }
}
