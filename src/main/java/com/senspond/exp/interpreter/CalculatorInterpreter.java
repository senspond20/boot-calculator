package com.senspond.exp.interpreter;
import com.senspond.exp.alntlr.CalculatorBaseVisitor;
import com.senspond.exp.alntlr.CalculatorParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorInterpreter extends CalculatorBaseVisitor<Double> {
    private static final Logger log = LoggerFactory.getLogger(CalculatorInterpreter.class);
    public CalculatorInterpreter(){}

    @Override
    public Double visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Double visitNum(CalculatorParser.NumContext ctx) {
        Double num = Double.parseDouble(ctx.getText());
        return num;
    }

    @Override
    public Double visitAddSub(CalculatorParser.AddSubContext ctx) {
        Double left =   Double.parseDouble(visit(ctx.expression(0)).toString());
        Double right =  Double.parseDouble(visit(ctx.expression(1)).toString());
        Double result = ctx.op.getType() == CalculatorParser.ADD ? left + right : left - right;
//        log.debug("{} {} {} {} = {}", ctx.op.getType() == CalculatorParser.ADD ? "더하기" : "빼기", left, ctx.op.getText(), right, result);
        return result;
    }

    @Override
    public Double visitMulDiv(CalculatorParser.MulDivContext ctx) {
        Double left = Double.parseDouble(visit(ctx.expression(0)).toString());
        Double right =  Double.parseDouble(visit(ctx.expression(1)).toString());
        Double result = ctx.op.getType() == CalculatorParser.MUL ? left * right : left / right;
//        log.debug("{} {} {} {} = {}", ctx.op.getType() == CalculatorParser.MUL ? "곱하기" : "나누기", left, ctx.op.getText(), right, result);
        return result;
    }
}
