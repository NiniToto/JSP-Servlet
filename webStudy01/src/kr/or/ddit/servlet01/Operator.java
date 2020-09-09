package kr.or.ddit.servlet01;

@FunctionalInterface //생략 가능
public interface Operator {
	public long operate(int leftOp, int rightOp);
}
