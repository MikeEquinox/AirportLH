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
 * ListDataProviderExtension3 shows Aircrafts on monitor screen
 * greetingService.getAircraft(new AsyncCallback<List<Aircraft>>()
 */
final class ListDataProviderExtension3 extends ListDataProvider<Aircraft> {
	
	private static ListDataProviderExtension3 instance;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	final Logger clientLogger = Logger.getLogger("clientLogger");
	private int end;
	
	private ListDataProviderExtension3() {
	}

	public static ListDataProviderExtension3 getInstance() {
	
		if(instance == null)
		instance = new ListDataProviderExtension3();
		return instance;
	}

	@Override
	protected void onRangeChanged(HasData<Aircraft> display) {

		final int start = display.getVisibleRange().getStart();
		end = start + display.getVisibleRange().getLength();

		greetingService.getAircraft(new AsyncCallback<List<Aircraft>>() {
			@Override
			public void onFailure(Throwable caught) {
				clientLogger.log(Level.SEVERE,
						"greetingService.getAircraft(), AsyncCallback failure. " + caught.getMessage());
				// Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Aircraft> aircraftList) {

				instance.setList(aircraftList);
				if (end >= aircraftList.size())
					end = aircraftList.size();

				List<Aircraft> sub = aircraftList.subList(start, end);
				updateRowData(start, sub);
				clientLogger.log(Level.INFO, "ListDataProviderExtension3 shows Aircrafts on monitor screen");
				clientLogger.log(Level.INFO, "greetingService.getAircraft(), AsyncCallback succeded.");

			}
		});
	}
}
