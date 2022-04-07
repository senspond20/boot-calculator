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
    private static Double calculator(String exp){
        CalculatorParser parser = getParserTreeFromExp(exp);
        ParseTree tree = parser.expression();
        Double query = new CalculatorInterpreter().visit(tree);
        return query;
    }
    @Test
    public void test1(){
        String exp = "21+2-4*2";
        System.out.println(exp + " = " + calculator(exp));
    }
    @Test
    public void test2(){
        String exp = "21+(2-4)*2";
        System.out.println(exp + " = " + calculator(exp));
    }
    @Test
    public void test3(){
        String exp = "6/2-4";
        System.out.println(exp + " = " + calculator(exp));
    }
    @Test
    public void test4(){
        String exp = "5*(2+3)/4";
        System.out.println(exp + " = " + calculator(exp));
    }
    @Test
    public void test5(){
        String exp = "5*(2+3)/4 + 5-3*10-(3+5)";
        System.out.println(exp + " = " + calculator(exp));
    }
}
