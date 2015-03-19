package qls.ast.visitor.pagebuilder;

import java.util.List;

import ql.ast.visitor.TypeVisitor;
import ql.gui.UIComponent;
import qls.ast.Statement;
import qls.ast.statement.Page;
import qls.ast.statement.Question;
import qls.ast.statement.Section;
import qls.ast.statement.Stylesheet;
import qls.ast.visitor.ExpressionVisitor;
import qls.ast.visitor.ProcessedCache;
import qls.ast.visitor.StatementVisitor;
import qls.ast.visitor.WidgetEnvironment;
import qls.ast.visitor.domaincreator.ConditionalDomain;
import qls.gui.structure.TabbedPanel;
import qls.gui.structure.UIPage;
import qls.gui.structure.UISection;

public class PageBuilder extends StatementVisitor<UIComponent> implements ExpressionVisitor<Void>, TypeVisitor<Void> {
	private ProcessedCache<UIComponent> processedComponents;
	private WidgetEnvironment widgetEnvironment;
	private List<ConditionalDomain> domains;
	
	private PageBuilder(List<ConditionalDomain> domains, WidgetEnvironment widgetEnvironment) {
		super.setExpressionVisitor(this);
		super.setTypeVisitor(this);
		
		this.domains = domains;
		this.widgetEnvironment = widgetEnvironment;
		processedComponents = new ProcessedCache<UIComponent>();
	}
	
	public static UIComponent build(Statement tree, List<ConditionalDomain> domains, WidgetEnvironment widgetEnvironment) {
		PageBuilder builder = new PageBuilder(domains, widgetEnvironment);
		
		return tree.accept(builder);
	}
	
	private void decreaseScope() {
		processedComponents = new ProcessedCache<UIComponent>(processedComponents);
	}
	
	private void increaseScope() {
		processedComponents = processedComponents.getParent();
	}
	
	@Override
	public UIComponent visit(Page pageNode) {
		decreaseScope();
		super.visit(pageNode);
		UIPage page = new UIPage(pageNode.getIdentifier());
		page.setSections(processedComponents.getProcessedObjects());
		increaseScope();
		
		processedComponents.addObject(page);
		return null;
	}

	@Override
	public UIComponent visit(Question questionNode) {
		processedComponents.addObject(widgetEnvironment.resolve(questionNode.getIdentifier()));
		
		return null;
	}

	@Override
	public UIComponent visit(Section sectionNode) {
		decreaseScope();
		super.visit(sectionNode);
		UISection section = new UISection(sectionNode.getHeader());
		section.setComponents(processedComponents.getProcessedObjects());
		increaseScope();
		
		processedComponents.addObject(section);
		return null;
	}

	@Override
	public UIComponent visit(Stylesheet stylesheetNode) {
		decreaseScope();
		super.visit(stylesheetNode);
		TabbedPanel stylesheet = new TabbedPanel(domains);
		stylesheet.setPages(processedComponents.getProcessedObjects());
		increaseScope();
		
		return stylesheet;
	}
}
