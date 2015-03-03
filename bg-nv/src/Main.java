import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import lang.ql.ast.form.Form;
import lang.ql.ast.type.DecType;
import lang.ql.ast.type.IntType;
import lang.ql.ast.type.Type;
import lang.ql.gui.Modeler;
import lang.ql.gui.SimpleGui;
import lang.ql.gui.canvas.Canvas;
import lang.ql.semantics.*;
import lang.ql.ast.AstBuilder;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;

import java.io.IOException;

import lang.ql.gen.*;

public class Main extends Application
{
    private static Form ast;
//    private static ValueTable values;

    public static void main(String[] args)
    {
        new JFXPanel(); //TODO: figure out why all hell breaks loose without this statement

        try
        {
            CharStream stream = new ANTLRFileStream("src/lang/tests/formInput");
            QLLexer lexer = new QLLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);
            ParserRuleContext tree = parser.form();

            AstBuilder visitor = new AstBuilder();
            ast = (Form) visitor.visit(tree);

            TypeChecker.check(ast);

            IntType i = new IntType();
            DecType d = new DecType();

            Type r = i.promoteTo(d);
            Type r2 = d.promoteInt(i);
            System.out.print(r2);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        SimpleGui.run(ast, primaryStage);
    }
}
