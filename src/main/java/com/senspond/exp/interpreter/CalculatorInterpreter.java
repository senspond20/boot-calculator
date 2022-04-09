package com.senspond.exp.interpreter;
import com.senspond.exp.alntlr.CalculatorBaseVisitor;
import com.senspond.exp.alntlr.CalculatorParser;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Accessors(chain = true, fluent = true)
public class CalculatorInterpreter extends CalculatorBaseVisitor<Double> {
    private static final Logger log = LoggerFactory.getLogger(CalculatorInterpreter.class);
    private int count;
    private Map<Integer,String> history;
    private Double result;
    private CalculatorInterpreter(){}

    public CalculatorInterpreter(ParseTree tree ){
        this.count = 1;
        this.history = new HashMap<>();
        this.result = super.visit(tree);
    }
    private void putItemToHistory(Double left, String op, Double right, Double result){
        history.put(count++, String.format("%s %s %s = %s", left, op, right, result));
    }

    @Override
    public Double visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Double visitNum(CalculatorParser.NumContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    @Override
    public Double visitAddSub(CalculatorParser.AddSubContext ctx) {
        Double left =   Double.parseDouble(visit(ctx.expression(0)).toString());
        Double right =  Double.parseDouble(visit(ctx.expression(1)).toString());
        Double result = ctx.op.getType() == CalculatorParser.ADD ? left + right : left - right;
        putItemToHistory(left, ctx.op.getText(), right, result);
        return result;
    }

    @Override
    public Double visitMulDiv(CalculatorParser.MulDivContext ctx) {
        Double left = Double.parseDouble(visit(ctx.expression(0)).toString());
        Double right =  Double.parseDouble(visit(ctx.expression(1)).toString());
        Double result = ctx.op.getType() == CalculatorParser.MUL ? left * right : left / right;
        putItemToHistory(left, ctx.op.getText(), right, result);
        return result;
    }
}
