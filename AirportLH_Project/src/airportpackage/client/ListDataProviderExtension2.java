package airportpackage.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import airportpackage.shared.Staff;

/**
 * ListDataProviderExtension2 shows Crew list on monitor screen
 * greetingService.getCrewList(new AsyncCallback<List<Staff>>()
 */
final class ListDataProviderExtension2 extends ListDataProvider<Staff> {
	
	private static ListDataProviderExtension2 instance;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	final Logger clientLogger = Logger.getLogger("clientLogger");
	private int end;

	private ListDataProviderExtension2() {
	}

	public static ListDataProviderExtension2 getInstance() {
	
		if(instance == null)
		instance = new ListDataProviderExtension2();
		return instance;
	}
	@Override
	protected void onRangeChanged(HasData<Staff> display) {

		// display.setRowCount(47);
		final int start = display.getVisibleRange().getStart();
		end = start + display.getVisibleRange().getLength();

		greetingService.getCrewList(new AsyncCallback<List<Staff>>() {
			@Override
			public void onFailure(Throwable caught) {
				clientLogger.log(Level.SEVERE,
						"greetingService.getCrewList(), AsyncCallback failure. " + caught.getMessage());
				// Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Staff> staffList) {

				instance.setList(staffList);
				end = end >= staffList.size() ? staffList.size() : end;
				List<Staff> sub = staffList.subList(start, end);
				updateRowData(start, sub);
				clientLogger.log(Level.INFO, "ListDataProviderExtension2 shows Crew list on monitor screen");
				clientLogger.log(Level.INFO, "greetingService.getCrewList(), AsyncCallback succeded.");

			}
		});
	}
}
