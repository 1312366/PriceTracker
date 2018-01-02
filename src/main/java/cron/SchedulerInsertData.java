
package cron;
import cron.ExecuteSchedulerInsertData;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Minh Tran
 */
public class SchedulerInsertData  implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        try {
            JobDetail job = JobBuilder.newJob(ExecuteSchedulerInsertData.class)
                    .withIdentity("job_insert-table", "group_insert-table").build();
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("job_insert-table", "group_insert-table")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
                    .build();
            //schedule it
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException e) {
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
