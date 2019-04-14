package com.my.calculator.utils.lexer;

import com.my.calculator.utils.lexer.states.LetterState;
import com.my.calculator.utils.lexer.states.NumberState;
import com.my.calculator.utils.lexer.states.OperatorState;
import com.my.calculator.utils.lexer.states.SpaceState;
import com.my.calculator.utils.ParserException;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final List<State> states = new ArrayList<>();
    private List<Token> tokens = new ArrayList<>();
    private State state = null;

    public Lexer() {
        states.add(new NumberState());
        states.add(new OperatorState());
        states.add(new SpaceState());
        states.add(new LetterState());
    }

    public List<Token> parse(String code) throws ParserException {

        for (int i = 0; i < code.length(); i++) {
            next(code.charAt(i));
        }

        return build();
    }

    private void next(char ch) throws ParserException {
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

    private List<Token> build() throws ParserException {
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

    private State getNextState(char ch) throws ParserException {
        for (State state : states) {
            if (state.isStartsWith(ch)) {
                return state;
            }
        }
        throw new ParserException("无法解析的字符: " + ch);
    }
}
