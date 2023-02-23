package services.requests;

import enums.BooksFilter;

public class GetBooksRequest extends RequestBase {
    private BooksFilter filter;
    private boolean updateQuantity;

    public GetBooksRequest(BooksFilter filter, boolean updateQuantity)
    {
        this.filter = filter;
        this.updateQuantity = updateQuantity;
    }

    public BooksFilter getFilter(){return filter;}

    public boolean getUpdateQuantity(){return this.updateQuantity;}
}
