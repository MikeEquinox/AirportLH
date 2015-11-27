package airportpackage.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import airportpackage.shared.Aircraft;

/**
 * ListDataProviderExtension4 shows Fleet on monitor screen
 * greetingService.getFleet(new AsyncCallback<List<Aircraft>>()
 */
final class ListDataProviderExtension4 extends ListDataProvider<Aircraft> {
	
	private static ListDataProviderExtension4 instance;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	final Logger clientLogger = Logger.getLogger("clientLogger");
	private int end;

	private ListDataProviderExtension4() {
	}

	public static ListDataProviderExtension4 getInstance() {
	
		if(instance == null)
		instance = new ListDataProviderExtension4();
		return instance;
	}

	@Override
	protected void onRangeChanged(HasData<Aircraft> display) {

		final int start;
		if(display.getVisibleRange().getStart()>=10) 
			start = display.getVisibleRange().getStart();
		else start = 1;
		end = start + display.getVisibleRange().getLength();
		
		greetingService.getFleet(new AsyncCallback<List<Aircraft>>() {
			@Override
			public void onFailure(Throwable caught) {
				clientLogger.log(Level.SEVERE,
						"greetingService.getFleet(), AsyncCallback failure. " + caught.getMessage());
				// Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Aircraft> aircraftList) {

				instance.setList(aircraftList);
				if (end >= aircraftList.size())
					end = aircraftList.size();

				List<Aircraft> sub = aircraftList.subList(start, end);
				updateRowData(start, sub);
				clientLogger.log(Level.INFO, "ListDataProviderExtension4 shows Fleet on monitor screen");
				clientLogger.log(Level.INFO, "greetingService.getFleet(), AsyncCallback succeded.");

			}
		});
	}
}