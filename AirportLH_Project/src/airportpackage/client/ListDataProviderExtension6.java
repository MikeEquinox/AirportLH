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
 * ListDataProviderExtension6 shows selected Crew on monitor screen
 * greetingService.getCrewListById(forwardCrewId, new AsyncCallback<List
 * <Staff>>()
 */
final class ListDataProviderExtension6 extends ListDataProvider<Staff> {
	
	private static ListDataProviderExtension6 instance;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	final Logger clientLogger = Logger.getLogger("clientLogger");
	private int end;
	static int forwardCrewId6;

	private ListDataProviderExtension6() {
	}

	public static ListDataProviderExtension6 getInstance(int forwardCrewId) {
	
		instance = new ListDataProviderExtension6();
		forwardCrewId6 = forwardCrewId;

		return instance;
	}
	@Override
	protected void onRangeChanged(HasData<Staff> display) {

		clientLogger.log(Level.INFO, "ListDataProvider6: " + forwardCrewId6);
		final int start = display.getVisibleRange().getStart();
		end = start + display.getVisibleRange().getLength();

		greetingService.getCrewListById(forwardCrewId6, new AsyncCallback<List<Staff>>() {
			@Override
			public void onFailure(Throwable caught) {
				clientLogger.log(Level.SEVERE,
						"greetingService.getCrewListById(), AsyncCallback failure. " + caught.getMessage());
				// Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Staff> staffList) {

				instance.setList(staffList);
				end = end >= staffList.size() ? staffList.size() : end;
				List<Staff> sub = staffList.subList(start, end);
				updateRowData(start, sub);
				clientLogger.log(Level.INFO, "ListDataProviderExtension6 shows Crew on monitor screen");
				clientLogger.log(Level.INFO, "greetingService.getCrewListById(), AsyncCallback succeded.");

			}
		});
	}
}