import QL.Grammar.constants as constants
import QL.AST.Expressions.Operations.binary_expression as b


class Equal(b.BinaryExpression):

    # get the return _type of the _expression
    def return_type_string(self, type_dict):
        return constants.BOOL

    # override as equal is allowed to have types on both sides which are not booleans
    def is_valid_expression_message(self, td):
        message = ""
        message += self._operand1.is_valid_expression_message(td)
        message += self._operand2.is_valid_expression_message(td)
        if self._operand1.return_type_string(td) != self._operand2.return_type_string(td):
            message += self._operand1.pretty_print() + " is not the same type as " + self._operand2.pretty_print() + "\n"
        return message