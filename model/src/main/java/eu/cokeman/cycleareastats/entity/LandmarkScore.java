package eu.cokeman.cycleareastats.entity;

import eu.cokeman.cycleareastats.valueObject.LandmarkScoreId;

import java.time.Instant;


public class LandmarkScore extends BaseEntity<LandmarkScoreId> {
    private Landmark landmark;
    private Instant scoreTime;
    private Instant lastVisit;
    private User user;
    private int visitCount;

    private LandmarkScore(Builder builder) {
        super.setId(builder.id);
        landmark = builder.landmark;
        scoreTime = builder.scoreTime;
        lastVisit = builder.lastVisit;
        user = builder.user;
        visitCount = builder.visitCount;
    }


    public static final class Builder {
        private LandmarkScoreId id;
        private Landmark landmark;
        private Instant scoreTime;
        private Instant lastVisit;
        private User user;
        private int visitCount;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(LandmarkScoreId val) {
            id = val;
            return this;
        }

        public Builder landmark(Landmark val) {
            landmark = val;
            return this;
        }

        public Builder scoreTime(Instant val) {
            scoreTime = val;
            return this;
        }

        public Builder lastVisit(Instant val) {
            lastVisit = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder visitCount(int val) {
            visitCount = val;
            return this;
        }

        public LandmarkScore build() {
            return new LandmarkScore(this);
        }
    }
}
