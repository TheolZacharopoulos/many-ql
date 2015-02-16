package interpreter

import (
	"log"

	"github.com/software-engineering-amsterdam/many-ql/carlos.cirello/ast"
)

func (exec Execute) resolveMathNode(n ast.Evaluatable) float32 {
	switch t := n.(type) {
	default:
		log.Fatalf("Unknown type while resolving node %T", t)
	case *ast.MathAddNode:
		return exec.MathAddNode(n.(*ast.MathAddNode))
	case *ast.MathSubNode:
		return exec.MathSubNode(n.(*ast.MathSubNode))
	case *ast.MathMulNode:
		return exec.MathMulNode(n.(*ast.MathMulNode))
	case *ast.MathDivNode:
		return exec.MathDivNode(n.(*ast.MathDivNode))
	case *ast.TermNode:
		return exec.MathTermNode(n.(*ast.TermNode))
	}
	return 0
}

func (exec *Execute) resolveNumeric(t *ast.TermNode) float32 {
	node := exec.resolveTermNode(t)
	var nodeVal float32
	switch t := node.(type) {
	default:
		log.Fatalf("Type impossible to execute comparison. got: %T", t)
	case int:
		nodeVal = float32(node.(int))
	case float32:
		nodeVal = node.(float32)
	}
	return nodeVal
}

func (exec *Execute) resolveTermNode(t *ast.TermNode) interface{} {
	identifier := t.IdentifierReference
	if identifier != "" {
		ret := make(chan *ast.QuestionNode)
		exec.symbolChan <- &symbolEvent{
			command: SymbolRead,
			name:    identifier,
			ret:     ret,
		}

		q := <-ret

		switch q.Type() {
		case ast.BoolQuestionType:
			content := q.Content().(*ast.BoolQuestion)
			return content.Value()
		case ast.IntQuestionType:
			content := q.Content().(*ast.IntQuestion)
			return content.Value()
		case ast.StringQuestionType:
			content := q.Content().(*ast.StringQuestion)
			return content.String()
		}
	}
	return t.NumericConstant
}

func (exec *Execute) resolveComparisonNode(n interface{}) bool {
	conditionState := true
	switch t := n.(type) {
	default:
		log.Fatalf("impossible condition type. got: %T", t)
	case *ast.TermNode:
		if !exec.TermNode(n.(*ast.TermNode)) {
			conditionState = false
		}
	case *ast.EqualsNode:
		if !exec.EqualsNode(n.(*ast.EqualsNode)) {
			conditionState = false
		}
	case *ast.MoreThanNode:
		if !exec.MoreThanNode(n.(*ast.MoreThanNode)) {
			conditionState = false
		}
	case *ast.LessThanNode:
		if !exec.LessThanNode(n.(*ast.LessThanNode)) {
			conditionState = false
		}
	case *ast.MoreOrEqualsThanNode:
		if !exec.MoreOrEqualsThanNode(n.(*ast.MoreOrEqualsThanNode)) {
			conditionState = false
		}
	case *ast.LessOrEqualsThanNode:
		if !exec.LessOrEqualsThanNode(n.(*ast.LessOrEqualsThanNode)) {
			conditionState = false
		}
	}
	return conditionState
}
