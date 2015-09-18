package main;


import java.awt.Dimension;

import javax.swing.JFrame;

import context.arch.discoverer.Discoverer;
import context.arch.discoverer.query.AbstractQueryItem;
import context.arch.enactor.Enactor;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;
import enactors.AmbientEnactor;
import services.WarningService;
import ui.Panel;
import widgets.AmbientWidget;
import widgets.WarningWidget;

public class App {
	
	public static final String ambient = "Ambient";
	
	protected Widget ambientWidget;
	protected Widget warningWidget;
	
	protected Enactor ambientEneactor;
	
	protected Panel ui;
	protected WarningService warningService;



	

	public App() {
		super();
		
		ambientWidget = new AmbientWidget(ambient);
		warningWidget = new WarningWidget(ambient);
		warningService = new WarningService(warningWidget);
		warningWidget.addService(warningService);
		AbstractQueryItem<?,?> ambientWidgetQuery = WidgetXmlParser.createWidgetSubscriptionQuery(ambientWidget);
		AbstractQueryItem<?,?> warningWidgetQuery = WidgetXmlParser.createWidgetSubscriptionQuery(warningWidget);
		ambientEneactor = new AmbientEnactor(ambientWidgetQuery, warningWidgetQuery);
		ui = new Panel(warningService.warningLabel, ambientWidget);
	}
	
	public static void main(String[] args) {
		Discoverer.start();
		
		App app = new App();

		JFrame frame = new JFrame("ContextToolkit");
		frame.add(app.ui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(300, 200));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	
}
