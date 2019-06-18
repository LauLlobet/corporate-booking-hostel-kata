package actions;

import javax.naming.OperationNotSupportedException;

public class





BookingPolicyService {

    public BookingPolicyService(CompanyService companyService) {
        throw new UnsupportedOperationException();
    }

    public Object isBookingAllowed(Long employeeId, String roomType) {
        throw new UnsupportedOperationException();
    }

    public void setCompanyPolicy(long companyId, String roomType, boolean allowedToBook) {
        throw new UnsupportedOperationException();
    }

    public void setEmployeePolicy(long employeeId, String roomType, boolean allowedToBook) {
        throw new UnsupportedOperationException();
    }
}
