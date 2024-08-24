public interface IAlertService {

    int SEVERITY_NORMAL = 0;
    int SEVERITY_WARN = 1;
    int SEVERITY_ERROR = 2;

    void raiseAlert(int severity, String message);

}
