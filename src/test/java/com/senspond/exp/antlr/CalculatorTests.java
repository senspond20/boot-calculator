package com.senspond.exp.antlr;
import com.senspond.exp.alntlr.CalculatorLexer;
import com.senspond.exp.alntlr.CalculatorParser;
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

    @Test
    public void test(){
        CalculatorParser parser = getParserTreeFromExp("21+2-4*2");
        ParseTree tree = parser.expression();
        System.out.println(tree.toStringTree(parser));
    }
}
