package nl.uva.sc.encoders.qls.runtime.ui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import nl.uva.sc.encoders.ql.validation.SyntaxError;
import nl.uva.sc.encoders.ql.validation.TypeValidation;
import nl.uva.sc.encoders.qlruntime.ui.ValidationsUI;
import nl.uva.sc.encoders.qls.ast.Stylesheet;
import nl.uva.sc.encoders.qls.parser.StylesheetParser;
import nl.uva.sc.encoders.qls.parser.StylesheetParsingResult;
import nl.uva.sc.encoders.qls.validation.TypeChecker;

import org.controlsfx.dialog.ExceptionDialog;

public class ParseButtonHandler implements EventHandler<ActionEvent> {

	private final StackPane stackPane;
	private final String inputFilePath;

	public ParseButtonHandler(StackPane stackPane, String inputFilePath) {
		this.stackPane = stackPane;
		this.inputFilePath = inputFilePath;
	}

	public StackPane getStackPane() {
		return stackPane;
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			URL resource = getURL(inputFilePath);
			File file;
			if (resource != null) {
				file = new File(resource.toURI());
			} else {
				file = new File(inputFilePath);
			}

			String absolutePath = file.getAbsolutePath();
			StylesheetParser stylesheetParser = new StylesheetParser();
			StylesheetParsingResult stylesheetParsingResult = stylesheetParser.parse(absolutePath);

			Node node = determineNodeToShow(stylesheetParsingResult);
			ObservableList<Node> stackPaneChildren = stackPane.getChildren();
			stackPaneChildren.clear();
			stackPaneChildren.add(node);

		} catch (IOException | URISyntaxException e) {
			ExceptionDialog dialog = new ExceptionDialog(e);
			dialog.show();
			e.printStackTrace();
		}
	}

	private URL getURL(String path) {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource(path);
	}

	private Node determineNodeToShow(StylesheetParsingResult stylesheetParsingResult) {
		List<SyntaxError> syntaxErrors = stylesheetParsingResult.getSyntaxErrors();
		if (!syntaxErrors.isEmpty()) {
			ValidationsUI validationsUIFactory = new ValidationsUI();
			return validationsUIFactory.generateUI(syntaxErrors);
		}

		Stylesheet stylesheet = stylesheetParsingResult.getStylesheet();
		TypeChecker typeChecker = new TypeChecker(stylesheet);
		List<TypeValidation> typeValidations = typeChecker.checkTypes();
		if (!typeValidations.isEmpty()) {
			ValidationsUI validationsUIFactory = new ValidationsUI();
			return validationsUIFactory.generateUI(typeValidations);
		}

		// How will we refer to the QL environment?

		StylesheetUI stylesheetUIFactory = new StylesheetUI();
		String stylesheetTitle = stylesheet.getName();
		return stylesheetUIFactory.generateUI(stylesheetTitle);
	}
}