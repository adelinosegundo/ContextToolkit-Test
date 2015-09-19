package main;


import java.awt.Dimension;

import javax.swing.JFrame;

import context.arch.discoverer.Discoverer;
import context.arch.discoverer.query.AbstractQueryItem;
import context.arch.enactor.Enactor;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;
import enactors.AirPollutionEnactor;
import enactors.FirePresenceEnactor;
import enactors.TemperatureEnactor;
import services.WarningService;
import ui.Panel;
import widgets.AirPollutionWidget;
import widgets.FirePresenceWidget;
import widgets.TemperatureWidget;
import widgets.WarningWidget;

public class App {
	
	public static final String ambient = "Ambient";
	
	protected Widget temperatureWidget;
	protected Widget airPollutionWidget;
	protected Widget firePresenceWidget;
	protected Widget warningWidget;
	
	protected Enactor temperatureEneactor;
	protected Enactor airPollutionEneactor;
	protected Enactor firePresenceEneactor;
	
	protected Panel ui;
	protected WarningService warningService;



	

	public App() {
		super();
		
		temperatureWidget = new TemperatureWidget(ambient);
		airPollutionWidget = new AirPollutionWidget(ambient);
		firePresenceWidget = new FirePresenceWidget(ambient);
		warningWidget = new WarningWidget(ambient);
		warningService = new WarningService(warningWidget);
		warningWidget.addService(warningService);
		AbstractQueryItem<?,?> temperatureWidgetQuery = WidgetXmlParser.createWidgetSubscriptionQuery(temperatureWidget);
		AbstractQueryItem<?,?> airPollutionWidgetQuery = WidgetXmlParser.createWidgetSubscriptionQuery(airPollutionWidget);
		AbstractQueryItem<?,?> firePresenceWidgetQuery = WidgetXmlParser.createWidgetSubscriptionQuery(firePresenceWidget);
		AbstractQueryItem<?,?> warningWidgetQuery = WidgetXmlParser.createWidgetSubscriptionQuery(warningWidget);
		
		temperatureEneactor = new TemperatureEnactor(temperatureWidgetQuery, warningWidgetQuery);
		temperatureEneactor = new AirPollutionEnactor(airPollutionWidgetQuery, warningWidgetQuery);
		temperatureEneactor = new FirePresenceEnactor(firePresenceWidgetQuery, warningWidgetQuery);
		
		ui = new Panel(warningService.warningLabel, temperatureWidget, airPollutionWidget, firePresenceWidget);
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
