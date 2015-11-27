package airportpackage.client;

import airportpackage.shared.Fleet;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

/**
 * ListDataProviderExtension8 shows registration data of Fleet on monitor
 * screen greetingService.getAerofleet(new AsyncCallback<List<Fleet>>()
 */
final class ListDataProviderExtension8 extends ListDataProvider<Fleet> {
	
	private static ListDataProviderExtension8 instance;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	final Logger clientLogger = Logger.getLogger("clientLogger");
	private int end;
	
	private ListDataProviderExtension8() {
	}

	public static ListDataProviderExtension8 getInstance() {
	
		if(instance == null)
		instance = new ListDataProviderExtension8();
		return instance;
	}
	
	@Override
	protected void onRangeChanged(HasData<Fleet> display) {
		
		final int start;
		start = display.getVisibleRange().getStart();
		end = start + display.getVisibleRange().getLength();
		
		greetingService.getAerofleet(new AsyncCallback<List<Fleet>>() {
			@Override
			public void onFailure(Throwable caught) {
				clientLogger.log(Level.SEVERE,
						"greetingService.getAeroFleet(), AsyncCallback failure. " + caught.getMessage());
				// Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Fleet> fleetList) {

				//provider8
				instance.setList(fleetList);
				end = end >= fleetList.size() ? fleetList.size() : end;
				List<Fleet> sub = fleetList.subList(start, end);
				updateRowData(start, sub);
				clientLogger.log(Level.INFO,
						"ListDataProviderExtension8 shows registration data of Fleet on monitor screen");
				clientLogger.log(Level.INFO, "greetingService.getAeroFleet(), AsyncCallback succeded.");

			}
		});
	}
}
