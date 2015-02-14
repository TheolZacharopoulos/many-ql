from decimal import *
from antlr4 import *
from QLLexer import QLLexer
from QLParser import QLParser
from QLListener import QLListener
import customQLVisitor

class AST(object):
	def __init__(self, inputQLFile):			
		inputStream = FileStream(inputQLFile)
		lexer = QLLexer(inputStream)
		stream = CommonTokenStream(lexer)
		parser = QLParser(stream)
		visitor = customQLVisitor.CustomQLVisitor()
		tree = parser.root()
		self.root = visitor.visit(tree)

	def prettyPrint(self):
		for statement in self.root.statements:
			self._printStatement(statement, 0)

	def _printStatement(self, statement, lev):
		spaces = " " * lev
		if spaces == None:
			spaces = ""

		print(spaces + str(statement))

		if hasattr(statement, 'statements'):
			for otherStatement in statement.statements:
				self._printStatement(otherStatement, lev + 4)

class RootNode(object):
	def __init__(self, statements):
		self.statements = statements

class FormStatementNode(object):
	def __init__(self, identifier, statements, lineNumber):
		self.identifier = identifier
		self.statements = statements
		self.lineNumber = lineNumber

	def __str__(self):
		return "formId:%s, line:%d" %(self.identifier, self.lineNumber)

class QuestionStatementNode(object):
	def __init__(self, identifier, text, questionType, lineNumber, expr = None):
		self.identifier = identifier
		self.text = text
		self.type = questionType
		self.lineNumber = lineNumber
		self.expr = expr

	def __str__(self):
		return "questionId:%s, line:%d, question:%s, type:%s, expr:%s"\
			%(self.identifier, self.lineNumber, self.text, self.type, self.expr)

class IfStatementNode(object):
	def __init__(self, expr, statements, lineNumber):
		self.expr = expr
		self.statements = statements
		self.lineNumber = lineNumber
	
	def __str__(self):
		return "ifStatement, line:%d, expr:%s" %(self.lineNumber, self.expr)

class ExpressionNode(object):
	pass

class AtomicExpressionNode(ExpressionNode):
	def __init__(self, left, lineNumber):
		self.left = left
		self.lineNumber = lineNumber

	def __str__(self):
		return str(self.left)

class UnaryExpressionNode(ExpressionNode):
	def __init__(self, op, right, lineNumber):
		self.op = op
		self.right = right
		self.lineNumber = lineNumber

	def __str__(self):
		return "(%s %s)" %(self.op, self.right)

class BinaryExpressionNode(ExpressionNode):
	def __init__(self, left, op, right, lineNumber):
		self.left = left
		self.op = op
		self.right = right
		self.lineNumber = lineNumber

	def __str__(self):
		return "(%s %s %s)" %(self.left, self.op, self.right)

class Money(Decimal):
	pass

class Identifier(str):
	def __new__(cls, string, lineNumber):
		obj = str.__new__(cls, string)
		obj.lineNumber = lineNumber
		return obj