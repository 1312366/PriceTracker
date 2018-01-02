
package cron;
import dao.PriceTrackerDAO;
import java.util.Calendar;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 *
 * @author Administrator
 */
public class ExecuteSchedulerInsertData implements Job{
     //<editor-fold defaultstate="collapsed" desc="EXECUTE">
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
           PriceTrackerDAO priceTrackerDAO =new PriceTrackerDAO();
           priceTrackerDAO.insertDataToDB();
        } catch (Exception e) {
        }
    }
    //</editor-fold>
}
