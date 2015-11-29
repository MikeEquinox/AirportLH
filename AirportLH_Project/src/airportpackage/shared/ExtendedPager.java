package airportpackage.shared;

import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.Range;

/**
 * A custom Pager that maintains a set page size and displays page numbers and
 * total pages more elegantly. SimplePager will ensure <code>pageSize</ 
code> 
 * rows are always rendered even if the "last" page has less than 
 * <code>pageSize</code> rows remain.
 */
public class ExtendedPager extends SimplePager {

	// Page size is normally derieved from the visibleRange
	private int pageSize = 10;

	@Override
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		super.setPageSize(pageSize);
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	// Page forward by an exact size rather than the number of visible
	// rows as is in the norm in the underlying implementation
	@Override
	public void nextPage() {
		if (getDisplay() != null) {
			Range range = getDisplay().getVisibleRange();
			setPageStart(range.getStart() + getPageSize());
		}
	}

	// Page back by an exact size rather than the number of visible
	//rows as is in the norm in the underlying implementation
	@Override 
	    public void previousPage() { 
	        if ( getDisplay() != null ) { 
	            Range range = getDisplay().getVisibleRange(); 
	            setPageStart( range.getStart() - getPageSize() ); 
	        } 
	    }
	
	// Override so the last page is shown with a number of rows less 
    // than the pageSize rather than always showing the pageSize number 
    // of rows 
    @Override 
    public void setPageStart(int index) { 
        if ( getDisplay() != null ) { 
            Range range = getDisplay().getVisibleRange(); 
            int displayPageSize = getPageSize(); 
            if ( isRangeLimited() 
                 && getDisplay().isRowCountExact() ) { 
                displayPageSize = Math.min( getPageSize(), 
                                            getDisplay().getRowCount() 
                                                    - index ); 
            } 
            index = Math.max( 0, 
                              index ); 
            if ( index != range.getStart() ) { 
                getDisplay().setVisibleRange( index, 
                                              displayPageSize ); 
            } 
        } 
    } 
}