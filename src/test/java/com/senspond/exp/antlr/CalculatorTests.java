package com.senspond.exp.antlr;
import com.senspond.exp.alntlr.CalculatorLexer;
import com.senspond.exp.alntlr.CalculatorParser;
import com.senspond.exp.interpreter.CalculatorInterpreter;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

public class CalculatorTests {

    private static CalculatorParser getParserTreeFromExp(String exp){
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(exp));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        return parser;
    }
    private static CalculatorInterpreter calculator(String exp){
        CalculatorParser parser = getParserTreeFromExp(exp);
        ParseTree tree = parser.expression();
        CalculatorInterpreter interpreter = new CalculatorInterpreter(tree);
        return interpreter;
    }

    private static void calculaterTest(String exp){
        System.out.println("[Input ] : " + exp);
        CalculatorInterpreter interpreter = calculator(exp);
        interpreter.history().forEach((k,v)-> System.out.println(k + ": " + v));
        System.out.println("[result] : " + interpreter.result() + "\n");
    }

    @Test
    public void test1() {
        calculaterTest("21+2-4*2");
        calculaterTest("21+(2-4)*2");
        calculaterTest("5*(2+3)/4");
        calculaterTest("5*(2+3)/4+5-3*10-(3+5)");
        calculaterTest("5*(2+3)/4+(5-3)*10-(3+5)");
    }
}
