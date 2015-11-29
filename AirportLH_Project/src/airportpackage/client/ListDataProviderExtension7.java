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
 * ListDataProviderExtension7 shows Staff in selected Crew on monitor screen
 * greetingService.getStaffByPosition("" + forwardStaffPosition, new
 * AsyncCallback<List<Staff>>()
 */
final class ListDataProviderExtension7 extends ListDataProvider<Staff> {
	
	private static ListDataProviderExtension7 instance;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	final Logger clientLogger = Logger.getLogger("clientLogger");
	private int end;
	static String forwardStaffPosition7;
	
	private ListDataProviderExtension7() {
	}

	public static ListDataProviderExtension7 getInstance(String forwardStaffPosition) {
	
		instance = new ListDataProviderExtension7();
		forwardStaffPosition7 = forwardStaffPosition;

		return instance;
	}
	@Override
	protected void onRangeChanged(HasData<Staff> display) {

		clientLogger.log(Level.INFO, "ListDataProvider7: " + forwardStaffPosition7);
		final int start = display.getVisibleRange().getStart();
		end = start + display.getVisibleRange().getLength();

		greetingService.getStaffByPosition("" + forwardStaffPosition7, new AsyncCallback<List<Staff>>() {
			@Override
			public void onFailure(Throwable caught) {
				clientLogger.log(Level.SEVERE,
						"greetingService.getStaffByPosition(), AsyncCallback failure. " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Staff> staffList) {

				instance.setList(staffList);
				end = end >= staffList.size() ? staffList.size() : end;
				List<Staff> sub = staffList.subList(start, end);
				updateRowData(start, sub);
				clientLogger.log(Level.INFO,
						"ListDataProviderExtension7 shows Staff in selected Crew on monitor screen");
				clientLogger.log(Level.INFO, "greetingService.getStaffByPosition, AsyncCallback succeded.");

			}
		});
	}
}
