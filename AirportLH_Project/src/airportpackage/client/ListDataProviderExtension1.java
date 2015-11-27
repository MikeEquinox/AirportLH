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
 * ListDataProviderExtension1 shows Staff list on monitor screen
 * greetingService.getStaff(new AsyncCallback<List<Staff>>()
 */
final class ListDataProviderExtension1 extends ListDataProvider<Staff> {
	
	private static ListDataProviderExtension1 instance;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	final Logger clientLogger = Logger.getLogger("clientLogger");
	private int end;

	private ListDataProviderExtension1() {
	}

	public static ListDataProviderExtension1 getInstance() {
	
		if(instance == null)
		instance = new ListDataProviderExtension1();
		return instance;
	}
	
	@Override
	protected void onRangeChanged(HasData<Staff> display) {

		final int start;
		if(display.getVisibleRange().getStart()>=20) 
			start = display.getVisibleRange().getStart();
		else start = 1;
		end = start + display.getVisibleRange().getLength();
		
		greetingService.getStaff(new AsyncCallback<List<Staff>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				clientLogger.log(Level.SEVERE,
						"greetingService.getStaff(), AsyncCallback failure. " + caught.getMessage());
				// Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Staff> staffList) {

				instance.setList(staffList);
				end = end >= staffList.size() ? staffList.size() : end;
				List<Staff> sub = staffList.subList(start, end);
				updateRowData(start, sub);
				clientLogger.log(Level.INFO, "ListDataProviderExtension1 shows Staff list on monitor screen");
				clientLogger.log(Level.INFO, "greetingService.getStaff(), AsyncCallback succeded.");

			}
		});
		
	}
}
