package statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruce.zhang
 */
public class AopStatistics {

    private static List<IAopStatistics> aopStatisticsList = new ArrayList<>(1);

    private AopStatistics(Builder builder) {
        this.aopStatisticsList = builder.aopStatisticsList;
    }

    public static void reportEvent(String bussinessType, String eventId, String repoParam) {
        for (IAopStatistics iAopStatistics : aopStatisticsList) {
            iAopStatistics.reportEvent(bussinessType, eventId, repoParam);
        }
    }

    public static void reportPageOnResume(String bussinessType, String pageId, String eventId, String repoParam) {
        for (IAopStatistics iAopStatistics : aopStatisticsList) {
            iAopStatistics.reportPageOnResume(bussinessType, pageId, eventId, repoParam);
        }
    }

    public static void reportPageOnPause(String bussinessType, String pageId, String eventId, String repoParam) {
        for (IAopStatistics iAopStatistics : aopStatisticsList) {
            iAopStatistics.reportPageOnPause(bussinessType, pageId, eventId, repoParam);
        }
    }

    public static class Builder {
        private List<IAopStatistics> aopStatisticsList;

        public Builder() {
            this.aopStatisticsList = new ArrayList<>();
        }

        public Builder addAopStatistics(IAopStatistics filter) {
            if (aopStatisticsList == null)
                throw new IllegalStateException("aopStatisticsList can not be null");

            this.aopStatisticsList.add(filter);
            return this;
        }

        public AopStatistics build() {
            return new AopStatistics(this);
        }
    }

}
