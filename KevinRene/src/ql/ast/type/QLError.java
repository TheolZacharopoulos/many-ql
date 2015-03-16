package ql.ast.type;

import ql.ast.QLType;
import ql.ast.visitor.TypeVisitor;

public class QLError extends QLType {
	public QLError() {}
	
	@Override
	public <T> T accept(TypeVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return "error";
	}
	
	/**
	 * Type compatibilities
	 */

	@Override
	public boolean add(QLType argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addNumeric(QLNumeric argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addString(QLString argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean divide(QLType argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean divideNumeric(QLNumeric argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean multiply(QLType argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean multiplyNumeric(QLNumeric argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean subtract(QLType argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean subtractNumeric(QLNumeric argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean not() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean positive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean negative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean or(QLType rightValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean orBoolean(QLBoolean argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notEqualTo(QLType argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notEqualToBoolean(QLBoolean argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notEqualToNumeric(QLNumeric argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notEqualToString(QLString argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lowerThan(QLType argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lowerThanNumeric(QLNumeric argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lowerOrEqual(QLType argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lowerOrEqualNumeric(QLNumeric argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean greaterThan(QLType argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean greaterThanNumeric(QLNumeric argument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean greaterOrEqual(QLType argument) {
		return false;
	}

	@Override
	public boolean greaterOrEqualThanNumeric(QLNumeric argument) {
		return false;
	}

	@Override
	public boolean equalTo(QLType argument) {
		return false;
	}

	@Override
	public boolean equalToBoolean(QLBoolean argument) {
		return false;
	}

	@Override
	public boolean equalToNumeric(QLNumeric argument) {
		return false;
	}

	@Override
	public boolean equalToString(QLString argument) {
		return false;
	}

	@Override
	public boolean and(QLType argument) {
		return false;
	}

	@Override
	public boolean andBoolean(QLBoolean argument) {
		return false;
	}
	
}
