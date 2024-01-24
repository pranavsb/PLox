package com.craftinginterpreters.lox;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReversePolishNotationPrinterTest {

    private static ReversePolishNotationPrinter printer;

    @BeforeAll
    public static void setUp() {
        printer = new ReversePolishNotationPrinter();
    }

    @Test
    void testSimpleUnaryExpr() {
        // -5
        Expr e = new Expr.Unary(new Token(TokenType.MINUS, "-", null, 1),
                new Expr.Literal(5));

        assertEquals("5 -", printer.print(e));
    }

    @Test
    void testSimpleBinaryExpr() {
        // 2 + 3
        Expr e = new Expr.Binary(new Expr.Literal(2),
                new Token(TokenType.PLUS, "+", null, 1),
                new Expr.Literal(3));

        assertEquals("2 3 +", printer.print(e));
    }

    @Test
    void testCompoundBinaryExpr() {
        // (1 + 2) * (4 - 3)
        Expr onePlusTwo = new Expr.Binary(new Expr.Literal(1),
                new Token(TokenType.PLUS, "+", null, 1),
                new Expr.Literal(2));
        Expr fourMinusThree = new Expr.Binary(new Expr.Literal(4),
                new Token(TokenType.MINUS, "-", null, 1),
                new Expr.Literal(3));
        Expr e = new Expr.Binary(onePlusTwo,
                new Token(TokenType.STAR, "*", null, 1),
                fourMinusThree);

        assertEquals("1 2 + 4 3 - *", printer.print(e));
    }
}